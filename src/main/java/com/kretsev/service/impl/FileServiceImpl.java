package com.kretsev.service.impl;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.File;
import com.kretsev.repository.FileRepository;
import com.kretsev.service.FileService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    @Override
    public File create(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File getById(Integer id) {
        return fileRepository.findById(id).orElseThrow(() -> new DataNotFoundException("File id=" + id + " not found!"));
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public void deleteById(Integer id) {
        fileRepository.delete(id);
    }
}
