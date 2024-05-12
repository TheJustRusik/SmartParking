package org.kenuki.smartparking.models.composites;

import lombok.*;
import org.kenuki.smartparking.models.enities.Parking;
import org.kenuki.smartparking.models.enities.ParkingPoint;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Data
public class MapPoint implements Serializable {
    private Double latitude;
    private Double longitude;

    public MapPoint(Parking parking) {
        latitude = parking.getLatitude();
        longitude = parking.getLongitude();
    }


    public MapPoint(ParkingPoint parkingPoint) {
        latitude = parkingPoint.getLatitude();
        longitude = parkingPoint.getLongitude();
    }
}
