package ru.alfabank.huskypay.app;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public class ProductInfo {
    private final String name;
    private final String details;
    private final int cost;

    public ProductInfo(String name, String details, int cost) {
        this.name = name;
        this.details = details;
        this.cost = cost;
    }
}
