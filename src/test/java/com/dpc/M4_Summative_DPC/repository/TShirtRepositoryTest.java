package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepository;
    TShirt testTShirt;

    @Before
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();
        testTShirt = new TShirt();
        testTShirt.setSize("small");
        testTShirt.setColor("blue");
        testTShirt.setDescription("blue unicorn");
        testTShirt.setPrice(14.99);
        testTShirt.setQuantity(45);
        testTShirt = tShirtRepository.save(testTShirt);
    }

    @Test
    public void shouldAddAndDeleteTShirt(){
        Optional<TShirt> tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertEquals(tShirtList.get(), testTShirt);
        tShirtRepository.deleteById(testTShirt.getId());
        tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertFalse(tShirtList.isPresent());
    }

    @Test
    public void shouldUpdateTShirt(){
        testTShirt.setSize("medium");

        tShirtRepository.save(testTShirt);
        Optional<TShirt> tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertEquals(tShirtList.get(),testTShirt);
    }

    @Test
    public void shouldGetAllTShirts(){
        testTShirt = new TShirt();
        testTShirt.setQuantity(86);
        testTShirt.setPrice(18.99);
        testTShirt.setDescription("blue unicorn");
        testTShirt.setColor("white");
        testTShirt.setSize("medium");

        testTShirt = tShirtRepository.save(testTShirt);

        List<TShirt> tShirtList = tShirtRepository.findAll();
        assertEquals(tShirtList.size(),2) ;
    }

    @Test
    public void shouldGetAllTShirtByColor(){
        assertEquals(tShirtRepository.findByColor("blue").size(), 1);
    }

    @Test
    public void shouldGetAllTShirtBySize(){
        assertEquals(tShirtRepository.findBySize("small").size(),1);
    }

}