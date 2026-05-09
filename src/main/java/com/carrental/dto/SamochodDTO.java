package com.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamochodDTO {

    private Long idSamochodu;

    @NotBlank(message = "Marka jest wymagana")
    @Size(max = 50)
    private String marka;

    @NotBlank(message = "Model jest wymagany")
    @Size(max = 50)
    private String model;

    @Min(1900)
    private Integer rokProdukcji;

    @NotNull(message = "Cena za dobę jest wymagana")
    @DecimalMin("0.01")
    private BigDecimal cenaZaDobe;

    @NotNull(message = "ID kategorii jest wymagane")
    private Long idKategorii;

    // Tylko do odczytu - wypełniane przez serwer
    private String nazwaKategorii;
}
