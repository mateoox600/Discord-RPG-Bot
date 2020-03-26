package fr.mateoox600.bot.map;

public class Map {
	
	public Maps currentMap;
	public int x, y;
	
	public Map(int x, int y) {
		currentMap = Maps.getMapsByPostion(x, y);
		this.x = x;
		this.y = y;
	}
	
	public String move(String dir, int level) {
		if(dir.equalsIgnoreCase("north") || dir.equalsIgnoreCase("nord") || dir.equalsIgnoreCase("n")) {
			Maps nextMap = Maps.getMapsByPostion(x, y+1);
			if(nextMap != null) {
				if(nextMap.getRequireLevel() > level) return "notLevel"+ nextMap.getRequireLevel();
				currentMap = nextMap;
				y++;
				return "true";
			}
		}else if(dir.equalsIgnoreCase("south") || dir.equalsIgnoreCase("sud") || dir.equalsIgnoreCase("s")) {
			Maps nextMap = Maps.getMapsByPostion(x, y-1);
			if(nextMap != null) {
				if(nextMap.getRequireLevel() > level) return "notLevel"+ nextMap.getRequireLevel();
				currentMap = nextMap;
				y--;
				return "true";
			}
		}else if(dir.equalsIgnoreCase("east") || dir.equalsIgnoreCase("e")) {
			Maps nextMap = Maps.getMapsByPostion(x+1, y);
			if(nextMap != null) {
				if(nextMap.getRequireLevel() > level) return "notLevel"+ nextMap.getRequireLevel();
				currentMap = nextMap;
				x++;
				return "true";
			}
		}else if(dir.equalsIgnoreCase("west") || dir.equalsIgnoreCase("ouest") || dir.equalsIgnoreCase("w")) {
			Maps nextMap = Maps.getMapsByPostion(x-1, y);
			if(nextMap != null) {
				if(nextMap.getRequireLevel() > level) return "notLevel"+ nextMap.getRequireLevel();
				currentMap = nextMap;
				x--;
				return "true";
			}
		}
		return "false";
	}
	
}
