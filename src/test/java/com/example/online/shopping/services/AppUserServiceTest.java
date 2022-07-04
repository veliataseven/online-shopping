package com.example.online.shopping.services;

import com.example.online.shopping.dtos.AppUser.AppUserDTO;
import com.example.online.shopping.enums.Role;
import com.example.online.shopping.exceptions.AppUser.AppUserIsAlreadyExistException;
import com.example.online.shopping.exceptions.AppUser.AppUserNotFoundException;
import com.example.online.shopping.exceptions.Category.NoCategoryFoundInListException;
import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.repositories.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.online.shopping.constants.ResponseConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppUserServiceTest {

    @InjectMocks
    AppUserService appUserService;

    @Mock
    AppUserRepository appUserRepository;

    @Spy
    AppUserService appUserServiceSpy = Mockito.spy(new AppUserService(AppUserRepository.getInstance()));

    AppUserRepository appUserRepositorySpy2 =  mock(AppUserRepository.class);

    @InjectMocks
    @Spy
    AppUserService appUserServiceSpy2 = new AppUserService(appUserRepositorySpy2);




    @Test
    void getAllUsersSuccessfully() {
        List<AppUser> appUserList = new ArrayList<>();

        AppUser appUserTest1 = AppUser.builder()
                .email("johnsmith7799@gmail.com")
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(Role.USER)
                .build();
        AppUser appUserTest2 = AppUser.builder()
                .email("johnsmith8899@gmail.com")
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(Role.USER)
                .build();

        appUserList.add(appUserTest1);
        appUserList.add(appUserTest2);

        ResponseEntity<List<AppUser>> responseEntity = new ResponseEntity<List<AppUser>>(appUserList, HttpStatus.OK);

        System.out.println("appUserList = " + appUserList);

        when(appUserRepository.findAll()).thenReturn(appUserList);

        System.out.println("appUserRepository.findAll() = " + appUserRepository.findAll());
        
        assertEquals(responseEntity, appUserService.getAllUsers());
    }

    @Test
    void getAllUsersWillThrowException() {
        List<AppUser> appUserList = new ArrayList<>();
        when(appUserRepository.findAll()).thenReturn(appUserList);
        assertThatThrownBy(() -> appUserService.getAllUsers())
                .isInstanceOf(NoCategoryFoundInListException.class)
                .hasMessageContaining(UNSUCCESSFUL_GET_ALL_USERS_MESSAGE);
    }

    @Test
    void getAppUserByIdSuccessfully() {
        
        AppUser appUserTest = AppUser.builder()
                .email("johnsmith25@gmail.com")
                .password("johnsmith25")
                .firstName("John25")
                .lastName("Smith")
                .role(Role.USER)
                .build();
        appUserTest.setId(23L);

        // AppUserService appUserServiceSpy = Mockito.spy(new AppUserService(appUserRepository));

        Mockito.doReturn(appUserTest).when(appUserServiceSpy).findUserById(appUserTest.getId());

        ResponseEntity<AppUser> responseEntity = new ResponseEntity<AppUser>(appUserTest, HttpStatus.OK);

        assertEquals(responseEntity, appUserServiceSpy.getAppUserById(appUserTest.getId()));
    }

    @Test
    void getAppUserByIdWillThrowException() {

        Exception exception = assertThrows(AppUserNotFoundException.class, () -> {
            appUserService.getAppUserById(112L);
        });

        String expectedMessage = UNSUCCESSFUL_GET_USER_MESSAGE + "112";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void signUpAppUserSuccessfully() {
        AppUserDTO appUserDto = AppUserDTO.builder()
                .email("johnsmith99@gmail.com")
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(String.valueOf(Role.USER))
                .build();

        AppUser newAppUser = AppUser.builder()
                .firstName(appUserDto.getFirstName())
                .lastName(appUserDto.getLastName())
                .email(appUserDto.getEmail())
                .password(appUserDto.getPassword())
                .role(Role.valueOf(appUserDto.getRole()))
                .build();

        Mockito.doReturn(false).when(appUserServiceSpy).IsUserExistByEmail(appUserDto.getEmail());

        when(appUserRepository.save(newAppUser)).thenReturn(newAppUser);

        System.out.println("appUserRepository.save(newAppUser) = " + appUserRepository.save(newAppUser));

        ResponseEntity<AppUser> responseEntity = new ResponseEntity<AppUser>(newAppUser, HttpStatus.CREATED);

        assertEquals(responseEntity, appUserServiceSpy.signUpAppUser(appUserDto));

        verify(appUserRepository, times(1)).save(newAppUser);
    }

    @Test
    void signUpAppUserWillThrowException() {
        String testEmail = "emailExist@gmail.com";
        AppUserDTO appUserDto = AppUserDTO.builder()
                .email(testEmail)
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(String.valueOf(Role.USER))
                .build();

        // Mockito.when(appUserServiceSpy2.IsUserExistByEmail(testEmail)).thenReturn(true); doesn't work
        // given(appUserServiceSpy2.IsUserExistByEmail(testEmail)).willReturn(true); doesn't work
        Mockito.doReturn(true).when(appUserServiceSpy).IsUserExistByEmail(testEmail);

        assertThatThrownBy(() ->  appUserServiceSpy.signUpAppUser(appUserDto))
                .isInstanceOf(AppUserIsAlreadyExistException.class)
                .hasMessageContaining(UNSUCCESSFUL_USER_SIGNUP_MESSAGE);
    }

    @Test
    public void deleteAppUserSuccessful() {

        AppUser appUserTest = AppUser.builder()
                .email("johnsmith33@gmail.com")
                .password("johnsmith33")
                .firstName("John25")
                .lastName("Smith")
                .role(Role.USER)
                .build();
        appUserTest.setId(33L);

        Mockito.doReturn(appUserTest).when(appUserServiceSpy2).findUserById(appUserTest.getId());

        ResponseEntity<AppUser> expectedResponseEntity = new ResponseEntity<AppUser>(appUserTest, HttpStatus.OK);

        assertEquals(expectedResponseEntity, appUserServiceSpy2.deleteAppUser(appUserTest.getId()));

        verify(appUserRepositorySpy2, times(1)).deleteById(appUserTest.getId());
    }

    @Test
    public void deleteAppUserUnsuccessful() {

    }

    @Test
    public void findUserByEmailSuccessfully() {
        String testEmail5 = "testEmail355@gmail.com";
        AppUser appUserTest = AppUser.builder()
                .email(testEmail5)
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(Role.USER)
                .build();
        appUserTest.setId(44L);
        Optional<AppUser> expectedAppUser = Optional.ofNullable(appUserTest);
        when(appUserRepository.findByEmail(testEmail5)).thenReturn(expectedAppUser);
        System.out.println("appUserRepository.findByEmail(testEmail35) = " + appUserRepository.findByEmail(testEmail5));
        assertEquals(expectedAppUser, appUserService.findUserByEmail(testEmail5));
    }

    @Test
    public void findUserByEmailUnsuccessfully() {
        String testEmail = "testEmail@gmail.com";
        assertEquals(Optional.empty(), appUserService.findUserByEmail(testEmail));
    }

    @Test
    public void IsUserExistByEmailSuccessful() {
        String testEmail = "testEmail000@gmail.com";
        when(appUserRepository.existsByEmail(testEmail)).thenReturn(true);
        assertEquals(true, appUserService.IsUserExistByEmail(testEmail));
    }

    @Test
    public void IsUserExistByEmailUnsuccessful() {
        String testEmail = "testEmail444@gmail.com";
        assertEquals(false, appUserService.IsUserExistByEmail(testEmail));
    }

    @Test
    public void IsUserExistByEmailAndPasswordSuccessful() {
        String testEmail667 = "testEmail6671@gmail.com";
        String testPassword667 = "testEmail6671@gmail.com";

        when(appUserRepository.existsByEmailAndPassword(testEmail667, testPassword667)).thenReturn(true);
        System.out.println("appUserRepository.existsByEmailAndPassword(testEmail, testPassword) = " + appUserRepository.existsByEmailAndPassword(testEmail667, testPassword667));
        assertEquals(true, appUserService.IsUserExistByEmailAndPassword(testEmail667, testPassword667));
    }

    @Test
    public void IsUserExistByEmailAndPasswordUnsuccessful() {
        String testEmail = "testEmail@gmail.com";
        String testPassword = "testEmail@gmail.com";

        assertEquals(false, appUserService.IsUserExistByEmailAndPassword(testEmail, testPassword));
    }

    @Test
    void findUserByIdSuccessfully() {
        AppUser appUserTest = AppUser.builder()
                .email("johnsmith999@gmail.com")
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(Role.USER)
                .build();
        appUserTest.setId(64L);
        when(appUserRepository.findById(appUserTest.getId())).thenReturn(Optional.of(appUserTest));
        assertEquals(appUserTest, appUserService.findUserById(appUserTest.getId()));
    }

    @Test
    void findUserByIdWillThrowException() {
        assertThatThrownBy(() -> appUserService.findUserById(111L))
                .isInstanceOf(AppUserNotFoundException.class)
                .hasMessageContaining(UNSUCCESSFUL_GET_USER_MESSAGE + "111");
    }
}