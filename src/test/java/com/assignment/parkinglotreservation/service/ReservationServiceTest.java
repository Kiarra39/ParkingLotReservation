package com.assignment.parkinglotreservation.service;


import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.Reservation;
import com.assignment.parkinglotreservation.entity.VehicleType;
import com.assignment.parkinglotreservation.repository.ParkingSlotRepository;
import com.assignment.parkinglotreservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {
    
    @Mock
    private ReservationRepository reservationRepository;
    
    @Mock
    private ParkingSlotRepository parkingSlotRepository;
    
    @InjectMocks
    private ReservationService reservationService;
    
    private ParkingSlot testSlot;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testSlot = new ParkingSlot("A1", VehicleType.FOUR_WHEELER, null);
        testSlot.setId(1L);
    }
    
    @Test
    void testCreateReservation_Success() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusHours(2);
        
        when(parkingSlotRepository.findById(1L)).thenReturn(Optional.of(testSlot));
        when(reservationRepository.existsOverlappingReservation(any(), any(), any())).thenReturn(false);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // Act
        Reservation result = reservationService.createReservation(
            1L, "KA01AB1234", VehicleType.FOUR_WHEELER, startTime, endTime);
        
        // Assert
        assertNotNull(result);
        assertEquals("KA01AB1234", result.getVehicleNumber());
        assertEquals(60.0, result.getTotalCost()); // 2 hours * 30
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }
    
    @Test
    void testCreateReservation_OverlappingReservation() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusHours(2);
        
        when(parkingSlotRepository.findById(1L)).thenReturn(Optional.of(testSlot));
        when(reservationRepository.existsOverlappingReservation(any(), any(), any())).thenReturn(true);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            reservationService.createReservation(1L, "KA01AB1234", VehicleType.FOUR_WHEELER, startTime, endTime));
    }
}
