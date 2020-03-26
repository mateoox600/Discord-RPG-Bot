package fr.mateoox600.bot.events;

import fr.mateoox600.bot.Config;
import fr.mateoox600.bot.Main;
import fr.mateoox600.bot.players.PlayerData;
import fr.mateoox600.bot.players.armor.ArmorType;
import fr.mateoox600.bot.players.classe.Class;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class MessageEvent extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		
		if(Objects.requireNonNull(e.getMember()).getUser().isBot()) return;
		
		if(args[0].substring(0, 1).equalsIgnoreCase(Config.PREFIX)) {
			
			boolean player_exist_request = Main.initPlayer(e.getMember().getUser().getId());
			String cmd_arg = args[0].substring(1);
			
			if(cmd_arg.equalsIgnoreCase("character") || cmd_arg.equalsIgnoreCase("c")) {
				
				if(player_exist_request) {
					
					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					e.getChannel().sendMessage(p.getCharacter().build()).queue();
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("language") || cmd_arg.equalsIgnoreCase("lang")) {
				
				if(player_exist_request) {
					
					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					
					if(args.length > 1) {
						
						if(args[1].equalsIgnoreCase("fr")) {
							e.getChannel().sendMessage("Tu as passer la langue du bot en français").queue();
							p.lang = 1;
						} else if(args[1].equalsIgnoreCase("en")) {
							e.getChannel().sendMessage("You change the bot language to english").queue();
							p.lang = 0;
						}
						
					}
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("levelup") || cmd_arg.equalsIgnoreCase("lvlup")) {
				
				if(player_exist_request) {
					
					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					
					if(args.length > 1) {
						
						if(args[1].equalsIgnoreCase("Armor")) {
							
							if(p.rank > ArmorType.getNextArmorByLevel(p.armor.armorType.getLevel()).getMin_level()) {
								
								p.armor.armorType = ArmorType.getArmorTypeByLevel(p.armor.armorType.getLevel());
								
							}else e.getChannel().sendMessage(Config.NOT_REQUIRE_LEVEL.split("////")[p.lang] + ArmorType.getNextArmorByLevel(p.armor.armorType.getLevel()).getMin_level()).queue();
							
						}else e.getChannel().sendMessage(Config.ONE_OF_THAT_PIECE.split("////")[p.lang] + " : \n"
								+ "- \"" + Config.ARMOR.split("////")[p.lang] + "\"").queue();
						
					}else e.getChannel().sendMessage(Config.TO_LEVEL_UP_STUFF.split("////")[p.lang]).queue();
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("map") || cmd_arg.equalsIgnoreCase("m")) {
				
				if(player_exist_request) {
					
					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					e.getChannel().sendMessage(p.getMap()).queue();
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("move")) {
				
				if(player_exist_request) {
					
					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					
					if(args.length > 1) {
						
						String request = p.map.move(args[1], p.rank);
						
						if(request.equals("true")) e.getChannel().sendMessage(Config.YOU_MOVE.split("////")[p.lang]).queue();
						else if(request.equals("false")) e.getChannel().sendMessage(Config.MOVE_IMPOSSIBLE.split("////")[p.lang]).queue();
						else if(request.startsWith("notLevel")) e.getChannel().sendMessage(Config.NOT_REQUIRE_LEVEL.split("////")[p.lang] + request.substring(8)).queue();
						
					}else e.getChannel().sendMessage(Config.TO_MOVE.split("////")[p.lang] + "\";move <North/South/East/West>\"").queue();
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("farm")) {
				
				if(player_exist_request) {

					PlayerData p = Main.players.get(e.getMember().getUser().getId());
					
					if(args.length > 1) {
						
						int[] request = p.gatherRessources(Integer.parseInt(args[1]));
						
						if(request != null) {
							if(request[0] > 0) {
								e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[0] + " " + Config.STONE.split("////")[p.lang]).queue();
							}else if(request[1] > 0) {
								e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[1] + " " + Config.IRON.split("////")[p.lang]).queue();
							}else if(request[2] > 0) {
								e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[2] + " " + Config.COPPER.split("////")[p.lang]).queue();
							}else if(request[3] > 0) {
								e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[3] + " " + Config.FISH.split("////")[p.lang]).queue();
							}else if(request[4] > 0) {
								e.getChannel().sendMessage(Config.FARMED.split("////")[p.lang] + request[4] + " " + Config.WOOD.split("////")[p.lang]).queue();
							}
						}else {
							e.getChannel().sendMessage(Config.NO_RESSOURCES.split("////")[p.lang]).queue();
						}
						
					}else e.getChannel().sendMessage(Config.TO_FARM.split("////")[p.lang]).queue();
					
				}else e.getChannel().sendMessage(Config.MUST_CREATE_ACCOUNT).queue();
				
			}else if(cmd_arg.equalsIgnoreCase("account")) {
				
				if(args.length > 2 && args[1].equalsIgnoreCase("create")) {
					
					if(player_exist_request) e.getChannel().sendMessage("Tu a déja un compte \n\n"
							+ "You have already an account").queue();
					
					else {
						
						if(args[2].equalsIgnoreCase("Warrior") || args[2].equalsIgnoreCase("Archer") || args[2].equalsIgnoreCase("Mage") || args[2].equalsIgnoreCase("Assasin")) {
							
							Main.players.put(e.getMember().getUser().getId(), new PlayerData(e.getMember().getUser().getId(), Class.getClassByName(args[2]).getId()));
							e.getChannel().sendMessage("Ton compte a été créer ! tu est un " + Main.players.get(e.getMember().getUser().getId()).classe.c.getName() + "\n\n"
									+ "Your account was create ! you are now a " + Main.players.get(e.getMember().getUser().getId()).classe.c.getName()).queue();
							
						}else {
							
							e.getChannel().sendMessage("classes list : \n"
									+ "- Warrior \n"
									+ "- Archer \n"
									+ "- Mage \n"
									+ "- Assasin").queue();
							
						}
						
					}
					
				}else e.getChannel().sendMessage("Pour créer un compte tu doit faire \";account create <classe>\" (pour avoir une list des classes tu peut faire \";classes\") \n\n"
						+ "To create an account you must do \";account create <classe>\" (To have a look at all the classes make \";classes\")").queue();
				
			}else if(cmd_arg.equalsIgnoreCase("classes")) {
				
				e.getChannel().sendMessage("classes list : \n"
						+ "- Warrior \n"
						+ "- Archer \n"
						+ "- Mage \n"
						+ "- Assasin").queue();
				
			}
			
		}
		
	}
	
}
