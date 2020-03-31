package fr.mateoox600.bot.players.quests;

import java.util.Random;

public class Quest {

    private double gold;
    private int time_seconds, xp;
    private String time_text;
    private boolean finished;
    private boolean started;
    private boolean choice;
    private boolean claim;

    public Quest(){
        claim = true;
    }

    public void createQuest(int player_level) {
        Random rand = new Random();
        this.gold = Math.floor(rand.nextDouble() * 2.5 * (player_level + 1) * 100) / 100;
        this.xp = rand.nextInt(100) * (player_level + 1);
        this.time_seconds = (int) ((gold * 100) * 1 + (xp / 10)) / Math.max((player_level + 1) / 4, 1);
        int time_minutes = (int) Math.floor((double) time_seconds / 60);
        int time_seconds_keep = this.time_seconds - time_minutes * 60;
        String time = "";
        if (time_seconds_keep < 10) time = time_minutes + ":0" + time_seconds_keep;
        else time = time_minutes + ":" + time_seconds_keep;
        this.time_text = time;
        this.choice = true;
    }

    public double getGold() {
        return gold;
    }

    public int getXp() {
        return xp;
    }

    public int getTime_seconds() {
        return time_seconds;
    }

    public String getTime_text() {
        return time_text;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isChoice() {
        return choice;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isClaim() {
        return claim;
    }

    public void statQuest(){
        started = true;
        this.claim = false;
    }

    public void finishQuest() {
        finished = true;
        started = false;
        choice = false;
    }

    public boolean loop(){
        if(!started) return false;
        time_seconds--;
        if(time_seconds == 0){
            finishQuest();
            return true;
        }
        return false;
    }

    public void claim(){
        claim = true;
    }

}
