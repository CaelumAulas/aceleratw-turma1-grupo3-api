package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BrandServiceTest {

    @Autowired
    BrandService brandServiceTest;

    private Brand createBrand() {
        Brand brand = new Brand();
        brand.setName("Fiat");
        return brand;
    }

    @Test
    void saveBrand() {
        Brand brandSave = brandServiceTest.add(createBrand());

        assertThat(brandSave).isNotNull();
    }

    @Test
    void checkIdBrandSave() {
        Brand brandSave = brandServiceTest.add(createBrand());

        assertThat(brandSave.getID()).isEqualTo(1);
    }

    @Test
    public void getBrandByID() {
        Brand brandSave = brandServiceTest.add(createBrand());

        Optional<Brand> found = brandServiceTest.findById(brandSave.getID());

        assertThat(found.get().getName())
                .isEqualTo(brandSave.getName());
    }
}
