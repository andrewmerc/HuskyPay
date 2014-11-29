package ru.alfabank.huskypay.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.Map;


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

        //Toast.makeText(getApplicationContext(), VIEW_MODE, Toast.LENGTH_LONG);
        Button actionButton = (Button) findViewById(R.id.buttonAddToBasket);
        String viewMode = getIntent().getExtras().getString(VIEW_MODE);
        if (viewMode.equals("acquire")) {
            actionButton.setText("Добавить в корзину");
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handelAddToBasketClick(v);
                }
            });
        } else if (viewMode.equals("view")) {
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
        ((TextView) findViewById(R.id.textProductCost)).setText("Цена: " + String.valueOf(product.getCost()) + " руб.");

        TableLayout table = (TableLayout) findViewById(R.id.tableProductParams);
        Map<String, String> details = product.getDetails();
        // а тут разбираем этот самый массив и складываем в table-view:
        for (String detailName : details.keySet()) {
            String detailValue = details.get(detailName);

            TableRow row = new TableRow(this);

            TextView param = new TextView(this);
            TextView value = new TextView(this);

            param.setPadding(0, 0, 10, 0);
            param.setGravity(Gravity.RIGHT);
            param.setText(Html.fromHtml("<b>" + detailName + ":</b>"));
            value.setText(detailValue);

            row.addView(param);
            row.addView(value);
            table.addView(row);
        }

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(product.getImage());
    }

    public void handelAddToBasketClick(View view) {
        ApplicationContext.INSTANCE.addToBasket(product);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void handelBackToBasketClick(View view) {
        onBackPressed();
    }
}
