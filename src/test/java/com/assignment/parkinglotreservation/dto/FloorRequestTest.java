package com.assignment.parkinglotreservation.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloorRequestTest {
    
    @Test
    void testGettersAndSetters() {
        FloorRequest request = new FloorRequest();
        request.setFloorNumber(5);
        
        assertEquals(5, request.getFloorNumber());
    }
    
    @Test
    void testDefaultConstructor() {
        FloorRequest request = new FloorRequest();
        assertNull(request.getFloorNumber());
    }
}