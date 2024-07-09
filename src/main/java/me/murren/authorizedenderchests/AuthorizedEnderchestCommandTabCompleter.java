package me.murren.authorizedenderchests;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorizedEnderchestCommandTabCompleter implements TabCompleter {
    private final List<String> operations = Arrays.asList("permit", "revoke", "open");
    private final List<String> empty = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        switch(args.length)
        {
            case 1:
                return operations;
            case 2:
                return null;
        }
        return empty;
    }
}
