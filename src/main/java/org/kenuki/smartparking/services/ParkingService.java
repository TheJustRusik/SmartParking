package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.CreateParkingDTO;
import org.kenuki.smartparking.models.dtos.FindParkingDTO;
import org.kenuki.smartparking.models.enities.Parking;
import org.kenuki.smartparking.models.enities.ParkingPoint;
import org.kenuki.smartparking.repositories.ParkingPointRepository;
import org.kenuki.smartparking.repositories.ParkingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final ParkingPointRepository parkingPointRepository;
    public ResponseEntity<?> findParking(FindParkingDTO findParkingDTO) {
        return null;
    }

    public ResponseEntity<?> createParking(CreateParkingDTO createParkingDTO) {
        int pointsNum = createParkingDTO.getPoints().size();
        if(pointsNum < 4 || pointsNum > 6)
            return ResponseEntity.badRequest().body("Amount of points should be between 4 and 6 (inclusive)");
        if(parkingRepository.existsByName(createParkingDTO.getName()))
            return ResponseEntity.badRequest().body("Parking name is taken!");
        if (createParkingDTO.getPoints().stream().anyMatch(parkingPointRepository::existsById)) {
            return ResponseEntity.badRequest().body("Some provided point is already in use!");
        }

        Parking parking = new Parking();
        parking.setName(createParkingDTO.getName());
        parking.setMaxPlaces(createParkingDTO.getMax_places());
        parking.setFreePlaces(createParkingDTO.getMax_places());
        parking.setRentPerHour(createParkingDTO.getRent_per_hour());
        AtomicReference<Double> sumLatitude = new AtomicReference<>((double) 0);
        AtomicReference<Double> sumLongitude = new AtomicReference<>((double) 0);
        createParkingDTO.getPoints().forEach(mapPoint -> {
            sumLatitude.updateAndGet(v -> (v + mapPoint.getLatitude()));
            sumLongitude.updateAndGet(v -> (v + mapPoint.getLongitude()));
        });
        double avgLatitude = sumLatitude.get() / pointsNum;
        double avgLongitude = sumLongitude.get() / pointsNum;
        parking.setLatitude(avgLatitude);
        parking.setLongitude(avgLongitude);

        parkingRepository.save(parking);
        createParkingDTO.getPoints().forEach(dto -> {
            ParkingPoint parkingPoint = new ParkingPoint();
            parkingPoint.setParking(parking);
            parkingPoint.setLongitude(dto.getLongitude());
            parkingPoint.setLatitude(dto.getLatitude());
            parkingPointRepository.save(parkingPoint);
        });

        return ResponseEntity.ok("Saved parking");
    }
}
