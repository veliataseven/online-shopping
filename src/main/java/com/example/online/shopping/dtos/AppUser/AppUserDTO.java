package com.example.online.shopping.dtos.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDTO {

    @NotEmpty(message = "firstname should not be null or empty")
    @Size(min = 2, message = "firstname should have at least 2 characters")
    private String firstName;

    @NotEmpty(message = "lastname should not be null or empty")
    @Size(min = 2, message = "lastname should have at least 2 characters")
    private String lastName;

    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "email should be a valid format")
    private String email;

    @NotEmpty(message = "password should not be null or empty")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    private String role;
}
