package fr.mateoox600.bot.players.weapon;

import fr.mateoox600.bot.players.classe.Class;

public enum WeaponType {

    SWORD(),
    BOW(),
    WAND(),
    DAGGER();

    public static WeaponType getWeaponTypeByClass(Class c) {
        WeaponType result = null;
        if (c == Class.ARCHER || c == Class.ULTRA_ARCHER) result = WeaponType.BOW;
        else if (c == Class.MAGE || c == Class.ULTRA_MAGE) result = WeaponType.WAND;
        else if (c == Class.WARRIOR || c == Class.ULTRA_WARRIOR) result = WeaponType.SWORD;
        else if (c == Class.ASSASIN || c == Class.ULTRA_ASSASINS) result = WeaponType.DAGGER;
        return result;
    }

}
