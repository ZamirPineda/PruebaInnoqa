package com.innoqa.pruebainnoqa.service;

import com.innoqa.pruebainnoqa.model.Brand;
import com.innoqa.pruebainnoqa.model.PricesConsult;
import com.innoqa.pruebainnoqa.repository.BrandRepo;
import com.innoqa.pruebainnoqa.repository.PricesRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesRepo pricesRepo;

    @Autowired
    private BrandRepo brandRepo;

    @BeforeEach
    public void setup() {
        PricesConsult pricesConsult = new PricesConsult();
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");
        brandRepo.save(brand);
        pricesConsult.setBrand(brand);
        pricesConsult.setPrice("35.50");
        pricesConsult.setId(1);
        pricesConsult.setCurr("COP");
        pricesConsult.setPriority(0);
        pricesConsult.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        pricesConsult.setEndDate(LocalDateTime.parse("2020-12-31T23:59:00"));
        pricesConsult.setPriceList("35455");
        pricesConsult.setName("PRICES");
        pricesConsult.setDescription("DAS");
        pricesConsult.setProductId(35455);
        pricesRepo.save(pricesConsult);

        PricesConsult pricesConsult1 = new PricesConsult();
        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setName("ZARA");
        brandRepo.save(brand1);
        pricesConsult1.setBrand(brand1);
        pricesConsult1.setPrice("25.45");
        pricesConsult1.setId(2);
        pricesConsult1.setCurr("COP");
        pricesConsult1.setPriority(1);
        pricesConsult1.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        pricesConsult1.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));
        pricesConsult1.setPriceList("35455");
        pricesConsult1.setName("PRICES");
        pricesConsult1.setDescription("DAS");
        pricesConsult1.setProductId(35455);
        pricesRepo.save(pricesConsult1);

        PricesConsult pricesConsult2 = new PricesConsult();
        Brand brand2 = new Brand();
        brand2.setId(1);
        brand2.setName("ZARA");
        brandRepo.save(brand2);
        pricesConsult2.setBrand(brand2);
        pricesConsult2.setPrice("30.50");
        pricesConsult2.setId(3);
        pricesConsult2.setCurr("COP");
        pricesConsult2.setPriority(1);
        pricesConsult2.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        pricesConsult2.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));
        pricesConsult2.setPriceList("35455");
        pricesConsult2.setName("PRICES");
        pricesConsult2.setDescription("DAS");
        pricesConsult2.setProductId(35455);
        pricesRepo.save(pricesConsult2);

        PricesConsult pricesConsult3 = new PricesConsult();
        Brand brand3 = new Brand();
        brand3.setId(1);
        brand3.setName("ZARA");
        brandRepo.save(brand3);
        pricesConsult3.setBrand(brand3);
        pricesConsult3.setPrice("38.95");
        pricesConsult3.setId(4);
        pricesConsult3.setCurr("COP");
        pricesConsult3.setPriority(1);
        pricesConsult3.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        pricesConsult3.setEndDate(LocalDateTime.parse("2020-12-31T23:59:00"));
        pricesConsult3.setPriceList("35455");
        pricesConsult3.setName("PRICES");
        pricesConsult3.setDescription("DAS");
        pricesConsult3.setProductId(35455);
        pricesRepo.save(pricesConsult3);

    }

     @AfterEach
     public void cleanup() {
     pricesRepo.deleteAll();
     }


    @Test
    public void testBrand() throws Exception {

        String requestBody = "{\"id\": 1, \"name\": \"ZARA\"}";
        //String requestBody = "{\"id\": \"ZARA\"}";

        ResultActions result = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testRequestAt10AMOnDay14() throws Exception {
        String cadena = "1"; // Cadena ZARA
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T10:00:00"); //Fecha de petición al servicio
        ResultActions result = mockMvc.perform(get("/prices/{cadena}", cadena)
                .param("startDate", String.valueOf(startDate))
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(35.50)); //Valor esperado
    }

    @Test
    public void testRequestAt4PMOnDay14() throws Exception {
        String cadena = "1"; // Cadena ZARA
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T16:00:00"); //Fecha de petición al servicio
        ResultActions result = mockMvc.perform(get("/prices/{cadena}", cadena)
                .param("startDate", String.valueOf(startDate))
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(25.45)); //Valor esperado
    }

    @Test
    public void testRequestAt9PMOnDay14() throws Exception {
        String cadena = "1"; // Cadena ZARA
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T21:00:00"); //Fecha de petición al servicio
        ResultActions result = mockMvc.perform(get("/prices/{cadena}", cadena)
                .param("startDate", String.valueOf(startDate))
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(35.50)); //Valor esperado
    }

    @Test
    public void testRequestAt10AMOnDay15() throws Exception {
        String cadena = "1"; // Cadena ZARA
        LocalDateTime startDate = LocalDateTime.parse("2020-06-15T10:00:00"); //Fecha de petición al servicio
        ResultActions result = mockMvc.perform(get("/prices/{cadena}", cadena)
                .param("startDate", String.valueOf(startDate))
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(30.50)); //Valor esperado
    }

    @Test
    public void testRequestAt9PMOnDay16() throws Exception {
        String cadena = "1"; // Cadena ZARA
        LocalDateTime startDate = LocalDateTime.parse("2020-06-16T21:00:00"); //Fecha de petición al servicio
        ResultActions result = mockMvc.perform(get("/prices/{cadena}", cadena)
                .param("startDate", String.valueOf(startDate))
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(38.95)); //Valor esperado
    }

}
