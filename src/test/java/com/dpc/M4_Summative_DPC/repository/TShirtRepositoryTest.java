package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        testTShirt = new TShirt("small", "blue", "blue unicorn", 14.99, 45);

    }

    @Test
    public void addGetDeleteTShirt(){
        testTShirt = tShirtRepository.save(testTShirt);
        Optional<TShirt> tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertEquals(tShirtList.get(), testTShirt);
        tShirtRepository.deleteById(testTShirt.getId());
        tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertFalse(tShirtList.isPresent());
    }

    @Test
    public void updateTShirt(){
        testTShirt = tShirtRepository.save(testTShirt);
        testTShirt.setSize("medium");

        tShirtRepository.save(testTShirt);
        Optional<TShirt> tShirtList = tShirtRepository.findById(testTShirt.getId());
        assertEquals(tShirtList.get(),testTShirt);

    }
}