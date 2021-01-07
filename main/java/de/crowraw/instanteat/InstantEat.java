package de.crowraw.instanteat;

import de.crowraw.instanteat.listener.FoodHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantEat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new FoodHandler(), this);
        getLogger().info("§6This plugin was made by Crowraw! Please rate it :DD");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
