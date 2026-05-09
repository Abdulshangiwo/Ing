package com.carrental.repository;

import com.carrental.model.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Long> {

    List<Wypozyczenie> findByKlientIdKlienta(Long idKlienta);

    List<Wypozyczenie> findBySamochodIdSamochodu(Long idSamochodu);

    List<Wypozyczenie> findByPracownikIdPracownika(Long idPracownika);

    // Aktywne wypożyczenia (brak daty zwrotu)
    List<Wypozyczenie> findByDataZwrotuIsNull();

    // Zakończone wypożyczenia
    List<Wypozyczenie> findByDataZwrotuIsNotNull();

    @Query("SELECT w FROM Wypozyczenie w WHERE w.dataWypozyczenia BETWEEN :od AND :do")
    List<Wypozyczenie> findByDataWypozyczeniaRange(
            @Param("od") LocalDate od,
            @Param("do") LocalDate do_);

    @Query("SELECT w FROM Wypozyczenie w WHERE w.samochod.idSamochodu = :idSamochodu AND w.dataZwrotu IS NULL")
    List<Wypozyczenie> findAktywneWypozyczenieForSamochod(@Param("idSamochodu") Long idSamochodu);
}
