package fr.angelsky.angelskycoalitions.managers.sql;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;

public class SQLManager {

    private final AngelSkyCoalitions angelSkyCoalitions;

    private SQLCoalition sqlCoalition;
    private SQLCoalitionPlayer sqlCoalitionPlayer;

    public SQLManager(AngelSkyCoalitions angelSkyCoalitions){
        this.angelSkyCoalitions = angelSkyCoalitions;
        init();
    }

    public void init(){
        this.sqlCoalition = new SQLCoalition(angelSkyCoalitions);
        this.sqlCoalitionPlayer = new SQLCoalitionPlayer(angelSkyCoalitions);
    }

    public SQLCoalition getSqlCoalition() {
        return sqlCoalition;
    }

    public SQLCoalitionPlayer getSqlCoalitionPlayer() {
        return sqlCoalitionPlayer;
    }
}
