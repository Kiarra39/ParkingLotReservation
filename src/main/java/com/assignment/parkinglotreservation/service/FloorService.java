package com.assignment.parkinglotreservation.service;

import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    // Corrected: use floor.getFloorNumber() to check existence
    public Floor createFloor(Integer floorNumber) {
    if (floorRepository.existsByFloorNumber(floorNumber)) {
        throw new IllegalArgumentException("Floor with number " + floorNumber + " already exists");
    }
    Floor floor = new Floor(floorNumber);
    return floorRepository.save(floor);
}

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public Optional<Floor> getFloorById(Long id) {
        return floorRepository.findById(id);
    }

    public Optional<Floor> getFloorByNumber(Integer floorNumber) {
        return floorRepository.findByFloorNumber(floorNumber);
    }

    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }
}
