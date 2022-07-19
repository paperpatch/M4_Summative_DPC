package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.*;
import com.dpc.M4_Summative_DPC.repository.*;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    private Game expectedGame;
    private Optional<Game> actualGame;
    private List<Game> actualGameList;
    private List<Game> expectedGameList;
    private Console expectedConsole;
    private Optional<Console> actualConsole;
    private List<Console> actualConsoleList;
    private List<Console> expectedConsoleList;
    private List<TShirt> tShirtList;
    private List<TShirt> tShirtListBlue;
    private List<TShirt> tShirtListSmall;
    private List<TShirt> tShirtListBlueSmall;
    private List<TShirt> actualTShirt;
    private List<TShirt> expectedTShirt;

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

        List<Game> gList = Arrays.asList(game, game2);
        List<Game> gListByRating = Arrays.asList(game2);
        List<Game> gListByStudio = Arrays.asList(game);

        doReturn(game).when(gameRepository).save(game);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(gList).when(gameRepository).findAll();
        doReturn(gListByRating).when(gameRepository).findByEsrbRating("E");
        doReturn(gListByStudio).when(gameRepository).findByStudio("FromSoftware Inc.");
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

        List<Console> cList = Arrays.asList(console, console2);
        List<Console> cListByManufacturer = Arrays.asList(console);

        doReturn(console).when(consoleRepository).save(console);
        doReturn(Optional.of(console)).when(consoleRepository).findById(1);
        doReturn(cList).when(consoleRepository).findAll();
        doReturn(cListByManufacturer).when(consoleRepository).findByManufacturer("Sony");
    }

    private void setUpTShirtRepositoryMock() {
        tShirtRepository = mock(TShirtRepository.class);
        TShirt tshirt = new TShirt();
        tshirt.setId(1);
        tshirt.setSize("S");
        tshirt.setColor("blue");
        tshirt.setDescription("normal blue shirt");
        tshirt.setPrice(9.99);
        tshirt.setQuantity(100);

        TShirt tshirt2 = new TShirt();
        tshirt2.setId(2);
        tshirt2.setSize("M");
        tshirt2.setColor("red");
        tshirt2.setDescription("normal red shirt");
        tshirt2.setPrice(10.99);
        tshirt2.setQuantity(40);

        tShirtList = Arrays.asList(tshirt, tshirt2);
        tShirtListBlue = Arrays.asList(tshirt);
        tShirtListSmall = Arrays.asList(tshirt);
        tShirtListBlueSmall = Arrays.asList(tshirt);

        doReturn(tshirt).when(tShirtRepository).save(tshirt);
        doReturn(Optional.of(tshirt)).when(tShirtRepository).findById(1);
        doReturn(tShirtList).when(tShirtRepository).findAll();
        doReturn(tShirtListBlue).when(tShirtRepository).findByColor("blue");
        doReturn(tShirtListSmall).when(tShirtRepository).findBySize("S");
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
    public void serviceShouldExpectGameListSize() {
        List<Game> gameList = service.getAllGames();
        assertEquals(2, gameList.size());
    }

    @Test
    public void serviceShouldFindAllGames() {
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

        expectedGameList = Arrays.asList(game, game2);

        actualGameList = service.getAllGames();

        assertEquals(expectedGameList, actualGameList);
    }

    @Test
    public void serviceShouldFindAllGamesByStudio() {
        Game game = new Game();
        game.setId(1);
        game.setTitle("Elden Ring");
        game.setEsrbRating("M (Mature 17+)");
        game.setDescription("Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.");
        game.setPrice(59.95);
        game.setStudio("FromSoftware Inc.");
        game.setQuantity(50);

        expectedGameList = Arrays.asList(game);

        actualGameList = service.getGamesByStudio("FromSoftware Inc.");

        assertEquals(expectedGameList, actualGameList);
    }

    @Test
    public void serviceShouldFindAllGamesByRating() {
        Game game2 = new Game();
        game2.setId(2);
        game2.setTitle("LEGO Star Wars: The Skywalker Saga");
        game2.setEsrbRating("E");
        game2.setDescription("Lego-themed action-adventure game.");
        game2.setPrice(49.00);
        game2.setStudio("Warner Bros. Interactive Entertainment");
        game2.setQuantity(50);

        expectedGameList = Arrays.asList(game2);

        actualGameList = service.getGamesByEsrbRating("E");
    }

    @Test
    public void serviceShouldFindGameById() {
        expectedGame = new Game();
        expectedGame.setId(1);
        expectedGame.setTitle("Elden Ring");
        expectedGame.setEsrbRating("M (Mature 17+)");
        expectedGame.setDescription("Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.");
        expectedGame.setPrice(59.95);
        expectedGame.setStudio("FromSoftware Inc.");
        expectedGame.setQuantity(50);

        actualGame = service.getGameById(1);

        assertEquals(expectedGame, actualGame.get());
    }

    @Test
    public void serviceShouldAddGame() {
        expectedGame = new Game();
        expectedGame.setId(1);
        expectedGame.setTitle("Elden Ring");
        expectedGame.setEsrbRating("M (Mature 17+)");
        expectedGame.setDescription("Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.");
        expectedGame.setPrice(59.95);
        expectedGame.setStudio("FromSoftware Inc.");
        expectedGame.setQuantity(50);

        Game actual = service.addGame(expectedGame);

        assertEquals(expectedGame, actual);
    }

    @Test
    public void serviceShouldExpectConsoleSize() {
        List<Console> consoleList = service.getAllConsole();
        assertEquals(2, consoleList.size());
    }

    @Test
    public void serviceShouldFindAllConsoles() {
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

        expectedConsoleList = Arrays.asList(console, console2);

        actualConsoleList = service.getAllConsole();

        assertEquals(expectedConsoleList, actualConsoleList);
    }

    @Test
    public void serviceShouldFindConsoleByManufacturer() {
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1 TB");
        console.setProcessor("AMD Zen 2-CPU");
        console.setPrice(549.99);
        console.setQuantity(20);

        expectedConsoleList = Arrays.asList(console);

        actualConsoleList = service.getConsoleByManufacturer("Sony");

        assertEquals(expectedConsoleList, actualConsoleList);
    }

    @Test
    public void serviceShouldFindConsoleById() {
        expectedConsole = new Console();
        expectedConsole.setConsoleId(1);
        expectedConsole.setModel("Playstation 5");
        expectedConsole.setManufacturer("Sony");
        expectedConsole.setMemoryAmount("1 TB");
        expectedConsole.setProcessor("AMD Zen 2-CPU");
        expectedConsole.setPrice(549.99);
        expectedConsole.setQuantity(20);

        actualConsole = service.getConsoleById(1);

        assertEquals(expectedConsole, actualConsole.get());
    }

    @Test
    public void serviceShouldAddConsole() {
        expectedConsole = new Console();
        expectedConsole.setConsoleId(1);
        expectedConsole.setModel("Playstation 5");
        expectedConsole.setManufacturer("Sony");
        expectedConsole.setMemoryAmount("1 TB");
        expectedConsole.setProcessor("AMD Zen 2-CPU");
        expectedConsole.setPrice(549.99);
        expectedConsole.setQuantity(20);

        Console actual = service.addConsole(expectedConsole);

        assertEquals(expectedConsole, actual);
    }

    @Test
    public void serviceShouldExpectTShirtListSize() {
        List<TShirt> tShirtList = service.findAllTshirt();
        assertEquals(2, tShirtList.size());
    }

    @Test
    public void serviceShouldFindAllTShirts() {
        List<TShirt> expectedTShirts = tShirtList;
        List<TShirt> actualTShirts = service.findAllTshirt();
        assertEquals(expectedTShirts,actualTShirts);
    }

    @Test
    public void serviceShouldFindTShirtByColor() {
        expectedTShirt = tShirtListBlue;
        actualTShirt = service.findAllTshirtByColor("blue");
        assertEquals(expectedTShirt, actualTShirt);
    }

    @Test
    public void serviceShouldFindTShirtBySize() {
        expectedTShirt = tShirtListSmall;
        actualTShirt = service.findAllTshirtBySize("S");
        assertEquals(expectedTShirt, actualTShirt);
    }

    @Test
    public void serviceShouldFindTShirtById() {
        TShirt expected = new TShirt();
        expected.setId(1);
        expected.setSize("S");
        expected.setColor("blue");
        expected.setDescription("normal blue shirt");
        expected.setPrice(9.99);
        expected.setQuantity(100);

        Optional<TShirt> actual = service.findATShirtById(1);
        assertEquals(expected, actual.get());
    }

    @Test
    public void serviceShouldAddTShirt() {
        TShirt expected = new TShirt();
        expected.setId(1);
        expected.setSize("S");
        expected.setColor("blue");
        expected.setDescription("normal blue shirt");
        expected.setPrice(9.99);
        expected.setQuantity(100);

        TShirt actual = service.saveTShirt(expected);
        assertEquals(expected, actual);
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