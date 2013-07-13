import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;

public class Query {

	private static AmazonDynamoDBClient client = AmazonDynamoDBSample.client;
	private static DynamoDBMapper mapper = new DynamoDBMapper(client);
	private static DynamoDBScanExpression query = new DynamoDBScanExpression();
	
	public static void findItemsWithGreaterValue(String attr, float value){
		Condition condition = new Condition()
									.withComparisonOperator(ComparisonOperator.GT)
									.withAttributeValueList(new AttributeValue().withN(String.valueOf(value)));
		query.addFilterCondition(attr, condition);
		List<ScoringData> result = mapper.scan(ScoringData.class, query);
		for(ScoringData item: result){
			PutAndDeleteItem.display(item.getINN());
		}
	}
	
	public static void countAllRows(Class<?> clazz){
		Condition condition = new Condition()
									.withComparisonOperator(ComparisonOperator.GT)
									.withAttributeValueList(new AttributeValue().withN("1"));;
		query.addFilterCondition("INN", condition);;
		int result = mapper.count(clazz, query);
		System.out.println("Number of rows: "+result);
	}
	
	
	
}
