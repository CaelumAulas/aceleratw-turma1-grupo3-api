package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
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
    public ResponseEntity<?> add(@RequestBody Brand brand){
        if(brandService.existsByName(brand.getName())) {
            return new ResponseEntity<>(new Response(false, "Brand already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        brandService.add(brand);
        return new ResponseEntity(new Response(true, "Brand registred successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        List<Brand> brands = brandService.list();
        if(brands == null){
            return new ResponseEntity<>(
                    new Response(false, "Not found brands"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        brandService.delete(id);
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