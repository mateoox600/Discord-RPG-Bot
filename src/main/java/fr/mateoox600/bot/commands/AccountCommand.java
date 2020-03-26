package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;
import fr.mateoox600.bot.players.classe.Class;

public class AccountCommand extends Command {

	public AccountCommand(){
		this.name = "account";
		this.aliases = new String[]{"acc"};
		this.help = "test 0";
	}

	@Override
	protected void execute(CommandEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
		if(args.length > 2 && args[1].equalsIgnoreCase("create")) {
			if(player_exist_request) {
				e.getChannel().sendMessage("Tu as déja un compte \n\n"
						+ "You have already an account").queue();
				Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
			}
			else {
				if(args[2].equalsIgnoreCase("Warrior") || args[2].equalsIgnoreCase("Archer") || args[2].equalsIgnoreCase("Mage") || args[2].equalsIgnoreCase("Assasin")) {
					Main.players.put(e.getMember().getUser().getId(), new PlayerData(e.getMember().getUser().getId(), Class.getClassByName(args[2]).getId()));
					e.getChannel().sendMessage("Ton compte a été créer ! tu es un " + Main.players.get(e.getMember().getUser().getId()).classe.c.getName() + "\n\n"
							+ "Your account was create ! you are now a " + Main.players.get(e.getMember().getUser().getId()).classe.c.getName()).queue();
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
				}else {
					e.getChannel().sendMessage("classes list : \n"
							+ "- Warrior \n"
							+ "- Archer \n"
							+ "- Mage \n"
							+ "- Assasin").queue();
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
				}
			}
		}else {
			e.getChannel().sendMessage("Pour créer un compte tu doit faire \";account create <classe>\" (pour avoir une list des classes tu peut faire \";classes\") \n\n"
					+ "To create an account you must do \";account create <classe>\" (To have a look at all the classes make \";classes\")").queue();
			Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
		}
	}
	
}
