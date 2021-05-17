package com.yb.demo.demo.controller;

import com.yb.demo.demo.plugin.IProtocolAdapter;
import com.yb.demo.demo.plugin.PluginManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author yb
 * @description 插件上传接口
 * @data 2021/2/8
 */
@RestController
public class pluginsUploadController {

    @PostMapping("/plugins/upload")
    public Map upload(MultipartFile pluginFile) throws IOException {
        //保存到指定路径
        String filename = pluginFile.getOriginalFilename();
        String pluginPath = PropertiesUtil.getStringValue("plugin.path");
        File file = new File(pluginPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String filePath = pluginPath + "/" + filename;
        try {
            pluginFile.transferTo(new File(filePath));
        } catch (IOException e) {
        }
        return null;
    }

    @GetMapping("/plugins/test")
    public Map testPlugins(String pluginsType) {
        IProtocolAdapter service = loadDemoPlugin();
        if (service == null) {
            System.out.print("no demo plugin service found");
        } else {
            service.decode();
            service.encode();
        }
        return null;
    }

    @PostMapping(value = "/plugins/install")
    public void addPlugins(@RequestParam("plugin") MultipartFile pluginFile) {

        System.out.println("=======");
    }

    public static IProtocolAdapter loadDemoPlugin() {
        IProtocolAdapter ret = null;
        ClassLoader loader = PluginManager.getInstance().getLoader();
        if (loader != null) {
            ServiceLoader<IProtocolAdapter> demoServiceLoader = ServiceLoader.load(IProtocolAdapter.class);
            Iterator<IProtocolAdapter> it = demoServiceLoader.iterator();
            while (it.hasNext()) {
                ret = it.next();
                System.out.println("new demo plugin found: " + ret.getClass().getName());
            }

        }
        return ret;
    }

    public static void main(String[] args) {
        String startTime = "2021-02-18 00:00:00";
        String endTime = "2021-02-19 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startTime, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endTime, dateTimeFormatter);
        System.out.println(end.compareTo(start));
    }
}
