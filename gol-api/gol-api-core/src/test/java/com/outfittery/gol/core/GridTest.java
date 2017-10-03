package com.outfittery.gol.core;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by hguerrero on 3/10/17.
 */
public class GridTest {


    @Test
    public void verifyThatCellHasCellsInAllAdjacentCoordinates() {

        Cell main = new Cell(2,2);

        Set<Cell> cells = new HashSet<Cell>();

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                cells.add(new Cell(i,j));
            }
        }

        Grid grid = new Grid(5,5,cells);

        int expectedAdjacentCount = 8;
        int actualAdjacentCount = grid.getAdjacentLiveCells(main).size();

        assertEquals(expectedAdjacentCount,actualAdjacentCount);

    }

    @Test
    public void verifyThatCellHasCellsInAllAdjacentCoordinatesExceptTheRight() {

        Cell main = new Cell(2,2);

        Set<Cell> cells = new HashSet<Cell>();

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {

                if (!(i == 3 && j == 2)) {
                    cells.add(new Cell(i,j));
                }


            }
        }

        Grid grid = new Grid(5,5,cells);

        int expectedAdjacentCount = 7;
        Set<Cell> adjacentCells = grid.getAdjacentLiveCells(main);
        int actualAdjacentCount = adjacentCells.size();

        assertEquals(expectedAdjacentCount,actualAdjacentCount);
        assertFalse(adjacentCells.contains(new Cell(3, 2)));

    }

    @Test
    public void verifyNextStateBlinkerMode() {


        Grid grid = new Grid(5,5,new HashSet<Cell>());

        grid.addCell(new Cell(1,2));
        grid.addCell(new Cell(2,2));
        grid.addCell(new Cell(3,2));


        Grid newGrid = grid.nextState();

        int expectedNewLiveCellCount = 3;

        assertEquals(expectedNewLiveCellCount, newGrid.getLiveCells().size());
        assertTrue(newGrid.getLiveCells().contains(new Cell(2,2)));
        assertTrue(newGrid.getLiveCells().contains(new Cell(2,1)));
        assertTrue(newGrid.getLiveCells().contains(new Cell(2, 3)));

        assertFalse(newGrid.getLiveCells().contains(new Cell(1, 2)));
        assertFalse(newGrid.getLiveCells().contains(new Cell(3, 2)));
    }

}