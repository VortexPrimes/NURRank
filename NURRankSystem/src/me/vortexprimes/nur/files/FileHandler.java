package me.vortexprimes.nur.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import me.vortexprimes.nur.Main;

public class FileHandler {
	
	private File file;
	private YamlConfiguration data;
	
	public YamlConfiguration getData() { return data; }
	
	public File getFile() { return file; }
	
	
	public FileHandler(Main main) {
		this.file = new File(main.getDataFolder(), "player_data.yml");
		this.data = YamlConfiguration.loadConfiguration(file);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		save();

	}
	
	
	public void save() {
		try {
			data.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
