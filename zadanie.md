# Projekt: System Zarządzania Zadaniami

## 1. Opis projektu
System pozwala użytkownikom na rejestrowanie się, logowanie oraz zarządzanie swoimi zadaniami. Użytkownicy mogą dodawać, edytować, usuwać i przeglądać swoje zadania. System składa się z dwóch mikroserwisów: **User Service** i **Task Service**.

## 2. Technologie
- **Język programowania:** Java
- **Framework:** Spring Boot
- **Baza danych:** MySQL
- **Zarządzanie zależnościami:** Maven
- **Bezpieczeństwo:** Spring Security

## 3. Architektura
+-----------------+                     +--------------------+
|                 |                     |                    |
|   User Service  | <----> (REST API)   |    Task Service    |
|                 |                     |                    |
+-----------------+                     +--------------------+
|  - Rejestracja  |                     |   - Zadania        |
|  - Logowanie    |                     |   - CRUD Operacje  |
+-----------------+                     +--------------------+

## 4. Wymagania funkcjonalne

### Użytkownicy:
- Rejestracja nowego użytkownika.
- Logowanie użytkownika.
- Wylogowanie użytkownika.

### Zadania:
- Dodawanie nowych zadań.
- Edytowanie istniejących zadań.
- Usuwanie zadań.
- Pobieranie listy wszystkich zadań.
- Pobieranie szczegółów konkretnego zadania.

## 5. Przepływ Danych

### Diagram Przepływu Danych
[ Użytkownik ]
|
| POST /api/users/register
v
+------------------+
|  User Service    |
+------------------+
|
| POST /api/users/login
v
+------------------+
|  User Service    |
|   (JWT Token)    |
+------------------+
|
| POST /api/tasks
v
+------------------+
|  Task Service    |
+------------------+
|
| GET /api/tasks
v
+------------------+
|  Task Service    |
+------------------+

## 6. Komunikacja między mikroserwisami

### a. Użytkownik
1. **Rejestracja**: Użytkownik wysyła żądanie POST do **User Service** na `/api/users/register`.
2. **Logowanie**: Użytkownik wysyła żądanie POST do **User Service** na `/api/users/login`, otrzymując token JWT.

### b. Task Service
1. Użytkownik wysyła żądanie POST do **Task Service** na `/api/tasks`, dołączając token JWT w nagłówku.
2. **Task Service** weryfikuje token, a następnie wykonuje odpowiednie operacje na zadaniach.

## 7. Weryfikacja użytkownika w Task Service

### Diagram Weryfikacji
+------------------+
|  Task Service    |
+------------------+
|  - Weryfikacja   |
|    token JWT     |
+------------------+
|
| (JWT Token)
v
+------------------+
|  User Service    |
|  (sprawdzenie    |
|  użytkownika)    |
+------------------+

## 8. Testowanie aplikacji

### a. Testy jednostkowe
- Użyj JUnit i Mockito do testowania komponentów **User Service** i **Task Service**.

## 10. Dokumentacja API
- Użyj Swaggera, aby stworzyć dokumentację dla API, co pozwoli innym łatwo zrozumieć, jak korzystać z Twojego systemu.
