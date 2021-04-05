package me.vortexprimes.nur;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.extras.convertToTier;
import me.vortexprimes.nur.playerManager.PlayerStorage;
import net.milkbowl.vault.economy.Economy;

public class Reward {
	
	
	
	public static void rewardPlayer(Player player) {
		if(PlayerStorage.containsPlayer(player)) {
			int value = PlayerStorage.getLevel(player);
			Ranks rank = convertToTier.toTier(value);
			String name = rank.getName();
			if(name.contains("-")) {
				name = name.replace("-", "minus");
			}
			if(name.contains("+")) {
				name = name.replace("+", "plus");
			}
			
			for(int i = 1; i < 4; i++) {
				String command = (String) Main.getMainInstance().getConfig().get(name + "_Reward_" + i);
				if(!(command == null || command.equalsIgnoreCase(""))) {
					if(command.contains("%player%")) {
						command = command.replace("%player%", player.getName());
					}
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						Bukkit.dispatchCommand(console, command);
				}
			}
			int moneycommand = Integer.parseInt(Main.getMainInstance().getConfig().getString(name + "_Reward_Money"));
			Economy economy = Main.getMainInstance().getEconomy();
			economy.depositPlayer(player, moneycommand);
			
			
		} else {
			return;
		}
	} 

}
