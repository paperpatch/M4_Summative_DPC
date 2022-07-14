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
    private SalesTaxRateRepository salesTaxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository, GameRepository gameRepository, ConsoleRepository consoleRepository, SalesTaxRateRepository salesTaxRateRepository, ProcessingFeeRepository processingFeeRepository) {
        this.tShirtRepository = tShirtRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
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
    public Console addConsole(Console console){
        return consoleRepository.save(console);
    }
    public List<Console> getConsoleByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer); }
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

    // Sales Tax CRUD
    public SalesTaxRate findSalesTaxRateByState(String state) {
        return salesTaxRateRepository.findByState(state);
    }

    // Processing Fee CRUD
//    public ProcessingFee findProcessingFee(Invoice invoice) {
//        return processingFeeRepository.findByProductType(invoice.<work to be done?>)
//    }

}
