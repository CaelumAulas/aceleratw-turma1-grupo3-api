package com.br.latavelhaapi.model.DTO;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.service.VehicleService;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class VehicleForm {

    @Column
    @NotNull
    @Size(max = 50)
    private String model;

    @OneToOne
    private Brand brand;

    @Column
    @NotNull
    private BigDecimal price;

    @DateTimeFormat
    @NotNull
    private String year;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //Verificar um ponto unico sem ser o id
    public Vehicle convert(VehicleService vehicleService){
        Vehicle vehicle = vehicleService.findByYear(year);
        return new Vehicle(model,brand,price,year);
    }
}
