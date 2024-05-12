package org.kenuki.smartparking.models.dtos;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    Long parking_id;
    String comment;
    Short grade;
}
