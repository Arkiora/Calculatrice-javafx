package fr.bts.sio.tp_calculatrice;

import javafx.beans.property.*;

public class Utilisateur {
    private final IntegerProperty id          = new SimpleIntegerProperty();
    private final StringProperty  name        = new SimpleStringProperty();
    private final StringProperty  email       = new SimpleStringProperty();
    private final StringProperty  departement = new SimpleStringProperty();

    public Utilisateur(int id, String name, String email, String departement) {
        this.id.set(id);
        this.name.set(name);
        this.email.set(email);
        this.departement.set(departement);
    }

    public IntegerProperty idProperty()            { return id; }
    public StringProperty  nameProperty()          { return name; }
    public StringProperty  emailProperty()         { return email; }
    public StringProperty  departementProperty()   { return departement; }

    public int    getId()          { return id.get(); }
    public String getName()        { return name.get(); }
    public String getEmail()       { return email.get(); }
    public String getDepartement() { return departement.get(); }
}
