package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.Console;
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
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;
    Console testConsole;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
        testConsole = new Console();
        testConsole.setModel("PlayStation5");
        testConsole.setManufacturer("Sony");
        testConsole.setMemoryAmount("1TB");
        testConsole.setProcessor("AMD Zen 2 CPU");
        testConsole.setPrice(575.99);
        testConsole.setQuantity(200);
        testConsole = consoleRepository.save(testConsole);
    }

    @Test
    public void shouldAddAndDeleteConsole(){
        Optional<Console> consoleList = consoleRepository.findById(testConsole.getConsoleId());
        assertEquals(consoleList.get(), testConsole);
        consoleRepository.deleteById(testConsole.getConsoleId());
        consoleList = consoleRepository.findById(testConsole.getConsoleId());
        assertFalse(consoleList.isPresent());
    }

    @Test
    public void shouldUpdateConsole(){
        testConsole.setPrice(700.00);
        consoleRepository.save(testConsole);
        Optional<Console> consoleList = consoleRepository.findById(testConsole.getConsoleId());
        assertEquals(consoleList.get(),testConsole);
    }

    @Test
    public void ShouldGetAllConsoles(){
        testConsole = new Console();
        testConsole.setModel("PlayStation5");
        testConsole.setManufacturer("Blizzard");
        testConsole.setMemoryAmount("1TB");
        testConsole.setProcessor("AMD Zen 2 CPU");
        testConsole.setPrice(575.99);
        testConsole.setQuantity(200);

        testConsole = consoleRepository.save(testConsole);

        List<Console> consoleList = consoleRepository.findAll();
        assertEquals(consoleList.size(),2);
    }

    @Test
    public void shouldGetAllConsolesByManufacture(){
        assertEquals(consoleRepository.findByManufacturer("Sony").size(), 1);
    }

}