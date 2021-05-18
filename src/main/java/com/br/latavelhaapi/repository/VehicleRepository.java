package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByID(long id);

    List<Vehicle> findByPrice(BigDecimal price);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrandID(long id);

    Vehicle findByYear(String year);

	List<Vehicle> findByBrandName(String brand);

    @Query(value = "SELECT v FROM vehicles v WHERE v.price BETWEEN rangeStart AND rangeEnd;", nativeQuery = true)
    List<Vehicle> findByRangeStartBetweenRangeEnd(@Param("rangeStart") double rangeStart,@Param("rangeEnd") double rangeEnd);
}
