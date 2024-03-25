package fr.angelsky.angelskycoalitions.commands.player;

import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionManager;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionPermissions;
import net.minecraft.network.chat.ChatClickable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoalitionsCommand implements CommandExecutor {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public CoalitionsCommand(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ChatColor.RED + "Seul un joueur peut executer cette commande");
            return false;
        }
        CoalitionManager coalitionManager = angelSkyCoalitions.getManagerLoader().getCoalitionManager();
        if (!coalitionManager.hasCoalition(player))
        {
            if (!player.hasPermission(CoalitionPermissions.ACCESS.getPermission()))
            {
                player.sendMessage(AngelSkyCoalitions.PREFIX + ChatColor.RED + "Vous devez atteindre le niveau 25 pour débloquer l'accès aux coalitions.");
                return false;
            }
            angelSkyCoalitions.getManagerLoader().getMenuManager().getCoalitionsMenu().openCoalitionSelectionMenu(player);
            return true;
        }
        angelSkyCoalitions.getManagerLoader().getMenuManager().getCoalitionsMenu().openCoalitionMenu(player, angelSkyCoalitions.getManagerLoader().getCoalitionManager().getCoalitionPlayer(player));
        return true;
    }
}
