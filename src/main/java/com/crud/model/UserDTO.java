package com.crud.model;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String email;
    private String name;
    private Integer age;
}
