package ru.alfabank.huskypay.app;

import java.io.Serializable;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public class ProductInfo implements Serializable {

    private final long id;
    private final String name;
    private final String details;
    private final int cost;

    public ProductInfo(long id, String name, String details, int cost) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getCost() {
        return cost;
    }
}
