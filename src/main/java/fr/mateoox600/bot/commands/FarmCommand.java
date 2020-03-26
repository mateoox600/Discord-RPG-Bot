package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class FarmCommand extends Command {

	public FarmCommand(){
		this.name = "farm";
		this.aliases = new String[]{"f"};
		this.help = "test 0";
	}

	@Override
	protected void execute(CommandEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
		if(player_exist_request) {
			PlayerData p = Main.players.get(e.getMember().getUser().getId());
			if(args.length > 1) {
				int[] request = p.gatherRessources(Integer.parseInt(args[1]));
				if(request != null) {
					if(request[0] > 0) {
						e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[0] + " " + Config.STONE.split("////")[p.lang]).queue();
					}else if(request[1] > 0) {
						e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[1] + " " + Config.IRON.split("////")[p.lang]).queue();
					}else if(request[2] > 0) {
						e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[2] + " " + Config.COPPER.split("////")[p.lang]).queue();
					}else if(request[3] > 0) {
						e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[3] + " " + Config.FISH.split("////")[p.lang]).queue();
					}else if(request[4] > 0) {
						e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[4] + " " + Config.WOOD.split("////")[p.lang]).queue();
					}
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
				}else {
					e.getChannel().sendMessage(Config.NO_RESSOURCES.split("////")[p.lang]).queue();
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
				}
			}else {
				e.getChannel().sendMessage(Config.TO_FARM.split("////")[p.lang]).queue();
				Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
			}
		}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
	}
	
}
