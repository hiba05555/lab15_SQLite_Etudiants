# Lab 15 SQLite — Gestion des Étudiants Android

## Présentation

Application Android de gestion des étudiants utilisant une base de données SQLite embarquée. L'application implémente les opérations CRUD complètes via une architecture en couches.

---

## Structure du projet

| Package | Fichier | Rôle |
|---------|---------|------|
| classes | Etudiant.java | Modèle métier |
| util | MySQLiteHelper.java | SQLiteOpenHelper |
| service | EtudiantService.java | CRUD SQLite |
| (root) | MainActivity.java | UI + événements |

---

## Fonctionnalités

| Fonctionnalité | Description |
|---|---|
| Ajouter | Insertion étudiant en SQLite |
| Chercher | Recherche par ID |
| Supprimer | Suppression par ID |
| Logcat | Test CRUD au démarrage |

---

## Base de données SQLite

```sql
CREATE TABLE etudiant(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom TEXT,
    prenom TEXT
);
```

---

## Personnalisation HC

- DB : `hc_ecole`
- Helper : `MySQLiteHelper`
- Service : `EtudiantService`
- TAG Logcat : `HC_SQLite`
- Palette : violet foncé / orange

---

## Démonstration


https://github.com/user-attachments/assets/3a625f9d-695f-428e-9f97-d1477a1e3ec1


---

## Technologies utilisées
- Android Studio
- Java
- SQLite
- SQLiteOpenHelper
- ContentValues
- Cursor
- MaterialComponents
