package com.makowski.user_service.service;

import com.makowski.user_service.entity.User;
import com.makowski.user_service.exceptions.InvalidRequestException;
import com.makowski.user_service.exceptions.NoSuchUserException;
import com.makowski.user_service.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createUser_ThrowInvalidRequestException_WhenUserAlreadyExists() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(user));

        assertThrows(InvalidRequestException.class, () -> userService.createUser(user));
    }

    @Test
    void createUser_SaveUser_WhenUserDoesNotExist() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("password");

        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("encodedPassword", createdUser.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void findByUsername_ThrowNoSuchUserException_WhenUserDoesNotExist() {
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        assertThrows(NoSuchUserException.class, () -> userService.findByUsername("nonExistingUser"));
    }

    @Test
    void findByUsername_ReturnUser_WhenUserExists() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("existingUser");

        assertEquals(user, result);
    }

    @Test
    void deleteUser_DeleteLoggedUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("loggedUser");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("loggedUser");
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByUsername("loggedUser")).thenReturn(Optional.of(user));

        userService.deleteUser();

        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void getLoggedUser_ReturnLoggedUser() {
        User user = new User();
        user.setUsername("loggedUser");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("loggedUser");
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByUsername("loggedUser")).thenReturn(Optional.of(user));

        User result = userService.getLoggedUser();

        assertEquals(user, result);
    }
}