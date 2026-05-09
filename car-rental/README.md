# 🚗 System Zarządzania Wypożyczalnią Samochodów

Projekt zaliczeniowy z przedmiotu Inżynieria Oprogramowania.  
Aplikacja webowa REST API do obsługi wypożyczalni samochodów, zrealizowana w **Java 17 + Spring Boot + SQL (H2)**.

---

## 📋 Opis projektu

System umożliwia kompleksowe zarządzanie wypożyczalnią samochodów:

- **Samochody** – dodawanie, edycja, usuwanie pojazdów; podział na kategorie (Ekonomiczne, Sedan, SUV, Sportowe)
- **Klienci** – baza danych klientów wypożyczających pojazdy
- **Pracownicy** – rejestr pracowników obsługujących wypożyczenia
- **Wypożyczenia** – pełna obsługa procesu: wypożyczenie, zwrot, automatyczne obliczanie opłaty

---

## 🛠️ Technologie

| Technologia         | Wersja  | Opis                                  |
|---------------------|---------|---------------------------------------|
| Java                | 17      | Język programowania                   |
| Spring Boot         | 3.2.3   | Framework aplikacyjny                 |
| Spring Data JPA     | 3.2.3   | Warstwa dostępu do danych (ORM)       |
| Hibernate           | 6.x     | Implementacja JPA                     |
| H2 Database         | 2.x     | Relacyjna baza danych SQL (in-memory) |
| Lombok              | 1.18.x  | Redukcja boilerplate                  |
| JUnit 5 + Mockito   | 5.x     | Testy jednostkowe i integracyjne      |
| Maven               | 3.x     | Zarządzanie zależnościami             |

---

## 🗃️ Schemat bazy danych

```
Kategorie (1) ──< Samochody (1) ──< Wypozyczenia >── (1) Klienci
                                         │
                                    >── (1) Pracownicy
```

### Tabele:
- **Kategorie** – rodzaje pojazdów (Ekonomiczne, Sedan, SUV, Sportowe)
- **Samochody** – flota pojazdów z przypisaną kategorią i ceną za dobę
- **Klienci** – dane osób wypożyczających
- **Pracownicy** – dane pracowników obsługi
- **Wypozyczenia** – historia transakcji (kto, co, kiedy, ile)

---

## 🚀 Uruchomienie projektu

### Wymagania
- **Java 17+**
- **Maven 3.6+**

### Kroki

```bash
# 1. Sklonuj repozytorium
git clone https://github.com/Abdulshangiwo/Ing.git
cd Ing

# 2. Zbuduj projekt
mvn clean install

# 3. Uruchom aplikację
mvn spring-boot:run
```

Aplikacja startuje na: **http://localhost:8080**

