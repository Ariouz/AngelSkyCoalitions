package fr.angelsky.angelskycoalitions.coalition;

import org.bukkit.entity.Player;

public class CoalitionPlayer {

    private final Player player;
    private final Coalition coalition;
    private int eventPoints;
    private int coalitionPoints;

    public CoalitionPlayer(Player player, Coalition coalition)
    {
        this.player = player;
        this.coalition = coalition;
    }

    public Player getPlayer() {
        return player;
    }

    public Coalition getCoalition() {
        return coalition;
    }

    public int getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(int eventPoints) {
        this.eventPoints = eventPoints;
    }

    public int getCoalitionPoints() {
        return coalitionPoints;
    }

    public void setCoalitionPoints(int coalitionPoints) {
        this.coalitionPoints = coalitionPoints;
    }
}
