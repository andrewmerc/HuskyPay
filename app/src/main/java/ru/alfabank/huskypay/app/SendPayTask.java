package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * @author bardyshev
 * @since 28.11.2014
 */

public class SendPayTask extends AsyncTask<String, Void, PaymentInfo> {

    private final CardInfo cardInfo;
    private final List<ProductInfo> products;

    public SendPayTask(CardInfo cardInfo, List<ProductInfo> products) {

        this.cardInfo = cardInfo;
        this.products = products;
    }

    public void send() {
        execute("http://backend-haskypay.rhcloud.com/rest/payment");
    }

    @Override
    protected PaymentInfo doInBackground(String... params) {

        JSONObject request = new JSONObject();

        try {

            request.put("card", cardInfo.toJson());

            JSONArray productsAsJson = new JSONArray();

            for (ProductInfo product : products) {

                JSONObject productAsJson = new JSONObject();
                productAsJson.put("id", product.getId());

                productsAsJson.put(productAsJson);

            }

            request.put("products", productsAsJson);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String response = ServiceHelper.POST(params[0], request);

        return responseToPaymentInfo(response);

    }

    private PaymentInfo responseToPaymentInfo(String response) {
        try {

            JSONObject paymentAsJson = new JSONObject(response);

            Map<String, BigDecimal> boughtProducts = new HashMap<String, BigDecimal>();

            JSONObject productsAsJson = paymentAsJson.getJSONObject("products");

            Iterator<String> keyIterator = productsAsJson.keys();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                boughtProducts.put(key, new BigDecimal(productsAsJson.getString(key)));
            }

            Date paymentDate = new Date(paymentAsJson.getLong("date"));

            String partnerName = paymentAsJson.getString("partnerName");

            BigDecimal paymentSum = new BigDecimal(paymentAsJson.getString("sum"));

            BigDecimal paymentVat = new BigDecimal(paymentAsJson.getString("nds"));

            String paymentId = paymentAsJson.getString("id");

            InputStream qrCodeInputStream;

            try {
                qrCodeInputStream = new URL(String.format("http://backend-haskypay.rhcloud.com/QRCodeServlet?id=%s", paymentId)).openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Bitmap checkQRCode = BitmapFactory.decodeStream(qrCodeInputStream);

            return new PaymentInfo(boughtProducts, paymentDate, partnerName, paymentSum, paymentVat, checkQRCode);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
