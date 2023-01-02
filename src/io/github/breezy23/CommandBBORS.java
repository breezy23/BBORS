package io.github.breezy23;

import io.github.breezy23.modifiers.Modifiers;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandBBORS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HashMap<Modifiers, Boolean> modifiers = BBORS.data.mods;

        // Nested if hell
        if(args.length > 0) {
            Modifiers mod;
            boolean modify = args.length == 2;

            if(args[0].equalsIgnoreCase("lifelink")) {
                mod = Modifiers.LIFELINK;
            } else if(args[0].equalsIgnoreCase("lifeshare")) {
                mod = Modifiers.LIFESHARE;
            } else if(args[0].equalsIgnoreCase("invshare")) {
                mod = Modifiers.INVSHARE;
            } else{
                sender.sendMessage(ChatColor.RED+"/BBORS <lifelink|lifeshare|invshare> [true|false] ");
                return true;
            }

            if(!modify) {
                sender.sendMessage(ChatColor.RED+mod.toString()+": "+modifiers.get(mod));
            } else {
                if(args[1].equals("true")) {
                    modifiers.put(mod, true);
                    sender.sendMessage(BBORS.data.mods.get(mod).toString());
                } else if(args[1].equals("false")) {
                    modifiers.put(mod, false);
                } else {
                    sender.sendMessage(ChatColor.RED+"/BBORS "+args[0]+" [true|false]");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED+"/BBORS <lifelink|lifeshare|invshare> [true|false] ");
        }

        return true;
    }
}
