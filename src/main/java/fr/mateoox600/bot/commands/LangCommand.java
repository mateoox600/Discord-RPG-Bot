package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class LangCommand extends Command {

	public LangCommand(){
		this.name = "lang";
		this.aliases = new String[]{""};
		this.help = "test 0";
	}

	@Override
	protected void execute(CommandEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
		if(player_exist_request) {
			PlayerData p = Main.players.get(e.getMember().getUser().getId());
			if(args.length > 1) {
				if(args[1].equalsIgnoreCase("fr")) {
					e.getChannel().sendMessage("Tu as passer la langue du bot en français").queue();
					p.lang = 1;
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
				} else if(args[1].equalsIgnoreCase("en")) {
					e.getChannel().sendMessage("You change the bot language to english").queue();
					p.lang = 0;
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
				}
			}
		}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
	}
	
}
