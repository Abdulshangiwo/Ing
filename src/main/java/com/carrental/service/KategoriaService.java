package com.carrental.service;

import com.carrental.dto.KategoriaDTO;
import com.carrental.model.Kategoria;
import com.carrental.repository.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KategoriaService {

    private final KategoriaRepository kategoriaRepository;

    public List<KategoriaDTO> getAllKategorie() {
        return kategoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public KategoriaDTO getKategoriaById(Long id) {
        Kategoria k = kategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategoria o ID " + id + " nie istnieje"));
        return toDTO(k);
    }

    public KategoriaDTO createKategoria(KategoriaDTO dto) {
        if (kategoriaRepository.existsByNazwaKategorii(dto.getNazwaKategorii())) {
            throw new RuntimeException("Kategoria o nazwie '" + dto.getNazwaKategorii() + "' już istnieje");
        }
        Kategoria k = new Kategoria();
        k.setNazwaKategorii(dto.getNazwaKategorii());
        k.setOpis(dto.getOpis());
        return toDTO(kategoriaRepository.save(k));
    }

    public KategoriaDTO updateKategoria(Long id, KategoriaDTO dto) {
        Kategoria k = kategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategoria o ID " + id + " nie istnieje"));
        k.setNazwaKategorii(dto.getNazwaKategorii());
        k.setOpis(dto.getOpis());
        return toDTO(kategoriaRepository.save(k));
    }

    public void deleteKategoria(Long id) {
        if (!kategoriaRepository.existsById(id)) {
            throw new RuntimeException("Kategoria o ID " + id + " nie istnieje");
        }
        kategoriaRepository.deleteById(id);
    }

    public KategoriaDTO toDTO(Kategoria k) {
        KategoriaDTO dto = new KategoriaDTO();
        dto.setIdKategorii(k.getIdKategorii());
        dto.setNazwaKategorii(k.getNazwaKategorii());
        dto.setOpis(k.getOpis());
        return dto;
    }

    public Kategoria fromDTO(KategoriaDTO dto) {
        Kategoria k = new Kategoria();
        k.setIdKategorii(dto.getIdKategorii());
        k.setNazwaKategorii(dto.getNazwaKategorii());
        k.setOpis(dto.getOpis());
        return k;
    }
}
