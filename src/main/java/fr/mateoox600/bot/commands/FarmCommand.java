package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;
import fr.mateoox600.bot.players.ressource.Ressources;

public class FarmCommand extends Command {

    public FarmCommand() {
        this.name = "farm";
        this.aliases = new String[]{"f"};
        this.help = "test 0";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        boolean player_exist_request = Main.initPlayer(e.getMember().getUser());
        if (player_exist_request) {
            PlayerData p = Main.players.get(e.getMember().getUser().getId());
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("farming")) {
                	if(!p.farming) {
						if (p.map.currentMap.getRessources() != null) {
							try {
								int numberOfFarm = Integer.parseInt(args[2]);
								if (numberOfFarm > 0) {
									int timeToFarm = 0;
									if (p.map.currentMap.getRessources() == Ressources.STONE) {
										timeToFarm = numberOfFarm;
									} else if (p.map.currentMap.getRessources() == Ressources.COPPER) {
										timeToFarm = 5 * numberOfFarm;
									} else if (p.map.currentMap.getRessources() == Ressources.IRON) {
										timeToFarm = 10 * numberOfFarm;
									} else if (p.map.currentMap.getRessources() == Ressources.FISH) {
										timeToFarm = 5 * numberOfFarm;
									} else if (p.map.currentMap.getRessources() == Ressources.WOOD) {
										timeToFarm = 10 * numberOfFarm;
									}
									timeToFarm *= 60;
									p.startFarming(timeToFarm, numberOfFarm);
								} else {
									e.getChannel().sendMessage(Config.VALID_ARG.split("////")[p.lang] + " \";farm <number (>0)>\"").queue();
									Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
								}
							} catch (Exception exc) {
								e.getChannel().sendMessage(Config.VALID_ARG.split("////")[p.lang] + " \";farm <number (>0)>\"").queue();
								Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
							}
						} else {
							e.getChannel().sendMessage(Config.NO_RESSOURCES.split("////")[p.lang]).queue();
							Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
						}
					} else {
						e.getChannel().sendMessage(Config.CANT_FARMING.split("////")[p.lang]).queue();
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
					}
                } else if (args[1].equalsIgnoreCase("claim")) {
                    int[] request = p.claim_resources();
                    e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + " : \n" +
                            "	- " + Config.STONE.split("////")[p.lang] + ": " + request[0] + "\n" +
                            "	- " + Config.COPPER.split("////")[p.lang] + ": " + request[1] + "\n" +
                            "	- " + Config.IRON.split("////")[p.lang] + ": " + request[2] + "\n" +
                            "	- " + Config.FISH.split("////")[p.lang] + ": " + request[3] + "\n" +
                            "	- " + Config.WOOD.split("////")[p.lang] + ": " + request[4]).queue();
                } else if (args[1].equalsIgnoreCase("time")) {
					e.getChannel().sendMessage(" - '" + p.farm_seconds + " s'").queue();
                } else {
                    e.getChannel().sendMessage("\";farm <farming/claim/time> <number (farming)>\"").queue();
                    Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                }
            } else {
				e.getChannel().sendMessage("\";farm <farming/claim/time> <number (farming)>\"").queue();
                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
            }
        } else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
    }

}
