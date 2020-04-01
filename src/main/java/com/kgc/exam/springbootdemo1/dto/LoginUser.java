package com.kgc.exam.springbootdemo1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUser {
    private long id;
    private String name;
    private int sex;
    private int age;
}
