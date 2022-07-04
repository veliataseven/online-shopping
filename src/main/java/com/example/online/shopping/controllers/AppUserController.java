package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.AppUser.SignInDTO;
import com.example.online.shopping.dtos.AppUser.AppUserDTO;
import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.services.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/appUser")
@Tag(name = "User")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get All Users", description = "Get all users in list.")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get User By Id", description = "Get a user by id.")
    public ResponseEntity<AppUser> getAppUserById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return appUserService.getAppUserById(id);
    }

    @PostMapping(value = "/signUp", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Sign up User", description = "Sign up user with user data.")
    public ResponseEntity<AppUser> signUp(@RequestBody @Valid AppUserDTO appUserDto) {
        return appUserService.signUpAppUser(appUserDto);
    }

    @PostMapping(value = "/signIn", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Sign in User", description = "Sign in user with user credentials.")
    public ResponseEntity<AppUser> signIn(@RequestBody @Valid SignInDTO signInDto) {
        return appUserService.signInAppUser(signInDto);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update User", description = "Update user with user data.")
    public ResponseEntity<AppUser> updateAppUser(
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody @Valid AppUserDTO appUserDto) {
        return appUserService.updateAppUser(id, appUserDto);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @Operation(summary = "Delete User By Id", description = "Delete user by id.")
    public ResponseEntity<AppUser> deleteAppUserById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return appUserService.deleteAppUser(id);
    }
}
