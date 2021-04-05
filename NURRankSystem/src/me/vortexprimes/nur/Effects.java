package me.vortexprimes.nur;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.extras.convertToTier;
import me.vortexprimes.nur.playerManager.PlayerStorage;

public class Effects {
	
	
	public Effects(Player player, Integer level) {
		Location loc = player.getLocation();
		Ranks rank = convertToTier.toTier(level);
		if(level > 15) {
			level = 15;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".Level", level);
		if(PlayerStorage.getLevel(player) > 15) {
			return;
		}
		loc.getWorld().playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
		loc.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 30);
		Reward.rewardPlayer(player);
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.DARK_PURPLE + "Congratulations to " + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " for reaching " + ChatColor.YELLOW + "Tier: " + rank.getColor() + rank.getName());
		}
		
	}
}
	
