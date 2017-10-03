package com.outfittery.gol.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hguerrero on 3/10/17.
 */
public class Grid {

    private Set<Cell> liveCells;

    private int width;
    private int height;

    public Grid() {

    }

    public Grid(int width, int height,Set<Cell> liveCells) {
        this.width = width;
        this.height = height;
        this.liveCells = liveCells;
    }

    public Set<Cell> getAllAdjacentCells(Cell cell) {

        int cellX = cell.getX();
        int cellY = cell.getY();

        Set<Cell> adjacent = new HashSet<Cell>();

        Cell top = new Cell(cellX,cellY - 1);
        Cell topLeft = new Cell(cellX - 1,cellY - 1);
        Cell left = new Cell(cellX - 1,cellY);
        Cell bottomLeft = new Cell(cellX - 1,cellY + 1);
        Cell bottom = new Cell(cellX,cellY + 1);
        Cell bottomRight = new Cell(cellX + 1,cellY + 1);
        Cell right = new Cell(cellX + 1,cellY);
        Cell topRight = new Cell(cellX + 1,cellY - 1);

        if (canAddCell(top)) {
            adjacent.add(top);
        }

        if (canAddCell(topLeft)) {
            adjacent.add(topLeft);
        }

        if (canAddCell(left)) {
            adjacent.add(left);
        }

        if (canAddCell(right)) {
            adjacent.add(bottomLeft);
        }

        if (canAddCell(bottom)) {
            adjacent.add(bottom);
        }

        if (canAddCell(bottomRight)) {
            adjacent.add(bottomRight);
        }

        if (canAddCell(right)) {
            adjacent.add(right);
        }

        if (canAddCell(topRight)) {
            adjacent.add(topRight);
        }

        return adjacent;

    }



    public Set<Cell> getAdjacentDeadCells(Cell cell) {


        Set<Cell> allAdjacentCells = getAllAdjacentCells(cell);

        Set<Cell> deadAdjacentCells = new HashSet<Cell>(allAdjacentCells);

        deadAdjacentCells.removeAll(liveCells);

        return deadAdjacentCells;

    }

    public Set<Cell> getAdjacentLiveCells(Cell cell) {

        Set<Cell> allAdjacentCells = getAllAdjacentCells(cell);
        Set<Cell> deadAdjacentCells = getAdjacentDeadCells(cell);
        Set<Cell> liveAdjacentCells = new HashSet<Cell>(allAdjacentCells);
        liveAdjacentCells.removeAll(deadAdjacentCells);

        return liveAdjacentCells;

    }

    private boolean canAddCell(Cell cell) {
        return cell.getX() >= 0 && cell.getX() < width && cell.getY() >= 0 & cell.getY() < height;
    }

    public void addCell(Cell cell) {

        if (canAddCell(cell)) {
            liveCells.add(cell);
        } else {
            throw new IllegalArgumentException("Cannot add a cell outside the board's dimensions");
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Grid nextState() {

        Set<Cell> newCellSet = new HashSet<Cell>();

        for (Cell liveCell : liveCells) {

            Set<Cell> adjacentLiveCells = getAdjacentLiveCells(liveCell);
            int adjacentLiveCellSize = adjacentLiveCells.size();

            boolean liveCellShouldLiveOn = adjacentLiveCellSize == 2 || adjacentLiveCellSize == 3;

            if (liveCellShouldLiveOn) {
                newCellSet.add(liveCell);
            }

            Set<Cell> adjacentDeadCells = getAdjacentDeadCells(liveCell);

            for (Cell deadCell : adjacentDeadCells) {

                int deadCellLiveNeighborCount = getAdjacentLiveCells(deadCell).size();
                boolean newCellShouldBeBorn = deadCellLiveNeighborCount == 3;

                if (newCellShouldBeBorn) {
                    newCellSet.add(deadCell);
                }

            }

        }

        return new Grid(width,height,newCellSet);
    }


    public Set<Cell> getLiveCells() {

        if (liveCells == null) {
            liveCells = new HashSet<Cell>();
        }

        return new HashSet<Cell>(liveCells);
    }


}
