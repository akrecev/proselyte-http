package com.kretsev.service.impl;

import com.kretsev.exception.DataNotFoundException;
import com.kretsev.model.File;
import com.kretsev.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.kretsev.utility.TestUtils.getTestFile;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    FileServiceImpl fileService;

    @Test
    void create() {
        when(fileRepository.save(any(File.class))).thenReturn(getTestFile());

        File actualTestFile = fileService.create(getTestFile());

        assertNotNull(actualTestFile);
        assertEquals(getTestFile().getId(), actualTestFile.getId());
        assertEquals(getTestFile().getName(), actualTestFile.getName());
        assertEquals(getTestFile().getFilePath(), actualTestFile.getFilePath());
        verify(fileRepository, times(1)).save(any());
    }

    @Test
    void getById() {
        when(fileRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getTestFile()));

        File actualTestFile = fileService.getById(getTestFile().getId());

        assertNotNull(actualTestFile);
        assertEquals(getTestFile().getId(), actualTestFile.getId());
        assertEquals(getTestFile().getName(), actualTestFile.getName());
        assertEquals(getTestFile().getFilePath(), actualTestFile.getFilePath());
        verify(fileRepository, times(1)).findById(anyInt());
    }

    @Test
    void getByIdNotFound() {
        when(fileRepository.findById(anyInt()))
                .thenThrow(new DataNotFoundException("File id=" + getTestFile().getId() + " not found."));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> fileService.getById(getTestFile().getId()));

        assertEquals("File id=10 not found.", exception.getMessage());
        verify(fileRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAll() {
        when(fileRepository.findAll()).thenReturn(Collections.singletonList(getTestFile()));

        List<File> testFileList = Collections.singletonList(getTestFile());
        List<File> actualFileList = fileService.getAll();

        assertNotNull(actualFileList);
        assertEquals(testFileList.size(), actualFileList.size());
        assertEquals(testFileList.get(0), actualFileList.get(0));
        verify(fileRepository, times(1)).findAll();
    }

    @Test
    void getAllEmpty() {
        when(fileRepository.findAll()).thenReturn(Collections.emptyList());

        List<File> actualFileList = fileService.getAll();

        assertNotNull(actualFileList);
        assertEquals(0, actualFileList.size());
        verify(fileRepository, times(1)).findAll();
    }

    @Test
    void update() {
        when(fileRepository.update(any(File.class))).thenReturn(getTestFile());

        File actualTestFile = fileService.update(getTestFile());

        assertNotNull(actualTestFile);
        assertEquals(getTestFile().getId(), actualTestFile.getId());
        assertEquals(getTestFile().getName(), actualTestFile.getName());
        assertEquals(getTestFile().getFilePath(), actualTestFile.getFilePath());
        verify(fileRepository, times(1)).update(any());
    }
}