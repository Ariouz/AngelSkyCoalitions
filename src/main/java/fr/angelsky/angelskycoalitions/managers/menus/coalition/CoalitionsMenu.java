package fr.angelsky.angelskycoalitions.managers.menus.coalition;

import fr.angelsky.angelskyapi.api.utils.HexColors;
import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.managers.coalitions.CoalitionManager;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoalitionsMenu {

    private final AngelSkyCoalitions angelSkyCoalitions;

    public CoalitionsMenu(AngelSkyCoalitions angelSkyCoalitions)
    {
        this.angelSkyCoalitions = angelSkyCoalitions;
    }

    @SuppressWarnings("deprecation")
    public void openCoalitionSelectionMenu(Player player)
    {
        FastInv inv = new FastInv(5*9, "Coalitions");

        ItemStack border = new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE).name(ChatColor.RED+ "").build();
        for (int i : inv.getBorders())
        {
            inv.setItem(i, border, event -> {
                event.setCancelled(true);
            });
        }
        CoalitionManager coalitionManager = angelSkyCoalitions.getManagerLoader().getCoalitionManager();

        int[] slots = {20, 21, 23, 24};
        int i = 0;
        for (Coalition coalition : coalitionManager.getCoalitions())
        {
            ItemStack coaItem = new ItemBuilder(OraxenItems.getItemById(coalition.getOraxenEmblem()).build())
                    .name(angelSkyCoalitions.getAngelSkyApiInstance().translateChatColorHex(HexColors.LIGHTER_GRAY + "Rejoindre " + coalition.getCoalitionType().getHexColor() + coalition.getCoalitionType().getDisplay()))
                    .build();
            inv.setItem(slots[i], coaItem, event -> {
                event.setCancelled(true);
                angelSkyCoalitions.getManagerLoader().getCoalitionManager().createAccount(player, coalition);
            });
            i++;
        }

        player.openInventory(inv.getInventory());
    }

    public void openCoalitionMenu(Player player, CoalitionPlayer coalitionPlayer)
    {
        FastInv inv = new FastInv(5*9, "Coalitions");


        inv.open(player);
    }

}
