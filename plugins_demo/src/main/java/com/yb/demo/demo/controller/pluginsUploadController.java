package com.yb.demo.demo.controller;

import com.yb.demo.demo.plugin.IProtocolAdapter;
import com.yb.demo.demo.plugin.PluginManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
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
    public Map upload(MultipartFile file) throws IOException {
        //保存到指定路径
        String filename = file.getOriginalFilename();
        String path = System.getProperty("user.dir")+"\\plugins_demo\\lib\\";
        System.out.println("地址："+path+filename);
        file.transferTo(new File(path+filename));
        return null;
    }

    @GetMapping("/plugins/test")
    public Map testPlugins(String pluginsType){
        IProtocolAdapter service = loadDemoPlugin();
        if ( service == null) {
            System.out.print("no demo plugin service found");
        } else {
            service.decode();
            service.encode();
        }
        return  null;
    }

    public static IProtocolAdapter loadDemoPlugin() {
        IProtocolAdapter ret = null;
        ClassLoader loader = PluginManager.getInstance().getLoader();
        if (loader != null) {
            ServiceLoader<IProtocolAdapter> demoServiceLoader = ServiceLoader.load(IProtocolAdapter.class,loader);
            Iterator<IProtocolAdapter> it = demoServiceLoader.iterator();
            while (it.hasNext()) {
                ret = it.next();
                System.out.println("new demo plugin found: " + ret.getClass().getName());
            }

        }
        return ret;
    }
}
