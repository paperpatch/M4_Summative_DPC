package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.TShirt;
import com.dpc.M4_Summative_DPC.repository.TShirtRepository;
import com.dpc.M4_Summative_DPC.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private TShirtRepository tShirtRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository) {
        this.tShirtRepository = tShirtRepository;
    }

    @Transactional
    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel){
        TShirt t = new TShirt();
                t.setSize(tShirtViewModel.getSize());
                t.setColor(tShirtViewModel.getColor());
                t.setDescription(tShirtViewModel.getDescription());
                t.setPrice(tShirtViewModel.getPrice());
                t.setQuantity(tShirtViewModel.getQuantity());

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

}
