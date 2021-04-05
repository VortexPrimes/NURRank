package me.vortexprimes.nur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vortexprimes.nur.extras.listStats;
import me.vortexprimes.nur.playerManager.PlayerStorage;

public class RankStatsCMD implements CommandExecutor {
	

	HashMap<String,Integer> qp = new HashMap<String,Integer>();
	HashMap<String,Integer> mk = new HashMap<String,Integer>();
	HashMap<String,Integer> pk = new HashMap<String,Integer>();
	
	
	TreeMap<String, Integer> topqp = new TreeMap<String, Integer>();
	TreeMap<String, Integer> topmk = new TreeMap<String, Integer>();
	TreeMap<String, Integer> toppk = new TreeMap<String, Integer>();
	
	@Override
	public boolean onCommand( CommandSender sender,  Command cmd,  String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length > 0) {
				if(!player.hasPermission("rankstats.manage")) {
					return false;
					
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.GOLD + "-------------------------------");
						player.sendMessage(ChatColor.YELLOW + "/rankstats");
						player.sendMessage(ChatColor.YELLOW + "/rankstats level (add/remove) (#) (player)");
						player.sendMessage(ChatColor.YELLOW + "/rankstats playerkills (add/remove) (#) (player)");
						player.sendMessage(ChatColor.YELLOW + "/rankstats mobkills (add/remove) (#) (player)");
						player.sendMessage(ChatColor.YELLOW + "/rankstats playerkills reset (player)");
						player.sendMessage(ChatColor.YELLOW + "/rankstats mobkills reset (player)");
						player.sendMessage(ChatColor.YELLOW + "/rankstats reset (player)");
						player.sendMessage(ChatColor.GOLD + "-------------------------------");
					} else if(args[0].equalsIgnoreCase("wipeName")){
						player.setDisplayName(player.getName());
					} else {
						Player offlineplayer = Bukkit.getPlayer(args[0]);
						if(offlineplayer != null) {
							listStats.statsList(player);
						} else {
							player.sendMessage(ChatColor.RED + "Player does not exist!");
						
						}
						
					}
				} else if(args.length == 4) {
					if(!isInt(args[2])) {
						player.sendMessage(ChatColor.RED + "Insufficent Usage!");
						return false;
					}
					Player offlineplayer = Bukkit.getPlayer(args[3]);
					if (args[0].equalsIgnoreCase("playerkills") || args[0].equalsIgnoreCase("pk")){
						if(args[1].equalsIgnoreCase("add")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.setPlayerKills(offlineplayer, Integer.parseInt(args[2]));
								player.sendMessage("Add Player Kills " + args[2] + " to " + offlineplayer.getName());
							}
							
						} else if(args[1].equalsIgnoreCase("remove")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.removePlayerKills(offlineplayer, Integer.parseInt(args[2]));
								player.sendMessage("Removed Player Kills " + args[2] + " from " + offlineplayer.getName());
							}
						}
						
					} else if (args[0].equalsIgnoreCase("mobkills") || args[0].equalsIgnoreCase("mk")){
						if(args[1].equalsIgnoreCase("add")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.setMobKills(offlineplayer, Integer.parseInt(args[2]));
								player.sendMessage("Added Mob Kills " + args[2] + " to " + offlineplayer.getName());
							}
						} else if(args[1].equalsIgnoreCase("remove")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.removeMobKills(offlineplayer, Integer.parseInt(args[2]));
								player.sendMessage("Removed Mob Kills " + args[2] + " from " + offlineplayer.getName());
							}
						}
						
					} else if (args[0].equalsIgnoreCase("level") || args[0].equalsIgnoreCase("l")) {
						if(args[1].equalsIgnoreCase("add")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.setLevel(offlineplayer, Integer.parseInt(args[2]));
								player.sendMessage("Added level " + args[2] + " to " + offlineplayer.getName());
							}
						} else if(args[1].equalsIgnoreCase("remove")) {
							if(args[2] != null && offlineplayer != null) {
								PlayerStorage.removeLevel(offlineplayer, Integer.parseInt(args[2]));
							}
						}
						
					} else {
						player.sendMessage(ChatColor.RED + "Invalid Command Usage!");
					}
					
				} else if(args.length == 3) {
					Player offlineplayer = Bukkit.getPlayer(args[2]);
					if (args[0].equalsIgnoreCase("playerkills") || args[0].equalsIgnoreCase("pk")){
						if(args[1].equalsIgnoreCase("reset")) {
							PlayerStorage.clearPlayerKills(offlineplayer);
							player.sendMessage("You cleared " + offlineplayer.getName() + "'s Player Kills.");
						}
					} else if (args[0].equalsIgnoreCase("mobkills") || args[0].equalsIgnoreCase("mk")){
						if(args[1].equalsIgnoreCase("reset")) {
							PlayerStorage.clearMobKills(offlineplayer);
							player.sendMessage("You cleared " + offlineplayer.getName() + "'s Mob Kills.");
						}
					} else if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("l")){
						if(args[1].equalsIgnoreCase("clear")) {
							PlayerStorage.clearLevel(offlineplayer);
							player.sendMessage("You cleared " + offlineplayer.getName() + "'s Levels.");
						}
					} else if(args[1].equalsIgnoreCase("reset")) {
						PlayerStorage.clearLevel(offlineplayer);
						PlayerStorage.clearMobKills(offlineplayer);
						PlayerStorage.clearPlayerKills(offlineplayer);
						PlayerStorage.clearQuestPoints(offlineplayer);
						offlineplayer.setLevel(0);
					} else {
						player.sendMessage(ChatColor.RED + "Invalid Command Usage!");
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("top")) {
						for(String p : Main.getInstance().getConfigurationSection("Players").getKeys(false)) {
							String name = Main.getInstance().getString("Players." + player.getUniqueId() + ".Name", player.getName());
							String qps = Main.getInstance().getString("Players." + p + ".QuestPoints");
							String mks = Main.getInstance().getString("Players." + p + ".MobKills");
							String pks = Main.getInstance().getString("Players." + p + ".PlayerKills");
							qp.put(name, Integer.parseInt(qps));
							mk.put(name, Integer.parseInt(mks));
							pk.put(name, Integer.parseInt(pks));
						}
						qp = (HashMap<String, Integer>) sortByValue(qp); 
						mk = (HashMap<String, Integer>) sortByValue(mk); 
						pk = (HashMap<String, Integer>) sortByValue(pk); 						
						
						if(args[1].equalsIgnoreCase("questpoints")) {
							String nextTop = "";
							Integer nextTopKills = 0;
							player.sendMessage(ChatColor.GOLD + "--------[Quest Points]--------");
							for(int i = 1; i < 6; i++){
								for(String playerName: qp.keySet()){
								if(qp.get(playerName) > nextTopKills){
								nextTop = playerName;
								nextTopKills = qp.get(playerName);
								}
								}
								sender.sendMessage(ChatColor.DARK_GREEN + "" + i + ". " + ChatColor.GREEN + nextTop + ": " + ChatColor.YELLOW + nextTopKills);
								qp.remove(nextTop);
								nextTop = "?";
								nextTopKills = 0;
								}
							player.sendMessage(ChatColor.GOLD + "--------------------");
							
						} else if(args[1].equalsIgnoreCase("playerkills")) {
							String nextTop = "";
							Integer nextTopKills = 0;
							player.sendMessage(ChatColor.GOLD + "--------[Player Kills]--------");
							for(int i = 1; i < 6; i++){
								for(String playerName: pk.keySet()){
								if(pk.get(playerName) > nextTopKills){
								nextTop = playerName;
								nextTopKills = pk.get(playerName);
								}
								}
								sender.sendMessage(ChatColor.DARK_GREEN + "" + i + ". " + ChatColor.GREEN + nextTop + ": " + ChatColor.YELLOW + nextTopKills);
								qp.remove(nextTop);
								nextTop = "?";
								nextTopKills = 0;
								}
							player.sendMessage(ChatColor.GOLD + "--------[Mob Kills]--------");
						} else if(args[1].equalsIgnoreCase("mobkills")) {
							String nextTop = "";
							Integer nextTopKills = 0;
							player.sendMessage(ChatColor.GOLD + "--------------------");
							for(int i = 1; i < 6; i++){
								for(String playerName: mk.keySet()){
								if(mk.get(playerName) > nextTopKills){
								nextTop = playerName;
								nextTopKills = mk.get(playerName);
								}
								}
								sender.sendMessage(ChatColor.DARK_GREEN + "" + i + ". " + ChatColor.GREEN + nextTop + ": " + ChatColor.YELLOW + nextTopKills);
								qp.remove(nextTop);
								nextTop = "?";
								nextTopKills = 0;
								}
							player.sendMessage(ChatColor.GOLD + "--------------------");
							
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "Invalid Command Usage!");
				}
				
			} else {
				listStats.statsList(player);
				
			}
		}
		
		
		return false;
	}
	
	public boolean isInt(String string) {
		try {
			Integer.parseInt(string);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
	
	

}
