package com.pierce.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pierce.domain.User;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceTest {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        when(this.userRepository.findByUsername((String) any())).thenReturn(user);
        assertSame(user, this.customUserDetailsService.loadUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userRepository.findByUsername((String) any())).thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> this.customUserDetailsService.loadUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testLoadUserById() {
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
        when(this.userRepository.getById((Long) any())).thenReturn(user);
        assertSame(user, this.customUserDetailsService.loadUserById(123L));
        verify(this.userRepository).getById((Long) any());
    }

    @Test
    void testLoadUserById2() {
        when(this.userRepository.getById((Long) any())).thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> this.customUserDetailsService.loadUserById(123L));
        verify(this.userRepository).getById((Long) any());
    }
}

