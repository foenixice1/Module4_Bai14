package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^[^\\d]+$", message = "Name only letters can't enter numbers")
    private String name;

    private String date;
    private int phone;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*(@gmail.com)$", message = "Please enter the correct format: .......@gmail.com")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_class")
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(Integer id, String name, String date, int phone, String email, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.classRoom = classRoom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
