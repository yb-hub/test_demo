package com.yb.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yb
 * @description
 * @data 2021/11/1
 */
@Data
public class Person {

    @NotBlank(message = "name不能为空")
    private String name;
    private Integer age;
}
