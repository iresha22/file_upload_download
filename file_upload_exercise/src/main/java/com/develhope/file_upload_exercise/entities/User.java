package com.develhope.file_upload_exercise.entities;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String age;

    // costruttore vuoto
    public User(){

    }
    // costruttore con tutti parametri
    public User(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
