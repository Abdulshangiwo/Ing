-- Kategorie pojazdów
INSERT INTO kategorie (id_kategorii, nazwa_kategorii, opis) VALUES (1, 'Ekonomiczne', 'Małe, oszczędne auta do miasta');
INSERT INTO kategorie (id_kategorii, nazwa_kategorii, opis) VALUES (2, 'Sedan', 'Komfortowe sedany do jazdy miejskiej i trasowej');
INSERT INTO kategorie (id_kategorii, nazwa_kategorii, opis) VALUES (3, 'SUV', 'Przestronne auta terenowe i crossovery');
INSERT INTO kategorie (id_kategorii, nazwa_kategorii, opis) VALUES (4, 'Sportowe', 'Dynamiczne auta sportowe');

-- Samochody
INSERT INTO samochody (id_samochodu, marka, model, rok_produkcji, cena_za_dobe, id_kategorii) VALUES (1, 'Toyota', 'Yaris', 2021, 150.00, 1);
INSERT INTO samochody (id_samochodu, marka, model, rok_produkcji, cena_za_dobe, id_kategorii) VALUES (2, 'Volkswagen', 'Passat', 2022, 250.00, 2);
INSERT INTO samochody (id_samochodu, marka, model, rok_produkcji, cena_za_dobe, id_kategorii) VALUES (3, 'Toyota', 'RAV4', 2023, 350.00, 3);
INSERT INTO samochody (id_samochodu, marka, model, rok_produkcji, cena_za_dobe, id_kategorii) VALUES (4, 'BMW', 'M3', 2022, 600.00, 4);
INSERT INTO samochody (id_samochodu, marka, model, rok_produkcji, cena_za_dobe, id_kategorii) VALUES (5, 'Ford', 'Focus', 2020, 180.00, 1);

-- Klienci
INSERT INTO klienci (id_klienta, imie, nazwisko, email, telefon) VALUES (1, 'Jan', 'Kowalski', 'jan.kowalski@email.com', '500100200');
INSERT INTO klienci (id_klienta, imie, nazwisko, email, telefon) VALUES (2, 'Anna', 'Nowak', 'anna.nowak@email.com', '600200300');
INSERT INTO klienci (id_klienta, imie, nazwisko, email, telefon) VALUES (3, 'Piotr', 'Wiśniewski', 'piotr.w@email.com', '700300400');

-- Pracownicy
INSERT INTO pracownicy (id_pracownika, imie, nazwisko, stanowisko) VALUES (1, 'Marek', 'Zielinski', 'Kierownik');
INSERT INTO pracownicy (id_pracownika, imie, nazwisko, stanowisko) VALUES (2, 'Katarzyna', 'Lewandowska', 'Pracownik obsługi');
INSERT INTO pracownicy (id_pracownika, imie, nazwisko, stanowisko) VALUES (3, 'Tomasz', 'Wójcik', 'Pracownik obsługi');

-- Wypożyczenia
INSERT INTO wypozyczenia (id_wypozyczenia, id_klienta, id_samochodu, id_pracownika, data_wypozyczenia, data_zwrotu, suma_platnosci)
    VALUES (1, 1, 1, 2, '2024-01-10', '2024-01-15', 750.00);
INSERT INTO wypozyczenia (id_wypozyczenia, id_klienta, id_samochodu, id_pracownika, data_wypozyczenia, data_zwrotu, suma_platnosci)
    VALUES (2, 2, 3, 1, '2024-02-01', '2024-02-05', 1400.00);
INSERT INTO wypozyczenia (id_wypozyczenia, id_klienta, id_samochodu, id_pracownika, data_wypozyczenia, data_zwrotu, suma_platnosci)
    VALUES (3, 3, 4, 3, '2024-03-15', NULL, NULL);
