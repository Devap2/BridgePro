package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
                // Do stuff with this player below the y-axis
                main.lobby.remove(p);
                main.ingame.remove(p);
                main.lobby.add(p);

                p.getInventory().clear();

                p.setHealth(0);

            }
        }

        if(main.lobby.contains(p)){
            if(p.getGameMode().equals(GameMode.SURVIVAL)){
                if (Objects.requireNonNull(e.getTo()).getY() < 80) {
                    // Do stuff with this player below the y-axis
                    main.ingame.remove(p);
                    main.lobby.remove(p);
                    main.ingame.add(p);

                    p.getInventory().clear();

                    // Giving the player the blocks for in-game.
                    ItemStack blocks = new ItemStack(Material.STONE, 64);
                    p.getInventory().setItem(0, blocks);
                    p.getInventory().setItem(1, blocks);
                    p.getInventory().setItem(2, blocks);
                    p.getInventory().setItem(3, blocks);
                    p.getInventory().setItem(4, blocks);
                    p.getInventory().setItem(5, blocks);
                    p.getInventory().setItem(6, blocks);
                    p.getInventory().setItem(7, blocks);
                    p.getInventory().setItem(8, blocks);
                }
            }
        }
    }
}
