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
            int lang = Main.sqlManager.getLang(p.author.getId());
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("farming")) {
                    if (!Main.sqlManager.getFarming(p.author.getId())) {
                        if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() != null) {
                            try {
                                int numberOfFarm = Integer.parseInt(args[2]);
                                if (numberOfFarm > 0) {
                                    int timeToFarm = 0;
                                    if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == Ressources.STONE) {
                                        timeToFarm = numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[lang] + " " + numberOfFarm + " " + Config.STONE.split("////")[lang]).queue();
                                    } else if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == Ressources.COPPER) {
                                        timeToFarm = 5 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[lang] + " " + numberOfFarm + " " + Config.COPPER.split("////")[lang]).queue();
                                    } else if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == Ressources.IRON) {
                                        timeToFarm = 10 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[lang] + " " + numberOfFarm + " " + Config.IRON.split("////")[lang]).queue();
                                    } else if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == Ressources.FISH) {
                                        timeToFarm = 5 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[lang] + " " + numberOfFarm + " " + Config.FISH.split("////")[lang]).queue();
                                    } else if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == Ressources.WOOD) {
                                        timeToFarm = 10 * numberOfFarm;
                                        e.getChannel().sendMessage(Config.FARMING_LAUNCH.split("////")[lang] + " " + numberOfFarm + " " + Config.WOOD.split("////")[lang]).queue();
                                    }
                                    timeToFarm *= 60;
                                    p.startFarming(timeToFarm, numberOfFarm);
                                    Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                                } else {
                                    e.getChannel().sendMessage(Config.VALID_ARG.split("////")[lang] + " \";farm <number (>0)>\"").queue();
                                    Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                                }
                            } catch (Exception exc) {
                                e.getChannel().sendMessage(Config.VALID_ARG.split("////")[lang] + " \";farm <number (>0)>\"").queue();
                                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                            }
                        } else {
                            e.getChannel().sendMessage(Config.NO_RESSOURCES.split("////")[lang]).queue();
                            Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                        }
                    } else {
                        e.getChannel().sendMessage(Config.CANT_FARMING.split("////")[lang]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    }
                } else if (args[1].equalsIgnoreCase("claim")) {
                    int[] request = p.claim_resources();
                    e.getChannel().sendMessage(Config.FARMED.split("////")[lang] + " : \n" +
                            "	- " + Config.STONE.split("////")[lang] + ": " + request[0] + "\n" +
                            "	- " + Config.COPPER.split("////")[lang] + ": " + request[1] + "\n" +
                            "	- " + Config.IRON.split("////")[lang] + ": " + request[2] + "\n" +
                            "	- " + Config.FISH.split("////")[lang] + ": " + request[3] + "\n" +
                            "	- " + Config.WOOD.split("////")[lang] + ": " + request[4]).queue();
                    Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                } else if (args[1].equalsIgnoreCase("time")) {
                    if (Main.sqlManager.getFarmSeconds(p.author.getId()) > 0) {
                        e.getChannel().sendMessage(" - '" + Main.sqlManager.getFarmSeconds(p.author.getId()) + " s'").queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                    } else if (Main.sqlManager.getToClaim(p.author.getId())) {
                        e.getChannel().sendMessage(" - Done").queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);
                    } else {
                        e.getChannel().sendMessage("\";farm <farming> <number>\"").queue();
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
