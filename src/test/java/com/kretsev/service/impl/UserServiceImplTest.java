package com.kretsev.service.impl;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.User;
import com.kretsev.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.kretsev.utility.TestUtils.getTestUser;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void create() {
        when(userRepository.save(any(User.class))).thenReturn(getTestUser());

        User actualTestUser = userService.create(getTestUser());

        assertNotNull(actualTestUser);
        assertEquals(getTestUser().getId(), actualTestUser.getId());
        assertEquals(getTestUser().getName(), actualTestUser.getName());
        assertEquals(getTestUser().getEvents(), actualTestUser.getEvents());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void getById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getTestUser()));

        User actualTestUser = userService.getById(getTestUser().getId());

        assertNotNull(actualTestUser);
        assertEquals(getTestUser().getId(), actualTestUser.getId());
        assertEquals(getTestUser().getName(), actualTestUser.getName());
        assertEquals(getTestUser().getEvents(), actualTestUser.getEvents());
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void getByIdNotFound() {
        when(userRepository.findById(anyInt()))
                .thenThrow(new DataNotFoundException("User id=" + getTestUser().getId() + " not found."));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> userService.getById(getTestUser().getId()));

        assertEquals("User id=10 not found.", exception.getMessage());
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(getTestUser()));

        List<User> testUserList = Collections.singletonList(getTestUser());
        List<User> actualUserList = userService.getAll();

        assertNotNull(actualUserList);
        assertEquals(testUserList.size(), actualUserList.size());
        assertEquals(testUserList.get(0), actualUserList.get(0));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getAllEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> actualUserList = userService.getAll();

        assertNotNull(actualUserList);
        assertEquals(0, actualUserList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void update() {
        when(userRepository.update(any(User.class))).thenReturn(getTestUser());

        User actualTestUser = userService.update(getTestUser());

        assertNotNull(actualTestUser);
        assertEquals(getTestUser().getId(), actualTestUser.getId());
        assertEquals(getTestUser().getName(), actualTestUser.getName());
        assertEquals(getTestUser().getEvents(), actualTestUser.getEvents());
        verify(userRepository, times(1)).update(any());
    }
}