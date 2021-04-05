package me.vortexprimes.nur.playerManager;

import org.bukkit.entity.Player;

import me.vortexprimes.nur.Main;

public class PlayerManager {
	
	
	Player player;
	
	
	public PlayerManager(Player player) {
		this.player = player;
		if(!Main.getInstance().contains("Players")) {
			Main.getInstance().createSection("Players");
			Main.getFileHandler().save();
		}
		if(!Main.getInstance().contains("Players." + player.getUniqueId())) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Name", player.getName());
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 1);
			Main.getInstance().set("Players." + player.getUniqueId() + ".QuestPoints", 0);
			Main.getInstance().set("Players." + player.getUniqueId() + ".PlayerKills", 0);
			Main.getInstance().set("Players." + player.getUniqueId() + ".MobKills", 0);
			Main.getFileHandler().save();
		}
		
		
	}
	
	public static int getPlayerXP(Player player) {
		return player.getLevel();
	}

	
	
	

}
