package com.carrental.service;

import com.carrental.dto.SamochodDTO;
import com.carrental.model.Kategoria;
import com.carrental.model.Samochod;
import com.carrental.repository.KategoriaRepository;
import com.carrental.repository.SamochodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SamochodServiceTest {

    @Mock
    private SamochodRepository samochodRepository;

    @Mock
    private KategoriaRepository kategoriaRepository;

    @InjectMocks
    private SamochodService samochodService;

    private Samochod samochod;
    private Kategoria kategoria;
    private SamochodDTO samochodDTO;

    @BeforeEach
    void setUp() {
        kategoria = new Kategoria(1L, "Ekonomiczne", "Małe auta", null);
        samochod = new Samochod(1L, "Toyota", "Yaris", 2021,
                new BigDecimal("150.00"), kategoria, null);
        samochodDTO = new SamochodDTO(null, "Toyota", "Yaris", 2021,
                new BigDecimal("150.00"), 1L, null);
    }

    @Test
    void getAllSamochody_powinienZwrocicWszystkieSamochody() {
        when(samochodRepository.findAll()).thenReturn(List.of(samochod));

        List<SamochodDTO> result = samochodService.getAllSamochody();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMarka()).isEqualTo("Toyota");
        assertThat(result.get(0).getModel()).isEqualTo("Yaris");
    }

    @Test
    void getSamochodById_istniejacyId_powinienZwrocicSamochod() {
        when(samochodRepository.findById(1L)).thenReturn(Optional.of(samochod));

        SamochodDTO result = samochodService.getSamochodById(1L);

        assertThat(result.getMarka()).isEqualTo("Toyota");
        assertThat(result.getCenaZaDobe()).isEqualByComparingTo("150.00");
    }

    @Test
    void createSamochod_poprawneDTO_powinienZapisac() {
        when(kategoriaRepository.findById(1L)).thenReturn(Optional.of(kategoria));
        when(samochodRepository.save(any(Samochod.class))).thenReturn(samochod);

        SamochodDTO result = samochodService.createSamochod(samochodDTO);

        assertThat(result.getMarka()).isEqualTo("Toyota");
        verify(samochodRepository, times(1)).save(any(Samochod.class));
    }

    @Test
    void createSamochod_nieistniejacaKategoria_powinienRzucicWyjatek() {
        when(kategoriaRepository.findById(99L)).thenReturn(Optional.empty());
        samochodDTO.setIdKategorii(99L);

        assertThatThrownBy(() -> samochodService.createSamochod(samochodDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void getDostepneSamochody_powinienZwrocicDostepne() {
        when(samochodRepository.findDostepneSamochody()).thenReturn(List.of(samochod));

        List<SamochodDTO> result = samochodService.getDostepneSamochody();

        assertThat(result).hasSize(1);
    }
}
