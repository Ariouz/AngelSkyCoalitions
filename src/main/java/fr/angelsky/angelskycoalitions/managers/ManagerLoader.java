package fr.angelsky.angelskycoalitions.managers;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.managers.sql.SQLManager;

public class ManagerLoader {

    private final AngelSkyCoalitions angelSkyCoalitions;
    private SQLManager sqlManager;

    public ManagerLoader(AngelSkyCoalitions angelSkyCoalitions) {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    public void load(){
        this.sqlManager = new SQLManager(this.angelSkyCoalitions);
    }

    public void unload(){

    }

}
