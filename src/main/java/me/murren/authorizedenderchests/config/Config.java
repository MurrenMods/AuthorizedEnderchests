package me.murren.authorizedenderchests.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public boolean ENABLE_EC;
    public boolean ENABLE_AEC;
    public boolean NEED_EC;

    public Config(FileConfiguration config)
    {
        ENABLE_EC = config.getBoolean("enable-ec");
        ENABLE_AEC = config.getBoolean("enable-aec");
        NEED_EC = config.getBoolean("needs-enderchest");
    }
}
