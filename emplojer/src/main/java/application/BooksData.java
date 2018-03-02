package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class BooksData {

    private final StringProperty ID;
    private final StringProperty tittle;
    private final StringProperty author;
    private final StringProperty type;


    public BooksData(String id, String tittle, String author, String type) {
        this.ID = new SimpleStringProperty(id);
        this.tittle = new SimpleStringProperty(tittle);
        this.author = new SimpleStringProperty(author);
        this.type = new SimpleStringProperty(type);


    }

    public String getID() {
        return (String) this.ID.get();
    }

    public String getTittle() {
        return (String) this.tittle.get();
    }

    public String getAuthor() {
        return (String) this.author.get();
    }

    public String getType() {

        return (String) this.type.get();
    }


    public void setID(String value) {
        this.ID.set(value);
    }

    public void setTittle(String value) {
        this.tittle.set(value);
    }

    public void setAuthor(String value) {
        this.author.set(value);
    }

    public void setType(String value) {
        this.type.set(value);
    }


    public StringProperty idProperty() {
        return this.ID;
    }

    public StringProperty tittleProperty() {
        return this.tittle;
    }

    public StringProperty authorProperty() {
        return this.author;
    }

    public StringProperty typeProperty() {
        return this.type;
    }


}



