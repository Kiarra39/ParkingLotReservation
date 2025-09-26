package com.assignment.parkinglotreservation.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {
    
    @Test
    void testSuccessResponse() {
        ApiResponse response = ApiResponse.success("Test message", "Test data");
        
        assertTrue(response.isSuccess());
        assertEquals("Test message", response.getMessage());
        assertEquals("Test data", response.getData());
    }
    
    @Test
    void testErrorResponse() {
        ApiResponse response = ApiResponse.error("Error message");
        
        assertFalse(response.isSuccess());
        assertEquals("Error message", response.getMessage());
        assertNull(response.getData());
    }
    
    @Test
    void testSetters() {
        ApiResponse response = new ApiResponse(true, "Message", "Data");
        
        response.setSuccess(false);
        response.setMessage("New message");
        response.setData("New data");
        
        assertFalse(response.isSuccess());
        assertEquals("New message", response.getMessage());
        assertEquals("New data", response.getData());
    }
}
