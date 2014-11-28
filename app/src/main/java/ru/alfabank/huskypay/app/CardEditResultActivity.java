package ru.alfabank.huskypay.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CardEditResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit_result);
    }

    public void onClickReturnButton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
