package com.innoqa.pruebainnoqa.service;

import com.innoqa.pruebainnoqa.model.Brand;
import com.innoqa.pruebainnoqa.repository.BrandRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
@Validated
public class BrandController {

    private final BrandRepo brandRepo;

    public BrandController(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }
    //Guardar una cadena
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand savedPrices = brandRepo.save(brand);
        return ResponseEntity.ok(savedPrices);
    }
}
