#!/bin/bash
# ==============================================
# SKRYPT WGRYWAJĄCY PROJEKT NA GITHUB
# Uruchom: bash upload_to_github.sh
# ==============================================

echo "🚗 Wgrywanie projektu Car Rental na GitHub..."

# Sprawdź czy git jest zainstalowany
if ! command -v git &> /dev/null; then
    echo "❌ Git nie jest zainstalowany. Pobierz z: https://git-scm.com"
    exit 1
fi

# Skonfiguruj git (jeśli jeszcze nie skonfigurowany)
git config --global user.email "kacperkrakowski09@gmail.com"
git config --global user.name "Abdulshangiwo"

echo "📁 Inicjalizacja repozytorium..."
git init
git add .
git commit -m "Inicjalny commit - System zarządzania wypożyczalnią samochodów

- Modele encji JPA: Kategoria, Samochod, Klient, Pracownik, Wypozyczenie
- Repozytoria Spring Data JPA z custom queries
- Serwisy z logiką biznesową
- REST API kontrolery (CRUD + operacje biznesowe)
- DTO dla wszystkich encji
- Walidacja danych (@Valid, @NotBlank, @Email)
- Globalna obsługa błędów
- Testy jednostkowe (Mockito) i integracyjne (MockMvc)
- Baza H2 in-memory z danymi inicjalnymi
- Pełna dokumentacja README"

echo "🔗 Łączenie z repozytorium GitHub..."
git branch -M main
git remote add origin https://github.com/Abdulshangiwo/Ing.git

echo "⬆️  Wysyłanie na GitHub..."
git push -u origin main --force

echo ""
echo "✅ GOTOWE! Projekt jest na GitHubie:"
echo "   https://github.com/Abdulshangiwo/Ing"
echo ""
echo "📌 Pamiętaj: GitHub może poprosić o token dostępu."
echo "   Wejdź na: https://github.com/settings/tokens"
echo "   → Generate new token (classic) → zaznacz 'repo' → skopiuj token"
echo "   Użyj go jako hasło przy pytaniu o password."
