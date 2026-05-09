package com.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "samochody")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Samochod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_samochodu")
    private Long idSamochodu;

    @NotBlank(message = "Marka jest wymagana")
    @Size(max = 50)
    @Column(name = "marka", nullable = false, length = 50)
    private String marka;

    @NotBlank(message = "Model jest wymagany")
    @Size(max = 50)
    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Min(value = 1900, message = "Rok produkcji musi być większy niż 1900")
    @Column(name = "rok_produkcji")
    private Integer rokProdukcji;

    @NotNull(message = "Cena za dobę jest wymagana")
    @DecimalMin(value = "0.01", message = "Cena musi być większa od 0")
    @Column(name = "cena_za_dobe", nullable = false, precision = 10, scale = 2)
    private BigDecimal cenaZaDobe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kategorii", nullable = false)
    private Kategoria kategoria;

    @OneToMany(mappedBy = "samochod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wypozyczenie> wypozyczenia;
}
