package com.carrental.repository;

import com.carrental.model.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long> {
    List<Pracownik> findByStanowisko(String stanowisko);
    List<Pracownik> findByNazwisko(String nazwisko);
}
