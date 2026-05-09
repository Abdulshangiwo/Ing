package com.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KlientDTO {

    private Long idKlienta;

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 50)
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 50)
    private String nazwisko;

    @Email(message = "Niepoprawny format email")
    @Size(max = 100)
    private String email;

    @Size(max = 15)
    private String telefon;
}
