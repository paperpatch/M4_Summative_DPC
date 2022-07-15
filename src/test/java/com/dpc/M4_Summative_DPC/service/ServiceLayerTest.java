package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.models.TShirt;
import com.dpc.M4_Summative_DPC.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    ServiceLayer service;

    TShirtRepository tShirtRepository;
    GameRepository gameRepository;
    ConsoleRepository consoleRepository;
    InvoiceRepository invoiceRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    ProcessingFeeRepository processingFeeRepository;

    @Before
    public void setUp() throws Exception {
        setUpTShirtRepositoryMock();
        setUpGameRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpSalesTaxRateRepositoryMock();
        setUpProcessingFeeRepositoryMock();

        service = new ServiceLayer(tShirtRepository, gameRepository, consoleRepository, invoiceRepository, salesTaxRateRepository, processingFeeRepository);
    }

    // Helper methods
    private void setUpTShirtRepositoryMock() {
        tShirtRepository = mock(TShirtRepository.class);
        TShirt tshirt = new TShirt();
        tshirt.setId(1);
        tshirt.setColor("blue");
        tshirt.setDescription("normal blue shirt");
        tshirt.setPrice(9.99);
        tshirt.setQuantity(100);

        TShirt tshirt2 = new TShirt();
        tshirt2.setId(2);
        tshirt2.setColor("red");
        tshirt2.setDescription("normal red shirt");
        tshirt2.setPrice(10.99);
        tshirt2.setQuantity(40);

        List<TShirt> tList = new ArrayList<>();
        tList.add(tshirt);

        doReturn(tshirt).when(tShirtRepository).save(tshirt2);
        doReturn(Optional.of(tshirt)).when(tShirtRepository).findById(1);
        doReturn(tList).when(tShirtRepository).findAll();
    }

    private void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);
        Game game = new Game();
        game.setId(1);
        game.setTitle("Elden Ring");
        game.setEsrbRating("M (Mature 17+)");
        game.setDescription("Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.");
        game.setPrice(59.95);
        game.setStudio("FromSoftware Inc.");
        game.setQuantity(50);

        Game game2 = new Game();
        game2.setId(2);
        game2.setTitle("LEGO Star Wars: The Skywalker Saga");
        game2.setEsrbRating("E (Everyone)");
        game2.setDescription("Lego-themed action-adventure game.");
        game2.setPrice(49.00);
        game2.setStudio("Warner Bros. Interactive Entertainment");
        game2.setQuantity(50);
    }

    private void setUpConsoleRepositoryMock() {}

    private void setUpInvoiceRepositoryMock() {}

    private void setUpSalesTaxRateRepositoryMock() {}

    private void setUpProcessingFeeRepositoryMock() {}

    @Test
    public void shouldFindTShirts() {
        List<TShirt> tShirtList = service.findAllTshirt();
        assertEquals(1, tShirtList.size());
    }
}