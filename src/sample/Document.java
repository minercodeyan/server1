package sample;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.StringProperty;

public class Document {

    StringProperty id;

    StringProperty name;

    StringProperty authorName;

    StringProperty clientName;

    StringProperty isAlright;

    Document(int id, String name, String authorName, String clientName, String isAlright) {

        this.id=new SimpleStringProperty(String.valueOf(id));

        this.name = new SimpleStringProperty(name);

        this.authorName = new SimpleStringProperty(authorName);

        this.clientName = new SimpleStringProperty(clientName);

        this.isAlright = new SimpleStringProperty(isAlright);

    }

    public String getId() {

        return id.get();

    }

    public String getName() {

        return name.get();

    }

    public String getAuthorName() {

        return authorName.get();

    }

    public String getClientName() {

        return clientName.get();

    }

    public String getIsAlright() {

        return isAlright.get();

    }

    public void setName(String name) {

        this.name.set(name);

    }

    public StringProperty idProperty() {

        return id;

    }

    public StringProperty nameProperty() {

        return name;

    }

    public StringProperty authorNameProperty() {

        return authorName;

    }

    public StringProperty clientNameProperty() {

        return clientName;

    }

    public StringProperty isAlrightProperty() {

        return isAlright;

    }

}