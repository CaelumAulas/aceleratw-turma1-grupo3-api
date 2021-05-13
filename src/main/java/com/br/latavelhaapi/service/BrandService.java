package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand add(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> list(){
        return brandRepository.findAll();
    }

    public void delete(long id){
        brandRepository.deleteById(id);
    }

    public Brand update(Brand brand){
        return brandRepository.save(brand);
    }

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }
}
