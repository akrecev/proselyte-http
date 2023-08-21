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
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        Session session = getSession();
        EntityGraph<?> entityGraph = session.getEntityGraph(getUserGraph());
        Map<String, Object> properties = new HashMap<>();
        properties.put(getFetchGraphPath(), entityGraph);
        User user = session.find(User.class, id, properties);
        session.close();

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        Session session = getSession();
        EntityGraph<?> entityGraph = session.getEntityGraph(getUserGraph());
        List<User> users = session.createQuery("FROM User", User.class)
                .setHint(getFetchGraphPath(), entityGraph)
                .list();
        session.close();

        return users;
    }

    @Override
    public User update(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public void delete(Integer id) {
        User deletedUser = findById(id).orElseThrow(() -> new DataNotFoundException("User id=" + id + " not found!"));
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(deletedUser);
        transaction.commit();
        session.close();
    }
}
