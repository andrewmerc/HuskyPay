package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Map;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public class ProductInfo implements Serializable {

    private final long id;
    private final String name;
    private final int cost;
    private final SerializableBitmap image;
    private final Map<String, String> details;

    public ProductInfo(long id, String name, int cost, Bitmap image, Map<String, String> details) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.image = new SerializableBitmap(image);
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Bitmap getImage() {
        return image.getBitmap();
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
