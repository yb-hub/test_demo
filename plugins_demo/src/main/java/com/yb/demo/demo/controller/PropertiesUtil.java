/**
* 版权所有： 爱WiFi无线运营中心
* 创建日期:2017年10月17日 上午10:29:18
* 创建作者：方志伟
* 文件名称：PropertiesUtil.java
* 版本：  v1.0
* 功能：
* 修改记录：
*/
package com.yb.demo.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
    /** 私有无参构造 */
    private PropertiesUtil() {}

    /** log4j */
    private static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    /** 配置类对象 */
    private static Properties CONF_PROPERTIES;

    /**
    * @Title: getInstance 
    * @Description: 单例模式
    * @return Properties
     */
    private static Properties getInstance() {
        if (null == CONF_PROPERTIES) {
            try {
                init();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.debug("classpath 路径下文件读取失败.");
            }
        }
        return CONF_PROPERTIES;
    }

    /**
    * @Title: init 
    * @Description: 初始化工具类
    * @throws IOException   
     */
    private static void init() throws IOException {
        File filePath = new File(PropertiesUtil.class.getClassLoader().getResource("").getPath());
        File[] propertiesFiles = filePath.listFiles(new FilenameFilter() {
            private String extension = ".properties";

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(extension);
            }
        });

        if (null == CONF_PROPERTIES) {
            CONF_PROPERTIES = new Properties();
        }
        for (File file : propertiesFiles) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                CONF_PROPERTIES.load(fis);
            } catch (FileNotFoundException e) { 
                e.printStackTrace();
                LOGGER.debug("classpath 路径下, " + filePath + " 文件无法找到.e:{}", e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.debug("classpath 路径下, " + filePath + " 文件读取失败.");
            }finally{
                if(null != fis){
                    fis.close();
                }
            }
        }
    }

    /**
    * @Title: getStringValue 
    * @Description: 获取配置项值 
    * @param key 键
    * @return String
     */
    public static String getStringValue(String key) {
        return PropertiesUtil.getInstance().getProperty(key);
    }
    
    /**
     * 获取某配置项的值
     * @param key 配置文件的key
     * @param defaultValue 对应的value
     * @return defaultValue
     */
    public static int get(String key, int defaultValue) {
        try{
            if (CONF_PROPERTIES == null) {
                init();
            }
            String value = CONF_PROPERTIES.getProperty(key);
            return Integer.parseInt(value);
        } catch (Exception e) {
            LOGGER.error("获取系统配置项" + key + "异常:", e.getMessage());
            return defaultValue;
        }
    }

    /**
     * 获取配置项,配置项为空或异常时返回默认值
     * @param key 配置key
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    public static String get(String key, String defaultValue) {
        try{
            if (CONF_PROPERTIES == null) {
                init();
            }
            String value = CONF_PROPERTIES.getProperty(key);
            LOGGER.info("从配置文件获取的配置项" + key + "的值为:{}" + value);
            return isEmptyOrNull(value) ? defaultValue : value;
        } catch (Exception e) {
            LOGGER.error("获取系统配置项" + key + "异常:", e.getMessage());
            return defaultValue;
        }
    }
    
    /**
     * @Title: isEmptyOrNull
     * @Description: 是否为空串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmptyOrNull(String str) {
        if(str == null || str.length() == 0 || "null".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }
}
