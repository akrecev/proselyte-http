package com.kretsev.repository.hibernate;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.File;
import com.kretsev.repository.FileRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

import static com.kretsev.utility.HibernateUtils.getSession;

public class HibernateFileRepositoryImpl implements FileRepository {
    @Override
    public File save(File file) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(file);
            transaction.commit();

            return file;
        }
    }

    @Override
    public Optional<File> findById(Integer id) {
        try (Session session = getSession()) {
            File file = session.get(File.class, id);

            return Optional.ofNullable(file);
        }
    }

    @Override
    public List<File> findAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM File", File.class).list();
        }
    }

    @Override
    public File update(File file) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(file);
            transaction.commit();

            return file;
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = getSession()) {
            File deletedFile = findById(id).orElseThrow(() -> new DataNotFoundException("File id=" + id + " not found!"));
            Transaction transaction = session.beginTransaction();
            session.remove(deletedFile);
            transaction.commit();
        }
    }
}
