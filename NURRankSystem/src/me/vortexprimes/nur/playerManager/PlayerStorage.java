package me.vortexprimes.nur.playerManager;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.Effects;
import me.vortexprimes.nur.Main;
import me.vortexprimes.nur.Ranks;
import me.vortexprimes.nur.extras.convertToTier;

public class PlayerStorage {
	

	/////////////////////////////////////////////
	
	
	public static void forceLevel(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		if(i > 15) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 15);
			Main.getFileHandler().save();
			new Effects(player, 15);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".Level", i);
		Main.getFileHandler().save();
		new Effects(player, i);
	}
	
	public static void forceQuestPoints(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".QuestPoints", i);
		Main.getFileHandler().save();
	}
	
	public static void forceMobKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".MobKills", i);
		Main.getFileHandler().save();
	}
	
	public static void forcePlayerKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".PlayerKills", i);
		Main.getFileHandler().save();
	}
	
	
	
	
	
	//////////////////////////////////////////////
	
	
	public static void setLevel(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Integer value = getLevel(player);
		if(value + i > 15) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 15);
			Main.getFileHandler().save();
			new Effects(player, 15);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".Level", value + i);
		Main.getFileHandler().save();
		new Effects(player, value + i);
	}
	
	public static void setQuestPoints(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".QuestPoints", getQuestPoints(player) + i);
		Main.getFileHandler().save();
		if(canLevelUpAuto(player)) {
			new Effects(player, PlayerStorage.getLevel(player) +1);
		} else {
			if(hasPoints(player, convertToTier.toTier(PlayerStorage.getLevel(player)+ 1).getName())) {
				player.sendTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + "Rank up!", ChatColor.GOLD + "/rankup", 1, 60, 1);
			}
		}
	}
	
	public static void setMobKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".MobKills", getMobKills(player) + i);
		Main.getFileHandler().save();
		if(canLevelUpAuto(player)) {
			new Effects(player, PlayerStorage.getLevel(player) +1);
		} else {
			if(hasPoints(player, convertToTier.toTier(PlayerStorage.getLevel(player)+ 1).getName())) {
				player.sendTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + "Rank up!", ChatColor.GOLD + "/rankup", 1, 60, 1);
			}
		}
	}
	
	public static void setPlayerKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".PlayerKills", getPlayerKills(player) + i);
		Main.getFileHandler().save();
		if(canLevelUpAuto(player)) {
			new Effects(player, PlayerStorage.getLevel(player) +1);
		} else {
			if(hasPoints(player, convertToTier.toTier(PlayerStorage.getLevel(player)+ 1).getName())) {
				player.sendTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + "Rank up!", ChatColor.GOLD + "/rankup", 1, 60, 1);
			}
		}
	}
	
	//REMOVE
	
	public static void removeLevel(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		int value = getLevel(player);
		if(value - i < 0) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 1);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".Level", value - i);
		Main.getFileHandler().save();
	}
	
	public static void removeQuestPoints(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		int value = getQuestPoints(player);
		if(value - i < 0) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 0);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".QuestPoints", value - i);
		Main.getFileHandler().save();
	}
	
	public static void removeMobKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		int value = getMobKills(player);
		if(value - i < 0) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 0);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".MobKills", value - i);
		Main.getFileHandler().save();
	}
	
	public static void removePlayerKills(Player player, int i) {
		if(!containsPlayer(player)) {
			return;
		}
		int value = getPlayerKills(player);
		if(value - i < 0) {
			Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 0);
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".PlayerKills", value - i);
		Main.getFileHandler().save();
	}
	
	//GET
	public static int getLevel(Player player) {
		if(!containsPlayer(player)) {
			return 0;
		}
		int i = Main.getInstance().getInt("Players." + player.getUniqueId() + ".Level");
		return i;
	}
	
	public static int getQuestPoints(Player player) {
		if(!containsPlayer(player)) {
			return 0;
		}
		int i = Main.getInstance().getInt("Players." + player.getUniqueId() + ".QuestPoints");
		return i;
	}
	
	public static int getPlayerKills(Player player) {
		if(!containsPlayer(player)) {
			return 0;
		}
		int i = Main.getInstance().getInt("Players." + player.getUniqueId() + ".PlayerKills");
		return i;
	}
	
	public static int getMobKills(Player player) {
		if(!containsPlayer(player)) {
			return 0;
		}
		int i = Main.getInstance().getInt("Players." + player.getUniqueId() + ".MobKills");
		return i;
	}
	
	public static boolean containsPlayer(Player player) {
		if(Main.getInstance().getConfigurationSection("Players").contains(player.getUniqueId().toString())) { return true; }
		return false;
	}
	
	public static void clearLevel(Player player) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".Level", 1);
		Main.getFileHandler().save();
	}
	
	public static void clearQuestPoints(Player player) {
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".QuestPoints", 0);
		Main.getFileHandler().save();
	}
	
	public static void clearMobKills(Player player) {
		if(!containsPlayer(player)) {
			return;
		}
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".MobKills", 0);
		Main.getFileHandler().save();
	}
	
	public static void clearPlayerKills(Player player) {
		if(!containsPlayer(player)) {
			return;
		}
		if(!containsPlayer(player)) {
			return;
		}
		Main.getInstance().set("Players." + player.getUniqueId() + ".PlayerKills", 0);
		Main.getFileHandler().save();
	}
	
	public static boolean canLevelUpAuto(Player player) {
		int level = PlayerStorage.getLevel(player) + 1;
		Ranks rank = convertToTier.toTier(level);
		String name = rank.getName();
		if((rank.getName() == "S-" || rank.getName() == "A-" || rank.getName() == "B-" || rank.getName() == "C-")) {
			return false;
		} else {
			if(hasPoints(player, name)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean canLevel(Player player) {
		int level = PlayerStorage.getLevel(player) + 1;
		Ranks rank = convertToTier.toTier(level);
		String name = rank.getName();
		if((rank.getName() == "S-" || rank.getName() == "A-" || rank.getName() == "B-" || rank.getName() == "C-")) {
			if(hasPoints(player, name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	
	private static boolean hasPoints(Player player, String label) {
		if(label.contains("-")) {
			label = label.replace("-", "minus");
		} else if(label.contains("+")) {
			label = label.replace("+", "plus");
		}
		String QPs = (String) Main.getMainInstance().getConfig().get(label + "_QP");
		String PKs = (String) Main.getMainInstance().getConfig().get(label + "_PK");
		String MKs = (String) Main.getMainInstance().getConfig().get(label + "_MK");
		String Ls = (String) Main.getMainInstance().getConfig().get(label + "_L");
		int QP = Integer.parseInt(QPs);
		int PK = Integer.parseInt(PKs);
		int MK = Integer.parseInt(MKs);
		int L = Integer.parseInt(Ls);
		
		if(!(PlayerStorage.getQuestPoints(player) >= QP)) {
			return false;
		}
		if(!(PlayerStorage.getMobKills(player) >= QP)) {
			return false;
		}
		if(!(PlayerStorage.getPlayerKills(player) >= QP)) {
			return false;
		}
		if(!(PlayerStorage.getPlayerKills(player) >= QP)) {
			return false;
		}
		if(!(PlayerManager.getPlayerXP(player) >= L)) {
			return false;
		}
		return true;
	}

	
	
}
