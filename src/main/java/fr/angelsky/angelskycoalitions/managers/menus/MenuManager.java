package fr.angelsky.angelskycoalitions.managers.menus;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.managers.menus.coalition.CoalitionsMenu;

public class MenuManager {

    private final AngelSkyCoalitions angelSkyCoalitions;

    private CoalitionsMenu coalitionsMenu;

    public MenuManager(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    public void init()
    {
        this.coalitionsMenu = new CoalitionsMenu(angelSkyCoalitions);
    }

    public CoalitionsMenu getCoalitionsMenu() {
        return coalitionsMenu;
    }
}
