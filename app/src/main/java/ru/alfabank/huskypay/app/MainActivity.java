package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import com.google.zxing.integration.android.IntentIntegrator;

import static com.google.zxing.client.android.Intents.Scan;


public class MainActivity extends Activity {

    private static final int SCAN_INTENT_REQUEST_CODE = 0;
    private IntentIntegrator zxingIntegrator;
    private ProgressBar spinner;
    private boolean showSpinner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        zxingIntegrator = new IntentIntegrator(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        spinner = (ProgressBar) findViewById(R.id.spinner);

        if (showSpinner){
            spinner.setVisibility(View.VISIBLE);
        } else {
            spinner.setVisibility(View.GONE);
        }
    }

    public void handleAddButtonClick(View view) {
        Intent scanIntent = zxingIntegrator.createScanIntent();
        scanIntent.putExtra(Scan.MODE, Scan.ONE_D_MODE);
        startActivityForResult(scanIntent, SCAN_INTENT_REQUEST_CODE);
    }

    public void handleTestButtonClick(View view) {
//        Intent intent = new Intent(this, ProductInfoActivity.class);
//        startActivity(intent);
        //onBarcodeScanned(4605246007125L);
        onBarcodeScanned(4605246004704L);
    }

    public void handleBasketButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProductsBasketActivity.class);
        startActivity(intent);
    }

    public void handleCardAddButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CardEditActivity.class);
        startActivity(intent);
    }

    public void handleHistoryButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), PaymentsHistoryActivity.class);
        startActivity(intent);
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

        showSpinner = true;

        new GetProductInfoTask() {
            @Override
            protected void onPostExecute(ProductInfo productInfo) {

                showSpinner = false;

                spinner.setVisibility(View.GONE);

                onProductInfoReceived(productInfo);
            }

        }.getProductInfo(barcode);
    }

    private void onProductInfoReceived(ProductInfo productInfo) {

        Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);
        intent.putExtra(ProductInfoActivity.PRODUCT_EXTRAS, productInfo);
        intent.putExtra(ProductInfoActivity.VIEW_MODE, "acquire");
        startActivity(intent);

    }
}
