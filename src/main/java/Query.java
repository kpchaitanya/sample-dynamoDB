import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import model.ScoringData;
import utility.AmazonClient;

public class Query extends Item {

        private static DynamoDBMapper mapper = new DynamoDBMapper(AmazonClient.getClient());
        private static DynamoDBScanExpression query = new DynamoDBScanExpression();

        public Query(){}
	
	public void findItemsWithGreaterValue(String attr, float value){
		Condition condition = new Condition()
					.withComparisonOperator(ComparisonOperator.GT)
					.withAttributeValueList(new AttributeValue().withN(String.valueOf(value)));
		query.addFilterCondition(attr, condition);
		List<ScoringData> result = mapper.scan(ScoringData.class, query);
		for(ScoringData item: result){
			display(item.getINN());
		}
	}
	
	public void countAllRows(Class<?> clazz){
		Condition condition = new Condition()
					.withComparisonOperator(ComparisonOperator.GT)
					.withAttributeValueList(new AttributeValue().withN("1"));
		query.addFilterCondition("INN", condition);
		int result = mapper.count(clazz, query);
		System.out.println("Number of rows: "+result);
	}
	
	
	
}
