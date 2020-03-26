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
                    if (!p.farming) {
                        if (p.map.currentMap.getRessources() != null) {
                            try {
                                int numberOfFarm = Integer.parseInt(args[2]);
                                if (numberOfFarm > 0) {
                                    int timeToFarm = 0;
                                    if (p.map.currentMap.getRessources() == Ressources.STONE) {
                                        timeToFarm = numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[p.lang] + " " + numberOfFarm + " " + Config.STONE.split("////")[p.lang]).queue();
                                    } else if (p.map.currentMap.getRessources() == Ressources.COPPER) {
                                        timeToFarm = 5 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[p.lang] + " " + numberOfFarm + " " + Config.COPPER.split("////")[p.lang]).queue();
                                    } else if (p.map.currentMap.getRessources() == Ressources.IRON) {
                                        timeToFarm = 10 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[p.lang] + " " + numberOfFarm + " " + Config.IRON.split("////")[p.lang]).queue();
                                    } else if (p.map.currentMap.getRessources() == Ressources.FISH) {
                                        timeToFarm = 5 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[p.lang] + " " + numberOfFarm + " " + Config.FISH.split("////")[p.lang]).queue();
                                    } else if (p.map.currentMap.getRessources() == Ressources.WOOD) {
                                        timeToFarm = 10 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[p.lang] + " " + numberOfFarm + " " + Config.WOOD.split("////")[p.lang]).queue();
                                    }
                                    timeToFarm *= 60;
                                    p.startFarming(timeToFarm, numberOfFarm);
									Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
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
					Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                } else if (args[1].equalsIgnoreCase("time")) {
                    if (p.farm_seconds > 0) {
                        e.getChannel().sendMessage(" - '" + p.farm_seconds + " s'").queue();
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                    } else if(p.to_claim) {
						e.getChannel().sendMessage(" - Done").queue();
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                    }else{
						e.getChannel().sendMessage("\";farm <farming/claim/time> <number (farming)>\"").queue();
						Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
					}
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
