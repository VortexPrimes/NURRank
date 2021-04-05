package me.vortexprimes.nur;

import org.bukkit.entity.Player;

import me.vortexprimes.nur.playerManager.PlayerStorage;

public class LevelUp {
	
	public static void Demote(Player player) {
		PlayerStorage.removeLevel(player, 1);
	}

}
