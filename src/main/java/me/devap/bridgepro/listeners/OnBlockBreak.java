package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    private final BridgePro plugin;

    public OnBlockBreak(BridgePro plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player p = e.getPlayer();

        if(plugin.ingame.contains(p) || plugin.lobby.contains(p)){
            if(plugin.getConfig().getBoolean("disable-block-breaking")){
                // If the player is not op, cancel the event.
                e.setCancelled(true);
            }
        }
    }
}
