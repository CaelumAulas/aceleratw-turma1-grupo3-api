package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.DTO.VehicleDTO;
import com.br.latavelhaapi.model.DTO.VehicleForm;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Vehicle add(@RequestBody @Valid VehicleForm vehicleForm){
        Vehicle vehicle = vehicleForm.convert(vehicleService);
        vehicleService.add(vehicle);
        return vehicle;
    }

    @GetMapping
    public List<VehicleDTO> list(){
        List<Vehicle> vehicles = vehicleService.list();
        return VehicleDTO.convert(vehicles);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        vehicleService.delete(id);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable long id, @RequestBody @Valid VehicleForm vehicleForm){
        Vehicle vehicle = vehicleForm.convert(vehicleService);
        return vehicleService.update(vehicle);
    }
}
