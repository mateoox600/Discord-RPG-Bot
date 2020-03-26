package fr.mateoox600.bot.players.weapon;

public enum Weapons {
	
	ST_BOW(0, 0, WeaponType.BOW, 2, 4, "Starter Bow", 0),
	ST_WAND(1, 0, WeaponType.WAND, 2, 4, "Starter Wand", 0),
	ST_SWORD(2, 0, WeaponType.SWORD, 2, 4, "Starter Sword", 0),
	ST_DAGGER(3, 0, WeaponType.DAGGER, 2, 4, "Starter Dagger", 0);
	
	private final int id, rank, d_min, d_max, min_level;
	private final WeaponType type;
	private final String name;
	
	Weapons(int id, int rank, WeaponType type, int d_min, int d_max, String name, int min_level) {
		this.id = id;
		this.rank = rank;
		this.type = type;
		this.d_min = d_min;
		this.d_max = d_max;
		this.name = name;
		this.min_level = min_level;
	}
	
	public static Weapons getWeaponByRankAndType(int rank, WeaponType type) {
		for(Weapons w : Weapons.values()) {
			if(w.getRank() == rank && type == w.getType()) return w;
		}
		return null;
	}
	
	public static Weapons getWeaponById(int id) {
		for(Weapons w : Weapons.values()) {
			if(w.getId() == id) return w;
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getD_min() {
		return d_min;
	}
	
	public int getD_max() {
		return d_max;
	}
	
	public WeaponType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getMin_level() {
		return min_level;
	}
	
}
