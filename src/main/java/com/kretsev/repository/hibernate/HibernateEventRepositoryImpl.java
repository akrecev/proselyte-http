package com.kretsev.repository.hibernate;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.Event;
import com.kretsev.repository.EventRepository;
import jakarta.persistence.EntityGraph;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kretsev.utility.HibernateUtils.*;

public class HibernateEventRepositoryImpl implements EventRepository {
    @Override
    public Event save(Event event) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(event);
        transaction.commit();
        session.close();

        return event;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        Session session = getSession();
        EntityGraph<?> entityGraph = session.getEntityGraph(getEventGraph());
        Map<String, Object> properties = new HashMap<>();
        properties.put(getFetchGraphPath(), entityGraph);
        Event event = session.find(Event.class, id, properties);
        session.close();

        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAll() {
        Session session = getSession();
        EntityGraph<?> entityGraph = session.getEntityGraph(getEventGraph());
        List<Event> events = session.createQuery("FROM Event", Event.class)
                .setHint(getFetchGraphPath(), entityGraph)
                .list();
        session.close();

        return events;
    }

    @Override
    public Event update(Event event) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(event);
        transaction.commit();
        session.close();

        return event;
    }

    @Override
    public void delete(Integer id) {
        Event deletedEvent = findById(id).orElseThrow(() -> new DataNotFoundException("Event id=" + id + " not found!"));
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(deletedEvent);
        transaction.commit();
        session.close();
    }
}
