package com.vextracraft.lava.game.world.geometry.task;

import com.vextracraft.lava.game.world.geometry.GeometryBuilder;
import com.vextracraft.lava.game.world.geometry.WorldGeometry;
import com.vextracraft.lava.game.world.geometry.shapes.SquareWorldGeometry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public abstract class GeometryBuildTask implements Runnable, CompletableTask {

    private final GeometryBuilder builder;
    private final Map<Location, Material> previousMaterials;
    private final boolean terrain;

    public GeometryBuildTask(GeometryBuilder builder, boolean terrain) {
        this.builder = builder;
        this.previousMaterials = new HashMap<>();
        this.terrain = terrain;
    }

    public GeometryBuilder getBuilder() {
        return builder;
    }

    public Map<Location, Material> getPreviousMaterials() {
        return previousMaterials;
    }

    public void run() {
        WorldGeometry geometry = builder.getGeometry();
        World world = geometry.getCenter().getWorld();

        if(geometry.getCenter() == null)
            System.out.println("NULL CENTER");

        for (Vector point : builder.getGeometry().getPoints()) {
            if(point == null) {
                System.out.println("Vector point is null");
                continue;
            }

            Location current = geometry.getCenter().clone().add(point);
            if(terrain) {
                int y = world.getHighestBlockYAt(current);
                current.setY(y);
            } else if(geometry instanceof SquareWorldGeometry) {
                current.setY(((SquareWorldGeometry) geometry).getY());
            }

            previousMaterials.put(current, world.getBlockAt(current).getType());
            world.getBlockAt(current).setType(builder.getBuildMaterial());

//            BlockState state = world.getBlockAt(current).getState();
//            previousMaterials.put(current, state.getType());
//            state.setType(builder.getBuildMaterial());
//            state.update();

//            if(builder.getData() > 0) {
//                state.setRawData(builder.getData());
//            }

        }

        whenComplete();

    }

    public abstract void whenComplete();

}

