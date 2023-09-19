package com.kretsev.service.impl;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.Event;
import com.kretsev.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.kretsev.utility.TestUtils.getTestEvent;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventServiceImpl eventService;

    @Test
    void create() {
        when(eventRepository.save(any(Event.class))).thenReturn(getTestEvent());

        Event actualTestEvent = eventService.create(getTestEvent());

        assertNotNull(actualTestEvent);
        assertEquals(getTestEvent().getId(), actualTestEvent.getId());
        assertEquals(getTestEvent().getUser(), actualTestEvent.getUser());
        assertEquals(getTestEvent().getFile(), actualTestEvent.getFile());
        verify(eventRepository, times(1)).save(any());
    }

    @Test
    void getById() {
        when(eventRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getTestEvent()));

        Event actualTestEvent = eventService.getById(getTestEvent().getId());

        assertNotNull(actualTestEvent);
        assertEquals(getTestEvent().getId(), actualTestEvent.getId());
        assertEquals(getTestEvent().getUser(), actualTestEvent.getUser());
        assertEquals(getTestEvent().getFile(), actualTestEvent.getFile());
        verify(eventRepository, times(1)).findById(anyInt());
    }

    @Test
    void getByIdNotFound() {
        when(eventRepository.findById(anyInt()))
                .thenThrow(new DataNotFoundException("Event id=" + getTestEvent().getId() + " not found."));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> eventService.getById(getTestEvent().getId()));

        assertEquals("Event id=10 not found.", exception.getMessage());
        verify(eventRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAll() {
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(getTestEvent()));

        List<Event> testEventList = Collections.singletonList(getTestEvent());
        List<Event> actualEventList = eventService.getAll();

        assertNotNull(actualEventList);
        assertEquals(testEventList.size(), actualEventList.size());
        assertEquals(testEventList.get(0), actualEventList.get(0));
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void getAllEmpty() {
        when(eventRepository.findAll()).thenReturn(Collections.emptyList());

        List<Event> actualEventList = eventService.getAll();

        assertNotNull(actualEventList);
        assertEquals(0, actualEventList.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void update() {
        when(eventRepository.update(any(Event.class))).thenReturn(getTestEvent());

        Event actualTestEvent = eventService.update(getTestEvent());

        assertNotNull(actualTestEvent);
        assertEquals(getTestEvent().getId(), actualTestEvent.getId());
        assertEquals(getTestEvent().getUser(), actualTestEvent.getUser());
        assertEquals(getTestEvent().getFile(), actualTestEvent.getFile());
        verify(eventRepository, times(1)).update(any());
    }
}