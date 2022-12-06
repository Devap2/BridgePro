package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class OnPlayerJoin implements Listener {

    private final BridgePro main;

    public OnPlayerJoin(BridgePro main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        int ping = p.getPing();

        main.lobby.remove(p);
        main.ingame.remove(p);
        main.lobby.add(p);

        e.setJoinMessage("");

        if(main.lobby.contains(p)){

            /* Getting the correct lobby location for the player to spawn to on join. */

            Location bridgeLobbyLocation = main.getConfig().getLocation("bridge-lobby-location");

            if (bridgeLobbyLocation != null){
                //teleport the player to the spawn point
                p.teleport(bridgeLobbyLocation);
            }
            else{
                p.sendMessage(ChatColor.RED + "Lobby location for bridge has not been set yet.");
                p.sendMessage(ChatColor.GRAY + "Try: '/bp set-spawn' to set the spawn location.");
            }

            if(bridgeLobbyLocation != null){
                p.setGameMode(GameMode.SURVIVAL);
                p.setFoodLevel(20);
                p.setHealth(20.0);
                p.setWalkSpeed(0.2F);
                p.performCommand("sb");
                p.setInvulnerable(false);

                String joinMessage = main.getConfig().getString("join-message");
                //String defaultJoinMessage = (org.bukkit.ChatColor.GRAY + "[" + org.bukkit.ChatColor.GREEN + "+" + org.bukkit.ChatColor.GRAY + "] " + org.bukkit.ChatColor.GRAY + pname + " has joined the bridge.");

                // Setting the player's custom join message if set in config.
                if (main.getConfig().getBoolean("enable-join-message")) {
                    assert joinMessage != null;
                    p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', joinMessage));
                }

                // Enabling a sound played when a player joins.
                if (main.getConfig().getBoolean("enable-sound-on-join")) {
                    p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.E));
                }

                // Clearing player's inventory if true in config.
                if (main.getConfig().getBoolean("join-clear-inventory")) {
                    p.getInventory().clear();
                }


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
                blockMeta.setDisplayName(ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Block Selector");
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
        }
        else{
            return;
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();
        p.getInventory().clear();

        main.lobby.remove(p);
        main.ingame.remove(p);
    }
}