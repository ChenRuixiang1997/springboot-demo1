package com.kgc.exam.springbootdemo1.vo;

import lombok.Data;

@Data
public class ReturnResults<T> {
    private Integer code;
    private String message;
    private T data;
}
