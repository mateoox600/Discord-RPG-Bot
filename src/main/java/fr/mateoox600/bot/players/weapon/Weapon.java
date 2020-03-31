package fr.mateoox600.bot.players.weapon;

import fr.mateoox600.bot.players.classe.Class;

public class Weapon {

    public WeaponType weaponType;
    public Weapons weapon;

    public Weapon(Class c, int rank) {
        weaponType = WeaponType.getWeaponTypeByClass(c);
        weapon = Weapons.getWeaponByRankAndType(rank, weaponType);
    }

    public Weapon(int id, Class c) {
        weaponType = WeaponType.getWeaponTypeByClass(c);
        weapon = Weapons.getWeaponById(id);
    }

}
