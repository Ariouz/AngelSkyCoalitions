package fr.angelsky.angelskycoalitions.listeners.player.level;

import fr.angelsky.angelskyapi.api.managers.luckperms.LuckPermsIntegrationManager;
import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionPermissions;
import fr.angelsky.skyblockapi.events.player.PlayerNextLevelEvent;
import net.luckperms.api.LuckPerms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerNextLevelListener implements Listener {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public PlayerNextLevelListener(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @EventHandler
    public void onPlayerNextLevel(PlayerNextLevelEvent event)
    {
        Player player = event.getTempPlayer().getPlayer();
        int rank = event.getTempPlayer().getPlayerLevel().getLevelRank();
        int newLevel = event.getNewLevel();

        if (rank == 0 && newLevel == 25)
        {
            LuckPermsIntegrationManager luckperms = angelSkyCoalitions.getAngelSkyApiInstance().getApiManager().getLuckPermsIntegrationManager();
            luckperms.addPermission(player.getUniqueId(), CoalitionPermissions.ACCESS.getPermission());
            player.sendMessage(AngelSkyCoalitions.PREFIX + "Vous avez débloqué l'accès aux coalitions. Faites /coalitions pour commencer.");
        }
    }

}
