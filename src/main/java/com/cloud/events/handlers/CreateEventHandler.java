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

public class CreateEventHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    // Tools we need
    private final ObjectMapper objectMapper;     // Converts JSON ↔ Java objects
    private final EventRepository repository;    // Saves events to database

    // Constructor - set up our tools
    public CreateEventHandler() {
        //Create new ObjectMapper()
        this.objectMapper = new ObjectMapper();
        //Create new EventRepository()
        this.repository = new EventRepository();
    }

    // Main method - AWS calls this when someone hits POST /events
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        try {
            // Step 1: Get the JSON from the request body
            String requestBody = request.getBody();

            //Check if body is null or empty, return 400 error if so
            if (requestBody == null || requestBody.trim().isEmpty()) {
                return createResponse(400, "Request body is missing or empty");
            }

            //Convert JSON to Event object Use objectMapper.readValue(requestBody, Event.class)
            Event event = objectMapper.readValue(requestBody, Event.class);
            
            //Generate ID if not present (Jackson doesn't call our UUID constructor)
            if (event.getId() == null) {
                event.setId(java.util.UUID.randomUUID().toString());
            }

            //Validate the event (check required fields)
            //Check if title and date are not null/empty, return 400 error if missing
            if (event.getTitle() == null || event.getTitle().trim().isEmpty() ||
                    event.getDate() == null || event.getDate().trim().isEmpty()) {
                return createResponse(400, "Title and date are required fields");
            }

            //Save the event to database
            //Use repository.save(event) to save the event
            Event savedEvent = repository.save(event);

            //Convert saved event back to JSON
            //Use objectMapper.writeValueAsString(savedEvent)
            String responseBody = objectMapper.writeValueAsString(savedEvent);


            // Return success response (200)
            // Create APIGatewayProxyResponseEvent with status 200 and JSON body
            return createResponse(200, responseBody);

        } catch (Exception e) {
            // Something went wrong - return error response (500)
            //Log the error and return 500 status with error message
            return createResponse(500, "Internal Server Error");
        }
    }

    // Helper method to create HTTP responses
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
