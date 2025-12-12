package com.cloud.events.handlers;

// Import statements you'll need
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cloud.events.model.Event;
import com.cloud.events.repository.EventRepository;
import java.util.HashMap;
import java.util.Map;

public class GetEventByIdHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    // Tools we need (same pattern as before)
    private final ObjectMapper objectMapper;     // Converts Java objects ↔ JSON
    private final EventRepository repository;    // Gets events from database

    // Constructor - set up our tools
    public GetEventByIdHandler() {
        //Create new ObjectMapper()
        this.objectMapper = new ObjectMapper();
        //Create new EventRepository()
        this.repository = new EventRepository();
    }

    // Main method - AWS calls this when someone hits GET /events/{id}
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        try {
            //Get the ID from the URL path
            // Use request.getPathParameters().get("id") to get the ID
            String id = request.getPathParameters().get("id");

            //Validate the ID
            //Check if ID is null or empty, return 400 error if so
            if (id == null || id.trim().isEmpty()) {
                return createResponse(400, "Request id is missing or empty");
            }

            // Find the event by ID
            // Use repository.findById(id) to search for the event
            Event event = repository.findById(id);

            // Check if event was found
            // If event is null, return 404 error with "Event not found" message
            if (event == null) {
                return createResponse(404, "Event not found");
            }

            //Convert event to JSON and return
            //Use objectMapper.writeValueAsString(event) to convert to JSON
            String responseId = objectMapper.writeValueAsString(event);
            //Return createResponse(200, jsonResponse)
            return createResponse(200, responseId);

        } catch (Exception e) {
            // Something went wrong - return error response (500)
            return createResponse(500, "Internal Server Error");
        }
    }

    // Helper method to create HTTP responses (same as before)
    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        //Set the status code
        response.setStatusCode(statusCode);
        //Set the body
        response.setBody(body);

        // Set headers (tells browser this is JSON)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        //Set the headers on the response
        response.setHeaders(headers);

        //Return the response
        return response;
    }
}


