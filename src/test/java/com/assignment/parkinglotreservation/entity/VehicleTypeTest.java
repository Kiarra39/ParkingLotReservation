package com.assignment.parkinglotreservation.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeTest {
    
    @Test
    void testHourlyRates() {
        assertEquals(20.0, VehicleType.TWO_WHEELER.getHourlyRate());
        assertEquals(30.0, VehicleType.FOUR_WHEELER.getHourlyRate());
    }
    
    @Test
    void testEnumValues() {
        VehicleType[] values = VehicleType.values();
        assertEquals(2, values.length);
        assertArrayEquals(new VehicleType[]{VehicleType.TWO_WHEELER, VehicleType.FOUR_WHEELER}, values);
    }
    
    @Test
    void testValueOf() {
        assertEquals(VehicleType.TWO_WHEELER, VehicleType.valueOf("TWO_WHEELER"));
        assertEquals(VehicleType.FOUR_WHEELER, VehicleType.valueOf("FOUR_WHEELER"));
    }
}
