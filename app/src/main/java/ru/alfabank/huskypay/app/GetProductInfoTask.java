package ru.alfabank.huskypay.app;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

/**
* @author bardyshev
* @since 28.11.2014
*/
public abstract class GetProductInfoTask extends AsyncTask<String, Void, String> {

    public void getProductInfo(long barcode){
        execute(String.format("http://backend-haskypay.rhcloud.com/rest/123/products/%d", barcode));
    }

    @Override
    protected String doInBackground(String... urls) {
        return ServiceHelper.GET(urls[0]);
    }

    @Override
    protected void onPostExecute(String result){

        JSONObject productAsJson;
        try {
            productAsJson = new JSONObject(result);
            onResult(new ProductInfo(productAsJson.getLong("id"), productAsJson.getString("name"),
                    productAsJson.getString("details"), productAsJson.getInt("cost")));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void onResult(ProductInfo productInfo);

}



