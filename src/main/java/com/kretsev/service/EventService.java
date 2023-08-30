package com.kretsev.service;

import com.kretsev.model.Event;

import java.util.List;

public interface EventService {
    Event create(Event event);

    Event getById(Integer id);

    List<Event> getAll();

    Event update(Event event);

    void deleteById(Integer id);
}
