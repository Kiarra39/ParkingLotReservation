package com.assignment.parkinglotreservation.service;


import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.VehicleType;
import com.assignment.parkinglotreservation.repository.FloorRepository;
import com.assignment.parkinglotreservation.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {
    
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    
    @Autowired
    private FloorRepository floorRepository;
    
    public ParkingSlot createSlot(Long floorId, String slotNumber, VehicleType vehicleType) {
        Floor floor = floorRepository.findById(floorId)
            .orElseThrow(() -> new IllegalArgumentException("Floor not found with id: " + floorId));
        
        if (parkingSlotRepository.existsBySlotNumberAndFloorId(slotNumber, floorId)) {
            throw new IllegalArgumentException("Slot with number " + slotNumber + " already exists on this floor");
        }
        
        ParkingSlot slot = new ParkingSlot(slotNumber, vehicleType, floor);
        return parkingSlotRepository.save(slot);
    }
    
    public List<ParkingSlot> getSlotsByFloor(Long floorId) {
        return parkingSlotRepository.findByFloorId(floorId);
    }
    public List<ParkingSlot> getAllSlots() {
    return parkingSlotRepository.findAll();
}

    public List<ParkingSlot> getAvailableSlots(VehicleType vehicleType,
                                              java.time.LocalDateTime startTime,
                                              java.time.LocalDateTime endTime) {
        return parkingSlotRepository.findAvailableSlots(vehicleType, startTime, endTime);
    }
    
    public Optional<ParkingSlot> getSlotById(Long id) {
        return parkingSlotRepository.findById(id);
    }
    
    public void deleteSlot(Long id) {
        parkingSlotRepository.deleteById(id);
    }
}
