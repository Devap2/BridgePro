package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnInventoryClick implements Listener {

    private final BridgePro plugin;

    // Particle type Lists
    public final ArrayList<Player> redstoneParticleList = new ArrayList<>();
    public final ArrayList<Player> heartParticleList = new ArrayList<>();
    public final ArrayList<Player> lavaParticleList = new ArrayList<>();
    public final ArrayList<Player> waterParticleList = new ArrayList<>();
    public final ArrayList<Player> potionParticleList = new ArrayList<>();
    public final ArrayList<Player> snowParticleList = new ArrayList<>();
    public final ArrayList<Player> musicParticleList = new ArrayList<>();

    // Block type lists
    public final ArrayList<Player> grayTerracottaList = new ArrayList<>();
    public final ArrayList<Player> greenTerracottaList = new ArrayList<>();
    public final ArrayList<Player> cyanTerracottaList = new ArrayList<>();
    public final ArrayList<Player> purpleTerracottaList = new ArrayList<>();
    public final ArrayList<Player> redTerracottaList = new ArrayList<>();

    public OnInventoryClick(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        // Cancelling the ability of moving items in the inventory.
        if (e.getWhoClicked() instanceof Player && e.getClickedInventory() != null) {
            List<ItemStack> items = new ArrayList<>();
            items.add(e.getCurrentItem());
            items.add(e.getCursor());
            // Disabling the number_key event on inventory click.
            items.add((e.getClick() == org.bukkit.event.inventory.ClickType.NUMBER_KEY) ? e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) : e.getCurrentItem());
            // if the ItemStack has one of the events above, set cancelled.
            for (ItemStack item : items) {
                if (item != null && item.hasItemMeta()) {
                    e.setCancelled(true);
                }
            }
        }
        // Checking if the clicked person is op, or is in creative mode.
        if (e.getWhoClicked().isOp() && e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        }

        InventoryView view = e.getView();

        /* ---------- PARTICLE SELECTOR MENU SECTION ---------- */

        // NOTE: THE CODE UNDERNEATH IS VERY BAD AND INEFFICIENT. DON'T REPLICATE THIS... I WILL RECODE THIS PART LATER.

        // Checking if the menu title == Particle Selector, then checking the slots
        if (view.getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Particle Selector")) {
            // Checking the slot and the item, if correct, execute.
            if (e.getSlot() == 10) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE_TORCH)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 11) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.RED_DYE)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 12) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.WATER_BUCKET)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 13) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.LAVA_BUCKET)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 14) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.POTION)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 15) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.SNOWBALL)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            } else if (e.getSlot() == 16) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.NOTE_BLOCK)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        } else if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        } else {
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                    }
                }
            }
            // Close inventory button.
            else if (e.getSlot() == 31) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();
                    }
                }
            }
        }

        /* ---------- BLOCK SELECTOR MENU SECTION ---------- */

        else if (view.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Block Selector")) {
            // Checking the slot and the item, if correct, execute.
            if (e.getSlot() == 11) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.GRAY_TERRACOTTA)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (grayTerracottaList.contains(p)) {
                            grayTerracottaList.remove(p);
                            p.sendMessage(ChatColor.RED + "Gray blocks de-selected.");
                        } else if (greenTerracottaList.contains(p)) {
                            greenTerracottaList.remove(p);
                            grayTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Gray blocks selected.");
                        } else if (cyanTerracottaList.contains(p)) {
                            cyanTerracottaList.remove(p);
                            grayTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Gray blocks selected.");
                        } else if (purpleTerracottaList.contains(p)) {
                            purpleTerracottaList.remove(p);
                            grayTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Gray blocks selected.");
                        } else if (redTerracottaList.contains(p)) {
                            redTerracottaList.remove(p);
                            grayTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Gray blocks selected.");
                        } else {
                            grayTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Gray blocks selected.");
                        }
                    }
                }
            }
            else if (e.getSlot() == 12) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.GREEN_TERRACOTTA)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (greenTerracottaList.contains(p)) {
                            greenTerracottaList.remove(p);
                            p.sendMessage(ChatColor.RED + "Green blocks de-selected.");
                        } else if (grayTerracottaList.contains(p)) {
                            grayTerracottaList.remove(p);
                            greenTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Green blocks selected.");
                        } else if (cyanTerracottaList.contains(p)) {
                            cyanTerracottaList.remove(p);
                            greenTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Green blocks selected.");
                        } else if (purpleTerracottaList.contains(p)) {
                            purpleTerracottaList.remove(p);
                            greenTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Green blocks selected.");
                        } else if (redTerracottaList.contains(p)) {
                            redTerracottaList.remove(p);
                            greenTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Green blocks selected.");
                        } else {
                            greenTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Green blocks selected.");
                        }
                    }
                }
            }
            else if (e.getSlot() == 13) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.CYAN_TERRACOTTA)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (cyanTerracottaList.contains(p)) {
                            cyanTerracottaList.remove(p);
                            p.sendMessage(ChatColor.RED + "Cyan blocks de-selected.");
                        } else if (grayTerracottaList.contains(p)) {
                            grayTerracottaList.remove(p);
                            cyanTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Cyan blocks selected.");
                        } else if (greenTerracottaList.contains(p)) {
                            greenTerracottaList.remove(p);
                            cyanTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Cyan blocks selected.");
                        } else if (purpleTerracottaList.contains(p)) {
                            purpleTerracottaList.remove(p);
                            cyanTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Cyan blocks selected.");
                        } else if (redTerracottaList.contains(p)) {
                            redTerracottaList.remove(p);
                            cyanTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Cyan blocks selected.");
                        } else {
                            cyanTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Cyan blocks selected.");
                        }
                    }
                }
            }
            else if (e.getSlot() == 14) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.PURPLE_TERRACOTTA)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (purpleTerracottaList.contains(p)) {
                            purpleTerracottaList.remove(p);
                            p.sendMessage(ChatColor.RED + "Purple blocks de-selected.");
                        } else if (grayTerracottaList.contains(p)) {
                            grayTerracottaList.remove(p);
                            purpleTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Purple blocks selected.");
                        } else if (greenTerracottaList.contains(p)) {
                            greenTerracottaList.remove(p);
                            purpleTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Purple blocks selected.");
                        } else if (cyanTerracottaList.contains(p)) {
                            cyanTerracottaList.remove(p);
                            purpleTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Purple blocks selected.");
                        } else if (redTerracottaList.contains(p)) {
                            redTerracottaList.remove(p);
                            purpleTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Purple blocks selected.");
                        } else {
                            purpleTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Purple blocks selected.");
                        }
                    }
                }
            }
            else if (e.getSlot() == 15) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.RED_TERRACOTTA)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();

                        if (redTerracottaList.contains(p)) {
                            redTerracottaList.remove(p);
                            p.sendMessage(ChatColor.RED + "Red blocks de-selected.");
                        } else if (grayTerracottaList.contains(p)) {
                            grayTerracottaList.remove(p);
                            redTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Red blocks selected.");
                        } else if (greenTerracottaList.contains(p)) {
                            greenTerracottaList.remove(p);
                            redTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Red blocks selected.");
                        } else if (cyanTerracottaList.contains(p)) {
                            cyanTerracottaList.remove(p);
                            redTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Red blocks selected.");
                        } else if (purpleTerracottaList.contains(p)) {
                            purpleTerracottaList.remove(p);
                            redTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Red blocks selected.");
                        } else {
                            redTerracottaList.add(p);
                            p.sendMessage(ChatColor.GREEN + "Red blocks selected.");
                        }
                    }
                }
            }
            // Close inventory button.
            else if (e.getSlot() == 31) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        World world = p.getWorld();

        /* PARTICLE SECTION */

        // When a player is moving and a certain particle is selected in the particle GUI, add the trail.

        if (redstoneParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(200, 0, 55), 1.0F);
                p.spawnParticle(Particle.REDSTONE, p.getLocation(), 50, dustOptions);
            }
        }
        else if(heartParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.HEART, p.getLocation(), 1);
            }
        }
        else if(waterParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 10);
            }
        }
        else if(lavaParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.FALLING_LAVA, p.getLocation(), 10);
            }
        }
        else if (potionParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                double red = 0 / 255D;
                double green = 127 / 255D;
                double blue = 255 / 255D;
                p.spawnParticle(Particle.SPELL_MOB, p.getLocation(), 0, red, green, blue, 1);
            }
        }
        else if (snowParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.SNOWBALL, p.getLocation(), 10);
            }
        }
        else if (musicParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                double note = 6 / 24D; // 6 is the value of the red note
                p.spawnParticle(Particle.NOTE, p.getLocation(), 0, note, 0, 0, 1);
            }
        }

        /* BLOCK SECTION */

        if(plugin.lobby.contains(p)) {
            if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                if (Objects.requireNonNull(e.getTo()).getY() < 80) {
                    if (plugin.getConfig().getLocation("bridge-lobby-location") != null) {
                        // Do stuff with this player below the y-axis
                        plugin.ingame.remove(p);
                        plugin.lobby.remove(p);
                        plugin.ingame.add(p);

                        p.closeInventory();
                        p.getOpenInventory().close();
                        p.getInventory().clear();
                        p.setFireTicks(0);

                        // Giving the player the blocks for in-game.
                        ItemStack weapon = new ItemStack(Material.STICK, 1);
                        ItemStack bow = new ItemStack(Material.BOW, 1);
                        ItemStack arrows = new ItemStack(Material.ARROW, 32);
                        ItemStack food = new ItemStack(Material.COOKED_BEEF, 32);

                        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

                        // Adding the enchantments to the items
                        //weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                        weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

                        // Adding the player armour
                        p.getInventory().setHelmet(helmet);
                        p.getInventory().setChestplate(chestplate);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);

                        p.getInventory().setItem(0, weapon);
                        p.getInventory().setItem(1, bow);
                        p.getInventory().setItem(8, food);
                        p.getOpenInventory().setItem(9, arrows);

                        if (grayTerracottaList.contains(p)) {

                            ItemStack grayBlocks = new ItemStack(Material.LIGHT_GRAY_WOOL, 64);

                            p.getInventory().setItem(2, grayBlocks);
                            p.getInventory().setItem(3, grayBlocks);
                            p.getInventory().setItem(4, grayBlocks);
                            p.getInventory().setItem(5, grayBlocks);
                            p.getInventory().setItem(6, grayBlocks);
                            p.getInventory().setItem(7, grayBlocks);

                        } else if (greenTerracottaList.contains(p)) {

                            ItemStack greenBlocks = new ItemStack(Material.GREEN_WOOL, 64);

                            p.getInventory().setItem(2, greenBlocks);
                            p.getInventory().setItem(3, greenBlocks);
                            p.getInventory().setItem(4, greenBlocks);
                            p.getInventory().setItem(5, greenBlocks);
                            p.getInventory().setItem(6, greenBlocks);
                            p.getInventory().setItem(7, greenBlocks);

                        } else if (cyanTerracottaList.contains(p)) {

                            ItemStack cyanBlocks = new ItemStack(Material.CYAN_WOOL, 64);

                            p.getInventory().setItem(2, cyanBlocks);
                            p.getInventory().setItem(3, cyanBlocks);
                            p.getInventory().setItem(4, cyanBlocks);
                            p.getInventory().setItem(5, cyanBlocks);
                            p.getInventory().setItem(6, cyanBlocks);
                            p.getInventory().setItem(7, cyanBlocks);

                        } else if (purpleTerracottaList.contains(p)) {

                            ItemStack purpleBlocks = new ItemStack(Material.PURPLE_WOOL, 64);

                            p.getInventory().setItem(2, purpleBlocks);
                            p.getInventory().setItem(3, purpleBlocks);
                            p.getInventory().setItem(4, purpleBlocks);
                            p.getInventory().setItem(5, purpleBlocks);
                            p.getInventory().setItem(6, purpleBlocks);
                            p.getInventory().setItem(7, purpleBlocks);

                        } else if (redTerracottaList.contains(p)) {

                            ItemStack redBlocks = new ItemStack(Material.RED_WOOL, 64);

                            p.getInventory().setItem(2, redBlocks);
                            p.getInventory().setItem(3, redBlocks);
                            p.getInventory().setItem(4, redBlocks);
                            p.getInventory().setItem(5, redBlocks);
                            p.getInventory().setItem(6, redBlocks);
                            p.getInventory().setItem(7, redBlocks);

                        } else {

                            ItemStack grayBlocks = new ItemStack(Material.LIGHT_GRAY_WOOL, 64);

                            p.getInventory().setItem(2, grayBlocks);
                            p.getInventory().setItem(3, grayBlocks);
                            p.getInventory().setItem(4, grayBlocks);
                            p.getInventory().setItem(5, grayBlocks);
                            p.getInventory().setItem(6, grayBlocks);
                            p.getInventory().setItem(7, grayBlocks);

                        }
                    }
                }
            }
        }

    }

}
