package fr.angelsky.angelskycoalitions.coalition;

import fr.angelsky.angelskyapi.api.enums.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;

public enum CoalitionType {

    // ROSE (blossom)
    // ROUGE (sakura)
    // VERT (crimson -> passer en vert)
    // BLANC (artic)

    OSARON("Osaron", "osaron_emblem", "##EB9BBA#", "city_osaron", new Location(null, -1.5, 88, -0.5, -90f, 0f)), // ROSE
    PROGATE("Progate", "progate_emblem", "##377252#", "city_progate", new Location(null, 0.5, 159, 0.5, 0f, 0f)), // VERT
    MORIA("Moria", "moria_emblem", "##d7f6f6#", "city_moria", new Location(null, 5.5, 106, 137.5, 180f, 0f)), // Artic
    ADAGAN("Adagan", "adagan_emblem", "##E60033#", "city_adagan", new Location(null, -0.5, 27, -0.5, 180f, 0f)) // ROUGE

    ;

    private final String display;
    private final String emblem;
    private final String hexColor;
    private final String worldName;
    private final Location spawn;

    CoalitionType(String display, String emblem, String hexColor, String worldName, Location spawn)
    {
        this.display = display;
        this.emblem = emblem;
        this.hexColor = hexColor;
        this.worldName = worldName;
        this.spawn = spawn;
    }

    public static CoalitionType getById(String id)
    {
        return Arrays.stream(values()).filter(coalitionType -> coalitionType.display.toLowerCase().equals(id)).findFirst().orElse(null);
    }

    public String getEmblem() {
        return emblem;
    }

    public Location getSpawn() {
        Location loc = spawn;
        loc.setWorld(Bukkit.getWorld(worldName));
        return loc;
    }

    public String getWorldName() {
        return worldName;
    }

    public String getDisplay() {
        return display;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getId()
    {
        return this.display.toLowerCase();
    }
}
