package com.outfittery.gol.web;

import com.outfittery.gol.core.Cell;
import com.outfittery.gol.core.Grid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by hguerrero on 3/10/17.
 */
@RestController
public class ApplicationController {

    @RequestMapping(value="/",method = RequestMethod.POST)
    public Set<Cell> nextState(@RequestBody GridCommand command) {
        Grid grid = new Grid(command.getWidth(),command.getHeight(),command.getLiveCells());

        return grid.nextState().getLiveCells();
    }

}
