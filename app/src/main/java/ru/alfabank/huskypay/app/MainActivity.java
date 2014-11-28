package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.zxing.integration.android.IntentIntegrator;

import static com.google.zxing.client.android.Intents.Scan;


public class MainActivity extends Activity {

    private static final int SCAN_INTENT_REQUEST_CODE = 0;
    private IntentIntegrator zxingIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zxingIntegrator = new IntentIntegrator(this);
    }

    public void handleAddButtonClick(View view) {
        Intent scanIntent = zxingIntegrator.createScanIntent();
        scanIntent.putExtra(Scan.MODE, Scan.ONE_D_MODE);
        startActivityForResult(scanIntent, SCAN_INTENT_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == SCAN_INTENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                long barcode = Long.parseLong(intent.getStringExtra(Scan.RESULT));

                onBarcodeScanned(barcode);
            }
        }
    }

    private void onBarcodeScanned(long barcode) {
        new GetProductInfoTask(){

            @Override
            public void onResult(ProductInfo productInfo) {
                onProductInfoReceived(productInfo);
            }
        }.getProductInfo(barcode);
    }

    private void onProductInfoReceived(ProductInfo productInfo){

        Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);
        intent.putExtra(ProductInfoActivity.PRODUCT_EXTRAS, productInfo);
        startActivity(intent);

    }

}
