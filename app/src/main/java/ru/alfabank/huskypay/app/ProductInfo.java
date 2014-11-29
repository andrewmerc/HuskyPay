package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author bardyshev
 * @since 28.11.2014
 */
public class ProductInfo implements Serializable {

    private final long id;
    private final String name;
    private final int cost;
    private final Map<String, String> details;
    private final String partnerName;
    private final Optional<SerializableBitmap> image;

    public ProductInfo(JSONObject productAsJson) {

        try {

            id = productAsJson.getLong("id");

            name = productAsJson.getString("name");

            cost = productAsJson.getInt("cost");

            JSONObject partnerAsJson = productAsJson.getJSONObject("partner");
            partnerName = partnerAsJson.getString("name");

            details = new HashMap<String, String>();
            if (!productAsJson.isNull("details")){

                JSONObject detailsAsJson = productAsJson.getJSONObject("details");

                Iterator<String> keyIterator = detailsAsJson.keys();
                while (keyIterator.hasNext()) {
                    String key = keyIterator.next();
                    details.put(key, detailsAsJson.getString(key));
                }
            }

            if (productAsJson.isNull("imageURL")){
                image = Optional.absent();
                return;
            }

            String imageUrl = productAsJson.getString("imageURL");

            InputStream in = new URL(imageUrl).openStream();
            image = Optional.of(new SerializableBitmap(BitmapFactory.decodeStream(in)));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public Optional<Bitmap> getImage() {

        return image.transform(new Function<SerializableBitmap, Bitmap>() {
            @Override
            public Bitmap apply(SerializableBitmap input) {
                return input.getBitmap();
            }
        });
    }
}
