package com.example.demo;

import org.springframework.context.annotation.Bean;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @author yb
 * @description
 * @data 2021/8/23
 */
public class TransientTest {
    private String name;

    class TransientEntity implements Serializable {
        private transient String name;
    }
}
