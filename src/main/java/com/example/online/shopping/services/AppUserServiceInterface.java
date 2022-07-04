package com.example.online.shopping.services;

import com.example.online.shopping.dtos.AppUser.SignInDTO;
import com.example.online.shopping.dtos.AppUser.AppUserDTO;
import com.example.online.shopping.models.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AppUserServiceInterface {

    ResponseEntity<List<AppUser>> getAllUsers();

    ResponseEntity<AppUser> getAppUserById(Long id);

    ResponseEntity<AppUser> signUpAppUser(AppUserDTO appUserDto);

    ResponseEntity<AppUser> signInAppUser(SignInDTO signInDto);

    ResponseEntity<AppUser> updateAppUser(Long id, AppUserDTO appUserDto);

    ResponseEntity<AppUser> deleteAppUser(Long id);

    Optional<AppUser> findUserByEmail(String email);

    boolean IsUserExistByEmail(String email);

    boolean IsUserExistByEmailAndPassword(String email, String password);

    AppUser findUserById(Long id);
}
