package com.carrental.controller;

import com.carrental.dto.SamochodDTO;
import com.carrental.service.SamochodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/samochody")
@RequiredArgsConstructor
public class SamochodController {

    private final SamochodService samochodService;

    @GetMapping
    public ResponseEntity<List<SamochodDTO>> getAllSamochody() {
        return ResponseEntity.ok(samochodService.getAllSamochody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SamochodDTO> getSamochod(@PathVariable Long id) {
        return ResponseEntity.ok(samochodService.getSamochodById(id));
    }

    @GetMapping("/dostepne")
    public ResponseEntity<List<SamochodDTO>> getDostepne() {
        return ResponseEntity.ok(samochodService.getDostepneSamochody());
    }

    @GetMapping("/szukaj")
    public ResponseEntity<List<SamochodDTO>> search(@RequestParam String fraza) {
        return ResponseEntity.ok(samochodService.searchSamochody(fraza));
    }

    @GetMapping("/kategoria/{idKategorii}")
    public ResponseEntity<List<SamochodDTO>> getByKategoria(@PathVariable Long idKategorii) {
        return ResponseEntity.ok(samochodService.getByKategoria(idKategorii));
    }

    @PostMapping
    public ResponseEntity<SamochodDTO> createSamochod(@Valid @RequestBody SamochodDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(samochodService.createSamochod(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SamochodDTO> updateSamochod(@PathVariable Long id,
                                                       @Valid @RequestBody SamochodDTO dto) {
        return ResponseEntity.ok(samochodService.updateSamochod(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSamochod(@PathVariable Long id) {
        samochodService.deleteSamochod(id);
        return ResponseEntity.noContent().build();
    }
}
