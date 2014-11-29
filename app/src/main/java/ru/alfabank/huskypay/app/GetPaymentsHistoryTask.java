package ru.alfabank.huskypay.app;

import android.os.AsyncTask;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author bardyshev
 * @since 29.11.2014
 */

public class GetPaymentsHistoryTask extends AsyncTask<String, Void, Multimap<Integer, ProductInfo>> {

    private CardInfo cardInfo;

    public GetPaymentsHistoryTask(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public void getPaymentsHistory(){
        execute("http://backend-haskypay.rhcloud.com/rest/history");
    }

    @Override
    protected Multimap<Integer, ProductInfo> doInBackground(String... params) {

        String response = ServiceHelper.POST(params[0], cardInfo.toJson());

        try {

            Multimap<Integer, ProductInfo> payments = ArrayListMultimap.create();

            JSONArray paymentsAsJsonArray = new JSONArray(response);

            for (int paymentIndex = 0; paymentIndex < paymentsAsJsonArray.length(); paymentIndex++) {

                JSONObject paymentAsJson = paymentsAsJsonArray.getJSONObject(paymentIndex);

                int paymentId = paymentAsJson.getInt("id");

                JSONArray boughtProductsAsJson = paymentAsJson.getJSONArray("products");

                for (int productIndex = 0; productIndex < boughtProductsAsJson.length(); productIndex++) {
                    payments.put(paymentId, new ProductInfo(boughtProductsAsJson.getJSONObject(productIndex)));
                }

            }

            return payments;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
