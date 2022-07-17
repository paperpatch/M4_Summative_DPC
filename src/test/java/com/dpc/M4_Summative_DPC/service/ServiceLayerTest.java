package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.*;
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
    public void setUp() {
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
        tList.add(tshirt2);

        doReturn(tshirt).when(tShirtRepository).save(tshirt);
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

        List<Game> gList = new ArrayList<>();
        gList.add(game);
        gList.add(game2);

        doReturn(game).when(gameRepository).save(game);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(gList).when(gameRepository).findAll();
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1 TB");
        console.setProcessor("AMD Zen 2-CPU");
        console.setPrice(549.99);
        console.setQuantity(20);

        Console console2 = new Console();
        console2.setConsoleId(1);
        console2.setModel("Xbox Series X");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("1 TB");
        console2.setProcessor("AMD Zen 2-CPU");
        console2.setPrice(499.99);
        console2.setQuantity(10);

        List<Console> cList = new ArrayList<>();
        cList.add(console);
        cList.add(console2);

        doReturn(console).when(consoleRepository).save(console);
        doReturn(Optional.of(console)).when(consoleRepository).findById(1);
        doReturn(cList).when(consoleRepository).findAll();
    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setStreet("123 fake street");
        invoice.setCity("Fake City");
        invoice.setState("CT");
        invoice.setZipCode("00000");
        invoice.setItemType("games");
        invoice.setItemId(1);
        invoice.setQuantity(2);

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(iList).when(invoiceRepository).findAll();
    }

    private void setUpSalesTaxRateRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setId(1);
        salesTaxRate.setRate(0.03);
        salesTaxRate.setState("CT");

        List<SalesTaxRate> sList = new ArrayList<>();
        sList.add(salesTaxRate);

        doReturn(salesTaxRate).when(salesTaxRateRepository).save(salesTaxRate);
        doReturn(Optional.of(salesTaxRate)).when(salesTaxRateRepository).findById(1);
        doReturn(sList).when(salesTaxRateRepository).findAll();
    }

    private void setUpProcessingFeeRepositoryMock() {
        processingFeeRepository = mock(ProcessingFeeRepository.class);
        ProcessingFee fee = new ProcessingFee();
        fee.setId(1);
        fee.setProductType("Games");
        fee.setFee(1.49);
    }

    @Test
    public void serviceShouldGetAllGames() {
        List<Game> gameList = service.getAllGames();
        assertEquals(2, gameList.size());
    }

    @Test
    public void serviceShouldFindAllTShirts() {
        List<TShirt> tShirtList = service.findAllTshirt();
        assertEquals(2, tShirtList.size());
    }

    @Test
    public void serviceShouldGetAllConsoles() {
        List<Console> consoleList = service.getAllConsole();
        assertEquals(2, consoleList.size());
    }

    @Test
    public void serviceShouldGetUnitPriceByItemTypeAndItemId() {
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setStreet("123 fake street");
        invoice.setCity("Fake City");
        invoice.setState("CT");
        invoice.setZipCode("00000");
        invoice.setItemType("games");
        invoice.setItemId(1);
        invoice.setQuantity(2);

        double output = 59.95;
        double input = service.getUnitPrice(invoice);

        assertEquals(output, input, 0.01);
    }

    @Test
    public void serviceShouldCalculateSubtotal() {
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setStreet("123 fake street");
        invoice.setCity("Fake City");
        invoice.setState("CT");
        invoice.setZipCode("00000");
        invoice.setItemType("games");
        invoice.setItemId(1);
        invoice.setQuantity(2);

        SalesTaxRate tax = new SalesTaxRate();
        tax.setId(1);
        tax.setState("CT");
        tax.setRate(0.03);

        double unitPrice = 59.95;
        double output = 119.9;
        double input = service.calculateSubtotal(invoice, unitPrice);

        assertEquals(output, input, 0.01);
    }

    @Test
    public void serviceShouldCalculateTotal() {
        double salesTax = 4.33;
        double processingFee = 1.25;
        double subtotal = 60.99;

        double expected = 66.57;
        double input = service.calculateTotal(salesTax, processingFee, subtotal);

        assertEquals(expected, input, 0.01);
    }

}