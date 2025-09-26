package com.assignment.parkinglotreservation.dto;

import com.assignment.parkinglotreservation.entity.VehicleType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlotRequestTest {
    
    @Test
    void testGettersAndSetters() {
        SlotRequest request = new SlotRequest();
        
        request.setFloorId(1L);
        request.setSlotNumber("A1");
        request.setVehicleType(VehicleType.TWO_WHEELER);
        
        assertEquals(1L, request.getFloorId());
        assertEquals("A1", request.getSlotNumber());
        assertEquals(VehicleType.TWO_WHEELER, request.getVehicleType());
    }
}
