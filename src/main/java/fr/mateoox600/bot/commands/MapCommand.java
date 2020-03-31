package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class MapCommand extends Command {

    public MapCommand() {
        this.name = "map";
        this.aliases = new String[]{"m"};
        this.help = "test 0";
    }

    @Override
    protected void execute(CommandEvent e) {
        boolean player_exist_request = Main.initPlayer(e.getMember().getUser());
        if (player_exist_request) {
            PlayerData p = Main.players.get(e.getMember().getUser().getId());
            e.getChannel().sendMessage(p.getMap()).queue();
            Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
        } else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
    }

}
