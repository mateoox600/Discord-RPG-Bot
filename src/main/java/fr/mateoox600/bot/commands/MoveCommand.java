package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.map.Map;
import fr.mateoox600.bot.players.PlayerData;

public class MoveCommand extends Command {

    public MoveCommand() {
        this.name = "move";
        this.aliases = new String[]{"mo"};
        this.help = "test 0";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        boolean player_exist_request = Main.initPlayer(e.getMember().getUser());
        if (player_exist_request) {
            PlayerData p = Main.players.get(e.getMember().getUser().getId());
            if (args.length > 1) {
                if (!Main.sqlManager.getFarming(p.author.getId())) {
                    Map p_map = Main.sqlManager.getMap(p.author.getId());
                    String request = p_map.move(args[1], Main.sqlManager.getLevel(p.author.getId()), p.author.getId());
                    if (request.equals("true")) {
                        e.getChannel().sendMessage(Config.YOU_MOVE.split("////")[Main.sqlManager.getLang(p.author.getId())]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                    } else if (request.equals("false")) {
                        e.getChannel().sendMessage(Config.MOVE_IMPOSSIBLE.split("////")[Main.sqlManager.getLang(p.author.getId())]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    } else if (request.startsWith("notLevel")) {
                        e.getChannel().sendMessage(Config.NOT_REQUIRE_LEVEL.split("////")[Main.sqlManager.getLang(p.author.getId())] + request.substring(8)).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    }
                    Main.sqlManager.setMapXAndY(p.author.getId(), p_map.x, p_map.y);
                } else {
					e.getChannel().sendMessage(Config.CANT_FARMING.split("////")[Main.sqlManager.getLang(p.author.getId())]).queue();
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                }
            } else {
                e.getChannel().sendMessage(Config.TO_MOVE.split("////")[Main.sqlManager.getLang(p.author.getId())] + "\";move <North/South/East/West>\"").queue();
                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
            }
        } else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
    }

}
