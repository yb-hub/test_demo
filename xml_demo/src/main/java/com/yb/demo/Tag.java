package com.yb.demo;

import lombok.Data;

/**
 * @Author: zhj
 * @Date: Created in 10:41 2021/11/29
 * @Description:
 * @return:
 */
@Data
public class Tag {
    private String name;
    private String pose;
    private Integer truncated;
    private Integer difficult;
    Bndbox bndbox;
}
