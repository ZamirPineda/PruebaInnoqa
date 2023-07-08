package com.innoqa.pruebainnoqa.service;

import com.innoqa.pruebainnoqa.model.PricesConsult;
import com.innoqa.pruebainnoqa.repository.PricesRepo;
import com.innoqa.pruebainnoqa.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricesController {
    private final PricesRepo pricesRepo;

    public PricesController(PricesRepo pricesRepo) {
        this.pricesRepo = pricesRepo;
    }

    //Traer todos los productos guardados
    @GetMapping
    public ResponseEntity<List<PricesConsult>> getAllProducts() {
        List<PricesConsult> prices = pricesRepo.findAll();
        return ResponseEntity.ok(prices);
    }

    //Traer el precio del producto dependiendo de la fecha enviada
    @GetMapping("/{cadena}")
    public ResponseEntity<List<PricesConsult>> getProductsByNameV2(@PathVariable String cadena,
                                                                   @RequestParam("startDate") String startDate) {
        List<PricesConsult> priceAll = pricesRepo.findAll();
        List<PricesConsult> response = Util.utilLogic(priceAll, startDate, cadena);
        return ResponseEntity.ok(response);
    }

    //Guardar un registro de un producto
    @PostMapping
    public ResponseEntity<PricesConsult> createPrice(@RequestBody PricesConsult pricesConsult) {
        PricesConsult savedPrices = pricesRepo.save(pricesConsult);
        return ResponseEntity.ok(savedPrices);
    }
}
