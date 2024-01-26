package org.example;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
    private final String pluginName;

    public PluginClassLoader(URL[] urls, ClassLoader parent, String pluginName) {
        super(urls, parent);
        this.pluginName = pluginName;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith(pluginName)) {
            Class<?> loadedClass = findClass(name);
            if (resolve) {
                resolveClass(loadedClass);
            }
            return loadedClass;
        } else {
            return super.loadClass(name, resolve);
        }
    }

}