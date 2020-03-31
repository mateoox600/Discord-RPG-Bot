package fr.mateoox600.bot.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import fr.mateoox600.bot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Log {

    private static File logFile;

    public Log() {
        Calendar cal = Calendar.getInstance();
        logFile = new File("H://Bot/Logs/Log File [" + cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR) + "].txt");
        createFile();
    }

    private void createFile() {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Main.messageOwner();
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void logCommand(Member m, Guild g, TextChannel c, String name, LogStat stat) {
        Date d = new Date(System.currentTimeMillis());
        try {
            FileWriter w = new FileWriter(logFile, true);
            w.write("[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] [Command/" + stat + "] Command + args '" + name + "'; Author Id '" + m.getId() + "'; Channel Id '" + c.getId() + "'; Guild Id '" + g.getId() + "' \n");
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }

    @SuppressWarnings("deprecation")
    public void logSystemMessage(String message, LogStat stat) {
        Date d = new Date(System.currentTimeMillis());
        try {
            FileWriter w = new FileWriter(logFile, true);
            w.write("[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] [Command/" + stat + "] " + message + "\n");
            System.out.print("[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] [Command/" + stat + "] " + message + "\n");
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
            Main.messageOwner();
        }
    }

}
