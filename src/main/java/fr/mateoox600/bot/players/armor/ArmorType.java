package fr.mateoox600.bot.players.armor;

public enum ArmorType {
	
	LEATHER(0, "Leather", 0),
	CHAINMAIL(1, "ChainMail", 50),
	METAL(2, "Metal", 100),
	CRYSTAL(3, "Crystal", 250);
	
	private final int level, min_level;
	private final String name;
	
	ArmorType(int level, String name, int min_level) {
		this.level = level;
		this.name = name;
		this.min_level = min_level;
	}

	public int getLevel() {
		return level;
	}
	
	public String getName() {
		return name;
	}

	public int getMin_level() {
		return min_level;
	}

	public static ArmorType getArmorTypeByLevel(int level) {
		ArmorType result = null;
		for(ArmorType a : ArmorType.values()) {
			if(a.getLevel() == level) result = a;
		}
		return result;
	}
	
	public static ArmorType getNextArmorByLevel(int level) {
		ArmorType result = null;
		for(ArmorType a : ArmorType.values()) {
			if(a.getLevel() == level+1) result = a;
		}
		return result;
	}
	
}
