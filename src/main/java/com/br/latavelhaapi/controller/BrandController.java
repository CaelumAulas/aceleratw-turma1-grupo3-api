package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public Brand add(@RequestBody Brand brand){
        return brandService.add(brand);
    }

    @GetMapping
    public List<Brand> list(){
        List<Brand> brands = brandService.list();
        return brands;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        brandService.delete(id);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable long id, @RequestBody Brand brand){
        return brandService.update(brand);
    }
}
