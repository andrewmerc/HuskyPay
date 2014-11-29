package ru.alfabank.huskypay.app;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by atom on 28.11.2014.
 */
public class CardInfo {

    private final String number;
    private final String validThru;
    private final String cvv2;

    public CardInfo(String number, String validThru, String CVV2) {
        this.number = number;
        this.validThru = validThru;
        this.cvv2 = CVV2;
    }

    public String getNumber() {
        return number;
    }

    public String getValidThru() {
        return validThru;
    }

    public String getCvv2() {
        return cvv2;
    }

    public JSONObject toJson(){

        JSONObject cardInfoAsJson = new JSONObject();

        try {

            cardInfoAsJson.put("number", number);
            cardInfoAsJson.put("cvc", cvv2);
            cardInfoAsJson.put("expDate", validThru);

        } catch (JSONException e){
            throw new RuntimeException(e);
        }

        return cardInfoAsJson;

    }

}
