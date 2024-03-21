package fr.angelsky.angelskycoalitions.managers.coalitions;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
        if (!this.angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().accountExists(player.getUniqueId()))
            return;
        this.coalitionPlayers.put(player.getUniqueId(), angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().loadPlayer(player));
    }

    public void createAccount(Player player, Coalition coalition)
    {
        angelSkyCoalitions.getManagerLoader().getSqlManager().getSqlCoalitionPlayer().createAccount(player.getUniqueId(), player.getName(), coalition.getCoalitionType());
        loadPlayer(player);
        CoalitionPlayer coalitionPlayer = getCoalitionPlayer(player);
        coalitionPlayer.setCoalition(coalition);
        Bukkit.broadcastMessage(angelSkyCoalitions.getAngelSkyApiInstance().translateChatColorHex(AngelSkyCoalitions.PREFIX + player.getName() + " a rejoint la coalition " + coalition.getCoalitionType().getHexColor() + coalition.getCoalitionType().getDisplay()));
        player.sendMessage(angelSkyCoalitions.getAngelSkyApiInstance().translateChatColorHex(AngelSkyCoalitions.PREFIX + "Vous avez rejoint la coalition " + coalition.getCoalitionType().getHexColor() + coalition.getCoalitionType().getDisplay()));
        player.teleport(coalition.getCoalitionType().getSpawn());
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 30, 50);
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

    public ArrayList<Coalition> getCoalitions() {
        return coalitions;
    }

    public CoalitionPlayer getCoalitionPlayer(Player player)
    {
        return this.coalitionPlayers.get(player.getUniqueId());
    }

    public HashMap<UUID, CoalitionPlayer> getCoalitionPlayers() {
        return coalitionPlayers;
    }

}
