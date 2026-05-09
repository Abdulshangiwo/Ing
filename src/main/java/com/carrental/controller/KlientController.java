package com.carrental.controller;

import com.carrental.dto.KlientDTO;
import com.carrental.service.KlientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klienci")
@RequiredArgsConstructor
public class KlientController {

    private final KlientService klientService;

    @GetMapping
    public ResponseEntity<List<KlientDTO>> getAllKlienci() {
        return ResponseEntity.ok(klientService.getAllKlienci());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlientDTO> getKlient(@PathVariable Long id) {
        return ResponseEntity.ok(klientService.getKlientById(id));
    }

    @PostMapping
    public ResponseEntity<KlientDTO> createKlient(@Valid @RequestBody KlientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(klientService.createKlient(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KlientDTO> updateKlient(@PathVariable Long id,
                                                   @Valid @RequestBody KlientDTO dto) {
        return ResponseEntity.ok(klientService.updateKlient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKlient(@PathVariable Long id) {
        klientService.deleteKlient(id);
        return ResponseEntity.noContent().build();
    }
}
