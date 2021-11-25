package com.yb.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author yb
 * @description
 * @data 2021/11/24
 */
@Data
public class ImageEntity {
    private String name;
    private String urlPath;
    private List<Tag> tagList;
}
