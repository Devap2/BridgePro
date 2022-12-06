package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class OnPlayerInteract implements Listener {

    private final BridgePro main;

    public OnPlayerInteract(BridgePro main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) throws InterruptedException {

        Player p = e.getPlayer();

        /* Getting the player interact event, if it is in a certain gui, do this ... */

        // Checking if the item in the main hand of the player is a ... and if the item has a display name of "..."
        if (p.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Settings")) {

                // Checking if the player action is equals to right-clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        Inventory settingsGUI = Bukkit.createInventory(p, 36, net.md_5.bungee.api.ChatColor.DARK_GREEN + "Settings");

                        // The item stacks for in the particles.
                        ItemStack redstoneParticle = new ItemStack(Material.REDSTONE_TORCH);

                        // Setting the redstone particle meta/lore.
                        ItemMeta redstoneParticleMeta = redstoneParticle.getItemMeta();
                        assert redstoneParticleMeta != null;
                        redstoneParticleMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD + "Settings");
                        ArrayList<String> redstoneParticleLore = new ArrayList<>();
                        redstoneParticleLore.add(net.md_5.bungee.api.ChatColor.GREEN + "NOT IMPLEMENTED YET.");
                        redstoneParticleMeta.setLore(redstoneParticleLore);
                        redstoneParticle.setItemMeta(redstoneParticleMeta);

                        // Setting the items in the gui.
                        settingsGUI.setItem(13, redstoneParticle);

                        p.openInventory(settingsGUI);

                    }
                }
            }
        }
        else if (p.getInventory().getItemInMainHand().getType() == Material.PAPER) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(org.bukkit.ChatColor.DARK_GREEN + "" + org.bukkit.ChatColor.BOLD + "Go To Spawn")) {

                // Checking if the player action is equals to right-clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        /* Getting the correct lobby location for the player to spawn to on join. */

                        Location bridgeLobbyLocation = main.getConfig().getLocation("bridge-lobby-location");

                        if(bridgeLobbyLocation != null){
                            // Sending the player to lobby location.
                            p.teleport(bridgeLobbyLocation);
                        }
                        else{
                            p.sendMessage(ChatColor.RED + "Lobby location for bridge has not been set yet.");
                            p.sendMessage(ChatColor.GRAY + "Try: '/bp set-spawn' to set the spawn location.");
                        }
                    }
                }
            }
        }
        else if (p.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Block Selector")) {

                // Checking if the player action is equals to right-clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        p.performCommand("bp block-selector");

                    }
                }
            }
        }
        else if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Particle Selector")) {

                // Checking if the player action is equals to right-clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        p.performCommand("bp particles");

                    }
                }
            }
        }
        else if (p.getInventory().getItemInMainHand().getType() == Material.BOOK) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Bridge Information")) {

                // Checking if the player action is equals to right-clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        p.performCommand("bp info");

                    }
                }
            }
        }
    }
}
