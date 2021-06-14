package com.example.exhibition.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class VideoFiles extends Files{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoId")
    private Long id;

    @NotNull
    private String origFilename;

    @NotNull
    private String filename;

    @NotNull
    @Lob
    private String filePath;
}
