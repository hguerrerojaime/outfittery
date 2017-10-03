package com.outfittery.gol.web;

import com.google.gson.Gson;
import com.outfittery.gol.core.Cell;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by hguerrero on 3/10/17.
 */
public class ApplicationControllerTest {

    private ApplicationController controller = new ApplicationController();

    private MockMvc mvc;

     @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(new GsonHttpMessageConverter()).build();
    }

    @org.junit.Test
    public void testNextState() throws Exception {

        GridCommand command = new GridCommand();
        command.setHeight(5);
        command.setWidth(5);

        Set<Cell> cells = new HashSet<Cell>();
        cells.add(new Cell(1,2));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(3, 2));
        command.setLiveCells(cells);

        mvc.perform(post("/").
                accept(MediaType.APPLICATION_JSON_UTF8).
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new Gson().toJson(command))).andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray())
        ;

    }
}