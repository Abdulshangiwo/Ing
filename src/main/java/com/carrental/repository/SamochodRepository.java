package com.carrental.repository;

import com.carrental.model.Samochod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamochodRepository extends JpaRepository<Samochod, Long> {

    List<Samochod> findByMarka(String marka);

    List<Samochod> findByKategoriaIdKategorii(Long idKategorii);

    @Query("SELECT s FROM Samochod s WHERE s.idSamochodu NOT IN " +
           "(SELECT w.samochod.idSamochodu FROM Wypozyczenie w WHERE w.dataZwrotu IS NULL)")
    List<Samochod> findDostepneSamochody();

    @Query("SELECT s FROM Samochod s WHERE s.marka LIKE %:fraza% OR s.model LIKE %:fraza%")
    List<Samochod> searchByMarkaOrModel(@Param("fraza") String fraza);
}
