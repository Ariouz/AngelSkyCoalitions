package fr.angelsky.angelskycoalitions.managers.coalitions;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoalitionManager {

    private final AngelSkyCoalitions angelSkyCoalitions;

    private final HashMap<UUID, CoalitionPlayer> coalitionPlayers = new HashMap<>();
    private final ArrayList<Coalition> coalitions = new ArrayList<>();

    public CoalitionManager(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }
    public void load() {
        this.loadCoalitions();
        Bukkit.getOnlinePlayers().forEach(this::loadPlayer);
    }

    public void loadCoalitions()
    {
        for (CoalitionType coalitionType : CoalitionType.values())
        {
            Coalition coalition = angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalition().loadCoalition(coalitionType);
            this.coalitions.add(coalition);
        }
    }

    public Coalition getCoalition(CoalitionType coalitionType)
    {
        return this.coalitions.stream().filter(coalition -> coalition.getCoalitionType() == coalitionType).findFirst().orElse(null);
    }

    public void loadPlayer(Player player)
    {
        this.coalitionPlayers.put(player.getUniqueId(), angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().loadPlayer(player));
    }

    public void savePlayer(Player player)
    {
        this.angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().save(getCoalitionPlayer(player));
        this.coalitionPlayers.remove(player.getUniqueId());
    }

    public boolean playerExists(Player player)
    {
        return this.getCoalitionPlayer(player) != null;
    }

    public void saveAll() {
        for (Map.Entry<UUID, CoalitionPlayer> entries : this.coalitionPlayers.entrySet())
        {
            this.angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().save(entries.getValue());
        }
    }

    public CoalitionPlayer getCoalitionPlayer(Player player)
    {
        return this.coalitionPlayers.get(player.getUniqueId());
    }

    public HashMap<UUID, CoalitionPlayer> getCoalitionPlayers() {
        return coalitionPlayers;
    }

}
