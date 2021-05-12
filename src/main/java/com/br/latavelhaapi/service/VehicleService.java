package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.repository.BrandRepository;
import com.br.latavelhaapi.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    //ADD
    public Object add(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    //List
    public List<Vehicle> list(){
        return vehicleRepository.findAll();
    }

    //Deletar
    public void delete(long id){
        Vehicle vehicle = vehicleRepository.findByID(id);
        if(vehicle != null){
            vehicleRepository.delete(vehicle);
        }
    }

    //Atualizar
    public Object update(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    //Listar por preco
    public List<Vehicle> listByPrice(BigDecimal price){
        return vehicleRepository.findByPrice(price);
    }

    //Listar por modelo
    public List<Vehicle> listByModel(String model){
        return vehicleRepository.findByModel(model);
    }

    //Listar por marca
    public List<Vehicle> listByBrand(long id){
        return vehicleRepository.findByBrandID(id);
    }
}
