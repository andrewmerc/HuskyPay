package ru.alfabank.huskypay.app;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by atom on 28.11.2014.
 */
public enum ApplicationContext {
    INSTANCE;
    private CardInfo cardInfo;

    public List<ProductInfo> getProductsInBasket() {
        return productsInBasket;
    }

    private List<ProductInfo> productsInBasket = Lists.newArrayList(
            new ProductInfo(1, "Валенки", "Теплые и удобные", 100),
            new ProductInfo(2, "Портянки", "Теплые и удобные тоже", 70),
            new ProductInfo(3, "Варежки", "Теплые и удобные, как валенки", 50)
    );

    public void addToBasket(ProductInfo product){
        productsInBasket.add(product);
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


}
