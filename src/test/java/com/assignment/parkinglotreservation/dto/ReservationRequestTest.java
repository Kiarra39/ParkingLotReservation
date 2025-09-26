package com.assignment.parkinglotreservation.dto;

import com.assignment.parkinglotreservation.entity.VehicleType;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRequestTest {
    
    @Test
    void testGettersAndSetters() {
        ReservationRequest request = new ReservationRequest();
        LocalDateTime now = LocalDateTime.now();
        
        request.setSlotId(1L);
        request.setVehicleNumber("KA01AB1234");
        request.setVehicleType(VehicleType.FOUR_WHEELER);
        request.setStartTime(now);
        request.setEndTime(now.plusHours(2));
        
        assertEquals(1L, request.getSlotId());
        assertEquals("KA01AB1234", request.getVehicleNumber());
        assertEquals(VehicleType.FOUR_WHEELER, request.getVehicleType());
        assertEquals(now, request.getStartTime());
        assertEquals(now.plusHours(2), request.getEndTime());
    }
    
    @Test
    void testDefaultConstructor() {
        ReservationRequest request = new ReservationRequest();
        
        assertNull(request.getSlotId());
        assertNull(request.getVehicleNumber());
        assertNull(request.getVehicleType());
        assertNull(request.getStartTime());
        assertNull(request.getEndTime());
    }
}
