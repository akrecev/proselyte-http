package com.kretsev.service;

import com.kretsev.model.File;

import java.util.List;

public interface FileService {
    File create(File file);

    File getById(Integer id);

    List<File> getAll();

    File update(File file);

    void deleteById(Integer id);
}
