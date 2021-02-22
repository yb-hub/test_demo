package com.yb.jarslinkmodule.module;

import com.alipay.jarslink.api.Action;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yb
 * @description
 * @data 2021/2/22
 */
@Component
public class jarsLinkModule1 implements Action<String,String> {

    @Override
    public String execute(String actionRequest) {
        System.out.println("actionRequest:"+actionRequest);
        return "execute ok";
    }

    @Override
    public String getActionName() {
        return "jarsLinkModule1";
    }
}
