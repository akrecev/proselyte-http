package com.kretsev.service;

import com.kretsev.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Integer id);

    List<User> getAll();

    User update(User user);

    void deleteById(Integer id);
}
