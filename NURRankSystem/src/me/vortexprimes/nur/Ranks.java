package me.vortexprimes.nur;

import org.bukkit.ChatColor;

public enum Ranks {
	
	
	Dminus("D-", ChatColor.DARK_GREEN, 1), //1
	D("D", ChatColor.DARK_GREEN, 2), // 2
	Dplus("D+", ChatColor.DARK_GREEN, 3), // 3
	Cminus("C-", ChatColor.GREEN, 4), // 4
	C("C", ChatColor.GREEN, 5), // 5
	Cplus("C+", ChatColor.GREEN, 6), // 6
	Bminus("B-", ChatColor.YELLOW, 7), // 7
	B("B", ChatColor.YELLOW, 8), // 8
	Bplus("B+", ChatColor.YELLOW, 9), // 9
	Aminus("A-", ChatColor.GOLD, 10), // 10
	A("A", ChatColor.GOLD, 11), // 11
	Aplus("A+", ChatColor.GOLD, 12), // 12
	Sminus("S-", ChatColor.RED, 13), // 13
	S("S", ChatColor.RED, 14), // 14
	Splus("S+", ChatColor.RED, 15); // 15
	
	String Name;
	ChatColor Color;
	int ID;
	
	
	private Ranks(String Name, ChatColor Color, int ID) {
		this.Name = Name;
		this.Color = Color;
		this.ID = ID;
	}
	
	public String getName() { return Name; }
	public ChatColor getColor() { return Color; }
	public int getID() { return ID; }

}
