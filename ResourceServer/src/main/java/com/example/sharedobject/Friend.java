package com.example.sharedobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Friend implements Serializable {
    private String name;
    private int age;
    private String gender;

    public Friend(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
