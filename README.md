# MINI NEPTUN (spring-angular-app)
A neptun miniatűr változata spring backend-el és angular frontend-el megvalósítva.

## Követelmények
### Funkcionális követelmények
Közös:
- bejelentkezés

Tanulóként:
- szeretnék felvenni egy tárgyat. --> Tárgyfelvétel
- szeretném látni a tantárgyaim listáját. --> Tárgyaim listázása
- szeretnék leadni egy tárgyat. --> Tárgyleadás

Tanárként:
- szeretnék új tárgyat rögzíteni.
- szeretnék meglévő, hozzám tartozó tárgyat módosítani.
- szeretnék tárgyat törölni (csak akkor lehetséges, ha egy tanuló se vette fel, és az adott tanárhoz tartozik).
- szeretném a felhasználókat kilistázni/törölni/módosítani.

### Nem funkcionális követelmények
- Felhasználóbarát, ergonomikus elrendezés és kinézet.
- Gyors működés.
- Biztonságos működés: jelszavak tárolása, funkciókhoz való hozzáférés.
- Keresési eredmények gyors megjelenítése.

### Szerepkörök
- Tanuló: hozzáfér a tantárgyak listájához, tantárgyat felvenni és leadni tud a meglévők közül.
- Tanár: a tantárgyak listájában a hozzá tartozó tárgyakat tudja módosítani/törölni, illetve a felhasználókat módosítani/törölni

## Tervezés

### Adatbázis terv
![](https://i.ibb.co/LZY5StC/image.png "UML")

### Végpontok
- `GET /` Főoldal
- `GET /login` Bejelentkező oldal
- `POST /login` Bejelentkezés
- `GET /logout` Kijelentkezés
- `GET /persons` Felhasználók kilistázása
- `POST /persons` Új Felhasználó felvétele
- `GET /persons/:id` Felhasználó adatainak megjelenítése
- `PUT /persons/:id` Felhasználó módosítása
- `POST /persons/:id/subjects` Tantárgy felvétele felhasználóhoz
- `DELETE /persons/:id/delete` Felhasználó törlése
- `GET /subjects` Tantárgyak kilistázása
- `POST /subjects` Új tantárgy felvétele
- `GET /subjects/:id` Tantárgy adatainak megjelenítése
- `PUT /subjects/:id` Tantárgy módosítása
- `GET /subjects/:id/persons` Tantárgyhoz tartózó felhasználók kilistázása
- `DELETE /subjects/:id/delete` Tantárgy törlése
- `GET /universities` Egyetemek kilistázása
- `POST /universities` Új egyetem felvétele
- `GET /universities/:id` Egyetem adatainak megjelenítése
- `PUT /universities/:id` Egyetem módosítása
- `GET /universities/:id/subjects` Egyetemhez tartózó tárgyak kilistázása
- `DELETE /universities/:id/delete` Egyetem törlése

### Szekvenciadiagram
1 db végpont működésének leírása, mi történik, milyen lépések követik egymást (szekvenciadiagram)

## Implementáció

### Használati eset diagram
![](https://i.ibb.co/XVnTKR8/hasznalati-eset-diagram.png "Használati eset diagram")

### Fejlesztői környezet
Használt technológiák: JAVA Spring, hibernate, Maven, H2 Database, Lombok, Spring Security, Angular 8.3.5, Bootstrap
- Backend: IntelliJ IDEA / Netbeans
- Frontend: Visual Studio Code

### Könyvtárstruktúra
Backend:

![](https://i.ibb.co/8YvjjzL/struktura.png "Backend directory structure")

Frontend:

![](https://i.ibb.co/18s9Qcg/image.png "Frontend directory structure")

### Kliensoldali szolgáltatások
- Ajax hívások: az alkalmazás az oldal újratöltése nélkül kommunikál a szerverrel
- Bejelentkezés: form kitöltése után validáció, sikertelen bejelentkezés esetén hibaüzenet
- Saját tantárgyak betöltése főoldalon (Student): dinamikusan frissülő lista lista
- Intuitív működés: Tantárgyak/egyetemek törlése, módosítása, felvétele gombnyomásra
- Kijelentkezés

### Kapcsolat
Az alkalmazás Ajax REST API hívásokkal kommunikál a backend-del.

### Egy funkció bemutatása: Tantárgy felvétel
Tantárgyak listáján, az 'Add' gomb kattintására felvehetjük az adott tantárgyat:

![](https://i.ibb.co/JsmBgB2/Untitled.png "Add subject")

Folyamat:
1. onClick: addSubjectToPerson(subject) metódus hívás
2. a modellben az adott student tantárgy listájába pusholódik a tantárgy: person.subjects.push(subject)
3. backend service hívás: personService.save(person)
4. sikeres service hívás esetén a tantárgyak listája frissül
5. visszajelzés: az 'Add' gomb elhalványul, disabled állapotba kerül

![](https://i.ibb.co/9VmX1Sw/image.png "Add button")

## Felhasználói dokumentáció
- **Bejelentkezés** felhasználónév és jelszó segítségével:

![](https://i.ibb.co/VTpXTXR/image.png "login")
- **Navigációs sáv**: tartalmazza az egyes menüpontokat és a kijelentkezés gombot.

![](https://i.ibb.co/bQcv4rK/image.png "login")

- **Főoldal**: Tanulóként megjelenik a felvett tantárgyainak a listája. A lista elemek a jobb oldali 'Remove' gombbal eltávolíthatóak.

![](https://i.ibb.co/Cm0GWSC/image.png "login")

- **Tantárgyak**: 
Tanulóként kilistázva látjuk az elérhető tantárgyakat és fel is vehetjük őket a jobb oldali 'Add' gombbal

![](https://i.ibb.co/DDCCkHN/image.png "login")

Tanárként felvehetünk új tantárgyat a lista felett található 'New subject' gombbal. Az alábbi formra navigál az alkalmazás:

![](https://i.ibb.co/cbNMZ3F/image.png "login")

Tanárként kilistázva látjuk az elérhető tantárgyakat és módosíthatjuk őket a jobb oldali 'Options' gombokkal

![](https://i.ibb.co/wMBpHnQ/image.png "login")

Tantárgy módosítás: 'Modify' gomb hatására az alkalmazás egy szerkeszthető formra navigál

![](https://i.ibb.co/N9C8vqP/image.png "login")

A form alatt látható azon tanulók listája, akik felvették az adott tantárgyat.
Mentésre frissül a tárgy. A vissza gomb visszanavigál a tantárgyak listájához.

Tantárgy törlés: 'Remove' gomb hatására a tárgy törlődik

- **Felhasználók**: a 'Persons' menüt megnyitva kilistázódnak az alkalmazásban szereplő felhasználók
Tanárként módosíthatóak is a 'Modify' gombbal:

![](https://i.ibb.co/kg7Vw26/image.png "login")
![](https://i.ibb.co/ssVsbt2/image.png "login")

- **Egyetemek**: a 'Universites' menüt megnyitva kilistázódnak az egyetemek
Tanárként lehet módosítani, törölni és létrehozni is
![](https://i.ibb.co/h1YXP92/image.png "login")
![](https://i.ibb.co/7grJ60Q/image.png "login")

