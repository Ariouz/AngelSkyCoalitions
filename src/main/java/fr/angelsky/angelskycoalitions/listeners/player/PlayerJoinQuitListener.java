package fr.angelsky.angelskycoalitions.listeners.player;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionManager;
import fr.angelsky.angelskycoalitions.managers.sql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.units.qual.A;

public class PlayerJoinQuitListener implements Listener {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public PlayerJoinQuitListener(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        SQLManager sqlManager = angelSkyCoalitions.getManagerLoader().getSqlManager();

        if (!sqlManager.getSqlCoalitionPlayer().accountExists(player.getUniqueId()))
            return ;
        angelSkyCoalitions.getManagerLoader().getCoalitionManager().loadPlayer(player);
        CoalitionPlayer coalitionPlayer = angelSkyCoalitions.getManagerLoader().getCoalitionManager().getCoalitionPlayer(player);
        Bukkit.broadcastMessage(ChatColor.RED+ coalitionPlayer.getCoalition().getCoalitionType().getDisplay());
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        CoalitionManager coalitionManager = angelSkyCoalitions.getManagerLoader().getCoalitionManager();

        if (!coalitionManager.playerExists(player))
            return ;
        coalitionManager.savePlayer(player);
    }

}
