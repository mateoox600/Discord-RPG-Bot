package fr.mateoox600.bot.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class JoinEvent extends ListenerAdapter {

    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent e) {

        Category cat = e.getGuild().createCategory("Mateoox600 RPG Bot").complete();
        Role role = e.getGuild().createRole().setName("Bot Auth").complete();
        TextChannel info_chan = e.getGuild().createTextChannel("Info").setParent(cat).complete();
        info_chan.createPermissionOverride(e.getGuild().getPublicRole()).setDeny(Permission.MESSAGE_WRITE).setAllow(Permission.MESSAGE_READ).setAllow(Permission.VIEW_CHANNEL).complete();
        info_chan.createPermissionOverride(role).setAllow(Permission.MESSAGE_WRITE).complete();
        e.getGuild().createTextChannel("Commands").setParent(cat).complete();

        super.onGuildJoin(e);
    }
}
