package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ProductsBasketActivity extends Activity {

    /**
     * Items entered by the user is stored in this ArrayList variable
     */
    ArrayList<String> listItems = new ArrayList<String>();

    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    ArrayAdapter<String> adapter;
    List<ProductInfo> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_basket);

        productList = ApplicationContext.INSTANCE.getProductsInBasket();
        for (ProductInfo aList : productList) {
            listItems.add(String.format("%s, цена: %d руб.", aList.getName(), aList.getCost()));
        }

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        ListView productsList = (ListView) findViewById(R.id.listProducts);
        productsList.setAdapter(adapter);

        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);
                intent.putExtra(ProductInfoActivity.PRODUCT_EXTRAS, productList.get((int)id));
                intent.putExtra(ProductInfoActivity.VIEW_MODE, "view");
                startActivity(intent);
            }
        });

//        adapter.notifyDataSetChanged();
    }

    public void handlePayClick(View view) {

        new SendPayTask(ApplicationContext.INSTANCE.getCardInfo(), ApplicationContext.INSTANCE.getProductsInBasket()){

            @Override
            protected void onPostExecute(PaymentInfo paymentInfo) {
                onPaymentCompleted(paymentInfo);
            }

        }.send();

    }

    private void onPaymentCompleted(PaymentInfo paymentInfo){

        ApplicationContext.INSTANCE.clearBasket();

        Intent intent = new Intent(getApplicationContext(), DisplayCheckActivity.class);
        intent.putExtra(DisplayCheckActivity.PAYMENT_INFO_EXTRAS, paymentInfo);

        startActivity(intent);

    }

}
