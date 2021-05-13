package com.br.latavelhaapi.model.DTO;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleDTO {

    private String model;
    private Brand brand;
    private BigDecimal price;
    private String year;

    public VehicleDTO(Vehicle vehicle) {
        this.model = vehicle.getModel();
        this.brand = vehicle.getBrand();
        this.price = vehicle.getPrice();
        this.year = vehicle.getYear();
    }

    public String getModel() {
        return model;
    }

    public Brand getBrand() {
        return brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public static List<VehicleDTO> convert(List<Vehicle> vehicles){
        return vehicles.stream().map(VehicleDTO::new).collect(Collectors.toList());
    }
}
