package com.carrental.service;

import com.carrental.dto.KlientDTO;
import com.carrental.model.Klient;
import com.carrental.repository.KlientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KlientServiceTest {

    @Mock
    private KlientRepository klientRepository;

    @InjectMocks
    private KlientService klientService;

    private Klient klient;
    private KlientDTO klientDTO;

    @BeforeEach
    void setUp() {
        klient = new Klient(1L, "Jan", "Kowalski", "jan@test.com", "500100200", null);
        klientDTO = new KlientDTO(null, "Jan", "Kowalski", "jan@test.com", "500100200");
    }

    @Test
    void getAllKlienci_powinienZwrocicListeKlientow() {
        when(klientRepository.findAll()).thenReturn(List.of(klient));

        List<KlientDTO> result = klientService.getAllKlienci();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getImie()).isEqualTo("Jan");
        assertThat(result.get(0).getNazwisko()).isEqualTo("Kowalski");
    }

    @Test
    void getKlientById_istniejacyId_powinienZwrocicKlienta() {
        when(klientRepository.findById(1L)).thenReturn(Optional.of(klient));

        KlientDTO result = klientService.getKlientById(1L);

        assertThat(result.getImie()).isEqualTo("Jan");
        assertThat(result.getEmail()).isEqualTo("jan@test.com");
    }

    @Test
    void getKlientById_nieistniejacyId_powinienRzucicWyjatek() {
        when(klientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> klientService.getKlientById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createKlient_nowyEmail_powinienZapisacKlienta() {
        when(klientRepository.existsByEmail("jan@test.com")).thenReturn(false);
        when(klientRepository.save(any(Klient.class))).thenReturn(klient);

        KlientDTO result = klientService.createKlient(klientDTO);

        assertThat(result.getImie()).isEqualTo("Jan");
        verify(klientRepository, times(1)).save(any(Klient.class));
    }

    @Test
    void createKlient_duplikatEmail_powinienRzucicWyjatek() {
        when(klientRepository.existsByEmail("jan@test.com")).thenReturn(true);

        assertThatThrownBy(() -> klientService.createKlient(klientDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("jan@test.com");

        verify(klientRepository, never()).save(any());
    }

    @Test
    void deleteKlient_istniejacyId_powinienUsunac() {
        when(klientRepository.existsById(1L)).thenReturn(true);

        klientService.deleteKlient(1L);

        verify(klientRepository, times(1)).deleteById(1L);
    }
}
