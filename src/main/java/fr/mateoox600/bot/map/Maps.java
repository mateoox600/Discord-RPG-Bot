package fr.mateoox600.bot.map;

import fr.mateoox600.bot.players.ressource.Ressources;

public enum Maps {
	
	CITY_01(0, 0, "Starter City", null, 0, 0),
	FOREST_01(1, 0, "Starter Forest", Ressources.WOOD, 0, 0),
	MOUNTAIN_01(-1, 0, "Starter Mountain", Ressources.STONE, 0, 0),
	LAKE_01(0, 1, "Starter Lake", Ressources.FISH, 0, 0),
	MINE_01(-1, 1, "Starter Mine", Ressources.IRON, 0, 25),
	UNDER_LAKE_01(-1, -1, "Starter Under Lake", Ressources.COPPER, 0, 10);
	
	private final int x, y, level, requireLevel;
	private final String name;
	private final Ressources ressources;
	
	Maps(int x, int y, String name, Ressources ressources, int level, int requireLevel) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.ressources = ressources;
		this.level = level;
		this.requireLevel = requireLevel;
	}
	
	public static Maps getMapsByPostion(int x, int y) {
		for(Maps m : Maps.values())
			if(m.getX() == x && m.getY() == y)
				return m;
		return null;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public Ressources getRessources() {
		return ressources;
	}

	public int getLevel() {
		return level;
	}

	public int getRequireLevel() {
		return requireLevel;
	}
	
}
