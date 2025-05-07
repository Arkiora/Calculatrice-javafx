````markdown
# TP JavaFX – Calculatrice & TableView MySQL

Ce projet JavaFX, structuré en deux parties, illustre :
1. **Partie 1 – Calculatrice** : une simple application JavaFX pour effectuer des opérations arithmétiques  
2. **Partie 2 – TableView + MySQL** : affichage d’une liste d’utilisateurs stockés dans une base MySQL

---

## 📋 Prérequis

- Java 23 (OpenJDK ou Oracle)  
- Maven 3.x  
- JavaFX 21 (les dépendances sont gérées par Maven)  
- (Optionnel) JavaFX SDK 21 pour l’édition FXML dans l’IDE  
- MySQL 8.x pour la Partie 2  

---

## 🚀 Installation & exécution

1. **Cloner le dépôt**  
   ```bash
   git clone <URL_DU_PROJET>
   cd tp_calculatrice
````

2. **Compiler & lancer la calculatrice (Partie 1)**

   ```bash
   mvn clean javafx:run
   ```

3. **Importer la base MySQL (Partie 2)**

   ```sql
   CREATE DATABASE IF NOT EXISTS bts_sio;
   USE bts_sio;
   SOURCE users.sql;
   ```

   – ou depuis l’invite :

   ```bash
   mysql -u root -p bts_sio < users.sql
   ```

4. **Compiler & lancer l’application Utilisateurs**

    * Créer la classe `UserApp.java` (voir section “Intégration Partie 2”)
    * Puis :

      ```bash
      mvn clean javafx:run -DmainClass=fr.bts.sio.tp_calculatrice.UserApp
      ```

---

## 📂 Structure du projet

```
tp_calculatrice/
├─ pom.xml
├─ users.sql             # dump MySQL (Partie 2)
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │   ├─ module-info.java
│  │  │   └─ fr/bts/sio/tp_calculatrice/
│  │  │       ├─ CalculatriceFX.java
│  │  │       ├─ CalculatorController.java
│  │  │       ├─ DbConnection.java         # Partie 2
│  │  │       ├─ Utilisateur.java          # Partie 2
│  │  │       ├─ UserController.java       # Partie 2
│  │  │       └─ DetailsUtilisateur.java   # Partie 2
│  │  └─ resources/
│  │      └─ fr/bts/sio/tp_calculatrice/
│  │          └─ view/
│  │              ├─ calculatrice.fxml
│  │              ├─ myCSS.css
│  │              └─ users.fxml             # Partie 2
└─ README.md
```

---

## 📖 Partie 1 – Calculatrice JavaFX

* **pom.xml** utilise le plugin `javafx-maven-plugin` pour lancer l’application
* **module-info.java**

  ```java
  module tp_calculatrice {
      requires javafx.controls;
      requires javafx.fxml;
      opens fr.bts.sio.tp_calculatrice to javafx.fxml;
      opens fr.bts.sio.tp_calculatrice.view to javafx.fxml;
      exports fr.bts.sio.tp_calculatrice;
  }
  ```
* **CalculatriceFX.java** : classe `Application` chargeant `calculatrice.fxml`
* **CalculatorController.java** : gère les clics chiffres/opérateurs/égal/clear
* **calculatrice.fxml** : `GridPane` 4×5, `TextField` + Buttons
* **myCSS.css** : style minimal (taille, padding, hover)

---

## 🛠 Partie 2 – TableView & MySQL

1. **DbConnection.java**

    * Singleton JDBC (`jdbc:mysql://localhost:3306/bts_sio`)
2. **Utilisateur.java**

    * POJO JavaFX avec `IntegerProperty` et `StringProperty`
3. **users.fxml**

    * `TableView<Utilisateur>` + colonnes ID, Nom, Prénom, Email + bouton “Détails…”
4. **UserController.java**

    * Chargement des données depuis MySQL → `ObservableList` → `tableView.setItems(...)`
    * Méthode `onDetailClicked` ouvre une alerte avec **DetailsUtilisateur.show(u)**
5. **DetailsUtilisateur.java**

    * Affiche un `Alert` JavaFX montrant les propriétés de l’utilisateur sélectionné

---

## 🔧 Personnalisation & extensions possibles

* **Calculatrice**

    * Gestion de la virgule, raccourcis clavier, historique
* **TableView**

    * Recherche, filtres, édition inline, pagination
* **UX**

    * Menu principal pour basculer entre “Calculatrice” et “Utilisateurs”
    * Bundle de propriétés pour i18n (FR/EN)

---

