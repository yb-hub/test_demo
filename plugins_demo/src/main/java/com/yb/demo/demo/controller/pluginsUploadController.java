package com.yb.demo.demo.controller;

import com.yb.demo.demo.plugin.IProtocolAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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
        String clazzName = pluginsType+"plugin";
        //通过反射获取实体类
        try {
            Class<?> aClass = Class.forName(clazzName);
            Object adapter = aClass.newInstance();
            IProtocolAdapter adp = null;
            if(adapter instanceof IProtocolAdapter){
                adp = (IProtocolAdapter) adapter;
            }
            adp.decode();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
