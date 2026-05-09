package com.carrental.service;

import com.carrental.dto.KlientDTO;
import com.carrental.model.Klient;
import com.carrental.repository.KlientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KlientService {

    private final KlientRepository klientRepository;

    public List<KlientDTO> getAllKlienci() {
        return klientRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public KlientDTO getKlientById(Long id) {
        return toDTO(klientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klient o ID " + id + " nie istnieje")));
    }

    public KlientDTO createKlient(KlientDTO dto) {
        if (dto.getEmail() != null && klientRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Klient z emailem '" + dto.getEmail() + "' już istnieje");
        }
        Klient k = fromDTO(dto);
        return toDTO(klientRepository.save(k));
    }

    public KlientDTO updateKlient(Long id, KlientDTO dto) {
        Klient k = klientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klient o ID " + id + " nie istnieje"));
        k.setImie(dto.getImie());
        k.setNazwisko(dto.getNazwisko());
        k.setEmail(dto.getEmail());
        k.setTelefon(dto.getTelefon());
        return toDTO(klientRepository.save(k));
    }

    public void deleteKlient(Long id) {
        if (!klientRepository.existsById(id)) {
            throw new RuntimeException("Klient o ID " + id + " nie istnieje");
        }
        klientRepository.deleteById(id);
    }

    public KlientDTO toDTO(Klient k) {
        return new KlientDTO(k.getIdKlienta(), k.getImie(), k.getNazwisko(), k.getEmail(), k.getTelefon());
    }

    public Klient fromDTO(KlientDTO dto) {
        Klient k = new Klient();
        k.setImie(dto.getImie());
        k.setNazwisko(dto.getNazwisko());
        k.setEmail(dto.getEmail());
        k.setTelefon(dto.getTelefon());
        return k;
    }
}
