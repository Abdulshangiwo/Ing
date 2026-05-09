package com.carrental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KategoriaDTO {

    private Long idKategorii;

    @NotBlank(message = "Nazwa kategorii jest wymagana")
    @Size(max = 50)
    private String nazwaKategorii;

    @Size(max = 200)
    private String opis;
}
