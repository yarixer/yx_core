package org.yanix.yx_core;

import org.bukkit.plugin.java.JavaPlugin;
import org.yanix.yx_core.api.dataHandler.YamlFileHandler;

public final class Yx_core extends JavaPlugin {
    private static Yx_core instance;
    private YamlFileHandler yamlFileHandler;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        yamlFileHandler = new YamlFileHandler(getDataFolder());
        getLogger().info("Core plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Core plugin disabled!");
    }

    public static Yx_core getInstance() {
        return instance;
    }

    public YamlFileHandler getYamlFileHandler() {
        return yamlFileHandler;
    }
}
