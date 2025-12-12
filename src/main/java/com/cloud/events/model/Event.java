package com.cloud.events.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

@DynamoDbBean
public class Event {

    // Fields - the data our event will store
    private String id;          // Unique identifier (like a barcode)
    private String title;       // Event name (required)
    private String date;        // When it happens (required)
    private String location;    // Where it happens (optional)
    private String description; // Extra details (optional)

    // Default constructor - DynamoDB needs this
    public Event() {
        //DynamoDB uses this to create objects
    }

    // Constructor with required fields only
    public Event(String title, String date) {
        this.title = title;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    // Constructor with all fields
    public Event(String title, String date, String location, String description) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    // Primary Key - tells DynamoDB how to find this event
    @DynamoDbPartitionKey
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Title getter/setter
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Date getter/setter
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Location getter/setter (optional field)
    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Description getter/setter (optional field)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}