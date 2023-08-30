package com.kretsev.service.impl;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.Event;
import com.kretsev.repository.EventRepository;
import com.kretsev.service.EventService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Event id=" + id + " not found!"));
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepository.delete(id);
    }
}
