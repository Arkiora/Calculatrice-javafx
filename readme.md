# TP JavaFX – Calculatrice & Liste Utilisateurs MySQL

Ce projet contient deux parties :

1. **Calculatrice JavaFX** (Partie 1)
2. **TableView JavaFX + MySQL** (Partie 2), affichant le contenu de la table `user_info`

---

## 📋 Prérequis

* Java 23 (OpenJDK ou Oracle)
* Maven wrapper (`mvnw.cmd` fourni)
* JavaFX 21 (géré par Maven)
* MySQL 8.x

---

## 🚀 Installer & Exécuter

1. Cloner / ouvrir ce projet
2. Définir `JAVA_HOME` sur votre JDK 23
3. Reload Maven (IntelliJ : icône ⟳ dans le panneau Maven)

---

## Partie 1 – Calculatrice

Pour lancer la calculatrice :

```bash
.\mvnw.cmd clean javafx:run
```

**Classes clés**

* `CalculatriceFX` : launcher JavaFX
* `CalculatorController` : gestion des boutons
* `calculatrice.fxml` + `myCSS.css`

---

## Partie 2 – Liste Utilisateurs

### 1. Préparer la base MySQL

```sql
CREATE DATABASE IF NOT EXISTS bts_sio;
USE bts_sio;
-- Importer votre dump users.sql
SOURCE users.sql;
```

### 2. Configuration JDBC

* **DbConnection.java**
  Singleton JDBC pour `jdbc:mysql://localhost:3306/bts_sio`

### 3. Entité `Utilisateur`

```java
public class Utilisateur {
  private IntegerProperty id;
  private StringProperty  name, email, departement;
  // idProperty(), nameProperty(), emailProperty(), departementProperty()
}
```

### 4. Vue FXML et contrôleur

* **users.fxml** : `TableView` avec colonnes
  ID, Nom, Email, Département
* **UserController.java**

  * `SELECT ID, name, email, departement FROM user_info`
  * Remplit un `ObservableList<Utilisateur>`
  * Bouton **Détails…** ouvre `DetailsUtilisateur`

### 5. Détails utilisateur

* **DetailsUtilisateur.java** :
  Affiche un `Alert` avec
  ID, Email, Département

### 6. Launcher `UserApp`

Pour lancer :

```bash
.\mvnw.cmd clean -DmainClass=fr.bts.sio.tp_calculatrice.UserApp javafx:run
```

ou modifier `<mainClass>` dans `pom.xml` sur `UserApp` et simplement :

```bash
.\mvnw.cmd clean javafx:run
```

---

## 🛠 POM & compilation

Le projet compile **sur le classpath**, sans **module-info.java**, grâce à cette configuration Maven :

```xml
<properties>
  <maven.compiler.release>23</maven.compiler.release>
  <javafx.version>21</javafx.version>
</properties>

<dependencies>
  <!-- JavaFX -->
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>${javafx.version}</version>
  </dependency>
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>${javafx.version}</version>
  </dependency>

  <!-- MySQL connector -->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <!-- Lance l’app JavaFX -->
    <plugin>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-maven-plugin</artifactId>
      <version>0.0.8</version>
      <configuration>
        <mainClass>fr.bts.sio.tp_calculatrice.CalculatriceFX</mainClass>
      </configuration>
    </plugin>

    <!-- Compilation sur classpath -->
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
      <configuration>
        <release>${maven.compiler.release}</release>
      </configuration>
    </plugin>
  </plugins>
</build>
```

---
