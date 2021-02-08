package com.yb.demo.plugin.demo.plugins;

import com.yb.demo.demo.plugin.IProtocolAdapter;

public class SecondPlugin implements IProtocolAdapter {
    @Override
    public void encode() {
        System.out.println("This is SecondPlugin:encode");
    }

    @Override
    public void decode() {
        System.out.println("This is SecondPlugin:decode");
    }
}
