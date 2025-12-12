package com.cloud.events.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import com.cloud.events.model.Event;
import com.cloud.events.config.DynamoDBClientFactory;
import java.util.List;
import java.util.ArrayList;

public class EventRepository {

    // Get reference to our Event table
    private final DynamoDbTable<Event> eventTable;

    // Constructor - sets up the table connection
    public EventRepository() {//Get the event table from DynamoDBClientFactory.getEventTable()
        eventTable = DynamoDBClientFactory.getEventTable();
    }

    // Save a new event to the database
    public Event save(Event event) {
        try {
            eventTable.putItem(event);
            return event;
        } catch (Exception e) {
            //Throw a RuntimeException with a helpful message
            throw new RuntimeException("Error saving event: " + e.getMessage(), e);


        }
    }

    // Find all events in the database
    public List<Event> findAll() {
        try {
            // Create empty list to store results
            List<Event> events = new ArrayList<>();

            // Use eventTable.scan() to get all items
            // Loop through each page of results
            for (var page : eventTable.scan()) {
                // Loop through items in each page
                for (Event event : page.items()) {
                    events.add(event);
                }
            }
            return events;

        } catch (Exception e) {
            //Throw a RuntimeException with a helpful message
            throw new RuntimeException("Error finding events: " + e.getMessage(), e);
        }
    }

    // Find a specific event by its ID
    public Event findById(String id) {
        try {
            //Create a Key object using Key.builder().partitionValue(id).build()
            Key key = Key.builder().partitionValue(id).build();
            //Use eventTable.getItem() with the key to find the event
            Event event = eventTable.getItem(key);
            //Return the found event (might be null if not found)
            return event;
        } catch (Exception e) {
            //Throw a RuntimeException with a helpful message
            throw new RuntimeException("Error finding event by ID: " + e.getMessage(), e);

        }
    }
}