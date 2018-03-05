package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class TableViewStuff {

    private final SimpleStringProperty type;
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;
    private ImageView image;

    public TableViewStuff(String type, String name, String description, ImageView img) {
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.image = img;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
