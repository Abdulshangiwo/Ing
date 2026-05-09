package com.carrental.controller;

import com.carrental.dto.PracownikDTO;
import com.carrental.service.PracownikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pracownicy")
@RequiredArgsConstructor
public class PracownikController {

    private final PracownikService pracownikService;

    @GetMapping
    public ResponseEntity<List<PracownikDTO>> getAllPracownicy() {
        return ResponseEntity.ok(pracownikService.getAllPracownicy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracownikDTO> getPracownik(@PathVariable Long id) {
        return ResponseEntity.ok(pracownikService.getPracownikById(id));
    }

    @PostMapping
    public ResponseEntity<PracownikDTO> createPracownik(@Valid @RequestBody PracownikDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pracownikService.createPracownik(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PracownikDTO> updatePracownik(@PathVariable Long id,
                                                         @Valid @RequestBody PracownikDTO dto) {
        return ResponseEntity.ok(pracownikService.updatePracownik(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePracownik(@PathVariable Long id) {
        pracownikService.deletePracownik(id);
        return ResponseEntity.noContent().build();
    }
}
