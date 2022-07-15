package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.TShirt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
//    TShirt testInput;

    @Before
    public void setUp(){
//        testInput = new TShirt("small", "grey","unicorn print", 17.99, 100);
    }

    @Test
    public void shouldReturnAllTShirtInInventory() throws Exception {

        mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0]").isNotEmpty());
    }


}