package fr.mateoox600.bot;

import fr.mateoox600.bot.map.Map;
import fr.mateoox600.bot.players.armor.Armor;
import fr.mateoox600.bot.players.classe.Classe;
import fr.mateoox600.bot.players.weapon.Weapon;

import java.sql.*;

public class SqlManager {

    private java.sql.Connection connection;
    private String url_base, host, database, user, pass;

    public SqlManager(String url_base, String host, String database, String user, String pass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        this.url_base = url_base;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
        DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
    }

    public void connection() throws SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection(url_base + "//" + host + "/" + database, user, pass);
        }
    }

    public void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public boolean playerExist(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT * FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            return rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return false;
    }

    public void createPlayer(String id, String name, int start_class) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("INSERT INTO rpg_bot (id, name, health, max_health, level, xp, coins, stone, iron, copper, fish, wood, farming, to_claim, ressources_to_claim_stone, ressources_to_claim_iron, ressources_to_claim_copper, ressources_to_claim_fish, ressources_to_claim_wood, farm_number, farm_seconds, lang, classe_id, classe_rank, weapon_id, armor_rank, map_x, map_y) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setInt(3, 20);
            stm.setInt(4, 20);
            stm.setInt(5, 0);
            stm.setInt(6, 0);
            stm.setInt(7, 0);
            stm.setInt(8, 0);
            stm.setInt(9, 0);
            stm.setInt(10, 0);
            stm.setInt(11, 0);
            stm.setInt(12, 0);
            stm.setBoolean(13, false);
            stm.setBoolean(14, false);
            stm.setInt(15, 0);
            stm.setInt(16, 0);
            stm.setInt(17, 0);
            stm.setInt(18, 0);
            stm.setInt(19, 0);
            stm.setInt(20, 0);
            stm.setInt(21, 0);
            stm.setInt(22, 0);
            stm.setInt(23, start_class);
            stm.setInt(24, 0);
            stm.setInt(25, new Weapon(new Classe(start_class, 0).c, 0).weapon.getId());
            stm.setInt(26, 0);
            stm.setInt(27, 0);
            stm.setInt(28, 0);
            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }

    public int getHealth(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT health FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("health");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setHealth(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET health = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getMaxHealth(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT max_health FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("max_health");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setMaxHealth(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET max_health = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getLevel(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT level FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("level");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setLevel(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET level = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getXp(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT xp FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("xp");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setXp(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET xp = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getCoins(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT coins FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("coins");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setCoins(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET coins = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getStone(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT stone FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("stone");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setStone(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET stone = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getIron(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT iron FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("iron");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setIron(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET iron = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getCopper(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT copper FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("copper");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setCopper(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET copper = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getFish(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT fish FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("fish");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setFish(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET fish = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getWood(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT wood FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("wood");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setWood(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET wood = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public boolean getFarming(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT farming FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            boolean result = false;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getBoolean("farming");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return false;
    }

    public void setFarming(String id, boolean value) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET farming = ? WHERE id = ?");

            stm.setBoolean(1, value);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public boolean getToClaim(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT to_claim FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            boolean result = false;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getBoolean("farming");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return false;
    }

    public void setToClaim(String id, boolean value) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET to_claim = ? WHERE id = ?");

            stm.setBoolean(1, value);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int[] getRessourcesToClaim(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT * FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            //rs.getInt("ressources_to_claim_stone"), rs.getInt("ressources_to_claim_copper"), rs.getInt("ressources_to_claim_iron"), rs.getInt("ressources_to_claim_fish"), rs.getInt("ressources_to_claim_wood")
            ResultSet rs = stm.executeQuery();
            int i1 = 0;
            while(rs.next()){
                i1 = rs.getInt("ressources_to_claim_stone");
            }
            int i2 = 0;
            while(rs.next()){
                i2 = rs.getInt("ressources_to_claim_copper");
            }
            int i3 = 0;
            while(rs.next()){
                i3 = rs.getInt("ressources_to_claim_iron");
            }
            int i4 = 0;
            while(rs.next()){
                i4 = rs.getInt("ressources_to_claim_fish");
            }
            int i5 = 0;
            while(rs.next()){
                i5 = rs.getInt("ressources_to_claim_wood");
            }
            stm.close();
            return new int[]{i1, i2, i3, i4, i5};
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return new int[]{0, 0, 0, 0, 0};
    }

    public void setRessourcesToClaim(String id, int[] value) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET ressources_to_claim_stone = ? WHERE id = ?");
            stm.setInt(1, value[0]);
            stm.setString(2, id);
            stm.executeUpdate();

            stm = connection.prepareStatement("UPDATE rpg_bot SET ressources_to_claim_copper = ? WHERE id = ?");
            stm.setInt(1, value[1]);
            stm.setString(2, id);
            stm.executeUpdate();

            stm = connection.prepareStatement("UPDATE rpg_bot SET ressources_to_claim_iron = ? WHERE id = ?");
            stm.setInt(1, value[2]);
            stm.setString(2, id);
            stm.executeUpdate();

            stm = connection.prepareStatement("UPDATE rpg_bot SET ressources_to_claim_fish = ? WHERE id = ?");
            stm.setInt(1, value[3]);
            stm.setString(2, id);
            stm.executeUpdate();

            stm = connection.prepareStatement("UPDATE rpg_bot SET ressources_to_claim_wood = ? WHERE id = ?");
            stm.setInt(1, value[4]);
            stm.setString(2, id);
            stm.executeUpdate();

            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getFarmNumber(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT farm_number FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("farm_number");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setFarmNumber(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET farm_number = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getFarmSeconds(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT farm_seconds FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("farm_seconds");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setFarmSeconds(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET farm_seconds = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public int getLang(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT lang FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            int result = 0;
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result = rs.getInt("lang");
            }
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return 0;
    }

    public void setLang(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET lang = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public Classe getClass(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT * FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            int classe_id = 0;
            while(rs.next()){
                classe_id = rs.getInt("classe_id");
            }
            int classe_rank = 0;
            while(rs.next()){
                classe_rank = rs.getInt("classe_rank");
            }
            stm.close();
            return new Classe(classe_id, classe_rank);
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return null;
    }

    public void setClassRank(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET classe_rank = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public Weapon getWeapon(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT weapon_id FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            int weapon_id = 0;
            while(rs.next()){
                weapon_id = rs.getInt("weapon_id");
            }
            stm.close();
            return new Weapon(weapon_id, this.getClass(id).c);
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return null;
    }

    public void setWeaponId(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET weapon_id = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public Armor getArmor(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT armor_rank FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            int armor_rank = 0;
            while(rs.next()){
                armor_rank = rs.getInt("armor_rank");
            }
            stm.close();
            return new Armor(armor_rank);
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return null;
    }

    public void setArmorRank(String id, int amount) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET armor_rank = ? WHERE id = ?");

            stm.setInt(1, amount);
            stm.setString(2, id);

            stm.executeUpdate();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }


    public Map getMap(String id) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("SELECT * FROM rpg_bot WHERE id = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            int x = 0;
            while(rs.next()){
                x = rs.getInt("map_x");
            }
            int y = 0;
            while(rs.next()){
                y = rs.getInt("map_y");
            }
            stm.close();
            return new Map(x, y);
        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
        return null;
    }

    public void setMapXAndY(String id, int x, int y) {
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("UPDATE rpg_bot SET map_x = ? WHERE id = ?");
            stm.setInt(1, x);
            stm.setString(2, id);
            stm.executeUpdate();

            stm = connection.prepareStatement("UPDATE rpg_bot SET map_y = ? WHERE id = ?");
            stm.setInt(1, y);
            stm.setString(2, id);
            stm.executeUpdate();

            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }

}
