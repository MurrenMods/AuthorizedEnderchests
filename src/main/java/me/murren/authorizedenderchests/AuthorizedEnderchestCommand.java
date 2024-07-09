package me.murren.authorizedenderchests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AuthorizedEnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!AuthorizedEnderchests.config().ENABLE_AEC)
        {
            commandSender.sendMessage("§c/aec is disabled!");
            return true;
        }

        // Permit player to access enderchest
        if(!(commandSender instanceof Player))
        {
            commandSender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if(args.length != 2)
        {
            commandSender.sendMessage("§cUsage: /aec <permit|revoke|open> <player>");
            return true;
        }

        Player player = (Player) commandSender;
        switch(args[0])
        {
            case "permit":
                PermitPlayer(player, args);
                break;
            case "revoke":
                RevokePlayer(player, args);
                break;
            case "open":
                OpenEnderchest(player, args);
                break;
        }
        return true;
    }

    private void PermitPlayer(Player player, String[] args)
    {
        if(args.length != 2)
        {
            player.sendMessage("§cUsage: /aec permit <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target == null)
        {
            player.sendMessage("§cPlayer not found.");
            return;
        }

        Data.addAuthorizedPlayer(player.getUniqueId(), target.getUniqueId());
        player.sendMessage("§aSuccessfully permitted " + args[1] + " to access your enderchest.");
    }

    private void RevokePlayer(Player player, String[] args)
    {
        if(args.length != 2)
        {
            player.sendMessage("§cUsage: /aec revoke <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target == null)
        {
            player.sendMessage("§cPlayer not found.");
            return;
        }
        Data.removeAuthorizedPlayer(player.getUniqueId(), target.getUniqueId());
        player.sendMessage("§aSuccessfully revoked " + args[1] + "'s access to your enderchest, if they had access.");
    }

    private void OpenEnderchest(Player player, String[] args)
    {
        if(args.length != 2)
        {
            player.sendMessage("§cUsage: /aec open <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target == null)
        {
            player.sendMessage("§cPlayer not found.");
            return;
        }
        if(target.equals(player))
        {
            player.sendMessage("§cYou cannot open your own enderchest!");
            return;
        }
        if(AuthorizedEnderchests.config().NEED_EC && !player.getInventory().contains(Material.ENDER_CHEST))
        {
            player.sendMessage("§cYou need to have an Ender Chest in your inventory!");
            return;
        }

        UUID targetUUID = target.getUniqueId();
        UUID playerUUID = player.getUniqueId();
        if(Data.isAuthorized(playerUUID, targetUUID))
        {
            player.openInventory(target.getEnderChest());
        }
        else
        {
            player.sendMessage("§cYou are not authorized to access this player's enderchest.");
        }
    }
}
