package com.BangMach.RestaurantMenuService.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {
    private String id;
    private String name;
    private Gender gender;
    private int grade;
    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public enum Gender {
        MALE,FEMALE
    }
}
