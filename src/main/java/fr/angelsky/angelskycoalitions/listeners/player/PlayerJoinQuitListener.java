package fr.angelsky.angelskycoalitions.listeners.player;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionManager;
import fr.angelsky.angelskycoalitions.managers.sql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.checkerframework.checker.units.qual.A;

public class PlayerJoinQuitListener implements Listener {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public PlayerJoinQuitListener(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if (!angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().accountExists(player.getUniqueId()))
        {
            System.out.println("Create account for player");
            angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().createAccount(player.getUniqueId(), player.getName(), CoalitionType.NONE);
        }
        angelSkyCoalitions.getManagerLoader().getCoalitionManager().loadPlayer(player);

        CoalitionPlayer coalitionPlayer = angelSkyCoalitions.getManagerLoader().getCoalitionManager().getCoalitionPlayer(player);
        if (coalitionPlayer.getCoalition().getCoalitionType() == CoalitionType.NONE) return;
        player.setPlayerListName(coalitionPlayer.getCoalition().getOraxenBannerChar() + " " + player.getPlayerListName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        CoalitionManager coalitionManager = angelSkyCoalitions.getManagerLoader().getCoalitionManager();

        coalitionManager.savePlayer(player);
    }

}
