package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class OnWeatherChange implements Listener {

    private final BridgePro plugin;

    public OnWeatherChange(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){

        // Disable weather change.
        if(plugin.getConfig().getBoolean("disable-weather-change")){
            e.setCancelled(true);
        }
    }
}
