package fr.mateoox600.bot.players;

import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.map.Map;
import fr.mateoox600.bot.map.Maps;
import fr.mateoox600.bot.players.armor.Armor;
import fr.mateoox600.bot.players.classe.Classe;
import fr.mateoox600.bot.players.ressource.Ressources;
import fr.mateoox600.bot.players.weapon.Weapon;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.io.*;

public class PlayerData {
	
	public User author;
	public File player_file;
	public int health, max_health, xp, rank, goalxp, coins, stone, iron, copper, fish, wood, lang;
	public Classe classe;
	public Weapon weapon;
	public Armor armor;
	public Map map;
	
	public PlayerData(User author, int start_class) {
		
		this.player_file = new File(Config.FILE_PREFIX + author.getId() + ".txt");
		this.author = author;
		
		if(player_file.exists()) {
			
			load();
			refresh();
			save();
			
		}else {
			
			try {
				player_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			health = 20;
			max_health = 20;
			xp = 0;
			rank = 0;
			coins = 0;
			stone = 0;
			iron = 0;
			copper = 0;
			fish = 0;
			wood = 0;
			lang = 0;
			classe = new Classe(start_class, 0);
			weapon = new Weapon(classe.c, 0);
			armor = new Armor(0);
			map = new Map(0, 0);
			save();
			refresh();
			
		}
	}
	
	public void refresh() {
		
		goalxp = (int) (((100 * ((rank + 1) * 1.1))));
		rank = (int) (xp / ((100 * ((rank + 1) * 1.1))));
		max_health = 20;
		
	}
	
	public void loop() {
		
		this.refresh();
		this.save();
		
	}
	
	public void save() {
		try {
			FileWriter fw = new FileWriter(player_file, false);
			
			fw.write(health + "\n");
			fw.write(max_health + "\n");
			fw.write(xp + "\n");
			fw.write(rank + "\n");
			fw.write(coins + "\n");
			fw.write(stone + "\n");
			fw.write(iron + "\n");
			fw.write(copper + "\n");
			fw.write(fish + "\n");
			fw.write(wood + "\n");
			fw.write(lang + "\n");
			fw.write(classe.class_id + "\n");
			fw.write(classe.rank + "\n");
			fw.write(weapon.weapon.getId() + "\n");
			fw.write(armor.armorType.getLevel() + "\n");
			fw.write(map.x + "\n");
			fw.write(map.y + "\n");
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(player_file));
			
			health = Integer.parseInt(br.readLine());
			max_health = Integer.parseInt(br.readLine());
			xp = Integer.parseInt(br.readLine());
			rank = Integer.parseInt(br.readLine());
			coins = Integer.parseInt(br.readLine());
			stone = Integer.parseInt(br.readLine());
			iron = Integer.parseInt(br.readLine());
			copper = Integer.parseInt(br.readLine());
			fish = Integer.parseInt(br.readLine());
			wood = Integer.parseInt(br.readLine());
			lang = Integer.parseInt(br.readLine());
			classe = new Classe(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
			weapon = new Weapon(Integer.parseInt(br.readLine()), classe.c);
			armor = new Armor(Integer.parseInt(br.readLine()));
			map = new Map(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
			
			
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} 
	}

	public EmbedBuilder getCharacter() {
		
		EmbedBuilder eb = new EmbedBuilder();

		eb.setTitle(author.getName() + " character");
		eb.setColor(Color.RED);
		
		eb.addField(new Field("	**" + Config.HEALTH.split("////")[lang] + "** : ", health + "/" + max_health, true));
		eb.addField(new Field("	**" + Config.LEVEL.split("////")[lang] + "** : ", rank + " [ Exp: " + xp + "]", true));
		eb.addField(new Field("	**Coins** : ", String.valueOf(coins), true));
		eb.addField(new Field("	**Ressources (1)**",
				Config.STONE.split("////")[lang] + ": " + stone + "\n" +
						Config.IRON.split("////")[lang] + ": " + iron + "\n" +
						Config.COPPER.split("////")[lang] + ": " + copper, true));
		eb.addField(new Field("	**Ressources (2)**",
						Config.FISH.split("////")[lang] + ": " + fish + "\n" +
						Config.WOOD.split("////")[lang] + ": " + wood, true));
		eb.addField(new Field("	**Classe** : ", classe.c.getName(), false));
		eb.addField(new Field("	**Classe Rank** : ", String.valueOf(classe.c.getRank()), true));
		eb.addField(new Field("	**" + Config.WEAPON.split("////")[lang] + "** : ", "*" + weapon.weapon.getName() + "*", false));
		eb.addField(new Field("	**" + Config.ARMOR.split("////")[lang] + "** : ", "*" + armor.armorType.getName() + "*", false));

		return eb;
		/*String inv = "<@" + author_id + "> : \n";
		
		inv += "	**" + Config.HEALTH.split("////")[lang] + "** : " + String.valueOf(health) + "/" + String.valueOf(max_health) + "\n";
		inv += "	**" + Config.LEVEL.split("////")[lang] + "** : " + String.valueOf(rank) + " (**Xp** : " + String.valueOf(xp) + "/" + String.valueOf(goalxp) + ")\n";
		inv += "	**Coins** : " + String.valueOf(coins) + "\n";
		inv += "	**" + Config.STONE.split("////")[lang] + "** : " + String.valueOf(stone) + "\n";
		inv += "	**" + Config.IRON.split("////")[lang] + "** : " + String.valueOf(iron) + "\n";
		inv += "	**" + Config.COPPER.split("////")[lang] + "** : " + String.valueOf(copper) + "\n";
		inv += "	**" + Config.FISH.split("////")[lang] + "** : " + String.valueOf(fish) + "\n";
		inv += "	**" + Config.WOOD.split("////")[lang] + "** : " + String.valueOf(wood) + "\n";
		inv += "	**Classe** : " + classe.c.getName() + " (**Rank** : " + String.valueOf(classe.c.getRank()) + ")\n";
		inv += "	**" + Config.WEAPON.split("////")[lang] + "** : *" + weapon.weapon.getName() + "*\n";
		inv += "	**" + Config.ARMOR.split("////")[lang] + "** : *" + armor.armorType.getName() + "*\n";
		
		return inv;*/
	}

	public String getMap() {
		
		StringBuilder map = new StringBuilder("Map \n");
		
		for(int y = 0; y < 5; y++) {
			map.append("\n");
			for(int x = 0; x < 5; x++) {
				Maps m = null;
				for(Maps c_m : Maps.values()) {
					if(c_m.getX() == -(2-x)+this.map.x && c_m.getY() == (2-y)+this.map.y) {
						m = c_m;
					}
				}
				if(m != null) {
					map.append("[").append(m.getName()).append("] ");
				}else {
					map.append("[] ");
				}
			}
		}
		
		map.append("\n\n");
		map.append(Config.YOUR_POS.split("////")[lang]).append(this.map.currentMap.getName());
		
		//map += "									 [" + Maps.LAKE_01.getName() + "]\n";
		//map += "[" + Maps.MOUNTAIN_01.getName() + "] [" + Maps.CITY_01.getName() + "] [" + Maps.FOREST_01.getName() + "] \n\n";
		//map += "Ta position est " + this.map.currentMap.getName();
		
		return map.toString();
	}
	
	public int[] gatherRessources(int numberOfTime) {
		int stone = 0;
		int iron = 0;
		int copper = 0;
		int fish = 0;
		int wood = 0;
		
		if(map.currentMap.getRessources() != null) {
			for(int i = 0; i < numberOfTime; i++) {
				int toAdd = (map.currentMap.getLevel() + 1);
				if(map.currentMap.getRessources() == Ressources.STONE) {
					stone+=toAdd;
					this.stone+=toAdd;
				} else if(map.currentMap.getRessources() == Ressources.IRON) {
					iron+=toAdd;
					this.iron+=toAdd;
				}
				else if(map.currentMap.getRessources() == Ressources.COPPER) {
					copper+=toAdd;
					this.copper+=toAdd;
				}
				else if(map.currentMap.getRessources() == Ressources.FISH) {
					fish+=toAdd;
					this.fish+=toAdd;
				}
				else if(map.currentMap.getRessources() == Ressources.WOOD) {
					wood+=toAdd;
					this.wood+=toAdd;
				}
			}
			return new int[] {stone, iron, copper, fish, wood};
		}
		return null;
	}
	
}
