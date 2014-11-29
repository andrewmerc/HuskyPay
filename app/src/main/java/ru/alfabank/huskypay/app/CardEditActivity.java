package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CardEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);
    }

    public void onClickCardAddButton(View v) {
        TextView cardNumber = (TextView) findViewById(R.id.editCardNumber);
        TextView cardValidThru = (TextView) findViewById(R.id.editValidThru);
        TextView cardCVV2 = (TextView) findViewById(R.id.editCVV2);

        CardInfo cardInfo = new CardInfo(cardNumber.getText().toString(),
                cardValidThru.getText().toString(), cardCVV2.getText().toString());

        ApplicationContext.INSTANCE.setCardInfo(cardInfo);

        Intent intent = new Intent(getApplicationContext(), CardEditResultActivity.class);
        startActivity(intent);
    }
}
