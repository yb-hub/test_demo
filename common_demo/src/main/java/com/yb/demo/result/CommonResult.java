package com.yb.demo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yb
 * @description 公共返回类
 * @data 2020/10/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;
    public CommonResult(String code,String message){
        this.code = code;
        this.message = message;
    }
}
