package com.assignment.parkinglotreservation.repository;


import com.assignment.parkinglotreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r " +
           "WHERE r.parkingSlot.id = :slotId AND " +
           "(:startTime < r.endTime AND :endTime > r.startTime)")
    boolean existsOverlappingReservation(@Param("slotId") Long slotId,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);
    
    List<Reservation> findByVehicleNumber(String vehicleNumber);
    
    @Query("SELECT r FROM Reservation r WHERE r.parkingSlot.id = :slotId AND " +
           "(:startTime < r.endTime AND :endTime > r.startTime)")
    List<Reservation> findOverlappingReservations(@Param("slotId") Long slotId,
                                                 @Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime);
}