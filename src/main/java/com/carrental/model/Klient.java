package com.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "klienci")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private Long idKlienta;

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 50)
    @Column(name = "imie", nullable = false, length = 50)
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 50)
    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;

    @Email(message = "Niepoprawny format adresu email")
    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 15)
    @Column(name = "telefon", length = 15)
    private String telefon;

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wypozyczenie> wypozyczenia;
}
