package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class OnPlayerMove implements Listener {

    private final BridgePro main;

    public OnPlayerMove(BridgePro main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if(main.ingame.contains(p)){
            if (Objects.requireNonNull(e.getTo()).getY() < 1) {
                if(main.getConfig().getLocation("bridge-lobby-location") != null){
                    // Do stuff with this player below the y-axis
                    main.lobby.remove(p);
                    main.ingame.remove(p);
                    main.lobby.add(p);

                    p.closeInventory();
                    p.getOpenInventory().close();
                    p.getInventory().clear();

                    p.setHealth(0);
                }
            }
        }

        if(main.lobby.contains(p)){
            if(p.getGameMode().equals(GameMode.SURVIVAL)){
                if (Objects.requireNonNull(e.getTo()).getY() < 80) {
                    if(main.getConfig().getLocation("bridge-lobby-location") != null){
                        // Do stuff with this player below the y-axis
                        main.ingame.remove(p);
                        main.lobby.remove(p);
                        main.ingame.add(p);

                        p.closeInventory();
                        p.getOpenInventory().close();
                        p.getInventory().clear();
                        p.setFireTicks(0);

                        // Giving the player the blocks for in-game.
                        ItemStack blocks = new ItemStack(Material.LIGHT_GRAY_WOOL, 64);
                        ItemStack weapon = new ItemStack(Material.STICK, 1);
                        ItemStack bow = new ItemStack(Material.BOW, 1);
                        ItemStack arrows = new ItemStack(Material.ARROW, 32);
                        ItemStack food = new ItemStack(Material.COOKED_BEEF, 32);

                        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

                        // Adding the enchantments to the items
                        weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                        weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

                        // Adding the player armour
                        p.getInventory().setHelmet(helmet);
                        p.getInventory().setChestplate(chestplate);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);

                        p.getInventory().setItem(0, weapon);
                        p.getInventory().setItem(1, bow);
                        p.getInventory().setItem(2, food);
                        p.getInventory().setItem(3, blocks);
                        p.getInventory().setItem(4, blocks);
                        p.getInventory().setItem(5, blocks);
                        p.getInventory().setItem(6, blocks);
                        p.getInventory().setItem(7, blocks);
                        p.getInventory().setItem(8, blocks);
                        p.getOpenInventory().setItem(9, arrows);
                    }
                }
            }
        }
    }
}
