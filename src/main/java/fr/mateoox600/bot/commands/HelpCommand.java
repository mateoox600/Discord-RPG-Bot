package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class HelpCommand extends Command {

    public HelpCommand() {
        this.name = "help";
        this.aliases = new String[]{"h"};
        this.help = "test 0";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        if (args.length == 1) {
            boolean player_exist_request = Main.initPlayer(e.getMember().getUser());
            if (player_exist_request) {
                PlayerData p = Main.players.get(e.getMember().getUser().getId());
                e.getMember().getUser().openPrivateChannel().complete().sendMessage(Config.HELP.split("////")[Main.sqlManager.getLang(p.author.getId())]).queue();
            } else {
                e.getMember().getUser().openPrivateChannel().complete().sendMessage(Config.HELP.split("////")[0]).queue();
            }
            Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
        } else {
            if (args[1].equalsIgnoreCase("en")) {
                e.getMember().getUser().openPrivateChannel().complete().sendMessage(Config.HELP.split("////")[0]).queue();
                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
            } else if (args[1].equalsIgnoreCase("fr")) {
                e.getMember().getUser().openPrivateChannel().complete().sendMessage(Config.HELP.split("////")[1]).queue();
                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
            } else {
                e.getChannel().sendMessage(";help <fr/en (optional)>").queue();
                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
            }
        }
    }

}
