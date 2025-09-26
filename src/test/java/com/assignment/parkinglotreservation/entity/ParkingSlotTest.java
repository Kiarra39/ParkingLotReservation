package com.assignment.parkinglotreservation.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {
    
    @Test
    void testConstructorAndGetters() {
        Floor floor = new Floor(1);
        ParkingSlot slot = new ParkingSlot("A1", VehicleType.FOUR_WHEELER, floor);
        slot.setId(1L);
        
        assertEquals(1L, slot.getId());
        assertEquals("A1", slot.getSlotNumber());
        assertEquals(VehicleType.FOUR_WHEELER, slot.getVehicleType());
        assertEquals(floor, slot.getFloor());
        assertNotNull(slot.getReservations());
    }
    
    @Test
    void testDefaultConstructor() {
        ParkingSlot slot = new ParkingSlot();
        
        assertNull(slot.getId());
        assertNull(slot.getSlotNumber());
        assertNull(slot.getVehicleType());
        assertNull(slot.getFloor());
        assertNotNull(slot.getReservations());
    }
}