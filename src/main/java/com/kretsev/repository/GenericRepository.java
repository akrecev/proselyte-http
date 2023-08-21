package com.kretsev.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    T update(T entity);
    void delete(ID id);
}
