package com.vextracraft.lava.game.map;

import com.vextracraft.lava.game.world.geometry.WorldGeometry;
import com.vextracraft.lava.game.world.geometry.shapes.SquareWorldGeometry;

/**
 * @author Ethan Borawski
 */
public class Map {

    private String name;
    private String author;
    private SquareWorldGeometry geometry;

    public Map(String name, String author, SquareWorldGeometry geometry) {
        this.name = name;
        this.author = author;
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public SquareWorldGeometry getGeometry() {
        return geometry;
    }
}
