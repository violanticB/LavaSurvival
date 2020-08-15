package com.vextracraft.lava;

import com.vextracraft.lava.game.Game;
import com.vextracraft.lava.game.map.Map;
import com.vextracraft.lava.game.world.geometry.WorldGeometry;
import com.vextracraft.lava.game.world.geometry.shapes.SquareWorldGeometry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Ethan Borawski
 */
public class LavaPlugin extends JavaPlugin {

    private static LavaPlugin instance;

    private Map gameMap;
    private Game currentGame;

    public void onEnable() {
        instance = this;

        initMap();
        currentGame = new Game(this);
        currentGame.start();
    }

    public Map getGameMap() {
        return gameMap;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    private void initMap() {
        String name = getConfig().getString("map.name");
        String author = getConfig().getString("map.author");

        int x0 = getConfig().getInt("map.x0");
        int y0 = getConfig().getInt("map.y0");
        int z0 = getConfig().getInt("map.z0");

        int x1 = getConfig().getInt("map.x1");
        int y1 = getConfig().getInt("map.y1");
        int z1 = getConfig().getInt("map.z1");

        SquareWorldGeometry geometry = new SquareWorldGeometry(x0, z0, x1, z1);
        geometry.setCenter(new Location(Bukkit.getWorld("world"), x0 + (Math.abs(x1 - x0) / 2), y0, z0 + (Math.abs(z1 - z0) / 2)));
        this.gameMap = new Map(name, author, geometry);

    }

    public static LavaPlugin getInstance() {
        return instance;
    }

}
