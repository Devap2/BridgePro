package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class OnExplosion implements Listener {

    private final BridgePro plugin;

    public OnExplosion(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTNTExplosion(BlockExplodeEvent e){

        // Disable tnt explosion
        if(e.getBlock().equals(Material.TNT)){
            if(plugin.getConfig().getBoolean("disable-tnt-explosion")){
                e.setCancelled(true);
            }
        }
    }
}
