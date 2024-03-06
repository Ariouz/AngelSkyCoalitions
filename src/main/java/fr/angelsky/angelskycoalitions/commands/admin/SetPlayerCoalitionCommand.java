package fr.angelsky.angelskycoalitions.commands.admin;

import fr.angelsky.angelskyapi.api.accounts.TempPlayerAccount;
import fr.angelsky.angelskyapi.api.enums.rank.Rank;
import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetPlayerCoalitionCommand implements CommandExecutor, TabCompleter {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public SetPlayerCoalitionCommand(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender instanceof Player player) {
            TempPlayerAccount tempPlayerAccount = angelSkyCoalitions.getAngelSkyApiInstance().getApiManager().getAccountManager().getAccount(player.getUniqueId());
            if (tempPlayerAccount.getRank().getPower() < Rank.ADMIN.getPower())
            {
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'executer cette commande");
                return false;
            }
        }

        if (args.length != 2)
        {
            sender.sendMessage(ChatColor.RED + "Syntaxe: /setplayercoalition <player> <coalition>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        CoalitionType coalitionType = CoalitionType.getById(args[1]);

        if (target == null)
        {
            sender.sendMessage(ChatColor.RED + "Joueur introuvable");
            return false;
        }

        if (coalitionType == null)
        {
            sender.sendMessage(ChatColor.RED + "Id de coalition invalide");
            return false;
        }

        CoalitionPlayer coalitionPlayer = angelSkyCoalitions.getManagerLoader().getCoalitionManager().getCoalitionPlayer(target);
        Coalition coalition = angelSkyCoalitions.getManagerLoader().getCoalitionManager().getCoalition(coalitionType);

        coalitionPlayer.setCoalition(coalition);
        sender.sendMessage(ChatColor.GREEN + "La coalition de " + target.getName() + " est désormais " + coalitionType.getDisplay());
        target.sendMessage(AngelSkyCoalitions.PREFIX + "Vous faites désormais parti de la coalition " + coalitionType.getDisplay());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length < 2)
            return result;
        for (CoalitionType coalitionType : CoalitionType.values())
        {
            if (coalitionType.getId().startsWith(args[1].toLowerCase()))
                result.add(coalitionType.getId());
        }
        return result;
    }
}
