package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.Collection;


public class PaymentsHistoryActivity extends Activity {

    public Multimap<Integer, ProductInfo> paymentsHistory;

    /**
     * Items entered by the user is stored in this ArrayList variable
     */
    private ArrayList<String> listItems = new ArrayList<String>();

    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    private ArrayAdapter<String> adapter;
    private Collection<ProductInfo> productList;
    private ProductInfo productInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_history);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final ProgressBar spinner = (ProgressBar) findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);

        new GetPaymentsHistoryTask(ApplicationContext.INSTANCE.getCardInfo()) {
            @Override
            protected void onPostExecute(Multimap<Integer, ProductInfo> result) {
                paymentsHistory = result;
                spinner.setVisibility(View.GONE);
                showHistory();
            }
        }.getPaymentsHistory();
    }

    public void showHistory() {
        Multiset<Integer> list = paymentsHistory.keys();
        for (Integer integer : list) {
            productList = paymentsHistory.get(integer);

            int summ = 0;
            String partner = "";
            for (ProductInfo info : productList) {
                summ += info.getCost();
                if (partner.equals("")) {
                    partner = info.getPartnerName();
                }
            }

            listItems.add(String.format("Покупка в \"%s\" на общую стоимость %d руб.", partner, summ));
        }

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        ListView operationsList = (ListView) findViewById(R.id.listOperations);
        operationsList.setAdapter(adapter);
    }

    public void handleReturnButton(View v) {
        onBackPressed();
    }
}
