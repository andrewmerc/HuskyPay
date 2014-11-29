package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

            long id = productAsJson.getLong("id");
            String name = productAsJson.getString("name");
            int cost = productAsJson.getInt("cost");
            String imageUrl = productAsJson.getString("imageURL");

            JSONObject detailsAsJson = productAsJson.getJSONObject("details");

            Map<String, String> details = new HashMap<String, String>();

            Iterator<String> keyIterator = detailsAsJson.keys();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                details.put(key, detailsAsJson.getString(key));
            }

            InputStream in = new URL(imageUrl).openStream();
            Bitmap image = BitmapFactory.decodeStream(in);

            return new ProductInfo(id, name, cost, image, details);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}