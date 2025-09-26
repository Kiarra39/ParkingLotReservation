package com.assignment.parkinglotreservation.service;

import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.repository.FloorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FloorServiceTest {

    @Mock
    private FloorRepository floorRepository;

    @InjectMocks
    private FloorService floorService;

    @Test
    void createFloor_Success() {
        // Given
        Floor floor = new Floor(1);
        floor.setId(1L);
        
        when(floorRepository.existsByFloorNumber(1)).thenReturn(false);
        when(floorRepository.save(any(Floor.class))).thenReturn(floor);

        // When
        Floor savedFloor = floorService.createFloor(1); // Pass Integer

        // Then
        assertNotNull(savedFloor);
        assertEquals(1, savedFloor.getFloorNumber());
        verify(floorRepository, times(1)).existsByFloorNumber(1);
        verify(floorRepository, times(1)).save(any(Floor.class));
    }

    @Test
    void createFloor_DuplicateFloor() {
        // Given
        when(floorRepository.existsByFloorNumber(1)).thenReturn(true); // Pass Integer

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> floorService.createFloor(1)); // Pass Integer
        
        assertEquals("Floor with number 1 already exists", exception.getMessage());
        verify(floorRepository, times(1)).existsByFloorNumber(1);
        verify(floorRepository, never()).save(any(Floor.class));
    }

    @Test
    void getAllFloors_Success() {
        // Given
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        List<Floor> floors = Arrays.asList(floor1, floor2);
        
        when(floorRepository.findAll()).thenReturn(floors);

        // When
        List<Floor> result = floorService.getAllFloors();

        // Then
        assertEquals(2, result.size());
        verify(floorRepository, times(1)).findAll();
    }

    @Test
    void getFloorById_Success() {
        // Given
        Floor floor = new Floor(1);
        floor.setId(1L);
        
        when(floorRepository.findById(1L)).thenReturn(Optional.of(floor));

        // When
        Optional<Floor> result = floorService.getFloorById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getFloorNumber());
        verify(floorRepository, times(1)).findById(1L);
    }

    @Test
    void getFloorById_NotFound() {
        // Given
        when(floorRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Floor> result = floorService.getFloorById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(floorRepository, times(1)).findById(1L);
    }

    @Test
    void deleteFloor_Success() {
        // Given
        doNothing().when(floorRepository).deleteById(1L);

        // When
        floorService.deleteFloor(1L);

        // Then
        verify(floorRepository, times(1)).deleteById(1L);
    }
}