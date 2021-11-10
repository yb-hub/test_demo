package com.yb.demo.demo.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class PluginManager {

    private ClassLoader pluginClassLoader = null;

    private PluginManager() {}

    private static class InnerObject{
        private static PluginManager instance = new PluginManager();
    }

    public static PluginManager getInstance() {
        return InnerObject.instance;
    }

    private ClassLoader initLoader() {
        try {
            String path = System.getProperty("user.dir")+"\\plugins_demo\\lib\\";
            File dir = new File(path);
            File[] files = dir.listFiles();
            ArrayList<URL> urls = new ArrayList<URL>();
            for (File file : files) {
                if (file.getName().endsWith(".jar")) {
                    System.out.println("loading new plugin " + file.getAbsolutePath() + "...");
                    urls.add(file.toURI().toURL());
                }
            }
            int validSize = urls.size();
            System.out.println("totally load " + validSize + " plugin jars." );
            pluginClassLoader = new URLClassLoader(urls.toArray(new URL[validSize]) );
            return pluginClassLoader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ClassLoader getLoader() {
        if (pluginClassLoader == null) {
            return initLoader();
        }
        return pluginClassLoader;
    }


}
