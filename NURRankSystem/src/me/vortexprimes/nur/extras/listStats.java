package me.vortexprimes.nur.extras;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.Ranks;
import me.vortexprimes.nur.playerManager.PlayerManager;
import me.vortexprimes.nur.playerManager.PlayerStorage;

public class listStats {
	
	
	public static void statsList(Player player) {
		int l = PlayerManager.getPlayerXP(player);
		Ranks rank = convertToTier.toTier(PlayerStorage.getLevel(player));
		String name = rank.getName();
		int m = PlayerStorage.getMobKills(player);
		int p = PlayerStorage.getPlayerKills(player);
		int q = PlayerStorage.getQuestPoints(player);
		player.sendMessage(ChatColor.DARK_BLUE + "===============" + ChatColor.BLUE + "[Stats]" + ChatColor.DARK_BLUE + "===============");
		player.sendMessage(ChatColor.GREEN + "Player: " + player.getName());
		player.sendMessage(ChatColor.GREEN + "Ranking: " + name);
		player.sendMessage(ChatColor.GREEN + "Level: " + l);
		player.sendMessage(ChatColor.GREEN + "Mob Kills: " + m);
		player.sendMessage(ChatColor.GREEN + "Player Kills: " + p);
		player.sendMessage(ChatColor.GREEN + "Quest Points: " + q);
		player.sendMessage(ChatColor.DARK_BLUE + "===============" + ChatColor.BLUE + "[Stats]" + ChatColor.DARK_BLUE + "===============");
		
	}

}
