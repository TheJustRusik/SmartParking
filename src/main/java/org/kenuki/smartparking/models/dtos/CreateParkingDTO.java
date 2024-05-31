package org.kenuki.smartparking.models.dtos;

import lombok.Data;
import org.kenuki.smartparking.models.composites.MapPoint;

import java.util.Set;

@Data
public class CreateParkingDTO {
    String name;
    Integer max_places;
    Double rent_per_hour;
    Set<MapPoint> points;
}
