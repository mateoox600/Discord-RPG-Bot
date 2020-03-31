package fr.mateoox600.bot.players.classe;

public enum Class {

    WARRIOR(0, 0, "Warrior"),
    ARCHER(1, 0, "Archer"),
    MAGE(2, 0, "Mage"),
    ASSASIN(3, 0, "Assasin"),
    ULTRA_WARRIOR(0, 1, "Ultra Warrior"),
    ULTRA_ARCHER(1, 1, "Ultra Archer"),
    ULTRA_MAGE(2, 1, "Ultra Mage"),
    ULTRA_ASSASINS(3, 1, "Ultra Assasin");

    private final int id, rank;
    private final String name;

    Class(int id, int rank, String name) {

        this.id = id;
        this.rank = rank;
        this.name = name;

    }

    public static Class getClassByIdAndRank(int id, int rank) {
        Class result = null;
        for (Class c : Class.values()) {
            if (c.getId() == id && c.getRank() == rank) result = c;
        }
        return result;
    }

    public static Class getClassByName(String name) {
        Class result = null;
        for (Class c : Class.values()) {
            if (c.getName().equalsIgnoreCase(name)) result = c;
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

}
