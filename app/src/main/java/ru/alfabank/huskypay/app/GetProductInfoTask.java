package ru.alfabank.huskypay.app;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public abstract class GetProductInfoTask extends AsyncTask<String, Void, ProductInfo> {

    public void getProductInfo(long barcode) {
        execute(String.format("http://backend-haskypay.rhcloud.com/rest/1/products/%d", barcode));
    }

    @Override
    protected ProductInfo doInBackground(String... urls) {

        String resultAsString = ServiceHelper.GET(urls[0]);

        try {

            JSONObject productAsJson = new JSONObject(resultAsString);

            return new ProductInfo(productAsJson);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}