package me.murren.authorizedenderchests;

import me.murren.authorizedenderchests.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthorizedEnderchests extends JavaPlugin {

    private static Config config;
    public static Config config() {
        return config;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        config = new Config(getConfig());

        PersistentDataHandler.loadData();

        getCommand("ec").setExecutor(new EnderchestCommand());
        getCommand("aec").setExecutor(new AuthorizedEnderchestCommand());
        getCommand("aec").setTabCompleter(new AuthorizedEnderchestCommandTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PersistentDataHandler.saveData();
    }
}
