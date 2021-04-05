package me.vortexprimes.nur;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.playerManager.PlayerManager;
import me.vortexprimes.nur.playerManager.PlayerStorage;

public class RankUpCommand implements CommandExecutor {
	
	Main main;
	
	public RankUpCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length > 0) {
				if(args.length == 1) {
					if(player.hasPermission("rankup.manage")) {
						if(args[0].equalsIgnoreCase("reset")) {
							PlayerStorage.clearLevel(player);
							player.sendMessage("You were reset!");
						} else if(args[0].equalsIgnoreCase("demote")) {
							PlayerStorage.removeLevel(player, 1);
							player.sendMessage("You were demoted to level " + PlayerStorage.getLevel(player));
						} else if(args[0].equalsIgnoreCase("help")){
							player.sendMessage(ChatColor.GOLD + "-------------------------------");
							player.sendMessage(ChatColor.YELLOW + "/rankup");
							player.sendMessage(ChatColor.YELLOW + "/rankup demote");
							player.sendMessage(ChatColor.YELLOW + "/rankup demote (player)");
							player.sendMessage(ChatColor.YELLOW + "/rankup (player) (level)");
							player.sendMessage(ChatColor.YELLOW + "/rankup reset");
							player.sendMessage(ChatColor.YELLOW + "/rankup reset (player)");
							player.sendMessage(ChatColor.GOLD + "-------------------------------");
							
						} else {
							//try {
								Player p = Bukkit.getPlayer(args[0]);
								PlayerStorage.setLevel(p, 1);	
							//} catch (StringIndexOutOfBoundsException e) {
								player.sendMessage(ChatColor.RED + "Invalid command usage!");
							//}
						}
					} else {
						if(args[0].equalsIgnoreCase("help")){
							player.sendMessage(ChatColor.GOLD + "-------------");
							player.sendMessage(ChatColor.YELLOW + "/rankup");
							player.sendMessage(ChatColor.GOLD + "-------------");

						}
					}
						
				} else if(args.length == 2) {
					if(player.hasPermission("rankup.manage")) {
						if(args[0].equalsIgnoreCase("reset")) {
							//try {
								Player p = Bukkit.getPlayer(args[1]);
								PlayerStorage.clearLevel(player);
								player.sendMessage("Cleared player " + p.getName() + "'s Level");
							//} cat
						} else if(args[0].equalsIgnoreCase("demote")){
							Player p = Bukkit.getPlayer(args[1]);
							PlayerStorage.removeLevel(p, 1);
							if(PlayerStorage.getLevel(p) <= 1) {
								PlayerStorage.forceLevel(p, 1);
							}
							player.sendMessage("Set player " + p.getName() + " to Level " + PlayerStorage.getLevel(p));
						} else {
							Player p = Bukkit.getPlayer(args[0]);
							int i = Integer.parseInt(args[1]);
							if(i > 15) {
								i = 15;
							}
							PlayerStorage.forceLevel(p, i);
							player.sendMessage("Set player " + p.getName() + " to Level " + i);
						}
					}
				}
				
			} else {
				if(!PlayerStorage.canLevel(player)) {
					player.sendMessage(ChatColor.RED + "You can't Rank Up at this time!");
					return false;
				}
				if(PlayerStorage.getLevel(player) >= 15) {
					player.sendMessage(ChatColor.RED + "You are already at the max Rank!");
					return false;
				}
				main.levelUp(player);
			}
		}
		return false;
	}
	
	
	

}
