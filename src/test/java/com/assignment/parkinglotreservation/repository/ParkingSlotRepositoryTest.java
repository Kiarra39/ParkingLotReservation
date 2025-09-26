package com.assignment.parkinglotreservation.repository;

import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ParkingSlotRepositoryTest {
    
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    
    @Autowired
    private FloorRepository floorRepository;
    
    @Test
    void testSaveParkingSlot() {
        Floor floor = new Floor();
        floor.setFloorNumber(1);
        Floor savedFloor = floorRepository.save(floor);
        
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber("A1");
        slot.setVehicleType(VehicleType.FOUR_WHEELER);
        slot.setFloor(savedFloor);
        
        ParkingSlot saved = parkingSlotRepository.save(slot);
        assertNotNull(saved.getId());
        assertEquals("A1", saved.getSlotNumber());
        assertEquals(VehicleType.FOUR_WHEELER, saved.getVehicleType());
    }
    
    @Test
    void testFindByFloorId() {
        Floor floor = new Floor();
        floor.setFloorNumber(1);
        Floor savedFloor = floorRepository.save(floor);
        
        ParkingSlot slot1 = new ParkingSlot();
        slot1.setSlotNumber("A1");
        slot1.setVehicleType(VehicleType.FOUR_WHEELER);
        slot1.setFloor(savedFloor);
        parkingSlotRepository.save(slot1);
        
        ParkingSlot slot2 = new ParkingSlot();
        slot2.setSlotNumber("A2");
        slot2.setVehicleType(VehicleType.TWO_WHEELER);
        slot2.setFloor(savedFloor);
        parkingSlotRepository.save(slot2);
        
        List<ParkingSlot> slots = parkingSlotRepository.findByFloorId(savedFloor.getId());
        assertEquals(2, slots.size());
    }
}