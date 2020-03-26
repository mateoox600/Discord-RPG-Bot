package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class CharacterCommand extends Command {

	public CharacterCommand(){
		this.name = "character";
		this.aliases = new String[]{"c"};
		this.help = "test 0";
	}

	@Override
	protected void execute(CommandEvent e) {
		boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
		if(player_exist_request) {
			PlayerData p = Main.players.get(e.getMember().getUser().getId());
			e.getChannel().sendMessage(p.getCharacter().build()).queue();
			Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
		}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
	}
	
}
