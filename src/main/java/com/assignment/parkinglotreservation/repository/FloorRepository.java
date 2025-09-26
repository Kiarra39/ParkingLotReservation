package com.assignment.parkinglotreservation.repository;



import com.assignment.parkinglotreservation.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    Optional<Floor> findByFloorNumber(Integer floorNumber);
    boolean existsByFloorNumber(Integer floorNumber);
}