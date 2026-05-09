package com.carrental.repository;

import com.carrental.model.Kategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {
    Optional<Kategoria> findByNazwaKategorii(String nazwaKategorii);
    boolean existsByNazwaKategorii(String nazwaKategorii);
}
