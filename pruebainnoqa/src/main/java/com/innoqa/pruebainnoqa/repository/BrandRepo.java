package com.innoqa.pruebainnoqa.repository;

import com.innoqa.pruebainnoqa.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    //Traer información de la cadena
    @NonNull
    List<Brand> findAll();
}
