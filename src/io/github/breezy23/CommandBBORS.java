package io.github.breezy23;

import io.github.breezy23.modifiers.Modifiers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandBBORS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HashMap<Modifiers, Boolean> modifiers = BBORS.data.mods;

        modifiers.forEach((k, v) -> {
            sender.sendMessage(k.toString()+" "+v.toString());
        });
        return true;
    }
}
