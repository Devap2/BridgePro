package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.Objects;

public class OnSwapHandItems implements Listener {

    private final BridgePro plugin;

    public OnSwapHandItems(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent e){

        /* Checking the player swap event */

        Player p = e.getPlayer();

        if(plugin.getConfig().getBoolean("disable-item-swap")){
            if(plugin.lobby.contains(p) || plugin.ingame.contains(p)) {
                // Checking if the item which is swapped in offhand is equal to emerald, diamond, paper, book or clock. If true, cancel the event.
                if(Objects.requireNonNull(e.getOffHandItem()).getType().equals(Material.EMERALD) ||
                        e.getOffHandItem().getType().equals(Material.BOOK) ||
                        (e.getOffHandItem().getType().equals(Material.PAPER)) ||
                        (e.getOffHandItem().getType().equals(Material.DIAMOND)) ||
                        (e.getOffHandItem().getType().equals(Material.STICK)) ||
                        (e.getOffHandItem().getType().equals(Material.BLAZE_ROD))) {
                    if(e.getOffHandItem().hasItemMeta()){
                        e.setCancelled(true);
                    }
                }
                // Checking if the item which is swapped in main hand is equal to emerald, diamond, paper, book or clock. If true, cancel the event.
                if(Objects.requireNonNull(e.getMainHandItem()).getType().equals(Material.EMERALD) ||
                        e.getMainHandItem().getType().equals(Material.BOOK) ||
                        (e.getOffHandItem().getType().equals(Material.PAPER)) ||
                        (e.getOffHandItem().getType().equals(Material.DIAMOND)) ||
                        (e.getOffHandItem().getType().equals(Material.STICK)) ||
                        (e.getOffHandItem().getType().equals(Material.BLAZE_ROD))) {
                    if(e.getMainHandItem().hasItemMeta()){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
