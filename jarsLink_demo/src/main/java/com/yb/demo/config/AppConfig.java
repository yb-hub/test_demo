package com.yb.demo.config;

import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.alipay.jarslink.api.impl.ModuleLoaderImpl;
import com.alipay.jarslink.api.impl.ModuleManagerImpl;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yb.demo.ModuleRefreshSchedulerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yb
 * @description
 * @data 2021/2/22
 */
@Component
public class AppConfig {
    @Bean
    public ModuleLoaderImpl moduleLoader() {
        return new ModuleLoaderImpl();
    }

    @Bean
    public ModuleManagerImpl moduleManager() {
        return new ModuleManagerImpl();
    }

    @Bean
    public ModuleRefreshSchedulerImpl moduleRefreshScheduler() {
        ModuleRefreshSchedulerImpl moduleRefreshScheduler = new ModuleRefreshSchedulerImpl();
        ModuleManager moduleManager = moduleManager();
        ModuleLoader moduleLoader = moduleLoader();
        moduleRefreshScheduler.setModuleLoader(moduleLoader);
        moduleRefreshScheduler.setModuleManager(moduleManager);
        return moduleRefreshScheduler;
    }

    public static ModuleConfig buildModuleConfig() {
        //获取模块URL
        URL moduleURL = Thread.currentThread().getContextClassLoader().getResource("jarslinkmodule1.jar");
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("demo1-module");
        moduleConfig.setOverridePackages(Arrays.asList("com.yb.jarslinkmodule.module"));
//        moduleConfig.addScanPackage("com.yb.demo.config");
        moduleConfig.addScanPackage("com.yb.jarslinkmodule.module");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0.0");
        //moduleConfig.setProperties(ImmutableMap.of("", new Object()));
        moduleConfig.setModuleUrl(ImmutableList.of(moduleURL));
        return moduleConfig;
    }

}
