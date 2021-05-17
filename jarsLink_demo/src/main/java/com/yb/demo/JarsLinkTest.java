package com.yb.demo;

import com.alipay.jarslink.api.*;
import com.yb.demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yb
 * @description
 * @data 2021/2/22
 */
@RestController
public class JarsLinkTest {

    @Autowired
    private ModuleLoader moduleLoader;
    @Autowired
    private ModuleManager moduleManager;
    @Autowired
    private ModuleRefreshSchedulerImpl moduleRefreshScheduler;

    @GetMapping("test1")
    public void test1() {
        //List<ModuleConfig> moduleConfigs = moduleRefreshScheduler.queryModuleConfigs();
        //ModuleConfig moduleConfig = AppConfig.buildModuleConfig();
        ModuleConfig moduleConfig = ModuleRefreshSchedulerImpl.buildModuleConfig();
        Module module = moduleLoader.load(moduleConfig);
        moduleManager.register(module);
        //查找module
        module.doAction("jarsLinkModule1","111");
        Module demo1Module = moduleManager.find("demo1-module", "1.0.0");
        Map<String, Action> actions = demo1Module.getActions();
        System.out.println(actions);

    }
}
