package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;
import fr.mateoox600.bot.players.PlayerData;

public class QuestCommand extends Command {

    public QuestCommand() {
        this.name = "quest";
        this.aliases = new String[]{"quests", "q"};
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
                if (args[1].equalsIgnoreCase("find") || args[1].equalsIgnoreCase("f")) {
                    if (!p.quest.isFinished()) {
                        if(p.quest.isClaim()) {
                            if (Main.sqlManager.getMap(p.author.getId()).currentMap.getRessources() == null) {
                                p.quest.createQuest(Main.sqlManager.getLevel(p.author.getId()));
                                e.getChannel().sendMessage("Coins: " + p.quest.getGold() + "\n" +
                                        "Exp: " + p.quest.getXp() + "\n" +
                                        "Time: " + p.quest.getTime_text()).queue();
                            } else {
                                e.getChannel().sendMessage(Config.NO_QUEST.split("////")[lang]).queue();
                                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                            }
                        } else {
                            e.getChannel().sendMessage(Config.QUEST_NOT_CLAIM.split("////")[lang]).queue();
                            Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                        }
                    } else {
                        e.getChannel().sendMessage(Config.CANT_QUEST.split("////")[lang]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    }
                }else if(args[1].equalsIgnoreCase("start") ||args[1].equalsIgnoreCase("st")){
                    if(!p.quest.isStarted()){
                        if(p.quest.isClaim()) {
                            if (p.quest.isChoice()) {
                                p.quest.statQuest();
                                e.getChannel().sendMessage(Config.QUEST_START.split("////")[lang] + p.quest.getTime_text() + " minutes").queue();
                            } else {
                                e.getChannel().sendMessage(";quest <find>").queue();
                                Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                            }
                        } else {
                            e.getChannel().sendMessage(Config.QUEST_NOT_CLAIM.split("////")[lang]).queue();
                            Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                        }
                    } else {
                        e.getChannel().sendMessage(Config.CANT_QUEST.split("////")[lang]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    }
                }else if(args[1].equalsIgnoreCase("claim") ||args[1].equalsIgnoreCase("c")){
                    if(p.quest.isFinished()){
                        p.quest.claim();
                        Main.sqlManager.setCoins(p.author.getId(), Main.sqlManager.getCoins(p.author.getId()) + p.quest.getGold());
                        Main.sqlManager.setXp(p.author.getId(), Main.sqlManager.getXp(p.author.getId()) + p.quest.getXp());
                        e.getChannel().sendMessage(Config.QUEST_GIVE_YOU.split("////")[lang] + "\n" +
                                "Coins: " + p.quest.getGold() + "\n" +
                                "Exp: " + p.quest.getXp()).queue();
                    } else {
                        e.getChannel().sendMessage(Config.QUEST_NOT_FINISH.split("////")[lang]).queue();
                        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.WARN);
                    }
                }else if(args[1].equalsIgnoreCase("time")){
                    if(!p.quest.isFinished()){
                        e.getChannel().sendMessage("seconds: " + p.quest.getTime_seconds()).queue();
                    }
                }
            }
        } else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
    }

}
