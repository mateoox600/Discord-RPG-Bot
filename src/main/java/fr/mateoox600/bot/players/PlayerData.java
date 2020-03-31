package fr.mateoox600.bot.players;

import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.map.Maps;
import fr.mateoox600.bot.players.quests.Quest;
import fr.mateoox600.bot.players.ressource.Ressources;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class PlayerData {

    public User author;
    public String map;
    public Quest quest;

    public PlayerData(User author, int start_class) {

        this.author = author;
        this.quest = new Quest();

        if (!Main.sqlManager.playerExist(author.getId())) {
            Main.sqlManager.createPlayer(author.getId(), author.getName(), start_class);
        }
        refresh();
    }

    public void refresh() {

        Main.sqlManager.setLevel(author.getId(), (int) (Main.sqlManager.getXp(author.getId()) / ((1000 * ((Main.sqlManager.getLevel(author.getId()) + 1) * 1.7)))));
        Main.sqlManager.setMaxHealth(author.getId(), 20);

    }

    public void loop() {

        this.refresh();
        quest.loop();

        if (Main.sqlManager.getFarming(author.getId())) {
            Main.sqlManager.setFarmSeconds(author.getId(), Main.sqlManager.getFarmSeconds(author.getId()) - 1);
            if (Main.sqlManager.getFarmSeconds(author.getId()) == 0) {
                if (Main.sqlManager.getMap(author.getId()).currentMap.getRessources() == Ressources.STONE) {
                    Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{Main.sqlManager.getRessourcesToClaim(author.getId())[0] + Main.sqlManager.getFarmNumber(author.getId()), Main.sqlManager.getRessourcesToClaim(author.getId())[1], Main.sqlManager.getRessourcesToClaim(author.getId())[2], Main.sqlManager.getRessourcesToClaim(author.getId())[3], Main.sqlManager.getRessourcesToClaim(author.getId())[4]});
                } else if (Main.sqlManager.getMap(author.getId()).currentMap.getRessources() == Ressources.COPPER) {
                    Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{Main.sqlManager.getRessourcesToClaim(author.getId())[0], Main.sqlManager.getRessourcesToClaim(author.getId())[1] + Main.sqlManager.getFarmNumber(author.getId()), Main.sqlManager.getRessourcesToClaim(author.getId())[2], Main.sqlManager.getRessourcesToClaim(author.getId())[3], Main.sqlManager.getRessourcesToClaim(author.getId())[4]});
                } else if (Main.sqlManager.getMap(author.getId()).currentMap.getRessources() == Ressources.IRON) {
                    Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{Main.sqlManager.getRessourcesToClaim(author.getId())[0], Main.sqlManager.getRessourcesToClaim(author.getId())[1], Main.sqlManager.getRessourcesToClaim(author.getId())[2] + Main.sqlManager.getFarmNumber(author.getId()), Main.sqlManager.getRessourcesToClaim(author.getId())[3], Main.sqlManager.getRessourcesToClaim(author.getId())[4]});
                } else if (Main.sqlManager.getMap(author.getId()).currentMap.getRessources() == Ressources.FISH) {
                    Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{Main.sqlManager.getRessourcesToClaim(author.getId())[0], Main.sqlManager.getRessourcesToClaim(author.getId())[1], Main.sqlManager.getRessourcesToClaim(author.getId())[2], Main.sqlManager.getRessourcesToClaim(author.getId())[3] + Main.sqlManager.getFarmNumber(author.getId()), Main.sqlManager.getRessourcesToClaim(author.getId())[4]});
                } else if (Main.sqlManager.getMap(author.getId()).currentMap.getRessources() == Ressources.WOOD) {
                    Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{Main.sqlManager.getRessourcesToClaim(author.getId())[0], Main.sqlManager.getRessourcesToClaim(author.getId())[1], Main.sqlManager.getRessourcesToClaim(author.getId())[2], Main.sqlManager.getRessourcesToClaim(author.getId())[3], Main.sqlManager.getRessourcesToClaim(author.getId())[4] + Main.sqlManager.getFarmNumber(author.getId())});
                }
                Main.sqlManager.setToClaim(author.getId(), true);
                Main.sqlManager.setFarming(author.getId(), false);
            }
        }

    }

    public EmbedBuilder getCharacter() {

        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(author.getName() + " character");
        eb.setColor(Color.RED);

        int lang = Main.sqlManager.getLang(author.getId());

        eb.addField(new Field("	**" + Config.HEALTH.split("////")[lang] + "** : ", Main.sqlManager.getHealth(author.getId()) + "/" + Main.sqlManager.getMaxHealth(author.getId()), true));
        eb.addField(new Field("	**" + Config.LEVEL.split("////")[lang] + "** : ", Main.sqlManager.getLevel(author.getId()) + " [ Exp: " + Main.sqlManager.getXp(author.getId()) + "]", true));
        eb.addField(new Field("	**Coins** : ", String.valueOf(Main.sqlManager.getCoins(author.getId())), true));
        eb.addField(new Field("	**Ressources (1)**",
                Config.STONE.split("////")[lang] + ": " + Main.sqlManager.getStone(author.getId()) + "\n" +
                        Config.IRON.split("////")[lang] + ": " + Main.sqlManager.getIron(author.getId()) + "\n" +
                        Config.COPPER.split("////")[lang] + ": " + Main.sqlManager.getCopper(author.getId()), true));
        eb.addField(new Field("	**Ressources (2)**",
                Config.FISH.split("////")[lang] + ": " + Main.sqlManager.getFish(author.getId()) + "\n" +
                        Config.WOOD.split("////")[lang] + ": " + Main.sqlManager.getWood(author.getId()), true));
        eb.addField(new Field("	**Classe** : ", Main.sqlManager.getClass(author.getId()).c.getName(), false));
        eb.addField(new Field("	**Classe Rank** : ", String.valueOf(Main.sqlManager.getClass(author.getId()).c.getRank()), true));
        eb.addField(new Field("	**" + Config.WEAPON.split("////")[lang] + "** : ", "*" + Main.sqlManager.getWeapon(author.getId()).weapon.getName() + "*", false));
        eb.addField(new Field("	**" + Config.ARMOR.split("////")[lang] + "** : ", "*" + Main.sqlManager.getArmor(author.getId()).armorType.getName() + "*", false));

        return eb;
    }

    public String getMap() {
        refreshMap();
        return map;
    }

    public void refreshMap() {

        StringBuilder map = new StringBuilder("Map \n");

        for (int y = 0; y < 5; y++) {
            map.append("\n");
            for (int x = 0; x < 5; x++) {
                Maps m = null;
                for (Maps c_m : Maps.values()) {
                    if (c_m.getX() == -(2 - x) + Main.sqlManager.getMap(author.getId()).x && c_m.getY() == (2 - y) + Main.sqlManager.getMap(author.getId()).y) {
                        m = c_m;
                    }
                }
                if (m != null) {
                    map.append("[").append(m.getName()).append("] ");
                } else {
                    map.append("[] ");
                }
            }
        }

        map.append("\n\n");
        map.append(Config.YOUR_POS.split("////")[Main.sqlManager.getLang(author.getId())]).append(Main.sqlManager.getMap(author.getId()).currentMap.getName());

        this.map = map.toString();
    }

    public void startFarming(int time, int number) {
        Main.sqlManager.setFarming(author.getId(), true);
        Main.sqlManager.setFarmSeconds(author.getId(), time);
        Main.sqlManager.setFarmNumber(author.getId(), number);
    }

    public int[] claim_resources() {
        int[] save = Main.sqlManager.getRessourcesToClaim(author.getId());
        Main.sqlManager.setStone(author.getId(), Main.sqlManager.getRessourcesToClaim(author.getId())[0] + Main.sqlManager.getStone(author.getId()));
        Main.sqlManager.setCopper(author.getId(), Main.sqlManager.getRessourcesToClaim(author.getId())[1] + Main.sqlManager.getCopper(author.getId()));
        Main.sqlManager.setIron(author.getId(), Main.sqlManager.getRessourcesToClaim(author.getId())[2] + Main.sqlManager.getIron(author.getId()));
        Main.sqlManager.setFish(author.getId(), Main.sqlManager.getRessourcesToClaim(author.getId())[3] + Main.sqlManager.getFish(author.getId()));
        Main.sqlManager.setWood(author.getId(), Main.sqlManager.getRessourcesToClaim(author.getId())[4] + Main.sqlManager.getWood(author.getId()));
        Main.sqlManager.setRessourcesToClaim(author.getId(), new int[]{0, 0, 0, 0, 0});
        return save;
    }

    public void startQuest(){
        quest.createQuest(Main.sqlManager.getLevel(author.getId()));
    }

}
