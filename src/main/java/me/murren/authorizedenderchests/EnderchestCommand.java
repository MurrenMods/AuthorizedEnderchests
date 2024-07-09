package me.murren.authorizedenderchests;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements org.bukkit.command.CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!AuthorizedEnderchests.config().ENABLE_EC)
        {
            commandSender.sendMessage("§c/ec is disabled!");
            return false;
        }

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(AuthorizedEnderchests.config().NEED_EC && !player.getInventory().contains(Material.ENDER_CHEST))
            {
                player.sendMessage("§cYou need to have an Ender Chest in your inventory!");
                return true;
            }

            player.openInventory(player.getEnderChest());
            player.setStatistic(Statistic.ENDERCHEST_OPENED, player.getStatistic(org.bukkit.Statistic.ENDERCHEST_OPENED) + 1);
        }
        else
        {
            commandSender.sendMessage("§cOnly players can use this command!");
        }
        return false;
    }
}
