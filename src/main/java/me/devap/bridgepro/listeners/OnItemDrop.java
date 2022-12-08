package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnItemDrop implements Listener {

    private final BridgePro plugin;

    public OnItemDrop(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){

        // Another way to do: Making a list with the ItemStacks and checking if that item is in it.

        Player p = e.getPlayer();

        if(plugin.lobby.contains(p) || plugin.ingame.contains(p)){
            //Checking if the dropped item is equal to an emerald, paper, fire-charge or book.
            if(e.getItemDrop().getItemStack().getType().equals(Material.EMERALD) ||
                    (e.getItemDrop().getItemStack().getType().equals(Material.PAPER)) ||
                    (e.getItemDrop().getItemStack().getType().equals(Material.BLAZE_ROD)) ||
                    (e.getItemDrop().getItemStack().getType().equals(Material.DIAMOND)) ||
                    (e.getItemDrop().getItemStack().getType().equals(Material.BOOK))) {
                e.setCancelled(true);
            }
            else if(e.getItemDrop().getItemStack().getType().equals(Material.STICK) && e.getItemDrop().getItemStack().hasItemMeta()){
                e.setCancelled(true);
            }
        }

    }
}
