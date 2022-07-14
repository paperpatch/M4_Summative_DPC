package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.*;
import com.dpc.M4_Summative_DPC.repository.*;
import com.dpc.M4_Summative_DPC.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Clear Database
    public void clearDatabase() {
        gameRepository.deleteAll();
        tShirtRepository.deleteAll();
        consoleRepository.deleteAll();
        salesTaxRateRepository.deleteAll();
        processingFeeRepository.deleteAll();
        // invoiceRepository.deleteAll();
    }

    // Game CRUD
    public List<Game> getAllGames() {return gameRepository.findAll(); }

    public Optional<Game> getGameById(int id) { return gameRepository.findById(id); }

    public Optional<Game> getGameByTitle(String title) { return gameRepository.findByTitle(title); }

    public List<Game> getGamesByEsrbRating(String esrbRating) { return gameRepository.findByEsrbRating(esrbRating); }

    public List<Game> getGamesByStudio(String studio) { return gameRepository.findByStudio(studio); }

    public Game addGame(Game game) { return gameRepository.save(game); }

    public void updateGame(Game game) { gameRepository.save(game); }

    public void deleteGame(int id) { gameRepository.deleteById(id); }

//    Console
    public Console addConsole(Console console){
        console = consoleRepository.save(console);
       return console;
    }

    public void seedGames() {
        gameRepository.save(new Game("Elden Ring", "M (Mature 17+)", "Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.", 59.95, "FromSoftware Inc.", 1));
        gameRepository.save(new Game("LEGO Star Wars: The Skywalker Saga", "E (Everyone)", "Lego-themed action-adventure game.", 49.00, "Warner Bros. Interactive Entertainment", 2));
        gameRepository.save(new Game("Among Us", "E (Everyone)", "Online multiplayer social deduction game.", 4.99, "InnerSloth LLC", 5));
    }

    // Console CRUD
    public List<Console> getConsoleByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }

    public List<Console> getAllConsole (){
        return consoleRepository.findAll();
    }

    public Optional<Console> getConsoleById(int id) {
        return consoleRepository.findById(id);
    }

    public void updateConsole(Console console){
        consoleRepository.save(console);
    }

    public void deleteConsole(int id){
        consoleRepository.deleteById(id);
    }

    public void seedConsole() {
        consoleRepository.save(new Console("Playstation 5", "Sony", "1 TB", "AMD Zen 2-CPU", 549.99, 20));
        consoleRepository.save(new Console("Xbox Series X", "Microsoft", "1 TB", "AMD Zen 2-CPU", 499.99, 10));
        consoleRepository.save(new Console("Nintendo Switch", "Nintendo", "32 GB", "Quad-core ARM Cortex", 299.99, 15));
    }

    //T-Shirt CRUD
    @Transactional
    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel){
        TShirt t = new TShirt();
        t.setSize(tShirtViewModel.getSize());
        t.setColor(tShirtViewModel.getColor());
        t.setDescription(tShirtViewModel.getDescription());
        t.setPrice(tShirtViewModel.getPrice());
        t.setQuantity(tShirtViewModel.getQuantity());

        t = tShirtRepository.save(t);
        tShirtViewModel.setId(t.getId());

        return tShirtViewModel;
    }
    public TShirtViewModel findATShirtById(int id){
        Optional<TShirt> tShirt = tShirtRepository.findById(id);
        return tShirt.isPresent()? buildTShirtViewModel(tShirt.get()) : null;
    }
    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        TShirtViewModel tvm = new TShirtViewModel();
        tvm.setId(tShirt.getId());
        tvm.setSize(tShirt.getSize());
        tvm.setColor(tShirt.getColor());
        tvm.setDescription(tShirt.getDescription());
        tvm.setPrice(tShirt.getPrice());
        tvm.setQuantity(tShirt.getQuantity());
        ;
        return tvm;
    }

    public List<TShirtViewModel> findAllTshirt(){
        List<TShirt> tShirtList = tShirtRepository.findAll();
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for(TShirt tShirt : tShirtList) {
            TShirtViewModel tvm = buildTShirtViewModel(tShirt);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public List<TShirtViewModel> findAllTshirtByColor(String color){
        List<TShirt> tShirtList = tShirtRepository.findByColor(color);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for(TShirt tShirt : tShirtList) {
            TShirtViewModel tvm = buildTShirtViewModel(tShirt);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public List<TShirtViewModel> findAllTshirtBySize(String size){
        List<TShirt> tShirtList = tShirtRepository.findBySize(size);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for(TShirt tShirt : tShirtList) {
            TShirtViewModel tvm = buildTShirtViewModel(tShirt);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    @Transactional
    public void updateTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setId(tShirtViewModel.getId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
   tShirtRepository.save(tShirt);
    }

    @Transactional
    public void removeTShirt(int id){
        tShirtRepository.deleteById(id);
    }

    public void seedTShirts() {
        tShirtRepository.save(new TShirt("small", "red", "small red shirt", 9.99, 14));
        tShirtRepository.save(new TShirt("medium", "blue", "medium sized blue shirt", 9.99, 30));
        tShirtRepository.save(new TShirt("large", "green", "large sized green shirt", 9.99, 21));
    }

    // Invoice CRUD

    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll(); }

    public Optional<Invoice> getInvoiceById(int id) { return invoiceRepository.findById(id); }

    public Invoice addInvoice(Invoice invoice) {
        Invoice invoice1 = invoice;
        double salesTax = invoice.getQuantity() * invoice.getUnitPrice();

        return invoiceRepository.save(invoice);
    }

    public void updateInvoice(Invoice invoice) { invoiceRepository.save(invoice); }

    public void deleteInvoice(int id) { invoiceRepository.deleteById(id); }

    // Sales Tax CRUD
    public SalesTaxRate findSalesTaxRateByState(String state) {
        return salesTaxRateRepository.findByState(state);
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

//     Processing Fee CRUD
//    public ProcessingFee findProcessingFee(Invoice invoice) {
//        return processingFeeRepository.findByProductType(invoice.<work to be done?>)
//    }

    public void seedFees() {
        processingFeeRepository.save(new ProcessingFee("Games", 1.49));
        processingFeeRepository.save(new ProcessingFee("Consoles", 14.99));
        processingFeeRepository.save(new ProcessingFee("TShirts", 1.99));
    }

}
