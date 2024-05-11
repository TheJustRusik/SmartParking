package org.kenuki.smartparking.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "Empty username!")
    private String username;

    @NotBlank(message = "Empty email!")
    @Email(message = "Invalid email format!")
    private String email;

    @NotBlank(message = "Empty password!")
    @Size(min = 8, message = "Password must be at least 8 characters!")
    private String password;
}
