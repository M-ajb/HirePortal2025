# HirePortal2025

Een full-stack vacatureplatform gebouwd met **Spring Boot**, waarmee werkzoekenden en werkgevers met elkaar verbonden worden.

---

## 📸 Screenshots

### 1. 🔐 Startpagina & Inlog/Registratie
![B52EACF5-12D2-478F-B394-D8A065789BCC.jpeg](screenshots%2FB52EACF5-12D2-478F-B394-D8A065789BCC.jpeg)

De beginscherm van de applicatie, waar zowel recruiters als jobseekers kunnen inloggen of registreren.

---

### 2. 👤 Profiel aanmaken (Job Seeker)
![9AB09E35-A91C-4D8F-9B0A-A539DAF254D5.jpeg](screenshots%2F9AB09E35-A91C-4D8F-9B0A-A539DAF254D5.jpeg)

Nadat een jobseeker zich registreert, wordt hij doorgestuurd naar deze pagina om zijn/haar gegevens, profielfoto en cv te uploaden.

---

### 3. 📄 Nieuwe vacature plaatsen (Recruiter)
![6E32C193-A74E-4018-84DA-3079F31269CB.jpeg](screenshots%2F6E32C193-A74E-4018-84DA-3079F31269CB.jpeg)

Recruiters kunnen hier een nieuwe vacature aanmaken met details zoals functietitel, locatie, beschrijving en salaris

---

### 4. 📋 Vacature weergave (Recruiter)
![9DBB706E-4E3F-41B6-8C05-CC9061828938.jpeg](screenshots%2F9DBB706E-4E3F-41B6-8C05-CC9061828938.jpeg)

De recruiter ziet hier de geplaatste vacature met volledige beschrijving, en heeft opties om de vacature te bewerken of te verwijderen.

---

### 5. 🧑‍💼 Vacature bekijken & solliciteren (Job Seeker)
![60C04CFE-882A-43BC-BA1B-0F8745D4EAB3.jpeg](screenshots%2F60C04CFE-882A-43BC-BA1B-0F8745D4EAB3.jpeg)

De jobseeker kan de vacature bekijken, solliciteren op de functie of deze opslaan voor later.

---

---

## 🧱 Technologie Stack

| Onderdeel     | Technologie                    |
|---------------|---------------------------------|
| **Backend**   | Java 17+, Spring Boot 3         |
| **Frontend**  | Thymeleaf, Bootstrap, jQuery    |
| **Database**  | MySQL                           |
| **Security**  | Spring Security                 |
| **Build tool**| Maven                           |

---

## ✅ Functionaliteiten

### Voor Werkzoekenden:
- Profiel aanmaken en aanpassen
- Vacatures zoeken
- Solliciteren op vacatures
- Sollicitatiestatus bekijken
- CV uploaden/downloaden

### Voor Recruiters:
- Bedrijf aanmaken
- Vacatures plaatsen en beheren
- Sollicitaties ontvangen en beheren
- Kandidaten benaderen

---

---

## ⚙️ Installatie & Setup

1. **Clone de repository**

Gebruik Git om de repository lokaal te klonen:

```bash
git clone https://github.com/M-ajb/HirePortal2025.git
```

2. **Maak de MySQL-database aan**

Open MySQL Workbench of een andere MySQL-client en voer dit commando uit:

```sql
CREATE DATABASE jobportal;
```

Zorg ervoor dat de naam van de database **precies overeenkomt** met wat je hebt ingevuld in `application.properties`.

3. **Configureer je `application.properties`**

Ga naar `src/main/resources/application.properties` en vul je eigen databasegegevens in:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=JOUW_GEBRUIKERSNAAM
spring.datasource.password=JOUW_WACHTWOORD
spring.jpa.hibernate.ddl-auto=update
```

4. **Open het project in IntelliJ en run**

- Open het project in IntelliJ IDEA
- Run `HirePortal2025Application.java` via de groene run-knop

5. **Bezoek de webapp in je browser**

Ga naar:

```
http://localhost:8080
```

Daar kun je de applicatie gebruiken als werkzoekende of recruiter.




