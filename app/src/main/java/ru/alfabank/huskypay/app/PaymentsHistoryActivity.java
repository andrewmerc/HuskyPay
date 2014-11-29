package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.os.Bundle;
import com.google.common.collect.Multimap;


public class PaymentsHistoryActivity extends Activity {

    public Multimap<Integer, ProductInfo> paymentsHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_history);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new GetPaymentsHistoryTask(ApplicationContext.INSTANCE.getCardInfo()){
            @Override
            protected void onPostExecute(Multimap<Integer, ProductInfo> result) {
                paymentsHistory = result;
            }
        }.getPaymentsHistory();

    }
}
