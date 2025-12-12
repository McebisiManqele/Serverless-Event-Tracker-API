package com.cloud.events.handlers;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cloud.events.model.Event;
import com.cloud.events.repository.EventRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ListEventsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    // Tools we need (same as CreateEventHandler)
    private final ObjectMapper objectMapper;     // Converts Java objects ↔ JSON
    private final EventRepository repository;    // Gets events from database

    // Constructor - set up our tools
    public ListEventsHandler() {
        //Create new ObjectMapper()
        this.objectMapper = new ObjectMapper();
        //Create new EventRepository()
        this.repository = new EventRepository();
    }

    // Main method - AWS calls this when someone hits GET /events
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        try {
            // Step 1: Get all events from database
            //Use repository.findAll() to get list of events
            List<Event> events = repository.findAll();

            // Use objectMapper.writeValueAsString(events) to convert list to JSON
            String jsonResponse = objectMapper.writeValueAsString(events);

            //Return success response with JSON array
            return createResponse(200, jsonResponse);

        } catch (Exception e) {
            // Something went wrong - return error response (500)
            return createResponse(500, "Internal Server Error");
        }
    }

    // Helper method to create HTTP responses (same as CreateEventHandler)
    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        //Set the status code
        response.setStatusCode(statusCode);
        //Set the body
        response.setBody(body);

        // et headers (tells browser this is JSON)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        //Set the headers on the response
        response.setHeaders(headers);

        //Return the response
        return response;
    }
}