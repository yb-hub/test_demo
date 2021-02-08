package com.yb.demo.demo.plugin;


/**
 * @author yb
 * @description
 * @data 2021/2/8
 */
public class FirstPlugin implements IProtocolAdapter {
    @Override
    public void encode() {
        System.out.println("This is FirstPlugin:encode");
    }

    @Override
    public void decode() {
        System.out.println("This is FirstPlugin:decode");
    }
}
