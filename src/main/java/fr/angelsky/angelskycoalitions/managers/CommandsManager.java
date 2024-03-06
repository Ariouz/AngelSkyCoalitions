package fr.angelsky.angelskycoalitions.managers;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.commands.admin.SetPlayerCoalitionCommand;

import java.util.Objects;

public class CommandsManager {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public CommandsManager(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    public void register()
    {
        Objects.requireNonNull(angelSkyCoalitions.getCommand("setplayercoalition")).setExecutor(new SetPlayerCoalitionCommand((angelSkyCoalitions)));
    }

    public AngelSkyCoalitions getAngelSkyCoalitions() {
        return angelSkyCoalitions;
    }
}
