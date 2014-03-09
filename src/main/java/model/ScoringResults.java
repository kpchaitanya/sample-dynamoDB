package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "scoringresults")
public class ScoringResults {
	private Long INN;
	private float scoringResult;
	
	@DynamoDBHashKey(attributeName="INN")
	public Long getINN(){return INN;}
	public void setINN(Long INN){this.INN = INN;} 
	
	@DynamoDBAttribute(attributeName="scoringResult")
	public float getSR(){return scoringResult;}
	public void setSR(float scoringResult){this.scoringResult = scoringResult;} 
}
