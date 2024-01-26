package org.example;

import java.io.File;
import java.net.URL;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    /**
     * Загружает плагин с заданным именем и классом
     *
     * @param pluginName       имя плагина
     * @param pluginClassName  имя класса плагина
     * @return экземпляр плагина
     */
    public Plugin load(String pluginName, String pluginClassName) {
        try {
            File pluginDirectory = new File(pluginRootDirectory, pluginName);

            PluginClassLoader pluginClassLoader = new PluginClassLoader(
                    new URL[]{pluginDirectory.toURI().toURL()},
                    getClass().getClassLoader(),
                    pluginName
            );

            Class<?> pluginClass = pluginClassLoader.loadClass(pluginClassName);

            Object pluginObject = pluginClass.newInstance();

            if (!(pluginObject instanceof Plugin)) {
                throw new IllegalArgumentException("Plugin class must implement the Plugin interface");
            }

            return (Plugin) pluginObject;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load plugin", e);
        }
    }
}