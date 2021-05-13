package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> list(){
        List<Brand> brands = brandService.list();
        if(brands == null){
            return new ResponseEntity<>(
                    new Response(false, "Not found brands"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<Brand> findBrand = brandService.findById(id);
        if(findBrand.isPresent()){
            brandService.delete(id);
            return new ResponseEntity<Brand>(findBrand.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found brand with id:" + id),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable("id") Long id) {
        Optional<Brand> findBrand = brandService.findById(id);
        if(findBrand.isPresent()){
            brand.setID(findBrand.get().getID());
            brandService.update(brand);
            return new ResponseEntity<Brand>(findBrand.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found brand with id: " + id),
                HttpStatus.NOT_FOUND);
    }
}
