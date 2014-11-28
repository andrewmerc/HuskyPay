package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ProductInfoActivity extends Activity {

    public static final String PRODUCT_EXTRAS = "product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fillProductInfo((ProductInfo) getIntent().getExtras().getSerializable(PRODUCT_EXTRAS));
    }

    private void fillProductInfo(ProductInfo product) {

        ((TextView) findViewById(R.id.product_name)).setText(product.getName());
        ((TextView) findViewById(R.id.product_cost_field)).setText(String.valueOf(product.getCost()));
        ((TextView) findViewById(R.id.product_details_field)).setText(product.getDetails());
    }

    public void handelAddToBasketClick(View view) {



    }
}