package com.example.online.shopping.services;

import com.example.online.shopping.dtos.AppUser.SignInDTO;
import com.example.online.shopping.dtos.AppUser.AppUserDTO;
import com.example.online.shopping.enums.Role;
import com.example.online.shopping.exceptions.AppUser.AppUserException;
import com.example.online.shopping.exceptions.AppUser.AppUserIsAlreadyExistException;
import com.example.online.shopping.exceptions.AppUser.AppUserNotFoundException;
import com.example.online.shopping.exceptions.Category.NoCategoryFoundInListException;
import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.repositories.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
public class AppUserService implements AppUserServiceInterface {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        if (appUsers.isEmpty()) throw new NoCategoryFoundInListException(UNSUCCESSFUL_GET_ALL_USERS_MESSAGE);
        return new ResponseEntity<List<AppUser>>(appUsers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppUser> getAppUserById(Long id) {
        AppUser appUser = findUserById(id);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppUser> signUpAppUser(AppUserDTO appUserDto) {
        boolean IsAppUserExistByEmail = IsUserExistByEmail(appUserDto.getEmail());
        System.out.println("Before    IsAppUserExistByEmail = " + IsAppUserExistByEmail);
        if (IsAppUserExistByEmail) throw new AppUserIsAlreadyExistException(UNSUCCESSFUL_USER_SIGNUP_MESSAGE);
        System.out.println("Later     IsAppUserExistByEmail = " + IsAppUserExistByEmail);

        AppUser newAppUser = AppUser.builder()
                .firstName(appUserDto.getFirstName())
                .lastName(appUserDto.getLastName())
                .email(appUserDto.getEmail())
                .password(appUserDto.getPassword())
                .role(Role.valueOf(appUserDto.getRole()))
                .build();

        appUserRepository.save(newAppUser);
        return new ResponseEntity<AppUser>(newAppUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AppUser> signInAppUser(SignInDTO signInDto) {
        boolean IsUserExist = IsUserExistByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword());

        AppUser appUserToBeSignedIn = appUserRepository
                .findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword())
                .orElseThrow(() -> new AppUserNotFoundException("Wrong Credentials!"));
        return new ResponseEntity<AppUser>(appUserToBeSignedIn, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AppUser> updateAppUser(Long id, AppUserDTO appUserDto) {
        AppUser appUserToBeUpdated = findUserById(id);

        boolean isEmailAlreadyTaken = appUserRepository.findAll().stream()
                .anyMatch(appUser -> !Objects.equals(appUser.getId(), appUserToBeUpdated.getId())
                        && appUser.getEmail().equals(appUserDto.getEmail()));
        if (isEmailAlreadyTaken) throw new AppUserException("Email has been already taken!");

        appUserToBeUpdated.setEmail(appUserDto.getEmail());
        appUserToBeUpdated.setFirstName(appUserDto.getFirstName());
        appUserToBeUpdated.setLastName(appUserDto.getLastName());
        appUserToBeUpdated.setPassword(appUserDto.getPassword());
        appUserToBeUpdated.setRole(Role.valueOf(appUserDto.getRole()));
        appUserRepository.save(appUserToBeUpdated);
        return new ResponseEntity<AppUser>(appUserToBeUpdated, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppUser> deleteAppUser(Long id) {
        AppUser appUserToBeDeleted = findUserById(id);
        appUserRepository.deleteById(id);
        return new ResponseEntity<AppUser>(appUserToBeDeleted, HttpStatus.OK);
    }

    @Override
    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public boolean IsUserExistByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    @Override
    public boolean IsUserExistByEmailAndPassword(String email, String password) {
        return appUserRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public AppUser findUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(UNSUCCESSFUL_GET_USER_MESSAGE + id));
    }



}
