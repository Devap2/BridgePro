package me.devap.bridgepro.commands;

import me.devap.bridgepro.BridgePro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class BridgeProCMDS implements CommandExecutor {

    private BridgePro main;

    public BridgeProCMDS(BridgePro main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        /* Setting different functions within the '/bp <insert command here>' commands. */

        // Checking if the sender != null and is and player.
        if (sender != null) {
            if (command.getName().equalsIgnoreCase("bp")) {
                // If the command is equal to 'lp' and the argument length is 0 do ...
                if (args.length == 0) {

                    // Sending the player the server information message.
                    p.sendMessage("");
                    p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");
                    p.sendMessage(org.bukkit.ChatColor.RED + "Bridge-Pro Plugin Help:");
                    p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp help " + org.bukkit.ChatColor.GRAY + "To show this page");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp join " + org.bukkit.ChatColor.GRAY + "To join a bridge game");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp info " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp updates " + org.bukkit.ChatColor.GRAY + "To show the plugin update information");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp version " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp reload " + org.bukkit.ChatColor.GRAY + "To reload the LobbyPro plugin");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-spawn " + org.bukkit.ChatColor.GRAY + "To set the bridge spawn location");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-boundary-one " + org.bukkit.ChatColor.GRAY + "To set the first boundary coordinate");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-boundary-two " + org.bukkit.ChatColor.GRAY + "To set the second boundary coordinate");
                    p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp particles " + org.bukkit.ChatColor.GRAY + "To open the particle gui");
                    p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");

                }
                // Else, if the arguments 0 are 'setspawn' or 'setlobby' set the server lobby/spawn location.
                else {
                    if (args[0].equalsIgnoreCase("set-spawn")) {

                        Location location = p.getLocation();

                        p.sendMessage(ChatColor.GREEN + "Successfully set the bridge spawn location.");

                        // Getting the configuration file and setting the location values.
                        main.getConfig().set("bridge-lobby-location.World-name", Objects.requireNonNull(location.getWorld()).getName());
                        main.getConfig().set("bridge-lobby-location.X", location.getX());
                        main.getConfig().set("bridge-lobby-location.Y", location.getY());
                        main.getConfig().set("bridge-lobby-location.Z", location.getZ());
                        main.getConfig().set("bridge-lobby-location.Yaw", location.getYaw());
                        main.getConfig().set("bridge-lobby-location.Pitch", location.getPitch());

                        // Saving the changes made in the configuration file.
                        main.getConfig().options().copyDefaults(true);
                        main.saveConfig();
                    } else if (args[0].equalsIgnoreCase("particles")) {

                            Inventory particleGUI = Bukkit.createInventory(p, 36, ChatColor.DARK_RED + "Particle Selector");

                            // The item stacks for in the particles.
                            ItemStack redstoneParticle = new ItemStack(Material.REDSTONE_TORCH);
                            ItemStack heartParticle = new ItemStack(Material.RED_DYE);
                            ItemStack waterParticle = new ItemStack(Material.WATER_BUCKET);
                            ItemStack lavaParticle = new ItemStack(Material.LAVA_BUCKET);
                            ItemStack potionParticle = new ItemStack(Material.POTION);
                            ItemStack snowParticle = new ItemStack(Material.SNOWBALL);
                            ItemStack musicParticle = new ItemStack(Material.NOTE_BLOCK);
                            ItemStack closeGUI = new ItemStack(Material.REDSTONE);

                            // Setting the redstone particle meta/lore.
                            ItemMeta redstoneParticleMeta = redstoneParticle.getItemMeta();
                            assert redstoneParticleMeta != null;
                            redstoneParticleMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Redstone");
                            ArrayList<String> redstoneParticleLore = new ArrayList<>();
                            redstoneParticleLore.add(ChatColor.GREEN + "Gives you a cool redstone particle effect.");
                            redstoneParticleMeta.setLore(redstoneParticleLore);
                            redstoneParticle.setItemMeta(redstoneParticleMeta);

                            // Setting the heart particle meta/lore.
                            ItemMeta heartParticleMeta = heartParticle.getItemMeta();
                            assert heartParticleMeta != null;
                            heartParticleMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lHeart"));
                            ArrayList<String> heartParticleLore = new ArrayList<>();
                            heartParticleLore.add(ChatColor.GREEN + "Gives you a cool heart particle effect.");
                            heartParticleMeta.setLore(heartParticleLore);
                            heartParticle.setItemMeta(heartParticleMeta);

                            // Setting the water particle meta/lore.
                            ItemMeta waterParticleMeta = waterParticle.getItemMeta();
                            assert waterParticleMeta != null;
                            waterParticleMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Water");
                            ArrayList<String> waterParticleLore = new ArrayList<>();
                            waterParticleLore.add(ChatColor.GREEN + "Gives you a cool water particle effect.");
                            waterParticleMeta.setLore(waterParticleLore);
                            waterParticle.setItemMeta(waterParticleMeta);

                            // Setting the lava particle meta/lore.
                            ItemMeta lavaParticleMeta = lavaParticle.getItemMeta();
                            assert lavaParticleMeta != null;
                            lavaParticleMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lava");
                            ArrayList<String> lavaParticleLore = new ArrayList<>();
                            lavaParticleLore.add(ChatColor.GREEN + "Gives you a cool lava particle effect.");
                            lavaParticleMeta.setLore(lavaParticleLore);
                            lavaParticle.setItemMeta(lavaParticleMeta);

                            // Setting the potion particle meta/lore.
                            ItemMeta potionParticleMeta = potionParticle.getItemMeta();
                            assert potionParticleMeta != null;
                            potionParticleMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Potion");
                            ArrayList<String> potionParticleLore = new ArrayList<>();
                            potionParticleLore.add(ChatColor.GREEN + "Gives you a cool potion particle effect.");
                            potionParticleMeta.setLore(potionParticleLore);
                            potionParticle.setItemMeta(potionParticleMeta);

                            // Setting the snow particle meta/lore.
                            ItemMeta snowParticleMeta = snowParticle.getItemMeta();
                            assert snowParticleMeta != null;
                            snowParticleMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Snow");
                            ArrayList<String> snowParticleLore = new ArrayList<>();
                            snowParticleLore.add(ChatColor.GREEN + "Gives you a cool snow particle effect.");
                            snowParticleMeta.setLore(snowParticleLore);
                            snowParticle.setItemMeta(snowParticleMeta);

                            // Setting the music particle meta/lore.
                            ItemMeta musicParticleMeta = musicParticle.getItemMeta();
                            assert musicParticleMeta != null;
                            musicParticleMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Music");
                            ArrayList<String> musicParticleLore = new ArrayList<>();
                            musicParticleLore.add(ChatColor.GREEN + "Gives you a cool music particle effect.");
                            musicParticleMeta.setLore(musicParticleLore);
                            musicParticle.setItemMeta(musicParticleMeta);

                            // Setting the close menu meta/lore.
                            ItemMeta closeGUIMeta = closeGUI.getItemMeta();
                            assert closeGUIMeta != null;
                            closeGUIMeta.setDisplayName(ChatColor.DARK_GRAY + "Close Menu");
                            ArrayList<String> closeGUILore = new ArrayList<>();
                            closeGUILore.add(ChatColor.GRAY + "Left click to leave the menu.");
                            closeGUIMeta.setLore(closeGUILore);
                            closeGUI.setItemMeta(closeGUIMeta);

                            // Setting the items in the gui.
                            particleGUI.setItem(10, redstoneParticle);
                            particleGUI.setItem(11, heartParticle);
                            particleGUI.setItem(12, waterParticle);
                            particleGUI.setItem(13, lavaParticle);
                            particleGUI.setItem(14, potionParticle);
                            particleGUI.setItem(15, snowParticle);
                            particleGUI.setItem(16, musicParticle);
                            particleGUI.setItem(31, closeGUI);

                            p.openInventory(particleGUI);

                    }
                    else if (args[0].equalsIgnoreCase("set-boundary-one")) {

                        if(sender instanceof Player) {

                            Block block = p.getLocation().getBlock();
                            World world = p.getWorld();
                            String worldname = world.getName();
                            Location location = p.getLocation();

                            main.getConfig().set("game-boundary-one.World-name", location.getWorld().getName());
                            main.getConfig().set("game-boundary-one.X", location.getX());
                            main.getConfig().set("game-boundary-one.Y", location.getY());
                            main.getConfig().set("game-boundary-one.Z", location.getZ());
                            main.getConfig().set("game-boundary-one.Yaw", location.getYaw());
                            main.getConfig().set("game-boundary-one.Pitch", location.getPitch());

                            main.saveConfig();

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&nFirst&r&a spawn location set."));

                        }
                        else{
                            System.out.println(ChatColor.RED + "You are not allowed to execute this command.");
                        }

                    }
                    else if(args[0].equalsIgnoreCase("set-boundary-two")){

                        if(sender instanceof Player) {

                            Block block = p.getLocation().getBlock();
                            World world = p.getWorld();
                            String worldname = world.getName();
                            Location location = p.getLocation();

                            main.getConfig().set("game-boundary-two.World-name", location.getWorld().getName());
                            main.getConfig().set("game-boundary-two.X", location.getX());
                            main.getConfig().set("game-boundary-two.Y", location.getY());
                            main.getConfig().set("game-boundary-two.Z", location.getZ());
                            main.getConfig().set("game-boundary-two.Yaw", location.getYaw());
                            main.getConfig().set("game-boundary-two.Pitch", location.getPitch());

                            main.saveConfig();

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&nSecond&r&a spawn location set."));


                        }
                        else{
                            System.out.println(ChatColor.RED + "You are not allowed to execute this command.");
                        }

                    }
                    else if (args[0].equalsIgnoreCase("info")) {

                        // Sending the player the server information message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED + "Bridge-Pro Plugin Information:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Author: " + org.bukkit.ChatColor.GRAY + "Devap");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Developer: " + org.bukkit.ChatColor.GRAY + "Devap");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Version: " + org.bukkit.ChatColor.GRAY + "v1.0.0");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Release: " + org.bukkit.ChatColor.GRAY + "11-30-2022");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Downloads: " + org.bukkit.ChatColor.GRAY + "See spigot page.");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Language: " + org.bukkit.ChatColor.GRAY + "English.");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");

                    } else if (args[0].equalsIgnoreCase("help")) {

                        // Sending the player the server information message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED + "Bridge-Pro Plugin Help:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp help " + org.bukkit.ChatColor.GRAY + "To show this page");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp join " + org.bukkit.ChatColor.GRAY + "To join a bridge game");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp info " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp updates " + org.bukkit.ChatColor.GRAY + "To show the plugin update information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp version " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp reload " + org.bukkit.ChatColor.GRAY + "To reload the LobbyPro plugin");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-spawn " + org.bukkit.ChatColor.GRAY + "To set the bridge spawn location");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-boundary-one " + org.bukkit.ChatColor.GRAY + "To set the first boundary coordinate");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp set-boundary-two " + org.bukkit.ChatColor.GRAY + "To set the second boundary coordinate");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/bp particles " + org.bukkit.ChatColor.GRAY + "To open the particle gui");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------");

                    } else if (args[0].equalsIgnoreCase("updates")) {

                        // Sending the player the server update message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED + "Bridge-Pro Update Information:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "New settings menu");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "New map: 'future'");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "More configurability");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-----------------------------");

                    } else if (args[0].equalsIgnoreCase("version")) {

                        // Sending the player the plugin version message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED + "Bridge-Pro Plugin Version:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Current Plugin Version " + org.bukkit.ChatColor.GRAY + "v1.0.0");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "-------------------------");

                    } else {
                        if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {

                            p.sendMessage(ChatColor.GRAY + "Reloading the plugin configuration file.");
                            main.reloadConfig();
                            main.saveDefaultConfig();
                            p.sendMessage(ChatColor.GREEN + "Reload complete.");

                        }
                        else{
                            p.sendMessage(ChatColor.RED + "Wrong usage. Type '/bp help' to get a list of all commands.");
                        }
                    }
                }
            }
        }
        return false;
    }
}
