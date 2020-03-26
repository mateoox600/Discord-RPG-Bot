package fr.mateoox600.bot;

public class Config {

    public static String PREFIX = ";";
    public static String FILE_PREFIX = "H://Bot/PlayerData/";

    public static String HEALTH = "Health////Vie";
    public static String LEVEL = "Level////Niveau";

    public static String STONE = "Stone////Pierre";
    public static String IRON = "Iron////Fer";
    public static String COPPER = "Copper////Cuivre";
    public static String FISH = "Fish////Poisson";
    public static String WOOD = "Wood////Bois";

    public static String WEAPON = "Weapon////Arme";
    public static String ARMOR = "Armor////Armure";

    public static String MUST_CREATE_ACCOUNT = "Tu doit créer un compte pour jouer ! \";account create <classe>\" \n\n"
            + "You must create an account to play ! \";account create <classe>\"";
    public static String VALID_ARG = "You must put a valid argument////Tu dois mettre un argument valide";

    public static String FARMED = "You farm ////Tu as farmer ";
    public static String NO_RESSOURCES = "There are no ressources in this area !////Tu est dans une zone sans ressources !";
    public static String FARMING_LAUNCH = "You launch the farm of////Tu a lancer le farming de";
    public static String CANT_FARMING = "You can't do that while farming////Tu ne peut pas faire ça pendant que tu farm";

    public static String NOT_REQUIRE_LEVEL = "You have not the require level. The require level is : ////Tu n'as pas le niveau requi. le niveau requi est is : ";

    public static String MOVE_IMPOSSIBLE = "This move is impossible !////il est impossible de bouger dans cette direction !";
    public static String YOU_MOVE = "You move on the map !////Tu a bouger sur la carte !";
    public static String TO_MOVE = "To move do ////Pour bouger fait ";
    public static String YOUR_POS = "Your position is ////Ta position est ";

    public static String ONE_OF_THAT_PIECE = "You must choice one of those piece of stuff////Tu doit choisir un de ces pièce de stuff";
    public static String TO_LEVEL_UP_STUFF = "To level up your stuff do \";levelup <piece of stuff>\"////Pour level up ton stuff fait \";levelup <pièce de stuff>\"";

    public static String HELP = "Help \n"
            + "- ;account : account management and creation \n"
            + "- ;character : see your stats \n"
            + "- ;map : see the game map \n"
            + "- ;move <north/south/east/west> : to move on the map \n"
            + "- ;levelup <piece of stuff> : to level up your stuff with the rigth ressources and level \n"
            + "- ;farm <number> : farm the ressource of the zone you are at \n"
            + "- ;lang <fr/en> : change lang betwen french and english \n"
            + "- ;classes : to see the list of classes////Aide \n"
            + "- ;account : Gestion et creation du compte \n"
            + "- ;character : voir tes stats \n"
            + "- ;map : voir la carte du jeu \n"
            + "- ;move <nord/sud/est/ouest> : bouger sur la carte \n"
            + "- ;levelup <piece of stuff> : pour améliorer ton stuff avec les bonne ressources et le bon niveau \n"
            + "- ;farm <number> : farme les ressources de ta zone (;map) \n"
            + "- ;lang <fr/en> : change la langue entre français et anglais \n"
            + "- ;classes : pour voir la list des classes";

}
