package io.github.breezy23;

import io.github.breezy23.modifiers.Modifiers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public class BBORS extends JavaPlugin {
    protected static Data data;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
        data = Data.load();

        this.getCommand("bbors").setExecutor(new CommandBBORS());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
        Data.save();
    }
}
