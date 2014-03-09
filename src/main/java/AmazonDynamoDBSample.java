import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import model.ScoringData;
import model.ScoringResults;
import utility.AmazonClient;
import utility.RandomGeneration;

public class AmazonDynamoDBSample {

	private static AmazonDynamoDBClient client = AmazonClient.getClient();
    
    public static void main(String[] args) throws Exception {
        RandomGeneration rnd = new RandomGeneration();
        long inn1 = rnd.randomNumberNotNull(12);
        long inn2 = rnd.randomNumberNotNull(12);

        Item item = new Item();
        item.insert(inn1);
        item.insert(inn2);
        item.display(inn2);
        item.delete(inn2);
        item.display(inn2);
        item.insert(rnd.randomNumberNotNull(12));
        item.insert(rnd.randomNumberNotNull(12));

        Query query = new Query();
        query.findItemsWithGreaterValue("kp1", 0.5F);
        query.countAllRows(ScoringData.class);

        // make some calculations and put result into another table
        try {
        	makeScoring(inn1);
        } catch (AmazonServiceException ase) {
            System.err.println("Operation failed. "+ase);
        } 
    }
    	
    private static void makeScoring(Long ID){
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
			System.out.println("Result put in table [scoringresults] \n"
							  +"Rating = "+result);	
    	}
    	catch (AmazonServiceException ase) {
    		System.err.println("Failed to make operation. "+ase );
    	}  
  } 
}