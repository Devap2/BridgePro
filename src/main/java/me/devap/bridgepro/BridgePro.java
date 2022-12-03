package me.devap.bridgepro;

import me.devap.bridgepro.commands.BridgeProCMDS;
import me.devap.bridgepro.commands.ScoreBoardCMD;
import me.devap.bridgepro.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class BridgePro extends JavaPlugin {

    public ArrayList<Player> lobby = new ArrayList<>();
    public ArrayList<Player> ingame = new ArrayList<>();

    @Override
    public void onEnable() {

        // Plugin startup logic.

        // Registering the listeners.
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new OnFoodLevelChange(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new OnItemDrop(this), this);
        getServer().getPluginManager().registerEvents(new OnWeatherChange(this), this);
        getServer().getPluginManager().registerEvents(new OnExplosion(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamage(this), this);
        getServer().getPluginManager().registerEvents(new OnSwapHandItems(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerRespawn(this), this);

        // Registering the commands.
        Objects.requireNonNull(getCommand("sb")).setExecutor(new ScoreBoardCMD(this));
        //Objects.requireNonNull(getCommand("bridge-spawn")).setExecutor(new BridgeSpawnCMD(this));
        Objects.requireNonNull(getCommand("bp")).setExecutor(new BridgeProCMDS(this));
        Objects.requireNonNull(getCommand("bp")).setTabCompleter(this);


        // Config
        // Load the configuration.
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        // Dispatch the '/help' command
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Bridge-Pro plugin shutting down...");

        this.saveDefaultConfig();

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // Creating the list of strings
        List<String> list = Arrays.asList("help", "set-spawn", "updates", "reload", "info", "version", "particles", "set-boundary-one", "set-boundary-two");
        String input = args[0].toLowerCase();

        List<String> completions = null;
        for(String s : list){
            if(s.startsWith(input)){

                if(completions == null){
                    completions = new ArrayList<>();
                }

                completions.add(s);

            }
        }

        if(completions != null)
            Collections.sort(completions);

        return completions;
    }

}
