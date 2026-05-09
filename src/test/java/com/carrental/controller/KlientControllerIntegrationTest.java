package com.carrental.controller;

import com.carrental.dto.KlientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class KlientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllKlienci_powinienZwrocic200() throws Exception {
        mockMvc.perform(get("/api/klienci"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createKlient_poprawnyDTO_powinienZwrocic201() throws Exception {
        KlientDTO dto = new KlientDTO(null, "Nowy", "Klient", "nowy@test.com", "999888777");

        mockMvc.perform(post("/api/klienci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.imie").value("Nowy"))
                .andExpect(jsonPath("$.nazwisko").value("Klient"));
    }

    @Test
    void createKlient_brakImienia_powinienZwrocic400() throws Exception {
        KlientDTO dto = new KlientDTO(null, "", "Klient", "test@test.com", null);

        mockMvc.perform(post("/api/klienci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getKlientById_nieistniejacyId_powinienZwrocic400() throws Exception {
        mockMvc.perform(get("/api/klienci/9999"))
                .andExpect(status().isBadRequest());
    }
}
