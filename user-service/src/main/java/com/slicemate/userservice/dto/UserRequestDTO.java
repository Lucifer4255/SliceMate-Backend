package com.slicemate.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message="UserName is required")
    private String username;
    @NotBlank(message="Password is required")
    private String password;

    @Email(message="Email is invalid")
    @NotBlank(message="Email is required")
    private String email;

    private String role;
}
