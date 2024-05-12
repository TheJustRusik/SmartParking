package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.composites.MapPoint;
import org.kenuki.smartparking.models.dtos.CreateParkingDTO;
import org.kenuki.smartparking.models.dtos.FindParkingDTO;
import org.kenuki.smartparking.models.dtos.ResponseParkingDTO;
import org.kenuki.smartparking.models.enities.Parking;
import org.kenuki.smartparking.models.enities.ParkingPoint;
import org.kenuki.smartparking.repositories.ParkingPointRepository;
import org.kenuki.smartparking.repositories.ParkingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final ParkingPointRepository parkingPointRepository;
    public ResponseEntity<?> findParking(FindParkingDTO findParkingDTO) {
        List<Parking> parks = new java.util.ArrayList<>(
                parkingRepository
                        .findParkingInRadius(findParkingDTO.getLatitude(), findParkingDTO.getLongitude(), findParkingDTO.getRadius())
                        .stream().filter(park -> park.getFreePlaces() != 0)
                        .toList()
        );

        parks.sort((p1, p2) -> {
            MapPoint mapPoint1 = new MapPoint(p1);
            MapPoint mapPoint2 = new MapPoint(p2);
            MapPoint startPoint = new MapPoint(findParkingDTO.getLatitude(), findParkingDTO.getLongitude());

            double delta1 =
                    Math.abs(mapPoint1.getLatitude() - startPoint.getLatitude()) +
                    Math.abs(mapPoint1.getLongitude() - startPoint.getLongitude());

            double delta2 =
                    Math.abs(mapPoint2.getLatitude() - startPoint.getLatitude()) +
                    Math.abs(mapPoint2.getLongitude() - startPoint.getLongitude());

            return Double.compare(delta1, delta2);
        });

        List<ResponseParkingDTO> response = new ArrayList<>();
        parks.forEach(parking -> {
            ResponseParkingDTO responseParkingDTO = new ResponseParkingDTO();
            responseParkingDTO.setId(parking.getId());
            responseParkingDTO.setFree_places(parking.getFreePlaces());
            responseParkingDTO.setMax_places(parking.getMaxPlaces());
            responseParkingDTO.setRent_per_hour(parking.getRentPerHour());
            responseParkingDTO.setName(parking.getName());
            responseParkingDTO.setPoints(parking.getParkingPoints().stream().map(MapPoint::new).toList());
            response.add(responseParkingDTO);
        });

        if(findParkingDTO.getOrder_by() == FindParkingDTO.Orders.cheapest) {
            parks.sort((p1, p2) -> {
                if (p1.getRentPerHour() > p2.getRentPerHour())
                    return 1;
                else if (p2.getRentPerHour().equals(p1.getRentPerHour())) {
                    return 0;
                }
                return -1;
            });
        }

        return ResponseEntity.ok(response);
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
