import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class Item {

	private static AmazonDynamoDBClient client = AmazonDynamoDBSample.client;
	private static DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
	private static DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	public static void insert(Long INN){
		ScoringData item = new ScoringData();
		item.setINN(INN);
		item.setKP1(RandomGeneration.randomFloat(3));
		item.setKP2(RandomGeneration.randomFloat(3));
		item.setKP3(RandomGeneration.randomFloat(3));
		item.setKP4(RandomGeneration.randomFloat(3));
		item.setKP5(RandomGeneration.randomFloat(3));
		item.setKP6(RandomGeneration.randomFloat(3));
		item.setKP7(RandomGeneration.randomFloat(3));
        mapper.save(item);
	}
	
	public static void delete(Long INN){
		ScoringData item = mapper.load(ScoringData.class, INN, config);
		if(item != null){
			mapper.delete(item);
			System.out.println("Item with INN "+INN+" was deleted.");
		}
	}	
	
	public static void display(Long INN){
		ScoringData item = mapper.load(ScoringData.class, INN, config);
		if(item != null){			
			System.out.println(item);
		}
		else{
			System.out.println("Item not found");
		}
	}
	
}
