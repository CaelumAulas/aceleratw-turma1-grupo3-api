package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByID(long id);

    List<Vehicle> findByPrice(BigDecimal price);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrandID(long id);
}
