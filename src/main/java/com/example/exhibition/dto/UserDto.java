package com.example.exhibition.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class UserDto {

    @NotNull
    private String name;

    @NotNull
    private String password;
}
