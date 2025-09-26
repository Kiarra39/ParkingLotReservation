package com.assignment.parkinglotreservation.service;


import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.VehicleType;
import com.assignment.parkinglotreservation.repository.FloorRepository;
import com.assignment.parkinglotreservation.repository.ParkingSlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParkingSlotServiceTest {
    
    @Mock
    private ParkingSlotRepository parkingSlotRepository;
    
    @Mock
    private FloorRepository floorRepository;
    
    @InjectMocks
    private ParkingSlotService parkingSlotService;
    
    private Floor testFloor;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testFloor = new Floor(1);
        testFloor.setId(1L);
    }
    
    @Test
    void testCreateSlot_Success() {
        // Arrange
        Long floorId = 1L;
        String slotNumber = "A1";
        VehicleType vehicleType = VehicleType.FOUR_WHEELER;
        
        when(floorRepository.findById(floorId)).thenReturn(Optional.of(testFloor));
        when(parkingSlotRepository.existsBySlotNumberAndFloorId(slotNumber, floorId)).thenReturn(false);
        when(parkingSlotRepository.save(any(ParkingSlot.class))).thenAnswer(invocation -> {
            ParkingSlot slot = invocation.getArgument(0);
            slot.setId(1L);
            return slot;
        });
        
        // Act
        ParkingSlot result = parkingSlotService.createSlot(floorId, slotNumber, vehicleType);
        
        // Assert
        assertNotNull(result);
        assertEquals(slotNumber, result.getSlotNumber());
        assertEquals(vehicleType, result.getVehicleType());
        verify(parkingSlotRepository, times(1)).save(any(ParkingSlot.class));
    }
    
    @Test
    void testCreateSlot_FloorNotFound() {
        // Arrange
        Long floorId = 1L;
        when(floorRepository.findById(floorId)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            parkingSlotService.createSlot(floorId, "A1", VehicleType.FOUR_WHEELER));
    }
    
    @Test
    void testGetSlotsByFloor() {
        // Arrange
        Long floorId = 1L;
        ParkingSlot slot1 = new ParkingSlot("A1", VehicleType.FOUR_WHEELER, testFloor);
        ParkingSlot slot2 = new ParkingSlot("A2", VehicleType.TWO_WHEELER, testFloor);
        List<ParkingSlot> slots = Arrays.asList(slot1, slot2);
        
        when(parkingSlotRepository.findByFloorId(floorId)).thenReturn(slots);
        
        // Act
        List<ParkingSlot> result = parkingSlotService.getSlotsByFloor(floorId);
        
        // Assert
        assertEquals(2, result.size());
        verify(parkingSlotRepository, times(1)).findByFloorId(floorId);
    }
}
