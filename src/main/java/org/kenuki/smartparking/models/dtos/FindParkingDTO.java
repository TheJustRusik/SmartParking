package org.kenuki.smartparking.models.dtos;

import lombok.Data;

@Data
public class FindParkingDTO {
    private enum Orders {
        cheapest,
        nearest
    }

    private Double latitude;
    private Double longitude;
    private Double radius;
    private Orders order_by;
}
