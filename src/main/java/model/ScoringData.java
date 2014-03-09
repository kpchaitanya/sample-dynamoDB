package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="scoringmethod")
public class ScoringData {
	private Long INN;
    private float kp1;
    private float kp2;
    private float kp3;
    private float kp4;
    private float kp5;
    private float kp6;
    private float kp7;
    
    @DynamoDBHashKey(attributeName="INN")  
    public  Long getINN() { return INN;}
    public void setINN(Long INN) {this.INN = INN;}
    
    @DynamoDBAttribute(attributeName="kp1")  
    public float getKP1() {return kp1; }
    public void setKP1(float kp1) { this.kp1 = kp1; }
    
    @DynamoDBAttribute(attributeName="kp2")  
    public float getKP2() {return kp2; }
    public void setKP2(float kp2) { this.kp2 = kp2; }
    
    @DynamoDBAttribute(attributeName="kp3")  
    public float getKP3() {return kp3; }
    public void setKP3(float kp3) { this.kp3 = kp3; }
    
    @DynamoDBAttribute(attributeName="kp4")  
    public float getKP4() {return kp4; }
    public void setKP4(float kp4) { this.kp4 = kp4; }
    
    @DynamoDBAttribute(attributeName="kp5")  
    public float getKP5() {return kp5; }
    public void setKP5(float kp5) { this.kp5 = kp5; }
    
    @DynamoDBAttribute(attributeName="kp6")  
    public float getKP6() {return kp6; }
    public void setKP6(float kp6) { this.kp6 = kp6; }
    
    @DynamoDBAttribute(attributeName="kp7")  
    public float getKP7() {return kp7; }
    public void setKP7(float kp7) { this.kp7 = kp7; }

    @Override
    public String toString() {
        return "Item "+INN
                +" [ kp1 = "+kp1+", "
                +"kp2 = "+kp2+", "
                +"kp3 = "+kp3+", "
                +"kp4 = "+kp4+", "
                +"kp5 = "+kp5+", "
                +"kp6 = "+kp6+", "
                +"kp7 = "+kp7+" ]";
    }
}
