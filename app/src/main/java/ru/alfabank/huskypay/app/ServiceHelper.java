package ru.alfabank.huskypay.app;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author bardyshev
 * @since 28.11.2014
 */

public class ServiceHelper {

    public static String GET(String url) {

        HttpClient httpclient = new DefaultHttpClient();

        try {

            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            InputStream inputStream = httpResponse.getEntity().getContent();

            if (inputStream == null) {
                throw new IllegalStateException("server send empty response");
            }

            return convertInputStreamToString(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder result = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        inputStream.close();

        return result.toString();

    }

}
