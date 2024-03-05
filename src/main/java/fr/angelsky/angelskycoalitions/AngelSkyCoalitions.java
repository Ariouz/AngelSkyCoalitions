package fr.angelsky.angelskycoalitions;

import fr.angelsky.angelskyapi.AngelskyApi;
import fr.angelsky.angelskyapi.api.AngelSkyApiInstance;
import fr.angelsky.angelskycoalitions.managers.ManagerLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AngelSkyCoalitions extends JavaPlugin {

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
}
