package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.enities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    boolean existsByName(String name);
    @Query("""
            SELECT p FROM Parking p WHERE
            (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) +
            sin(radians(:latitude)) * sin(radians(p.latitude)))) <= :radius
            """)
    List<Parking> findParkingInRadius(@Param("latitude") double latitude,
                                      @Param("longitude") double longitude,
                                      @Param("radius") double radius);
}
