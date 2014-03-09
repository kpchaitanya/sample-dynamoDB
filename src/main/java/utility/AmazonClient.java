package utility;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.io.IOException;

public class AmazonClient {

    private static AmazonDynamoDBClient client;

    static {
        AWSCredentials credentials = null;
        try {
            credentials = new PropertiesCredentials(
                    ClassLoader.getSystemResourceAsStream("AwsCredentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new AmazonDynamoDBClient(credentials);
    }

    public static AmazonDynamoDBClient getClient() {
        return client;
    }
}
