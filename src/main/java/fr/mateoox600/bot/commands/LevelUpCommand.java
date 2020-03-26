package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;
import fr.mateoox600.bot.players.armor.ArmorType;

public class LevelUpCommand extends Command {

	public LevelUpCommand(){
		this.name = "levelup";
		this.aliases = new String[]{"lvlup"};
		this.help = "test 0";
	}

	@Override
	protected void execute(CommandEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
		if (player_exist_request) {
			PlayerData p = Main.players.get(e.getMember().getUser().getId());
			if (args.length > 1) {
				if (args[1].equalsIgnoreCase("Armor")) {
					if (p.rank > ArmorType.getNextArmorByLevel(p.armor.armorType.getLevel()).getMin_level()) {
						p.armor.armorType = ArmorType.getArmorTypeByLevel(p.armor.armorType.getLevel());
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
					} else {
						e.getChannel().sendMessage(Config.NOT_REQUIRE_LEVEL.split("////")[p.lang] + ArmorType.getNextArmorByLevel(p.armor.armorType.getLevel()).getMin_level()).queue();
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
					}
				} else {
					e.getChannel().sendMessage(Config.ONE_OF_THAT_PIECE.split("////")[p.lang] + " : \n"
							+ "- \"" + Config.ARMOR.split("////")[p.lang] + "\"").queue();
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
				}
			} else {
				e.getChannel().sendMessage(Config.TO_LEVEL_UP_STUFF.split("////")[p.lang]).queue();
				Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
			}
		} else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
	}
	
}
