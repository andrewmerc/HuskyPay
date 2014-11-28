package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;

/**
 * @author bardyshev
 * @since 29.11.2014
 */

public class SerializableBitmap implements Serializable {


    private static final long serialVersionUID = -5228835919664263905L;
    private Bitmap bitmap;

    public SerializableBitmap(Bitmap b) {
        bitmap = b;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        boolean success = bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        if (success) {
            out.write(bitmapBytes, 0, bitmapBytes.length);
        } else {
            throw new RuntimeException("could not serialize bitmap");
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        while((b = in.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

}