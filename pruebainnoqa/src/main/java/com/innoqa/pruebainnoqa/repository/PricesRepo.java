package com.innoqa.pruebainnoqa.repository;

import com.innoqa.pruebainnoqa.model.PricesConsult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PricesRepo extends JpaRepository<PricesConsult, Long> {

    // Traer información de todos los productos guardados en la BD
    @NonNull
    List<PricesConsult> findAll();

}
