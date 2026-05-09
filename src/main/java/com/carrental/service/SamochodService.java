package com.carrental.service;

import com.carrental.dto.SamochodDTO;
import com.carrental.model.Kategoria;
import com.carrental.model.Samochod;
import com.carrental.repository.KategoriaRepository;
import com.carrental.repository.SamochodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SamochodService {

    private final SamochodRepository samochodRepository;
    private final KategoriaRepository kategoriaRepository;

    public List<SamochodDTO> getAllSamochody() {
        return samochodRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public SamochodDTO getSamochodById(Long id) {
        return toDTO(samochodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Samochód o ID " + id + " nie istnieje")));
    }

    public List<SamochodDTO> getDostepneSamochody() {
        return samochodRepository.findDostepneSamochody()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<SamochodDTO> searchSamochody(String fraza) {
        return samochodRepository.searchByMarkaOrModel(fraza)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<SamochodDTO> getByKategoria(Long idKategorii) {
        return samochodRepository.findByKategoriaIdKategorii(idKategorii)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public SamochodDTO createSamochod(SamochodDTO dto) {
        Kategoria k = kategoriaRepository.findById(dto.getIdKategorii())
                .orElseThrow(() -> new RuntimeException("Kategoria o ID " + dto.getIdKategorii() + " nie istnieje"));
        Samochod s = new Samochod();
        s.setMarka(dto.getMarka());
        s.setModel(dto.getModel());
        s.setRokProdukcji(dto.getRokProdukcji());
        s.setCenaZaDobe(dto.getCenaZaDobe());
        s.setKategoria(k);
        return toDTO(samochodRepository.save(s));
    }

    public SamochodDTO updateSamochod(Long id, SamochodDTO dto) {
        Samochod s = samochodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Samochód o ID " + id + " nie istnieje"));
        Kategoria k = kategoriaRepository.findById(dto.getIdKategorii())
                .orElseThrow(() -> new RuntimeException("Kategoria o ID " + dto.getIdKategorii() + " nie istnieje"));
        s.setMarka(dto.getMarka());
        s.setModel(dto.getModel());
        s.setRokProdukcji(dto.getRokProdukcji());
        s.setCenaZaDobe(dto.getCenaZaDobe());
        s.setKategoria(k);
        return toDTO(samochodRepository.save(s));
    }

    public void deleteSamochod(Long id) {
        if (!samochodRepository.existsById(id)) {
            throw new RuntimeException("Samochód o ID " + id + " nie istnieje");
        }
        samochodRepository.deleteById(id);
    }

    public SamochodDTO toDTO(Samochod s) {
        SamochodDTO dto = new SamochodDTO();
        dto.setIdSamochodu(s.getIdSamochodu());
        dto.setMarka(s.getMarka());
        dto.setModel(s.getModel());
        dto.setRokProdukcji(s.getRokProdukcji());
        dto.setCenaZaDobe(s.getCenaZaDobe());
        dto.setIdKategorii(s.getKategoria().getIdKategorii());
        dto.setNazwaKategorii(s.getKategoria().getNazwaKategorii());
        return dto;
    }
}
