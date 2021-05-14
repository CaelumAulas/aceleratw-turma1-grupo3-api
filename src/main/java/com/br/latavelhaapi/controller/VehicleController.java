package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.DTO.VehicleForm;
import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid VehicleForm vehicleForm){
        Vehicle vehicle = vehicleForm.convert(vehicleService);
        vehicleService.add(vehicle);
        return new ResponseEntity(new Response(true, "Vehcile registred successfully"),
                HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> list() {
        List<Vehicle> vehicles = vehicleService.list();
        if(vehicles == null){
            return new ResponseEntity<>(
                    new Response(false, "Not found vehicles"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<Vehicle> findVehicle = vehicleService.findById(id);
        if(findVehicle.isPresent()){
            vehicleService.delete(id);
            return new ResponseEntity<Vehicle>(findVehicle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found vehicle with id:" + id),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Vehicle vehicle, @PathVariable("id") Long id) {
        Optional<Vehicle> findVehicle = vehicleService.findById(id);
        if(findVehicle.isPresent()){
            vehicle.setID(findVehicle.get().getID());
            vehicleService.update(vehicle);
            return new ResponseEntity<Vehicle>(findVehicle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found user with id: " + id),
                HttpStatus.NOT_FOUND);
    }
}
