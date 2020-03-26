package fr.mateoox600.bot.players.armor;

public class Armor {
	
	public ArmorType armorType;
	
	public Armor(int armorLevel) {
		armorType = ArmorType.getArmorTypeByLevel(armorLevel);
	}
	
}
