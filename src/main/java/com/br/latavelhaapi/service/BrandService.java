package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    //ADD
    public Object add(Brand brand){
        return brandRepository.save(brand);
    }

    //List
    public List<Brand> list(){
        return brandRepository.findAll();
    }

    //Deletar
    public Brand delete(long id){
        Brand brand = brandRepository.findByID(id);
        brandRepository.delete(brand);
        return brand;
    }

    //Atualizar
    public Object update(Brand brand){
        return brandRepository.save(brand);
    }
}
