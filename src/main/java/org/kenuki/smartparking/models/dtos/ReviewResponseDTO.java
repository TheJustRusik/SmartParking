package org.kenuki.smartparking.models.dtos;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    String user;
    String comment;
    Short grade;
}
