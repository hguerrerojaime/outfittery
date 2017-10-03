package com.outfittery.gol.web;

import com.outfittery.gol.core.Cell;

import java.util.Set;

/**
 * Created by hguerrero on 3/10/17.
 */
public class GridCommand {

    private int height;
    private int width;
    private Set<Cell> liveCells;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Set<Cell> getLiveCells() {
        return liveCells;
    }

    public void setLiveCells(Set<Cell> liveCells) {
        this.liveCells = liveCells;
    }
}
