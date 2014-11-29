package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Map;


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
        String date = new SimpleDateFormat("dd.MM.yyyy").format(paymentInfo.getPaymentDate());
        String time = new SimpleDateFormat("HH:mm").format(paymentInfo.getPaymentDate());

        String list = "";
        Map<String, BigDecimal> details = paymentInfo.getBoughtProducts();
        for (String detailName : details.keySet()) {
            BigDecimal detailValue = details.get(detailName);
            list += String.format(
                    "<b>%s</b> 1 шт. %s руб.<br>",
                    detailName,
                    detailValue.toString()
            );
        }

        String htmlTemplate = String.format(
                "<b>Дата:</b> %s г.<br>\n<b>Время:</b> %s<br>\n<b>Продавец:</b> %s<br>\n<b>Сумма чека:</b><br>\n<br>\n———————<br>\n%s——————<br>\n<b>ИТОГ:</b> %s руб.<br>\n<b>В т.ч. НДС:</b> %s руб.<br>",
                date,
                time,
                paymentInfo.getPartnerName(),
                list,
                paymentInfo.getPaymentSum().toString(),
                paymentInfo.getPaymentVat().toString()
        );

        TextView textOrderInfo = (TextView) findViewById(R.id.textOrderInfo);
        textOrderInfo.setText(Html.fromHtml(htmlTemplate));

        ImageView img = (ImageView) findViewById(R.id.imageOrderQRCode);
        img.setImageBitmap(paymentInfo.getCheckQRCode());
    }

    public void handleReturnButton(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
