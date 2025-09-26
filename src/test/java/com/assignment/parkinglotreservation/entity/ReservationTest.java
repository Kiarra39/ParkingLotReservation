package com.assignment.parkinglotreservation.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    
    @Test
    void testConstructorAndGetters() {
        ParkingSlot slot = new ParkingSlot("A1", VehicleType.FOUR_WHEELER, null);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);
        
        Reservation reservation = new Reservation("KA01AB1234", VehicleType.FOUR_WHEELER, slot, start, end, 60.0);
        reservation.setId(1L);
        
        assertEquals(1L, reservation.getId());
        assertEquals("KA01AB1234", reservation.getVehicleNumber());
        assertEquals(VehicleType.FOUR_WHEELER, reservation.getVehicleType());
        assertEquals(slot, reservation.getParkingSlot());
        assertEquals(start, reservation.getStartTime());
        assertEquals(end, reservation.getEndTime());
        assertEquals(60.0, reservation.getTotalCost());
    }
    
    @Test
    void testDefaultConstructor() {
        Reservation reservation = new Reservation();
        
        assertNull(reservation.getId());
        assertNull(reservation.getVehicleNumber());
        assertNull(reservation.getVehicleType());
        assertNull(reservation.getParkingSlot());
        assertNull(reservation.getStartTime());
        assertNull(reservation.getEndTime());
        assertNull(reservation.getTotalCost());
    }
}