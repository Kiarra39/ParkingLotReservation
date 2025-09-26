package com.assignment.parkinglotreservation.entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    
    @Test
    void testConstructorAndGetters() {
        Floor floor = new Floor(5);
        floor.setId(1L);
        
        assertEquals(1L, floor.getId());
        assertEquals(5, floor.getFloorNumber());
        assertNotNull(floor.getSlots());
    }
    
    @Test
    void testDefaultConstructor() {
        Floor floor = new Floor();
        
        assertNull(floor.getId());
        assertNull(floor.getFloorNumber());
        assertNotNull(floor.getSlots());
    }
    
    @Test
    void testSetters() {
        Floor floor = new Floor();
        floor.setId(10L);
        floor.setFloorNumber(3);
        
        assertEquals(10L, floor.getId());
        assertEquals(3, floor.getFloorNumber());
    }
}