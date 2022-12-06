package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class OnPlayerRespawn implements Listener {

    private final BridgePro plugin;

    public OnPlayerRespawn(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) throws InterruptedException {
        Player p = e.getPlayer();

            /* Getting the correct lobby location for the player to spawn to on join. */

            Location bridgeLobbyLocation = plugin.getConfig().getLocation("bridge-lobby-location");

            // Sending the player to lobby location if true in config.
            if (bridgeLobbyLocation != null) {

                if(plugin.ingame.contains(p)){

                    e.setRespawnLocation(bridgeLobbyLocation);
                    p.getInventory().clear();
                    plugin.lobby.remove(p);
                    plugin.ingame.remove(p);
                    plugin.lobby.add(p);

                    // Setting default settings to the player on join.
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setHealth(20.0);
                    p.setFoodLevel(20);
                    p.setGlowing(false);

                    p.performCommand("sb");

                    /* Giving the player the lobby inventory items. */

                    // Settings ItemStack + ItemMeta
                    ItemStack settings = new ItemStack(Material.EMERALD);
                    ItemMeta settingsMeta = settings.getItemMeta();
                    assert settingsMeta != null;
                    settingsMeta.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Settings");
                    settingsMeta.setLore(Collections.singletonList(org.bukkit.ChatColor.GRAY + "Right click to modify the settings to your will!"));
                    settings.setItemMeta(settingsMeta);

                    // Particle Selector ItemStack + ItemMeta
                    ItemStack particleSelector = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta particleSelectorMeta = particleSelector.getItemMeta();
                    assert particleSelectorMeta != null;
                    particleSelectorMeta.setDisplayName(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "Particle Selector");
                    particleSelectorMeta.setLore(Arrays.asList(org.bukkit.ChatColor.GRAY + "Right click to open the particle selector menu.", org.bukkit.ChatColor.GRAY + "New: redstone, cloud and coal!"));
                    particleSelector.setItemMeta(particleSelectorMeta);

                    // Block Selector ItemStack + ItemMeta
                    ItemStack block = new ItemStack(Material.DIAMOND);
                    ItemMeta blockMeta = block.getItemMeta();
                    assert blockMeta != null;
                    blockMeta.setDisplayName(org.bukkit.ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Block Selector");
                    blockMeta.setLore(Collections.singletonList(org.bukkit.ChatColor.GRAY + "Right click to choose a block type."));
                    block.setItemMeta(blockMeta);

                    // Go To Spawn ItemStack + ItemMeta
                    ItemStack spawn = new ItemStack(Material.PAPER);
                    ItemMeta spawnMeta = spawn.getItemMeta();
                    assert spawnMeta != null;
                    spawnMeta.setDisplayName(org.bukkit.ChatColor.DARK_GREEN + "" + org.bukkit.ChatColor.BOLD + "Go To Spawn");
                    spawnMeta.setLore(Collections.singletonList(org.bukkit.ChatColor.GRAY + "Right click to go back to the bridge spawn."));
                    spawn.setItemMeta(spawnMeta);

                    // Server Information Book ItemStack + ItemMeta
                    ItemStack infoBook = new ItemStack(Material.BOOK);
                    ItemMeta infoBookMeta = infoBook.getItemMeta();
                    assert infoBookMeta != null;
                    infoBookMeta.setDisplayName(org.bukkit.ChatColor.DARK_GRAY + "" + org.bukkit.ChatColor.BOLD + "Bridge Information");
                    infoBookMeta.setLore(Collections.singletonList(org.bukkit.ChatColor.GRAY + "Right click to see information about Bridge-Pro."));
                    infoBook.setItemMeta(infoBookMeta);

                    // Checking the config to see if any of the inventory items on join are disabled

                    // Giving the player the items
                    p.getInventory().setItem(0, settings);
                    p.getInventory().setItem(1, particleSelector);
                    p.getInventory().setItem(4, block);
                    p.getInventory().setItem(7, spawn);
                    p.getInventory().setItem(8, infoBook);
                }

            } else {
                p.sendMessage(ChatColor.RED + "Bridge lobby location has not been set yet.");
            }
    }
}
