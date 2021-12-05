package com.yb.demo;

import lombok.Data;

/**
 * @Author: zhj
 * @Date: Created in 10:43 2021/11/29
 * @Description:
 * @return:
 */
@Data
public class Bndbox {
    private Integer xmin;
    private Integer ymin;
    private Integer xmax;
    private Integer ymax;
}
