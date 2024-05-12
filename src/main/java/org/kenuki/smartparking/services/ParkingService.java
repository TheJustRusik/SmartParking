package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.FindParkingDTO;
import org.kenuki.smartparking.repositories.ParkingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;
    public ResponseEntity<?> findParking(FindParkingDTO findParkingDTO) {
        return null;
    }
}
