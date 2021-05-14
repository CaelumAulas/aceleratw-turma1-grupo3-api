package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.BrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Add a new Brand")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered brand", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
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

    @ApiOperation(value = "Finds a list brands")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list brands", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
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


    @ApiOperation(value = "Delete a brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the a brand deleted", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        brandService.delete(id);
    }

    @ApiOperation(value = "Edit brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the brand who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
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