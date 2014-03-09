import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import model.ScoringData;
import utility.AmazonClient;
import utility.RandomGeneration;

public class Item extends RandomGeneration {

	private static DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
	private static DynamoDBMapper mapper = new DynamoDBMapper(AmazonClient.getClient());

    public Item(){ }
	
	public void insert(Long INN){
		ScoringData item = new ScoringData();
		item.setINN(INN);
		item.setKP1(randomFloat(3));
		item.setKP2(randomFloat(3));
		item.setKP3(randomFloat(3));
		item.setKP4(randomFloat(3));
		item.setKP5(randomFloat(3));
		item.setKP6(randomFloat(3));
		item.setKP7(randomFloat(3));
        mapper.save(item);
	}
	
	public void delete(Long INN){
		ScoringData item = mapper.load(ScoringData.class, INN, config);
		if(item != null){
			mapper.delete(item);
			System.out.println("Item with INN "+INN+" was deleted.");
		}
	}	
	
	public void display(Long INN){
		ScoringData item = mapper.load(ScoringData.class, INN, config);
		if(item != null){			
			System.out.println(item);
		}
		else{
			System.out.println("Item not found");
		}
	}
	
}
