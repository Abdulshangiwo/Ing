package com.carrental.controller;

import com.carrental.dto.KategoriaDTO;
import com.carrental.service.KategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kategorie")
@RequiredArgsConstructor
public class KategoriaController {

    private final KategoriaService kategoriaService;

    @GetMapping
    public ResponseEntity<List<KategoriaDTO>> getAllKategorie() {
        return ResponseEntity.ok(kategoriaService.getAllKategorie());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategoriaDTO> getKategoria(@PathVariable Long id) {
        return ResponseEntity.ok(kategoriaService.getKategoriaById(id));
    }

    @PostMapping
    public ResponseEntity<KategoriaDTO> createKategoria(@Valid @RequestBody KategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kategoriaService.createKategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KategoriaDTO> updateKategoria(@PathVariable Long id,
                                                         @Valid @RequestBody KategoriaDTO dto) {
        return ResponseEntity.ok(kategoriaService.updateKategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategoria(@PathVariable Long id) {
        kategoriaService.deleteKategoria(id);
        return ResponseEntity.noContent().build();
    }
}
