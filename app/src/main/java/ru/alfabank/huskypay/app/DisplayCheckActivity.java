package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.os.Bundle;


public class DisplayCheckActivity extends Activity {

    public static final String PAYMENT_INFO_EXTRAS = "payment_info_extras";

    private PaymentInfo paymentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_check);
    }

    @Override
    protected void onResume() {
        super.onResume();

        paymentInfo = (PaymentInfo) getIntent().getExtras().getSerializable(PAYMENT_INFO_EXTRAS);

    }
}
