package com.carrental.service;

import com.carrental.dto.WypozyczenieDTO;
import com.carrental.model.Klient;
import com.carrental.model.Pracownik;
import com.carrental.model.Samochod;
import com.carrental.model.Wypozyczenie;
import com.carrental.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WypozyczenieService {

    private final WypozyczenieRepository wypozyczenieRepository;
    private final KlientRepository klientRepository;
    private final SamochodRepository samochodRepository;
    private final PracownikRepository pracownikRepository;

    public List<WypozyczenieDTO> getAllWypozyczenia() {
        return wypozyczenieRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public WypozyczenieDTO getWypozyczenieById(Long id) {
        return toDTO(wypozyczenieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wypożyczenie o ID " + id + " nie istnieje")));
    }

    public List<WypozyczenieDTO> getAktywneWypozyczenia() {
        return wypozyczenieRepository.findByDataZwrotuIsNull()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<WypozyczenieDTO> getWypozyczenieKlienta(Long idKlienta) {
        return wypozyczenieRepository.findByKlientIdKlienta(idKlienta)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public WypozyczenieDTO createWypozyczenie(WypozyczenieDTO dto) {
        // Sprawdź czy samochód nie jest już wypożyczony
        List<Wypozyczenie> aktywne = wypozyczenieRepository.findAktywneWypozyczenieForSamochod(dto.getIdSamochodu());
        if (!aktywne.isEmpty()) {
            throw new RuntimeException("Samochód o ID " + dto.getIdSamochodu() + " jest już wypożyczony");
        }

        Klient klient = klientRepository.findById(dto.getIdKlienta())
                .orElseThrow(() -> new RuntimeException("Klient o ID " + dto.getIdKlienta() + " nie istnieje"));
        Samochod samochod = samochodRepository.findById(dto.getIdSamochodu())
                .orElseThrow(() -> new RuntimeException("Samochód o ID " + dto.getIdSamochodu() + " nie istnieje"));
        Pracownik pracownik = pracownikRepository.findById(dto.getIdPracownika())
                .orElseThrow(() -> new RuntimeException("Pracownik o ID " + dto.getIdPracownika() + " nie istnieje"));

        Wypozyczenie w = new Wypozyczenie();
        w.setKlient(klient);
        w.setSamochod(samochod);
        w.setPracownik(pracownik);
        w.setDataWypozyczenia(dto.getDataWypozyczenia() != null ? dto.getDataWypozyczenia() : LocalDate.now());
        w.setDataZwrotu(dto.getDataZwrotu());
        if (dto.getDataZwrotu() != null) {
            w.obliczSumePlatnosci();
        }

        return toDTO(wypozyczenieRepository.save(w));
    }

    /**
     * Zwrot samochodu - ustawia datę zwrotu i oblicza sumę płatności.
     */
    public WypozyczenieDTO zwrocSamochod(Long idWypozyczenia, LocalDate dataZwrotu) {
        Wypozyczenie w = wypozyczenieRepository.findById(idWypozyczenia)
                .orElseThrow(() -> new RuntimeException("Wypożyczenie o ID " + idWypozyczenia + " nie istnieje"));
        if (w.getDataZwrotu() != null) {
            throw new RuntimeException("Samochód z wypożyczenia " + idWypozyczenia + " został już zwrócony");
        }
        w.setDataZwrotu(dataZwrotu != null ? dataZwrotu : LocalDate.now());
        w.obliczSumePlatnosci();
        return toDTO(wypozyczenieRepository.save(w));
    }

    public void deleteWypozyczenie(Long id) {
        if (!wypozyczenieRepository.existsById(id)) {
            throw new RuntimeException("Wypożyczenie o ID " + id + " nie istnieje");
        }
        wypozyczenieRepository.deleteById(id);
    }

    public WypozyczenieDTO toDTO(Wypozyczenie w) {
        WypozyczenieDTO dto = new WypozyczenieDTO();
        dto.setIdWypozyczenia(w.getIdWypozyczenia());
        dto.setIdKlienta(w.getKlient().getIdKlienta());
        dto.setIdSamochodu(w.getSamochod().getIdSamochodu());
        dto.setIdPracownika(w.getPracownik().getIdPracownika());
        dto.setDataWypozyczenia(w.getDataWypozyczenia());
        dto.setDataZwrotu(w.getDataZwrotu());
        dto.setSumaPatnosci(w.getSumaPatnosci());
        dto.setImieNazwiskoKlienta(w.getKlient().getImie() + " " + w.getKlient().getNazwisko());
        dto.setOpisSamochodu(w.getSamochod().getMarka() + " " + w.getSamochod().getModel());
        dto.setImieNazwiskoPracownika(w.getPracownik().getImie() + " " + w.getPracownik().getNazwisko());
        return dto;
    }
}
