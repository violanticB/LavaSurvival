package com.vextracraft.lava.game.task;

import com.vextracraft.lava.game.Game;
import com.vextracraft.lava.game.world.geometry.GeometryBuilder;
import com.vextracraft.lava.game.world.geometry.WorldGeometry;
import com.vextracraft.lava.game.world.geometry.shapes.SquareWorldGeometry;
import com.vextracraft.lava.game.world.geometry.task.GeometryBuildTask;
import com.vextracraft.lava.game.world.geometry.task.GeometryUndoTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Ethan Borawski
 */
public class GameLavaTask implements GameTask {

    private Game game;
    private SquareWorldGeometry geometry;
    private final int MINIMUM_HEIGHT = 2;
    private final int MAXIMUM_HEIGHT = 73;
    private int currentHeight;

    public GameLavaTask(Game game) {
        this.game = game;
        this.geometry = game.getPlugin().getGameMap().getGeometry();
        this.currentHeight = MINIMUM_HEIGHT;
    }

    public int frequencyTicks() {
        return 60;
    }

    public void run() {
        World world = Bukkit.getWorld("world");
        Block current = null;

        if(currentHeight % 10 == 0) {

            int y = ThreadLocalRandom.current().nextInt(currentHeight + 10, MAXIMUM_HEIGHT);
            SquareWorldGeometry newPlatform = new SquareWorldGeometry(geometry.getRandomPointWithSpace(), 7);
            newPlatform.setY(y);

            GeometryBuilder builder = new GeometryBuilder(newPlatform, Material.COBBLESTONE, (byte) -1);
            game.getPlugin().getServer().getScheduler().runTask(game.getPlugin(), new GeometryBuildTask(builder, false) {
                @Override
                public void whenComplete() {
                    game.getPlugin().getServer().getScheduler().runTaskLater(game.getPlugin(), new GeometryUndoTask(world, this.getPreviousMaterials()), 20 * 30);
                }
            });

            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The shop platform is shifting!");
        }

        for(double x = geometry.minimumX(); x < geometry.maximumX(); x++) {
            for(double z = geometry.minimumZ(); z < geometry.maximumZ(); z++) {
                world.getBlockAt((int) x, currentHeight, (int) z);
                current.setType(Material.LAVA);
            }
        }

        currentHeight++;
    }
}
