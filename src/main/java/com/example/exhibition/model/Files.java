package com.example.exhibition.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class Files {

    private Long id;

    private String origFilename;
    private String filename;
    private String filePath;
}
