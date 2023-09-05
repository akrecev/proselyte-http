package com.kretsev.repository.hibernate;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.User;
import com.kretsev.repository.UserRepository;
import jakarta.persistence.EntityGraph;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kretsev.utility.HibernateUtils.*;

public class HibernateUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

            return user;
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Session session = getSession()) {
            EntityGraph<?> entityGraph = session.getEntityGraph(getUserGraph());
            Map<String, Object> properties = new HashMap<>();
            properties.put(getFetchGraphPath(), entityGraph);
            User user = session.find(User.class, id, properties);

            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = getSession()) {
            EntityGraph<?> entityGraph = session.getEntityGraph(getUserGraph());

            return session.createQuery("FROM User", User.class)
                    .setHint(getFetchGraphPath(), entityGraph)
                    .list();
        }
    }

    @Override
    public User update(User user) {

        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();

            return user;
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = getSession()) {
            User deletedUser = findById(id).orElseThrow(() -> new DataNotFoundException("User id=" + id + " not found!"));
            Transaction transaction = session.beginTransaction();
            session.remove(deletedUser);
            transaction.commit();
        }
    }
}
