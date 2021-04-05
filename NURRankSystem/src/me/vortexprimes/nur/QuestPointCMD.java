package me.vortexprimes.nur;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.playerManager.PlayerStorage;

public class QuestPointCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(!(args.length <= 0 )) {
				if(args.length == 2) {
					Player offlineplayer = Bukkit.getPlayer(args[1]);
					if(args[0].equalsIgnoreCase("reset")) {
						if(sender.hasPermission("questpoints.manage")) {
								PlayerStorage.clearQuestPoints(player);
								player.sendMessage("You cleared " + player.getName() + "'s Quest Points.");
								return false;
						}
					} else if(args[0].equalsIgnoreCase("add")) {
							PlayerStorage.setQuestPoints(offlineplayer, 1);
							player.sendMessage("Added Quest Point " + 1 + " to " + offlineplayer.getName());
					} else if(args[0].equalsIgnoreCase("remove")) {
							PlayerStorage.removeQuestPoints(offlineplayer, Integer.parseInt(args[2]));
							player.sendMessage("Removed Quest Points " + args[2] + " from " + offlineplayer.getName());
					}
				} else if(args.length == 1){
					if(args[0].equalsIgnoreCase("help")) { 
						player.sendMessage(ChatColor.GOLD + "---------------");
						player.sendMessage(ChatColor.YELLOW + "/questpoints add (ign)");
						player.sendMessage(ChatColor.YELLOW + "/questpoints remove (ign)");
						player.sendMessage(ChatColor.YELLOW + "/questpoints clear (ign)");
					}
				} else {
					
					player.sendMessage(ChatColor.RED + "Invalid command usage!");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Invalid command usage!");

			}
		}
		return false;
	}


	
	
	
	
}
