package com.kretsev.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    Integer id;
    @Column(nullable = false, length = 50)
    @Expose
    String name;
    @Column(name = "file_path", nullable = false)
    @Expose
    String filePath;
}
