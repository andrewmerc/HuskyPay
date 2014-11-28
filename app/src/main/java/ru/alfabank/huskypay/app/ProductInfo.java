package ru.alfabank.huskypay.app;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public class ProductInfo {
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
}
