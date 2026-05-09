package com.carrental.repository;

import com.carrental.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {
    Optional<Klient> findByEmail(String email);
    List<Klient> findByNazwisko(String nazwisko);
    boolean existsByEmail(String email);
}
