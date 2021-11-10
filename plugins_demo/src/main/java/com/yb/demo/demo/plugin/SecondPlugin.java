package com.yb.demo.demo.plugin;

/**
 * @author yb
 * @description
 * @data 2021/2/9
 */
public class SecondPlugin implements IProtocolAdapter {
    @Override
    public void encode() {
        System.out.println("This is Second:encode");
    }

    @Override
    public void decode() {
        System.out.println("This is Second:encode");
    }
}
