package com.carrental.service;

import com.carrental.dto.PracownikDTO;
import com.carrental.model.Pracownik;
import com.carrental.repository.PracownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PracownikService {

    private final PracownikRepository pracownikRepository;

    public List<PracownikDTO> getAllPracownicy() {
        return pracownikRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PracownikDTO getPracownikById(Long id) {
        return toDTO(pracownikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pracownik o ID " + id + " nie istnieje")));
    }

    public PracownikDTO createPracownik(PracownikDTO dto) {
        Pracownik p = fromDTO(dto);
        return toDTO(pracownikRepository.save(p));
    }

    public PracownikDTO updatePracownik(Long id, PracownikDTO dto) {
        Pracownik p = pracownikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pracownik o ID " + id + " nie istnieje"));
        p.setImie(dto.getImie());
        p.setNazwisko(dto.getNazwisko());
        p.setStanowisko(dto.getStanowisko());
        return toDTO(pracownikRepository.save(p));
    }

    public void deletePracownik(Long id) {
        if (!pracownikRepository.existsById(id)) {
            throw new RuntimeException("Pracownik o ID " + id + " nie istnieje");
        }
        pracownikRepository.deleteById(id);
    }

    public PracownikDTO toDTO(Pracownik p) {
        return new PracownikDTO(p.getIdPracownika(), p.getImie(), p.getNazwisko(), p.getStanowisko());
    }

    public Pracownik fromDTO(PracownikDTO dto) {
        Pracownik p = new Pracownik();
        p.setImie(dto.getImie());
        p.setNazwisko(dto.getNazwisko());
        p.setStanowisko(dto.getStanowisko());
        return p;
    }
}
