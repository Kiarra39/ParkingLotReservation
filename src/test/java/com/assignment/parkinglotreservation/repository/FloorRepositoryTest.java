package com.assignment.parkinglotreservation.repository;

import com.assignment.parkinglotreservation.entity.Floor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FloorRepositoryTest {
    
    @Autowired
    private FloorRepository floorRepository;
    
    @Test
    void testSaveFloor() {
        Floor floor = new Floor();
        floor.setFloorNumber(1);
        
        Floor saved = floorRepository.save(floor);
        assertNotNull(saved.getId());
        assertEquals(1, saved.getFloorNumber());
    }
    
    @Test
    void testFindById() {
        Floor floor = new Floor();
        floor.setFloorNumber(2);
        Floor saved = floorRepository.save(floor);
        
        Optional<Floor> found = floorRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(2, found.get().getFloorNumber());
    }
    
    @Test
    void testFindByFloorNumber() {
        Floor floor = new Floor();
        floor.setFloorNumber(3);
        floorRepository.save(floor);
        
        Optional<Floor> found = floorRepository.findByFloorNumber(3);
        assertTrue(found.isPresent());
        assertEquals(3, found.get().getFloorNumber());
    }
}