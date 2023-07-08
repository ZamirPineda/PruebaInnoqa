package com.innoqa.pruebainnoqa.util;

import com.innoqa.pruebainnoqa.model.PricesConsult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<PricesConsult> utilLogic(List<PricesConsult> priceAll, String date, String cadena) {
        List<PricesConsult> response = new ArrayList<>();
        List<PricesConsult> responsePrio = new ArrayList<>();
        // Validación de data vacia o nula
        if (validateNull(priceAll) && validateNull(date) && validateNull(cadena)) {
            // Recorrer la lista de la BD
            for (int i = 0; i < priceAll.size(); i++) {
                int priority = priceAll.get(i).getPriority();
                LocalDateTime startDate = LocalDateTime.parse(priceAll.get(i).getStartDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                LocalDateTime endDate = LocalDateTime.parse(priceAll.get(i).getEndDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                LocalDateTime dateInput = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                // Validamos si la fecha de entrada esta dentro de algun parametro de las listas dentro de la BD
                boolean isDateInRange = startDate.isBefore(dateInput) && endDate.isAfter(dateInput);
                if (isDateInRange) {
                    String price = priceAll.get(i).getPrice();
                    LocalDateTime end = priceAll.get(i).getEndDate();
                    Integer pr = priceAll.get(i).getProductId();
                    PricesConsult pricesConsult = new PricesConsult();
                    PricesConsult pricesConsultPrio = new PricesConsult(); // Segunda instancia solo para validar la prioridad
                    pricesConsultPrio.setPriority(priority);
                    responsePrio.add(pricesConsultPrio);
                    pricesConsult.setPrice(price);
                    pricesConsult.setStartDate(dateInput);
                    pricesConsult.setEndDate(end);
                    pricesConsult.setProductId(pr);
                    pricesConsult.setId(Integer.valueOf(cadena.substring(0, 1)));
                    if (response.isEmpty()) { // Validamos si es la primera vez que creamos el objeto response
                        response.add(pricesConsult); // Agregar el primer objeto a la lista vacía
                    } else {
                        PricesConsult pricesConsultVal = responsePrio.get(0);
                        if (pricesConsultVal.getPriority() == 0) {
                            response.clear(); // Limpiar la lista si el objeto actual tiene prioridad 0
                        }
                        response.add(pricesConsult); // Devolvemos la lista final
                    }
                }
            }
        }
        return response;
    }

    private static boolean validateNull(List<PricesConsult> priceAll) {
        boolean resp = false;
        if (priceAll != null || !priceAll.isEmpty()) {
            resp = true;
        }
        return resp;
    }
    private static boolean validateNull(String param) {
        boolean resp = false;
        if (param != null || !param.isEmpty()) {
            resp = true;
        }
        return resp;
    }
}
