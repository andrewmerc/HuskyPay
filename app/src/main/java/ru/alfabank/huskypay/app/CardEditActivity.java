package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.common.base.Strings;

public class CardEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);
        setTitle(R.string.card_edit_title);
    }

    public void onClickCardAddButton(View v) {
        TextView cardNumber = (TextView) findViewById(R.id.editCardNumber);
        TextView cardValidThru = (TextView) findViewById(R.id.editValidThru);
        TextView cardCVV2 = (TextView) findViewById(R.id.editCVV2);

        String number = cardNumber.getText().toString();
        if (Strings.isNullOrEmpty(number)) {
            return;
        }

        CardInfo cardInfo = new CardInfo(number,
                cardValidThru.getText().toString(), cardCVV2.getText().toString());


        ApplicationContext.INSTANCE.setCardInfo(cardInfo);

        Intent intent = new Intent(getApplicationContext(), CardEditResultActivity.class);
        startActivity(intent);
    }
}
