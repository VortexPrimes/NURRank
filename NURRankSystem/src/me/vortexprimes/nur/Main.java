package me.vortexprimes.nur;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.vortexprimes.nur.extras.convertToTier;
import me.vortexprimes.nur.files.FileHandler;
import me.vortexprimes.nur.playerManager.PlayerManager;
import me.vortexprimes.nur.playerManager.PlayerStorage;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {
	
	
	static FileHandler filehandler;
	
	private static Main instance;
	
	FileConfiguration config = getConfig();
	
    private Economy econ;
    private Permission perms;
    private Chat chat;
    
    private static Scoreboard sb;
	
	
    @Override
	public void onEnable() {
    	instance = this;
    	this.getServer().getPluginManager().registerEvents(this, this);
    	Bukkit.getPluginCommand("rankstats").setExecutor(new RankStatsCMD());
    	Bukkit.getPluginCommand("questpoints").setExecutor(new QuestPointCMD());
        Bukkit.getPluginCommand("rankup").setExecutor(new RankUpCommand(this));
		FileHandler file = new FileHandler(this);
		filehandler = file;
		file.save();
		if(!file.getData().contains("Players")) {
			file.getData().createSection("Players");
		}
		config.addDefault("D_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("D_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("D_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("D_Reward_Money", "10");
		
		config.addDefault("Dplus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Dplus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Dplus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Dplus_Reward_Money", "10");
		
		config.addDefault("Cminus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Cminus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Cminus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Cminus_Reward_Money", "10");
		
		config.addDefault("C_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("C_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("C_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("C_Reward_Money", "10");
		
		config.addDefault("Cplus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Cplus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Cplus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Cplus_Reward_Money", "10");
		
		config.addDefault("Bminus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Bminus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Bminus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Bminus_Reward_Money", "10");
		
		config.addDefault("B_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("B_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("B_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("B_Reward_Money", "10");
		
		config.addDefault("Bplus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Bplus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Bplus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Bplus_Reward_Money", "10");
		
		config.addDefault("Aminus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Aminus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Aminus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Aminus_Reward_Money", "10");
		
		config.addDefault("A_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("A_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("A_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("A_Reward_Money", "10");
		
		config.addDefault("Aplus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Aplus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Aplus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Aplus_Reward_Money", "10");
		
		config.addDefault("Sminus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Sminus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Sminus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Sminus_Reward_Money", "10");
		
		config.addDefault("S_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("S_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("S_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("S_Reward_Money", "10");
		
		config.addDefault("Splus_Reward_1", "give %player% minecraft:diamond 1");
		config.addDefault("Splus_Reward_2", "give %player% minecraft:diamond 1");
		config.addDefault("Splus_Reward_3", "give %player% minecraft:diamond 1");
		config.addDefault("Splus_Reward_Money", "10");
		
		
		config.addDefault("Dminus_QP", "0");
		config.addDefault("Dminus_PK", "0");
		config.addDefault("Dminus_MK", "0");
		config.addDefault("Dminus_L", "0");
		
		config.addDefault("D_QP", "5");
		config.addDefault("D_PK", "5");
		config.addDefault("D_MK", "5");
		config.addDefault("D_L", "5");
		
		config.addDefault("Dplus_QP", "10");
		config.addDefault("Dplus_PK", "10");
		config.addDefault("Dplus_MK", "10");
		config.addDefault("Dplus_L", "10");
		
		config.addDefault("Cminus_QP", "15");
		config.addDefault("Cminus_PK", "15");
		config.addDefault("Cminus_MK", "15");
		config.addDefault("Cminus_L", "15");
		
		config.addDefault("C_QP", "20");
		config.addDefault("C_PK", "20");
		config.addDefault("C_MK", "20");
		config.addDefault("C_L", "20");
		
		config.addDefault("Cplus_QP", "25");
		config.addDefault("Cplus_PK", "25");
		config.addDefault("Cplus_MK", "25");
		config.addDefault("Cplus_L", "25");
		
		config.addDefault("Bminus_QP", "30");
		config.addDefault("Bminus_PK", "30");
		config.addDefault("Bminus_MK", "30");
		config.addDefault("Bminus_L", "30");
		
		config.addDefault("B_QP", "35");
		config.addDefault("B_PK", "35");
		config.addDefault("B_MK", "35");
		config.addDefault("B_L", "35");
		
		config.addDefault("Bplus_QP", "40");
		config.addDefault("Bplus_PK", "40");
		config.addDefault("Bplus_MK", "40");
		config.addDefault("Bplus_L", "40");
		
		config.addDefault("Aminus_QP", "45");
		config.addDefault("Aminus_PK", "45");
		config.addDefault("Aminus_MK", "45");
		config.addDefault("Aminus_L", "45");
		
		config.addDefault("A_QP", "50");
		config.addDefault("A_PK", "50");
		config.addDefault("A_MK", "50");
		config.addDefault("A_L", "50");
		
		config.addDefault("Aplus_QP", "55");
		config.addDefault("Aplus_PK", "55");
		config.addDefault("Aplus_MK", "55");
		config.addDefault("Aplus_L", "55");
		
		config.addDefault("Sminus_QP", "60");
		config.addDefault("Sminus_PK", "60");
		config.addDefault("Sminus_MK", "60");
		config.addDefault("Sminus_L", "60");
		
		config.addDefault("S_QP", "65");
		config.addDefault("S_PK", "65");
		config.addDefault("S_MK", "65");
		config.addDefault("S_L", "65");
		
		config.addDefault("Splus_QP", "70");
		config.addDefault("Splus_PK", "70");
		config.addDefault("Splus_MK", "70");
		config.addDefault("Splus_L", "70");
		
		config.options().copyDefaults(true);
		saveConfig();
		
		 if (!setupEconomy()) {
	            this.getLogger().severe("Disabled due to no Vault dependency found!");
	            return;
	        }
	        this.setupPermissions();
	        this.setupChat();
		
		
		
	}
    @Override
	public void onDisable() {
		
	}
    
    
	public void levelUp(Player player) {
		if(PlayerStorage.containsPlayer(player)) {
			int level = PlayerStorage.getLevel(player) +1;
			Ranks rank = convertToTier.toTier(level);
			String label = rank.getName();
			if(rank.getName().contains("-")) {
				label = rank.getName().replace("-", "minus");
			} else if(rank.getName().contains("+")) {
				label = rank.getName().replace("+", "plus");
			} else {
				label = rank.getName();
			}
			//get players level CHECK. Check if they have the requirements in the config. if they do add 1 to level. By setting new Level!
			String QPs = (String) getConfig().get(label + "_QP");
			String PKs = (String) getConfig().get(label + "_PK");
			String MKs = (String) getConfig().get(label + "_MK");
			int QP = Integer.parseInt(QPs);
			int PK = Integer.parseInt(PKs);
			int MK = Integer.parseInt(MKs);
			
			if(!(PlayerStorage.getQuestPoints(player) >= QP)) {
				player.sendMessage(ChatColor.RED + "You do not have the required points to Rank Up!");
				return;
			}
			
			if(!(PlayerStorage.getPlayerKills(player) >= PK)) {
				player.sendMessage(ChatColor.RED + "You do not have the required points to Rank Up!");
				return;
			}
			
			if(!(PlayerStorage.getMobKills(player) >= MK)) {
				player.sendMessage(ChatColor.RED + "You do not have the required points to Rank Up!");
				return;
			}
			
			PlayerStorage.setLevel(player, 1);
			
		} else {
			player.sendMessage(ChatColor.RED + "You cannot Rank Up!");
		}
	}
	
	
	
	// /rankstats
	// /rankstats (ign to get players)
	// /rankstats questpoints add (int) (player)
	// /rankstats questpoints remove (int) (player)
	// /rankstats questpoints reset (player)
	// /rankstats playerkills add (int) (player)
	// /rankstats playerkills remove (int) (player)
	// /rankstats playerkills reset (player)
	// /rankstats mobkills add (int) (player)
	// /rankstats mobkills remove (int) (player)
	// /rankstats mobkills reset (player)
	
	
	// /rankup
	// /rankup demote
	// /rankup (player)
	// /rankup (player) (level)
	// /rankup reset (player)
	// /rankup reset
    
    public static YamlConfiguration getInstance() {
    	return filehandler.getData();
    }
    
    public static File getInstanceFile() {
    	return filehandler.getFile();
    }
    
    public static FileHandler getFileHandler() {
    	return filehandler;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	Player player = e.getPlayer();
    	new PlayerManager(player); 
    }
    
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
    	if(!(e.getEntity().getKiller() instanceof Player)) {
    		return;
    	}
    	if(PlayerStorage.containsPlayer(e.getEntity().getKiller())){
    		PlayerStorage.setPlayerKills(e.getEntity().getKiller(), 1);
    	}
    }
    
    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
    	if(e.getEntity() instanceof Player) {
    		return;
    	}
    	if(e.getEntity().getKiller() instanceof Player ) {
    		if(PlayerStorage.containsPlayer(e.getEntity().getKiller())){
    			PlayerStorage.setMobKills(e.getEntity().getKiller(), 1);
    		}
    	}
    }
    
    public static Main getMainInstance() {
    	return instance;
    }
    
    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
      Player player = e.getPlayer();
      player.setDisplayName(String.valueOf(getPrefix(player)) + ChatColor.RESET + player.getDisplayName());
    }
    
    public static String getPrefix(Player player) {
    	if(PlayerStorage.containsPlayer(player)) {
    		int level = PlayerStorage.getLevel(player);
			Ranks rank = convertToTier.toTier(level);
			String prefix = rank.getColor() + "[" + rank.getName() + "] " + ChatColor.RESET;
			return prefix;
    	} else {
    		return "";
    	}
    }
    
    //Vault
    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public Permission getPermissions() {
        return perms;
    }

    public Chat getChat() {
        return chat;
    }
    
  
	
	
	
}
