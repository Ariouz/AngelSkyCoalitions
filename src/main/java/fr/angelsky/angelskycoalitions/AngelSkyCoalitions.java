package fr.angelsky.angelskycoalitions;

import fr.angelsky.angelskyapi.AngelskyApi;
import fr.angelsky.angelskyapi.api.AngelSkyApiInstance;
import fr.angelsky.angelskycoalitions.managers.ManagerLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AngelSkyCoalitions extends JavaPlugin {

    public static final String PREFIX = translateChatColorHex(ChatColor.translateAlternateColorCodes('&', "##FF008B#&lC##FD137D#&lo##FB256F#&la##F83862#&ll##F64B54#&li##F45D46#&lt##F27038#&li##EF832B#&lo##ED951D#&ln##EBA80F#&ls &f» &f"));

    private AngelSkyApiInstance angelSkyApiInstance;
    private ManagerLoader managerLoader;
    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("AngelskyApi") != null){
            AngelskyApi angelskyApi = (AngelskyApi) Bukkit.getPluginManager().getPlugin("AngelskyApi");
            assert angelskyApi != null;
            this.angelSkyApiInstance = angelskyApi.getAngelSkyApiInstance();

            this.getLogger().info("Instance API récupérée");
        }else throw new RuntimeException("L'API n'est pas détéctée !");

        this.managerLoader = new ManagerLoader(this);
        this.managerLoader.load();

        this.getLogger().info("Coalitions loaded");
    }

    @Override
    public void onDisable() {
        this.managerLoader.unload();
    }

    public ManagerLoader getManagerLoader() {
        return managerLoader;
    }

    public AngelSkyApiInstance getAngelSkyApiInstance() {
        return angelSkyApiInstance;
    }

    public static String translateChatColorHex(String stringOfTranslate) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < stringOfTranslate.length(); ++i) {
            if (i >= stringOfTranslate.length() - 9) {
                sb.append(stringOfTranslate.charAt(i));
            } else {
                String temp = stringOfTranslate.substring(i, i + 9);
                if (temp.startsWith("##") && temp.endsWith("#")) {
                    try {
                        Integer.parseInt(temp.substring(2, 8), 16);
                        sb.append("§x");
                        char[] c = temp.toCharArray();

                        for(int i1 = 2; i1 < c.length - 1; ++i1) {
                            sb.append('§').append(c[i1]);
                        }

                        i += 8;
                    } catch (NumberFormatException var7) {
                    }
                } else {
                    sb.append(stringOfTranslate.charAt(i));
                }
            }
        }

        return sb.toString();
    }
}
