package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.composites.MapPoint;
import org.kenuki.smartparking.models.enities.ParkingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPointRepository extends JpaRepository<ParkingPoint, MapPoint> {

}
