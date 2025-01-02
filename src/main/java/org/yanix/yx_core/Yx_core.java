package org.yanix.yx_core;

import org.bukkit.plugin.java.JavaPlugin;
import org.yanix.yx_core.api.testCl;

public final class Yx_core extends JavaPlugin {

    public testCl testcl;

    @Override
    public void onEnable() {
        testcl = new testCl();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
