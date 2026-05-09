package com.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "wypozyczenia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wypozyczenie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wypozyczenia")
    private Long idWypozyczenia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_klienta", nullable = false)
    private Klient klient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_samochodu", nullable = false)
    private Samochod samochod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pracownika", nullable = false)
    private Pracownik pracownik;

    @NotNull(message = "Data wypożyczenia jest wymagana")
    @Column(name = "data_wypozyczenia", nullable = false)
    private LocalDate dataWypozyczenia;

    @Column(name = "data_zwrotu")
    private LocalDate dataZwrotu;

    @Column(name = "suma_platnosci", precision = 10, scale = 2)
    private BigDecimal sumaPatnosci;

    /**
     * Oblicza sumę płatności na podstawie ceny za dobę i liczby dni.
     */
    public void obliczSumePlatnosci() {
        if (dataZwrotu != null && dataWypozyczenia != null && samochod != null) {
            long dni = dataWypozyczenia.until(dataZwrotu).getDays();
            if (dni <= 0) dni = 1;
            this.sumaPatnosci = samochod.getCenaZaDobe().multiply(BigDecimal.valueOf(dni));
        }
    }
}
