package com.crud.entity;

import jakarta.persistence.*;
import lombok.*;

//Annotations used to identify which class is the table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_user")
public class User {

    //Annotations used to auto increment id and declare as an id
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String email;
}
