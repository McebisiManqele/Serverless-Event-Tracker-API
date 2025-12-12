package com.cloud.events.config;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import com.cloud.events.model.Event;

public class DynamoDBClientFactory {

    // Static variables - shared across all instances
    private static DynamoDbClient dynamoDbClient;           // Basic connection
    private static DynamoDbEnhancedClient enhancedClient;   // Smart connection
    private static DynamoDbTable<Event> eventTable;         // Our specific table

    // Table name - matches what's in template.yaml
    private static final String TABLE_NAME = "EventTable";

    // Private constructor - prevents creating instances
    private DynamoDBClientFactory() {
        //Left empty - this is a utility class
    }

    // Get the basic DynamoDB client
    public static DynamoDbClient getDynamoDbClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.create();
        }
        return dynamoDbClient;
    }

    // Get the enhanced DynamoDB client
    public static DynamoDbEnhancedClient getEnhancedClient() {
        if (enhancedClient == null) {
            //Create enhanced client using DynamoDbEnhancedClient.builder()
            enhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(getDynamoDbClient())
                    .build();

        }
        return enhancedClient;
    }

    // Get our Event table reference
    public static DynamoDbTable<Event> getEventTable() {
        if (eventTable == null) {
            // TODO: Get table from enhanced client using getEnhancedClient().table()
            eventTable = getEnhancedClient().table(TABLE_NAME, TableSchema.fromBean(Event.class));


        }
        //Return the table
        return eventTable;
    }

    // Clean up resources (good practice)
    public static void close() {
        if (dynamoDbClient != null) {
            dynamoDbClient.close();
            dynamoDbClient = null;
        }
    }

}