package net.diversionmc.modding.api;

public enum ChatColor {
	RESET("§r"),
	BLACK("§0"),
	DARK_BLUE("§1"),
	DARK_GREEN("§2"),
	CYAN("§3"),
	DARK_RED("§4"),
	DARK_PURPLE("§5"),
	GOLD("§6"),
	LIGHT_GREY("§7"),
	DARK_GREY("§8"),
	BLUE("§9"),
	GREEN("§a"),
	AQUA("§b"),
	RED("§c"),
	PINK("§d"),
	YELLOW("§e"),
	WHITE("§f"),
	RANDOM("§k");
	
	private String code;
	
	private ChatColor(String code) {
		this.code = code; 
	}
	
	public static String translate(String c, String message) {
		return message.replaceAll(c, "§");
	}
	
	public String toString() {
		return code;
	}
	
	

}
