import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class PutAndDeleteItem {

	private static AmazonDynamoDBClient client = AmazonDynamoDBSample.client;;
	private static DynamoDBMapperConfig config = new DynamoDBMapperConfig(
														DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
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
			System.out.println("Item "+item.getINN()
				           +" [ kp1 = "+item.getKP1()+", "
				           +"kp2 = "+item.getKP2()+", "
				           +"kp3 = "+item.getKP3()+", "
				           +"kp4 = "+item.getKP4()+", "
				           +"kp5 = "+item.getKP5()+", "
				           +"kp6 = "+item.getKP6()+", "
				           +"kp7 = "+item.getKP7()+" ]"   );
		}
		else{
			System.out.println("Item not found");
		}
	}
	
}
