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

    private List<ProductInfo> productsInBasket = Lists.newArrayList();

    public void addToBasket(ProductInfo product) {
        productsInBasket.add(product);
    }

    public void clearBasket(){
        productsInBasket.clear();
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


}
