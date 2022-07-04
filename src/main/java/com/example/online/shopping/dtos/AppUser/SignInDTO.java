package com.example.online.shopping.dtos.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDTO {

    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "email should be a valid format")
    private String email;

    @NotEmpty(message = "password should not be null or empty")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
}
