package com.yb.demo;

import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2021/2/22
 */

//public class ModuleRefreshSchedulerImpl extends AbstractModuleRefreshScheduler {
//
//    @Autowired
//    public ModuleRefreshSchedulerImpl() {
//    }
//
//    @Override
//    public List<ModuleConfig> queryModuleConfigs() {
//        return ImmutableList.of(ModuleRefreshSchedulerImpl.buildModuleConfig());
//    }
//    public static ModuleConfig buildModuleConfig() {
//        //获取模块URL
//        URL moduleURL = Thread.currentThread().getContextClassLoader().getResource("jarslinkmodule1.jar");
//        ModuleConfig moduleConfig = new ModuleConfig();
//        moduleConfig.setName("demo1-module");
//        moduleConfig.addScanPackage("com.yb.demo.config");
//        moduleConfig.addScanPackage("com.yb.jarslinkmodule.module");
//        moduleConfig.setEnabled(true);
//        moduleConfig.setVersion("1.0.0");
//        moduleConfig.setProperties(ImmutableMap.of("", new Object()));
//        moduleConfig.setModuleUrl(ImmutableList.of(moduleURL));
//        return moduleConfig;
//    }
//}

public class ModuleRefreshSchedulerImpl extends AbstractModuleRefreshScheduler {
    @Override
    public List<ModuleConfig> queryModuleConfigs() {
        return ImmutableList.of(buildModuleConfig()/*, buildModuleConfig2()*/);
    }

    public static ModuleConfig buildModuleConfig() {
        URL demoModule = Thread.currentThread().getContextClassLoader().getResource("jarslinkmodule1.jar");
        ModuleConfig moduleConfig = new ModuleConfig();
        //通过该方法构建的配置都是使用注解形式扫描bean的
        moduleConfig.setOverridePackages(Arrays.asList("com.yb.jarslinkmodule.module"));
        moduleConfig.addScanPackage("com.yb.jarslinkmodule.module");
        moduleConfig.setName("demo1-module");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0.0");
//        moduleConfig.setProperties(ImmutableMap.of("lalala", new Object()));
        moduleConfig.setModuleUrl(ImmutableList.of(demoModule));
        return moduleConfig;
    }
}
