package fr.mateoox600.bot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.mateoox600.bot.commands.*;
import fr.mateoox600.bot.log.Log;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    private static JDA jda;
    private static File tokenFile = new File("h://Bot/token.txt");
    private static boolean running = true;
    public static SqlManager sqlManager;
    public static Log logger;
    public static HashMap<String, PlayerData> players = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws LoginException, InterruptedException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        if (!tokenFile.exists()) {
            return;
        }

        logger = new Log();

        sqlManager = new SqlManager("jdbc:mysql:", "127.0.0.1:3306", "discord bot", "root", "");
        sqlManager.connection();
        logger.logSystemMessage("SqlManager Connected", LogStat.INFO);

        BufferedReader bf = new BufferedReader(new FileReader(tokenFile));
        String TOKEN = bf.readLine();
        bf.close();
        jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).build();
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(Config.PREFIX);

        logger.logSystemMessage("Stating...", LogStat.INFO);
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.playing("Launching please wait"));
        jda.awaitReady();

        builder.addCommand(new HelpCommand());
        logger.logSystemMessage("Help Command Register !", LogStat.INFO);
        builder.addCommand(new CharacterCommand());
        logger.logSystemMessage("Character Command Register !", LogStat.INFO);
        builder.addCommand(new LangCommand());
        logger.logSystemMessage("Lang Command Register !", LogStat.INFO);
        builder.addCommand(new LevelUpCommand());
        logger.logSystemMessage("Level Up Command Register !", LogStat.INFO);
        builder.addCommand(new MapCommand());
        logger.logSystemMessage("Map Command Register !", LogStat.INFO);
        builder.addCommand(new MoveCommand());
        logger.logSystemMessage("Move Command Register !", LogStat.INFO);
        builder.addCommand(new FarmCommand());
        logger.logSystemMessage("Farm Command Register !", LogStat.INFO);
        builder.addCommand(new AccountCommand());
        logger.logSystemMessage("Account Command Register !", LogStat.INFO);
        builder.addCommand(new ClassesCommand());
        logger.logSystemMessage("Classes Command Register !", LogStat.INFO);

        builder.setOwnerId("664945581470253078");
        CommandClient client = builder.build();
        jda.addEventListener(client);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Runnable(), 0, 1000);
        logger.logSystemMessage("Timer Launch !", LogStat.INFO);


        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("Bot launch !"));
        logger.logSystemMessage("Start !", LogStat.INFO);
        Date start_date = new Date(System.currentTimeMillis());
        Objects.requireNonNull(jda.getTextChannelById("691749591485251612")).sendMessage("Bot stated successfuly at " + start_date.getHours() + ":" + start_date.getMinutes() + ":" + start_date.getSeconds() + " !").queue();

        Scanner sc = new Scanner(System.in);
        while (running) {
            String[] cmd_args = sc.nextLine().split("\\s+");
            String cmd = cmd_args[0];
            if (cmd.equalsIgnoreCase("stop")) {
                System.out.println("Stoping Bot");
                logger.logSystemMessage("Getting shutdown !", LogStat.INFO);
                Date down_date = new Date(System.currentTimeMillis());
                Objects.requireNonNull(jda.getTextChannelById("691749591485251612")).sendMessage("Bot shutdown successfuly at " + down_date.getHours() + ":" + down_date.getMinutes() + ":" + down_date.getSeconds() + " !").queue();
                running = false;
                timer.cancel();
                logger.logSystemMessage("Timer Stop !", LogStat.INFO);
                jda.shutdown();
                logger.logSystemMessage("JDA shutdown !", LogStat.INFO);
                sqlManager.disconnect();
                logger.logSystemMessage("SqlManager disconnected !", LogStat.INFO);
                sc.close();
                logger.logSystemMessage("Shutdown !", LogStat.INFO);
            }
        }

    }

    public static boolean initPlayer(User author) {
        if (sqlManager.playerExist(author.getId())) {
            if (!Main.players.containsKey(author.getId())) {
                players.put(author.getId(), new PlayerData(author, 0));
            }
            return true;
        } else return false;
    }

    public static void messageOwner() {
        Objects.requireNonNull(jda.getUserById("251978573139673088")).openPrivateChannel().complete().sendMessage("Une érreur a été détècter XD").queue();
    }

}

class Runnable extends TimerTask {
    public void run() {
        for (Map.Entry<String, PlayerData> p : Main.players.entrySet()) {
            p.getValue().loop();
        }
    }
}
