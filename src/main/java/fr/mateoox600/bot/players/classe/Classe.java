package fr.mateoox600.bot.players.classe;

public class Classe {
	
	public int rank, class_id;
	public Class c;
	
	public Classe(int id, int start_rank) {
		
		this.class_id = id;
		this.rank = start_rank;
		
		if(class_id > 3) class_id = 3;
		else if(class_id < 0) class_id = 0;
		
		if(rank < 0) rank = 0;
		else if(rank > 1) rank = 1;
		
		c = Class.getClassByIdAndRank(class_id, rank);
		
	}
	
}
