package com.carrental.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WypozyczenieDTO {

    private Long idWypozyczenia;

    @NotNull(message = "ID klienta jest wymagane")
    private Long idKlienta;

    @NotNull(message = "ID samochodu jest wymagane")
    private Long idSamochodu;

    @NotNull(message = "ID pracownika jest wymagane")
    private Long idPracownika;

    @NotNull(message = "Data wypożyczenia jest wymagana")
    private LocalDate dataWypozyczenia;

    private LocalDate dataZwrotu;

    private BigDecimal sumaPatnosci;

    // Pola wypełniane przez serwer (tylko odczyt)
    private String imieNazwiskoKlienta;
    private String opisSamochodu;
    private String imieNazwiskoPracownika;
}
