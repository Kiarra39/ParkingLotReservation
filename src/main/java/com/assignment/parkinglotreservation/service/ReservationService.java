package com.assignment.parkinglotreservation.service;



import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.Reservation;
import com.assignment.parkinglotreservation.entity.VehicleType;
import com.assignment.parkinglotreservation.repository.ParkingSlotRepository;
import com.assignment.parkinglotreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    
    public Reservation createReservation(Long slotId, String vehicleNumber, 
                                        VehicleType vehicleType, LocalDateTime startTime, 
                                        LocalDateTime endTime) {
        
        // Validate time range
        validateReservationTime(startTime, endTime);
        
        // Check if slot exists
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
            .orElseThrow(() -> new IllegalArgumentException("Parking slot not found with id: " + slotId));
        
        // Check if slot is compatible with vehicle type
        if (slot.getVehicleType() != vehicleType) {
            throw new IllegalArgumentException("Vehicle type " + vehicleType + 
                                             " is not compatible with slot type " + slot.getVehicleType());
        }
        
        // Check for overlapping reservations
        if (reservationRepository.existsOverlappingReservation(slotId, startTime, endTime)) {
            throw new IllegalArgumentException("Slot is already reserved for the given time range");
        }
        
        // Calculate cost
        double totalCost = calculateCost(startTime, endTime, vehicleType);
        
        Reservation reservation = new Reservation(vehicleNumber, vehicleType, slot, startTime, endTime, totalCost);
        return reservationRepository.save(reservation);
    }
    
    private void validateReservationTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start time cannot be in the past");
        }
        
        Duration duration = Duration.between(startTime, endTime);
        if (duration.toHours() > 24) {
            throw new IllegalArgumentException("Reservation duration cannot exceed 24 hours");
        }
    }
    
    private double calculateCost(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        
        // Round up partial hours
        if (minutes > 0) {
            hours++;
        }
        
        // Minimum 1 hour charge
        if (hours == 0) {
            hours = 1;
        }
        
        return hours * vehicleType.getHourlyRate();
    }
    
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }
    
    public List<Reservation> getReservationsByVehicle(String vehicleNumber) {
        return reservationRepository.findByVehicleNumber(vehicleNumber);
    }
    
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
    
    public List<ParkingSlot> checkAvailability(VehicleType vehicleType, 
                                              LocalDateTime startTime, LocalDateTime endTime) {
        validateReservationTime(startTime, endTime);
        return parkingSlotRepository.findAvailableSlots(vehicleType, startTime, endTime);
    }
}
