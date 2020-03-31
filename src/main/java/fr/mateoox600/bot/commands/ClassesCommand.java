package fr.mateoox600.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.log.LogStat;

public class ClassesCommand extends Command {

    public ClassesCommand() {
        this.name = "classes";
        this.aliases = new String[]{"clas"};
        this.help = "test 0";
    }

    @Override
    protected void execute(CommandEvent e) {
        e.getChannel().sendMessage("classes list : \n"
                + "- Warrior \n"
                + "- Archer \n"
                + "- Mage \n"
                + "- Assasin").queue();
        Main.logger.logCommand(e.getMember(), e.getGuild(), e.getTextChannel(), e.getMessage().getContentRaw(), LogStat.INFO);

    }

}
