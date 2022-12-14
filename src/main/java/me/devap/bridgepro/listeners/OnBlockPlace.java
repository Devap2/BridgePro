package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnBlockPlace implements Listener {

    private final BridgePro plugin;

    public OnBlockPlace(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

        Player p = e.getPlayer();
        Block placedBlock = e.getBlockPlaced();

            new BukkitRunnable() {
                public void run() {
                    // If the player is in-game, not in creative and placed a stone block. Set to air after ... seconds.
                    if(!p.getGameMode().equals(GameMode.CREATIVE)){
                        if (placedBlock.getType().equals(Material.LIGHT_GRAY_WOOL) ||
                                placedBlock.getType().equals(Material.GREEN_WOOL) ||
                                    placedBlock.getType().equals(Material.CYAN_WOOL) ||
                                        placedBlock.getType().equals(Material.PURPLE_WOOL) ||
                                            placedBlock.getType().equals(Material.RED_WOOL)) {

                            e.getPlayer().spawnParticle(Particle.BLOCK_DUST,placedBlock.getLocation(),50,placedBlock.getBlockData());
                            placedBlock.setType(Material.AIR);

                        }
                    }
                }
            }.runTaskLater(plugin, 60);
        }
}
