package dev.cat.mahmoudelbaz.vaccination;

/**
 * Created by mahmoudelbaz on 1/18/18.
 */

public class Vaccine_item {
    private int id;
    private String name;
    private int image;
    private int colorId;

    public Vaccine_item(int id, String name, int image, int colorId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.colorId = colorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
