package com.example.blogjava.service;

import com.example.blogjava.entities.User;
import com.example.blogjava.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    //    Mockito使用继承实现Mock，所以该类不能是final
    @Mock
    BCryptPasswordEncoder mockEncoder;
    @Mock
    UserMapper mockMapper;
    @InjectMocks
    UserService userSevice;
//    UserService userSevice = new UserService(mockEncoder, mockMapper);

    @Test
    public void testSave() {
        when(mockEncoder.encode("mypassword")).thenReturn("myEncodedPassword");
        userSevice.save("myUser", "mypassword");
        Mockito.verify(mockMapper).save("myUser", "myEncodedPassword");
    }

    @Test
    public void testGetUserByUsername() {
        userSevice.getUserByUsername("myUser");
        Mockito.verify(mockMapper).findUserByUsername("myUser");
    }

    @Test
    public void throwExceptionWhenUserNotFound() {
        when(mockMapper.findUserByUsername("myUser")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userSevice.loadUserByUsername("myUser"));
    }

    @Test
    public void returnUserDetailsWhenUserFound() {
        when(mockMapper.findUserByUsername("myUser"))
                .thenReturn(new User(1, "myUser", "myEncodedPassword", null, Instant.now(), Instant.now()));
        UserDetails userDetails = userSevice.loadUserByUsername("myUser");
        Assertions.assertEquals("myUser", userDetails.getUsername());
        Assertions.assertEquals("myEncodedPassword", userDetails.getPassword());

    }


}