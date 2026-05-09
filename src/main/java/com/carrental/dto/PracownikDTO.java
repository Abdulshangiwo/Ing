package com.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracownikDTO {

    private Long idPracownika;

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 50)
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 50)
    private String nazwisko;

    @Size(max = 50)
    private String stanowisko;
}
