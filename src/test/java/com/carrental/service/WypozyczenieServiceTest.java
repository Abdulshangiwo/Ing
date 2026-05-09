package com.carrental.service;

import com.carrental.dto.WypozyczenieDTO;
import com.carrental.model.*;
import com.carrental.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WypozyczenieServiceTest {

    @Mock private WypozyczenieRepository wypozyczenieRepository;
    @Mock private KlientRepository klientRepository;
    @Mock private SamochodRepository samochodRepository;
    @Mock private PracownikRepository pracownikRepository;

    @InjectMocks
    private WypozyczenieService wypozyczenieService;

    private Klient klient;
    private Samochod samochod;
    private Pracownik pracownik;
    private Wypozyczenie wypozyczenie;

    @BeforeEach
    void setUp() {
        Kategoria kat = new Kategoria(1L, "Sedan", "Komfortowe", null);
        klient = new Klient(1L, "Jan", "Kowalski", "jan@test.com", "500", null);
        samochod = new Samochod(1L, "VW", "Passat", 2022, new BigDecimal("250.00"), kat, null);
        pracownik = new Pracownik(1L, "Marek", "Zielinski", "Manager", null);
        wypozyczenie = new Wypozyczenie(1L, klient, samochod, pracownik,
                LocalDate.of(2024, 1, 10), null, null);
    }

    @Test
    void createWypozyczenie_dostepnySamochod_powinienUtworzycWypozyczenie() {
        WypozyczenieDTO dto = new WypozyczenieDTO(null, 1L, 1L, 1L,
                LocalDate.now(), null, null, null, null, null);

        when(wypozyczenieRepository.findAktywneWypozyczenieForSamochod(1L))
                .thenReturn(Collections.emptyList());
        when(klientRepository.findById(1L)).thenReturn(Optional.of(klient));
        when(samochodRepository.findById(1L)).thenReturn(Optional.of(samochod));
        when(pracownikRepository.findById(1L)).thenReturn(Optional.of(pracownik));
        when(wypozyczenieRepository.save(any())).thenReturn(wypozyczenie);

        WypozyczenieDTO result = wypozyczenieService.createWypozyczenie(dto);

        assertThat(result).isNotNull();
        verify(wypozyczenieRepository, times(1)).save(any());
    }

    @Test
    void createWypozyczenie_samochodJuzWypozyczony_powinienRzucicWyjatek() {
        WypozyczenieDTO dto = new WypozyczenieDTO(null, 1L, 1L, 1L,
                LocalDate.now(), null, null, null, null, null);

        when(wypozyczenieRepository.findAktywneWypozyczenieForSamochod(1L))
                .thenReturn(List.of(wypozyczenie));

        assertThatThrownBy(() -> wypozyczenieService.createWypozyczenie(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("już wypożyczony");
    }

    @Test
    void zwrocSamochod_aktywneWypozyczenie_powinienObliczSume() {
        when(wypozyczenieRepository.findById(1L)).thenReturn(Optional.of(wypozyczenie));
        when(wypozyczenieRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        WypozyczenieDTO result = wypozyczenieService.zwrocSamochod(1L, LocalDate.of(2024, 1, 15));

        assertThat(result.getDataZwrotu()).isEqualTo(LocalDate.of(2024, 1, 15));
        assertThat(result.getSumaPatnosci()).isNotNull();
        // 5 dni * 250 = 1250
        assertThat(result.getSumaPatnosci()).isEqualByComparingTo("1250.00");
    }

    @Test
    void getAktywneWypozyczenia_powinienZwrocicTylkoAktywne() {
        when(wypozyczenieRepository.findByDataZwrotuIsNull())
                .thenReturn(List.of(wypozyczenie));

        List<WypozyczenieDTO> result = wypozyczenieService.getAktywneWypozyczenia();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDataZwrotu()).isNull();
    }
}
