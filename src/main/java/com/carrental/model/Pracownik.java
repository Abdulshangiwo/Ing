package com.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pracownicy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pracownika")
    private Long idPracownika;

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 50)
    @Column(name = "imie", nullable = false, length = 50)
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 50)
    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;

    @Size(max = 50)
    @Column(name = "stanowisko", length = 50)
    private String stanowisko;

    @OneToMany(mappedBy = "pracownik", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wypozyczenie> wypozyczenia;
}
