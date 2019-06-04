package com.cookandroid.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    Button btn_can;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("강의실 예약하기");
        Button btn_reservation = (Button) findViewById(R.id.Btn_reservation);

        btn_reservation.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Reservation.class);
                startActivity(intent);
            }
        });
    }
}
