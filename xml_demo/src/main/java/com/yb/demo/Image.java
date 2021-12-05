package com.yb.demo;

import lombok.Data;

import java.util.List;

/**
 * @Author: zhj
 * @Date: Created in 10:35 2021/11/29
 * @Description:
 * @return:
 */
@Data
public class Image {
    private String id;
    private String url;
    private List<Tag> object;
}
