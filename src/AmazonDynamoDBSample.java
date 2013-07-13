import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class AmazonDynamoDBSample {

	public static AmazonDynamoDBClient client;
    static String TableName = "scoringmethod";
    static String TableForSaveScoringResult = "scoringresults";
    
    public static void main(String[] args) throws Exception {    	
        createClient();   
        //try add some values to table        
        long inn1 = RandomGeneration.randomNumberNotNULL(12);
        long inn2 = RandomGeneration.randomNumberNotNULL(12);
        PutAndDeleteItem.insert(inn1);
        PutAndDeleteItem.insert(inn2);
        PutAndDeleteItem.display(inn2);        	
        //delete second item
        PutAndDeleteItem.delete(inn2);
        PutAndDeleteItem.display(inn2);
        //try query
        PutAndDeleteItem.insert(RandomGeneration.randomNumberNotNULL(12));
        PutAndDeleteItem.insert(RandomGeneration.randomNumberNotNULL(12));        
        Query.findItemsWithGreaterValue("kp1", 0.5f);
        Query.countAllRows(ScoringData.class);
        // make some calculations and put result into another table
        try {
        	makeScoring(TableName, inn1);
        } catch (AmazonServiceException ase) {
            System.err.println("Operation failed. "+ase);
        } 
    }
    
    private static void createClient() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(
                AmazonDynamoDBSample.class.getResourceAsStream("AwsCredentials.properties"));
        client = new AmazonDynamoDBClient(credentials);
    }
    	
    private static void makeScoring(String table, Long ID){
    	DynamoDBMapper mapper = new DynamoDBMapper(client);
    	ScoringData data = mapper.load(ScoringData.class, ID);    	
    	try{
    		float result = (  			
    			((float)0.75*data.getKP1()) +
    			((float)1.25*data.getKP2()) +
    			((float)0.50*data.getKP3()) +
    			((float)0.25*data.getKP4()) +
    			((float)0.63*data.getKP5()) +
    			((float)1.50*data.getKP6()) +
    			((float)0.75*data.getKP7())	);
		
    		ScoringResults sr = new ScoringResults();
    		sr.setINN(ID);
    		sr.setSR(result);
			mapper.save(sr, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));			
		
			System.out.println("Result put in table "+TableForSaveScoringResult+"\n"
							  +"Rating = "+result);	
    	}
    	catch (AmazonServiceException ase) {
    		System.err.println("Failed to make operation. "+ase );
    	}  
  } 
}