### Konsola H2 (przeglądarka bazy danych)
Dostępna pod: **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:carrentaldb`
- Username: `sa`
- Password: *(puste)*

---

## 📡 API Endpoints

### Kategorie `/api/kategorie`
| Metoda | Endpoint           | Opis                    |
|--------|--------------------|-------------------------|
| GET    | `/api/kategorie`   | Lista wszystkich kategorii |
| GET    | `/api/kategorie/{id}` | Szczegóły kategorii  |
| POST   | `/api/kategorie`   | Dodaj nową kategorię    |
| PUT    | `/api/kategorie/{id}` | Zaktualizuj kategorię |
| DELETE | `/api/kategorie/{id}` | Usuń kategorię        |

### Samochody `/api/samochody`
| Metoda | Endpoint                        | Opis                        |
|--------|---------------------------------|-----------------------------|
| GET    | `/api/samochody`                | Lista wszystkich samochodów |
| GET    | `/api/samochody/{id}`           | Szczegóły samochodu         |
| GET    | `/api/samochody/dostepne`       | Tylko dostępne (nie wypożyczone) |
| GET    | `/api/samochody/szukaj?fraza=X` | Wyszukaj po marce/modelu    |
| GET    | `/api/samochody/kategoria/{id}` | Samochody danej kategorii   |
| POST   | `/api/samochody`                | Dodaj samochód              |
| PUT    | `/api/samochody/{id}`           | Zaktualizuj samochód        |
| DELETE | `/api/samochody/{id}`           | Usuń samochód               |

### Klienci `/api/klienci`
| Metoda | Endpoint           | Opis                  |
|--------|--------------------|-----------------------|
| GET    | `/api/klienci`     | Lista klientów        |
| GET    | `/api/klienci/{id}` | Szczegóły klienta    |
| POST   | `/api/klienci`     | Dodaj klienta         |
| PUT    | `/api/klienci/{id}` | Zaktualizuj klienta  |
| DELETE | `/api/klienci/{id}` | Usuń klienta         |

### Pracownicy `/api/pracownicy`
| Metoda | Endpoint              | Opis                     |
|--------|-----------------------|--------------------------|
| GET    | `/api/pracownicy`     | Lista pracowników        |
| POST   | `/api/pracownicy`     | Dodaj pracownika         |
| PUT    | `/api/pracownicy/{id}` | Zaktualizuj pracownika  |
| DELETE | `/api/pracownicy/{id}` | Usuń pracownika         |

### Wypożyczenia `/api/wypozyczenia`
| Metoda | Endpoint                              | Opis                                  |
|--------|---------------------------------------|---------------------------------------|
| GET    | `/api/wypozyczenia`                   | Lista wszystkich wypożyczeń           |
| GET    | `/api/wypozyczenia/aktywne`           | Aktywne (niezwrócone)                 |
| GET    | `/api/wypozyczenia/klient/{id}`       | Historia klienta                      |
| POST   | `/api/wypozyczenia`                   | Utwórz nowe wypożyczenie              |
| PATCH  | `/api/wypozyczenia/{id}/zwrot`        | Zwrot samochodu (oblicza kwotę)       |
| DELETE | `/api/wypozyczenia/{id}`              | Usuń wypożyczenie                     |

---

## 🧪 Testy

```bash
# Uruchom wszystkie testy
mvn test
```

Projekt zawiera:
- **Testy jednostkowe** (Mockito) – serwisy: `KlientServiceTest`, `SamochodServiceTest`, `WypozyczenieServiceTest`
- **Testy integracyjne** (MockMvc + H2) – kontrolery: `KlientControllerIntegrationTest`

---

## 📁 Struktura projektu

```
src/
├── main/
│   ├── java/com/carrental/
│   │   ├── CarRentalApplication.java   # Punkt wejścia
│   │   ├── model/                      # Encje JPA (tabele)
│   │   │   ├── Kategoria.java
│   │   │   ├── Samochod.java
│   │   │   ├── Klient.java
│   │   │   ├── Pracownik.java
│   │   │   └── Wypozyczenie.java
│   │   ├── repository/                 # Spring Data JPA repozytoria
│   │   ├── service/                    # Logika biznesowa
│   │   ├── controller/                 # REST API kontrolery
│   │   ├── dto/                        # Obiekty transferu danych
│   │   └── config/                     # Konfiguracja (obsługa błędów)
│   └── resources/
│       ├── application.properties      # Konfiguracja aplikacji
│       └── data.sql                    # Dane inicjalne
└── test/
    └── java/com/carrental/
        ├── service/                    # Testy jednostkowe serwisów
        └── controller/                 # Testy integracyjne
```

---

## 💡 Przykład użycia API

### Wypożyczenie samochodu
```json
POST /api/wypozyczenia
{
  "idKlienta": 1,
  "idSamochodu": 2,
  "idPracownika": 1,
  "dataWypozyczenia": "2024-06-01"
}
```

### Zwrot samochodu
```
PATCH /api/wypozyczenia/1/zwrot?dataZwrotu=2024-06-05
```
Odpowiedź zawiera automatycznie obliczoną `sumaPatnosci` (4 dni × cena_za_dobę).

---

## 👤 Autor

**Kacper Krakowski**  
GitHub: [@Abdulshangiwo](https://github.com/Abdulshangiwo)
