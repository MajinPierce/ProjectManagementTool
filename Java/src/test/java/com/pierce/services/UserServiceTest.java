package com.pierce.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pierce.domain.User;
import com.pierce.exceptions.UsernameAlreadyExistsException;
import com.pierce.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;

@ContextConfiguration(classes = {UserService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");
        assertSame(user, this.userService.saveUser(user1));
        verify(this.userRepository).save((User) any());
        assertEquals("janedoe", user1.getUsername());
        assertEquals("", user1.getConfirmPassword());
    }

    @Test
    void testSaveUserError() {
        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        when(this.userRepository.save((User) any())).thenThrow(UsernameAlreadyExistsException.class);
        Throwable exception = assertThrows(UsernameAlreadyExistsException.class, () -> userRepository.save(user));
    }

}

