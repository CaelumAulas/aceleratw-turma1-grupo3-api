package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.Vehicle;
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

    public Object add(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> list(){
        return vehicleRepository.findAll();
    }

    public void delete(long id){
        Vehicle vehicle = vehicleRepository.findByID(id);
        if(vehicle != null){
            vehicleRepository.delete(vehicle);
        }
    }

    public Vehicle update(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> listByPrice(BigDecimal price){
        return vehicleRepository.findByPrice(price);
    }

    public List<Vehicle> listByModel(String model){
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> listByBrand(long id){
        return vehicleRepository.findByBrandID(id);
    }

    public Vehicle findByYear(String year) {
        return vehicleRepository.findByYear(year);
    }

    public Optional<Vehicle> findById(long id) {
        return vehicleRepository.findById(id);
    }

	public List<Vehicle> findByBrandID(long id) {
		return vehicleRepository.findByBrandID(id);
	}

	public List<Vehicle> listByBrandName(String brand) {
		return vehicleRepository.findByBrandName(brand);
	}

    public List<Vehicle> listByPriceRange(double rangeStart, double rangeEnd) {
        return vehicleRepository.findByRangeStartBetweenRangeEnd(rangeStart, rangeEnd);
    }
}
