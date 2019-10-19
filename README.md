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

### Fejlesztői környezet
fejlesztői környezet bemutatása, beállítása, használt technológiák...

### Adatbázis terv
UML...

### Könyvtárstruktúra
alkalmazott könyvtárstruktúra bemutatása

### Végpontok
- `GET /` Főoldal
- `GET /login` Bejelentkező oldal
- `POST /login` Bejelentkezés
- `GET /logout` Kijelentkezés
- `GET /register` Regisztrációs oldal
- `POST /register` Regisztrációs adatok elküldése
- `GET /persons` Felhasználók kilistázása
- `POST /persons` Új Felhasználó felvétele
- `GET /persons/:id` Felhasználó adatainak megjelenítése
- `DELETE /persons/:id/delete` Felhasználó törlése
- `GET /subjects` Tantárgyak kilistázása
- `POST /subjects` Új tantárgy felvétele
- `GET /subjects/:id` Tantárgy adatainak megjelenítése
- `GET /subjects/:id/persons` Tantárgyhoz tartózó felhasználók kilistázása
- `DELETE /subjects/:id/delete` Tantárgy törlése
- `GET /universities` Egyetemek kilistázása
- `POST /universities` Új egyetem felvétele
- `GET /universities/:id` Egyetem adatainak megjelenítése
- `GET /universities/:id/subjects` Egyetemhez tartózó tárgyak kilistázása
- `DELETE /universities/:id/delete` Egyetem törlése

### Szekvenciadiagram
1 db végpont működésének leírása, mi történik, milyen lépések követik egymást (szekvenciadiagram)
