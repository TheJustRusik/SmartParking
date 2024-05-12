package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.enities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
