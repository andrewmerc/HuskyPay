package ru.alfabank.huskypay.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class ProductInfoActivity extends Activity {

    public static final String PRODUCT_EXTRAS = "product";
    public static final String VIEW_MODE = "view_mode";
    private ProductInfo product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
    }

    @Override
    protected void onResume() {
        super.onResume();

        product = (ProductInfo) getIntent().getExtras().getSerializable(PRODUCT_EXTRAS);

        Button actionButton = (Button) findViewById(R.id.buttonAddToBasket);
        if (VIEW_MODE.equals("acquire")) {
            actionButton.setText("Добавить в корзину");
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handelAddToBasketClick(v);
                }
            });
        } else if (VIEW_MODE.equals("view")) {
            actionButton.setText("Вернуться в корзину");
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handelBackToBasketClick(v);
                }
            });
        }
        fillProductInfo(product);

    }

    @TargetApi(Build.VERSION_CODES.L)
    private void fillProductInfo(ProductInfo product) {
//      Тут так же нужно будет раскомментить, как только появятся данные:
        ((TextView) findViewById(R.id.textProductName)).setText(product.getName());
        ((TextView) findViewById(R.id.textProductCost)).setText(String.valueOf(product.getCost()));
        //     вместо text-detail должен быть 2-мерный массив:
        //       ((TextView) findViewById(R.id.product_details_field)).setText(product.getDetails());

        TableLayout table = (TableLayout) findViewById(R.id.tableProductPatams);
        // а тут разбираем этот самый массив и складываем в table-view:
        for (int i = 0; i < 15; i++) {
            TableRow row = new TableRow(this);

            TextView param = new TextView(this);
            TextView value = new TextView(this);

            param.setMinimumWidth(100);
            param.setText("Параметр");
            value.setText("123");

            row.addView(param);
            row.addView(value);
            table.addView(row);
        }

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setMaxHeight(100);
//        Bitmap imgBitmap;
//        img.setImageBitmap(imgBitmap);
        int resID = getResources().getIdentifier("husky", "drawable", getPackageName());
        img.setImageResource(resID);
    }

    public void handelAddToBasketClick(View view) {
        ApplicationContext.INSTANCE.addToBasket(product);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void handelBackToBasketClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProductsBasketActivity.class);
        startActivity(intent);
    }
}
