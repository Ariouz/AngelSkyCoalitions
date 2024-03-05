package fr.angelsky.angelskycoalitions.coalition;

import java.util.Arrays;

public enum CoalitionType {

    // ROSE (blossom)
    // ROUGE (sakura)
    // VERT (crimson -> passer en vert)
    // BLANC (artic)

    OSARON("Osaron", ""),
    PROGATE("Progate", ""),
    MORIA("Moria", "#d7f6f6"), // Artic
    ADAGAN("Adagan"," ")

    ;

    private final String display;
    private final String hexColor;

    CoalitionType(String display, String hexColor)
    {
        this.display = display;
        this.hexColor = hexColor;
    }

    public static CoalitionType getById(String id)
    {
        return Arrays.stream(values()).filter(coalitionType -> coalitionType.display.toLowerCase().equals(id)).findFirst().orElse(null);
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
