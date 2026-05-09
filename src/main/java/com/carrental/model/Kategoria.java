package com.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "kategorie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategorii")
    private Long idKategorii;

    @NotBlank(message = "Nazwa kategorii jest wymagana")
    @Size(max = 50, message = "Nazwa kategorii nie może przekraczać 50 znaków")
    @Column(name = "nazwa_kategorii", nullable = false, length = 50)
    private String nazwaKategorii;

    @Size(max = 200, message = "Opis nie może przekraczać 200 znaków")
    @Column(name = "opis", length = 200)
    private String opis;

    @OneToMany(mappedBy = "kategoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Samochod> samochody;
}
