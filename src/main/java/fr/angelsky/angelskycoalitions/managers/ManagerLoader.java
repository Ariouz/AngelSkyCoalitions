package fr.angelsky.angelskycoalitions.managers;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionManager;
import fr.angelsky.angelskycoalitions.managers.menus.MenuManager;
import fr.angelsky.angelskycoalitions.managers.sql.SQLManager;
import fr.mrmicky.fastinv.FastInvManager;
import org.checkerframework.checker.units.qual.C;

import java.beans.EventHandler;

public class ManagerLoader {

    private final AngelSkyCoalitions angelSkyCoalitions;
    private SQLManager sqlManager;
    private CoalitionManager coalitionManager;
    private MenuManager menuManager;

    public ManagerLoader(AngelSkyCoalitions angelSkyCoalitions) {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    public void load(){
        FastInvManager.register(this.angelSkyCoalitions);
        ListenerManager listenerManager = new ListenerManager(angelSkyCoalitions);
        listenerManager.register();
        CommandsManager commandsManager = new CommandsManager(angelSkyCoalitions);
        commandsManager.register();
        this.sqlManager = new SQLManager(this.angelSkyCoalitions);
        this.coalitionManager = new CoalitionManager(this.angelSkyCoalitions);
        this.coalitionManager.load();
        this.menuManager = new MenuManager(angelSkyCoalitions);
        this.menuManager.init();
    }

    public void unload(){
        this.coalitionManager.saveAll();
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public CoalitionManager getCoalitionManager() {
        return coalitionManager;
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }
}
