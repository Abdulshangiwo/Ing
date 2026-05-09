package com.carrental.controller;

import com.carrental.dto.WypozyczenieDTO;
import com.carrental.service.WypozyczenieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/wypozyczenia")
@RequiredArgsConstructor
public class WypozyczenieController {

    private final WypozyczenieService wypozyczenieService;

    @GetMapping
    public ResponseEntity<List<WypozyczenieDTO>> getAllWypozyczenia() {
        return ResponseEntity.ok(wypozyczenieService.getAllWypozyczenia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WypozyczenieDTO> getWypozyczenie(@PathVariable Long id) {
        return ResponseEntity.ok(wypozyczenieService.getWypozyczenieById(id));
    }

    @GetMapping("/aktywne")
    public ResponseEntity<List<WypozyczenieDTO>> getAktywne() {
        return ResponseEntity.ok(wypozyczenieService.getAktywneWypozyczenia());
    }

    @GetMapping("/klient/{idKlienta}")
    public ResponseEntity<List<WypozyczenieDTO>> getWypozyczenieKlienta(@PathVariable Long idKlienta) {
        return ResponseEntity.ok(wypozyczenieService.getWypozyczenieKlienta(idKlienta));
    }

    @PostMapping
    public ResponseEntity<WypozyczenieDTO> createWypozyczenie(@Valid @RequestBody WypozyczenieDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wypozyczenieService.createWypozyczenie(dto));
    }

    @PatchMapping("/{id}/zwrot")
    public ResponseEntity<WypozyczenieDTO> zwrotSamochodu(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataZwrotu) {
        return ResponseEntity.ok(wypozyczenieService.zwrocSamochod(id, dataZwrotu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWypozyczenie(@PathVariable Long id) {
        wypozyczenieService.deleteWypozyczenie(id);
        return ResponseEntity.noContent().build();
    }
}
