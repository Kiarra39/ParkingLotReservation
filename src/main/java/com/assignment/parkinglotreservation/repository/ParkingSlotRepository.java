package com.assignment.parkinglotreservation.repository;


import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByFloorId(Long floorId);
    Optional<ParkingSlot> findBySlotNumberAndFloorId(String slotNumber, Long floorId);
    
    @Query("SELECT ps FROM ParkingSlot ps WHERE ps.vehicleType = :vehicleType AND ps.id NOT IN " +
           "(SELECT r.parkingSlot.id FROM Reservation r WHERE " +
           "(:startTime < r.endTime AND :endTime > r.startTime))")
    List<ParkingSlot> findAvailableSlots(@Param("vehicleType") VehicleType vehicleType,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);
    
    boolean existsBySlotNumberAndFloorId(String slotNumber, Long floorId);
}