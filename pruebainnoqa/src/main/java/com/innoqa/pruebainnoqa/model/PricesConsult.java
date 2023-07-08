package com.innoqa.pruebainnoqa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES_CONSULT")
@NoArgsConstructor
@AllArgsConstructor
public class PricesConsult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceList;
    private Integer priority;
    private String price;
    private String curr;
    private Integer productId;
    @ManyToOne
    @JoinColumn(name = "BRAND_ID") // Nombre de la columna que representa la clave foránea en la tabla de PRICES_CONSULT
    private Brand brand;
}
