package com.pierce.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pierce.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;

class UserValidatorTest {
    // Test to show that random objects are not supported
    @Test
    void testSupports() {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.supports(Object.class));
    }

    // Test to show that User classes are supported
    @Test
    void testSupports2() {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.supports(User.class));
    }

    // test properly validated user response
    @Test
    void testValidate() {
        UserValidator userValidator = new UserValidator();

        // Set properties of the User object
        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        user.setCreatedAt(fromResult);
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        user.setUpdatedAt(fromResult1);
        user.setUsername("janedoe");

        // Validate the user that was just created and set
        userValidator.validate(user, new BindException(user, "com.pierce.domain.User"));
        // test that all properties and responses are as expected
        assertEquals("janedoe", user.getUsername());
        assertSame(fromResult1, user.getUpdatedAt());
        assertTrue(user.getProjects().isEmpty());
        assertEquals("iloveyou", user.getPassword());
        assertEquals(123L, user.getId().longValue());
        assertEquals("Dr Jane Doe", user.getFullName());
        assertSame(fromResult, user.getCreatedAt());
        assertEquals("iloveyou", user.getConfirmPassword());
    }

    // test validation error where passwords don't match
    @Test
    void testValidate2() {
        UserValidator userValidator = new UserValidator();

        // create user with different password and confirm password
        User user = new User();
        user.setConfirmPassword("Confirm Password");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        BindException bindException = new BindException(user, "com.pierce.domain.User");

        // validate user and confirm that the correct error was thrown
        userValidator.validate(user, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 1 errors\n"
                        + "Field error in object 'com.pierce.domain.User' on field 'confirmPassword': rejected value [Confirm"
                        + " Password]; codes [Match.com.pierce.domain.User.confirmPassword,Match.confirmPassword,Match.java.lang"
                        + ".String,Match]; arguments []; default message [Passwords must match]",
                bindException.toString());
        // make sure error where bound to the exception entity
        assertTrue(bindException.hasErrors());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
    }

    // test validation error where password isn't long enough
    @Test
    void testValidate3() {
        UserValidator userValidator = new UserValidator();

        // create new user with a short password
        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("42");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        BindException bindException = new BindException(user, "com.pierce.domain.User");

        // validate that the proper error is thrown and bound
        userValidator.validate(user, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 2 errors\n"
                        + "Field error in object 'com.pierce.domain.User' on field 'password': rejected value [42]; codes"
                        + " [Length.com.pierce.domain.User.password,Length.password,Length.java.lang.String,Length]; arguments"
                        + " []; default message [Password must be at least 6 characters]\n"
                        + "Field error in object 'com.pierce.domain.User' on field 'confirmPassword': rejected value [iloveyou];"
                        + " codes [Match.com.pierce.domain.User.confirmPassword,Match.confirmPassword,Match.java.lang.String,Match];"
                        + " arguments []; default message [Passwords must match]",
                bindException.toString());
        assertTrue(bindException.hasErrors());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
    }
}

