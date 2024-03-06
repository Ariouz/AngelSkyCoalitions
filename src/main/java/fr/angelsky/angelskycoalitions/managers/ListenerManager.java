package fr.angelsky.angelskycoalitions.managers;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.listeners.player.PlayerJoinQuitListener;
import org.bukkit.Bukkit;

public class ListenerManager {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public ListenerManager(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    public void register()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinQuitListener(this.angelSkyCoalitions), this.angelSkyCoalitions);
    }

    public AngelSkyCoalitions getAngelSkyCoalitions() {
        return angelSkyCoalitions;
    }
}
