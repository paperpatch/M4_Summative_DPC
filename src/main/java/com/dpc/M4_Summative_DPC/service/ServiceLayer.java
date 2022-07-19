package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.*;
import com.dpc.M4_Summative_DPC.repository.*;
import com.dpc.M4_Summative_DPC.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private TShirtRepository tShirtRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private InvoiceRepository invoiceRepository;
    private SalesTaxRateRepository salesTaxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository, GameRepository gameRepository, ConsoleRepository consoleRepository, InvoiceRepository invoiceRepository, SalesTaxRateRepository salesTaxRateRepository, ProcessingFeeRepository processingFeeRepository) {
        this.tShirtRepository = tShirtRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.invoiceRepository = invoiceRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
    }

    public ServiceLayer(GameRepository gameRepository) {
    }

    public ServiceLayer(ConsoleRepository consoleRepository) {
    }

    public ServiceLayer(TShirtRepository tShirtRepository) {
    }

    // Clear Database
    public void clearDatabase() {
        gameRepository.deleteAll();
        tShirtRepository.deleteAll();
        consoleRepository.deleteAll();
        salesTaxRateRepository.deleteAll();
        processingFeeRepository.deleteAll();
        invoiceRepository.deleteAll();
    }

    // Game CRUD
    public List<Game> getAllGames() {return gameRepository.findAll(); }

    public Optional<Game> getGameById(int id) { return gameRepository.findById(id); }

    public List<Game> getGameByTitle(String title) { return gameRepository.findByTitle(title); }

    public List<Game> getGamesByEsrbRating(String esrbRating) { return gameRepository.findByEsrbRating(esrbRating); }

    public List<Game> getGamesByStudio(String studio) { return gameRepository.findByStudio(studio); }

    public Game addGame(Game game) { return gameRepository.save(game); }

    public void updateGame(Game game) { gameRepository.save(game); }

    public void deleteGame(int id) { gameRepository.deleteById(id); }


    // Console CRUD
    public List<Console> getAllConsole (){
        return consoleRepository.findAll();
    }

    public Optional<Console> getConsoleById(int id) {
          return consoleRepository.findById(id);
    }

    public List<Console> getConsoleByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }

    public Console addConsole(Console console){
       return consoleRepository.save(console);
    }

    public void updateConsole(Console console){
        consoleRepository.save(console);
    }

    public void deleteConsole(int id){
        consoleRepository.deleteById(id);
    }


    //T-Shirt CRUD
    @Transactional
    public TShirt saveTShirt(TShirt tShirt){
        return tShirtRepository.save(tShirt);
    }

    public Optional<TShirt> findATShirtById(int id){
        return tShirtRepository.findById(id);
    }

    public List<TShirt> findAllTshirt(){
        return tShirtRepository.findAll();
    }

    public List<TShirt> findAllTshirtByColor(String color){
        return tShirtRepository.findByColor(color);
    }

    public List<TShirt> findAllTshirtBySize(String size){
        return tShirtRepository.findBySize(size);
    }

    @Transactional
    public void updateTShirt(TShirt tShirt) {
        tShirtRepository.save(tShirt);
    }

    @Transactional
    public void removeTShirt(int id){
        tShirtRepository.deleteById(id);
    }


    // Invoice CRUD
    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll(); }

    public Optional<Invoice> getInvoiceById(int id) { return invoiceRepository.findById(id); }

    public Invoice addInvoice(Invoice invoice) {
        Invoice invoice1 = invoice;
        System.out.println(invoice1);
        double unitPrice = getUnitPrice(invoice);
        double subtotal = calculateSubtotal(invoice, unitPrice);
        double salesTax = calculateTaxRate(invoice, unitPrice);
        double processingFee = calculateProcessingFee(invoice);
        double total = calculateTotal(salesTax, processingFee, subtotal);

        calculateQuantity(invoice1);

        invoice1.setUnitPrice(unitPrice);
        invoice1.setSubtotal(subtotal);
        invoice1.setTax(salesTax);
        invoice1.setProcessingFee(processingFee);
        invoice1.setTotal(total);

        return invoiceRepository.save(invoice1);
    }

    public double getUnitPrice(Invoice invoice) {
        switch (invoice.getItemType().toLowerCase()) {
            case "games":
                Game game = getGameById(invoice.getItemId()).get();
                return game.getPrice();
            case "consoles":
                Console console = getConsoleById(invoice.getItemId()).get();
                return console.getPrice();
            case "tshirts":
                TShirt tshirt = findATShirtById(invoice.getItemId()).get();
                return tshirt.getPrice();
            default:
                return 0;
        }
    }

    public double calculateSubtotal(Invoice invoice, double unitPrice) {
        return formatDouble(invoice.getQuantity()  * unitPrice);
    }

    public double calculateTaxRate(Invoice invoice, double unitPrice) {
        double subtotal = calculateSubtotal(invoice, unitPrice);
        double rate = salesTaxRateRepository.findByState(invoice.getState()).getRate();
        return formatDouble(subtotal * rate);
    }

    // https://mkyong.com/java/how-to-format-a-double-in-java/
    public double formatDouble(double d) {
        return Double.parseDouble(String.format("%.2f", d));
    }

    public double calculateProcessingFee(Invoice invoice) {
        double processingFee = processingFeeRepository.findByProductType(invoice.getItemType()).getFee();
        if (invoice.getQuantity() > 10) {
            processingFee += 15.49;
        }
        return processingFee;
    }

    public double calculateTotal(double salesTax, double processingFee, double subTotal) {
        return salesTax + processingFee + subTotal;
    }

    public void calculateQuantity(Invoice invoice) {
        int quantity = invoice.getQuantity();
        if (quantity <= 0) {
            throw new IndexOutOfBoundsException("Quantity purchased must be greater than zero.");
        }

        int availableQuantity;
        int updateQuantity;

        switch (invoice.getItemType().toLowerCase()) {
            case "games":
                Game game = getGameById(invoice.getItemId()).get();

                availableQuantity = game.getQuantity();
                if (availableQuantity < quantity) {
                    throw new IllegalArgumentException("Not enough stock in the inventory.");
                }

                updateQuantity = availableQuantity - quantity;
                game.setQuantity(updateQuantity);
                updateGame(game);
                break;
            case "consoles":
                Console console = getConsoleById(invoice.getItemId()).get();

                availableQuantity = console.getQuantity();
                if (availableQuantity < quantity) {
                    throw new IllegalArgumentException("Not enough stock in the inventory.");
                }

                updateQuantity = availableQuantity - quantity;
                console.setQuantity(updateQuantity);
                updateConsole(console);
                break;
            case "tshirts":
                TShirt tshirt = findATShirtById(invoice.getItemId()).get();
                availableQuantity = tshirt.getQuantity();
                if (availableQuantity < quantity) {
                    throw new IllegalArgumentException("Not enough stock in the inventory.");
                }

                updateQuantity = availableQuantity - quantity;
                tshirt.setQuantity(updateQuantity);
                updateTShirt(tshirt);
                break;
        }
    }

    public void updateInvoice(Invoice invoice) { invoiceRepository.save(invoice); }

    public void deleteInvoice(int id) { invoiceRepository.deleteById(id); }

    // Invoice View Modal CRUD
    public InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipCode(invoice.getZipCode());
        ivm.setItemType(invoice.getItemType());
        ivm.setUnitPrice(invoice.getUnitPrice());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setSubtotal(invoice.getSubtotal());
        ivm.setTax(invoice.getTax());
        ivm.setProcessingFee(invoice.getProcessingFee());
        ivm.setTotal(invoice.getTotal());
        ivm.setItemId(invoice.getItemId());

        return ivm;
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();

        List<InvoiceViewModel> iList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel avm = buildInvoiceViewModel(invoice);
            iList.add(avm);
        }

        return iList;
    }

    // Seed Database
    public void seedGames() {
        gameRepository.save(new Game("Elden Ring", "M", "Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.", 59.95, "FromSoftware Inc.", 50));
        gameRepository.save(new Game("LEGO Star Wars: The Skywalker Saga", "E", "Lego-themed action-adventure game.", 49.00, "Warner Bros. Interactive Entertainment", 100));
        gameRepository.save(new Game("Among Us", "E", "Online multiplayer social deduction game.", 4.99, "InnerSloth LLC", 73));
        gameRepository.save(new Game("Pokémon Legends: Arceus", "E", "Eighth generation of the Pokémon video game series and serves as a perquel to Pokémon Diamond and Pearl", 59.99, "Game Freak", 530));
        gameRepository.save(new Game("Harvestella", "T", "In a vibrant and colorful world, players will tend their crops, befriend the townsfolk, overcome threats, discover the origins of the world and the truth", 59.99, "Square Enix", 64));
    }

    public void seedTShirts() {
        tShirtRepository.save(new TShirt("XXS", "yellow", "extra extra small yellow shirt", 5.99, 54));
        tShirtRepository.save(new TShirt("XS", "teal", "extra small teal shirt", 6.99, 14));
        tShirtRepository.save(new TShirt("S", "red", "small red shirt", 7.99, 14));
        tShirtRepository.save(new TShirt("M", "blue", "medium sized blue shirt", 9.99, 40));
        tShirtRepository.save(new TShirt("M", "white", "medium sized white shirt", 9.99, 70));
        tShirtRepository.save(new TShirt("L", "green", "large sized green shirt", 9.99, 21));
        tShirtRepository.save(new TShirt("XL", "navy", "extra large sized navy shirt", 11.99, 4));
        tShirtRepository.save(new TShirt("XXL", "black", "extra extra large sized black shirt", 12.99, 5));
    }

    public void seedConsole() {
        consoleRepository.save(new Console("Playstation 5", "Sony", "1 TB", "AMD Zen 2-CPU", 549.99, 20));
        consoleRepository.save(new Console("Xbox Series X", "Microsoft", "1 TB", "AMD Zen 2-CPU", 499.99, 10));
        consoleRepository.save(new Console("Nintendo Switch", "Nintendo", "32 GB", "Quad-core ARM Cortex", 299.99, 15));
    }

    public void seedFees() {
        processingFeeRepository.save(new ProcessingFee("Games", 1.49));
        processingFeeRepository.save(new ProcessingFee("Consoles", 14.99));
        processingFeeRepository.save(new ProcessingFee("TShirts", 1.99));
    }

    public void seedTaxes() {
        salesTaxRateRepository.save(new SalesTaxRate("AL", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("AK", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("AZ", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("AR", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("CA", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("CO", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("CT", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("DE", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("FL", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("GA", 0.07));
        salesTaxRateRepository.save(new SalesTaxRate("HI", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("ID", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("IL", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("IN", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("IA", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("KS", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("KY", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("LA", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("ME", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("MD", 0.07));
        salesTaxRateRepository.save(new SalesTaxRate("MA", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("MI", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("MN", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("MS", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("MO", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("MT", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("NE", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("NV", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("NH", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("NJ", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("NM", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("NY", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("NC", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("ND", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("OH", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("OK", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("OR", 0.07));
        salesTaxRateRepository.save(new SalesTaxRate("PA", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("RI", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("SC", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("SD", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("TN", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("TX", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("UT", 0.04));
        salesTaxRateRepository.save(new SalesTaxRate("VT", 0.07));
        salesTaxRateRepository.save(new SalesTaxRate("VA", 0.06));
        salesTaxRateRepository.save(new SalesTaxRate("WA", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("WV", 0.05));
        salesTaxRateRepository.save(new SalesTaxRate("WI", 0.03));
        salesTaxRateRepository.save(new SalesTaxRate("WY", 0.04));
    }
}
