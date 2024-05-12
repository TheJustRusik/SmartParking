package org.kenuki.smartparking.models.dtos;

import lombok.Data;
import org.kenuki.smartparking.models.composites.MapPoint;

import java.util.List;

@Data
public class ResponseParkingDTO {
    private Long id;
    private String name;
    private Integer max_places;
    private Integer free_places;
    private Double rent_per_hour;
    private List<MapPoint> points;
}
