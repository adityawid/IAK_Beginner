package com.ammolitestudio.pemesanan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {
    TextView Harga;
    String get_Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button back = (Button)findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });

        Harga = (TextView) findViewById(R.id.tvTotalHarga);

        get_Price = getIntent().getExtras().getString("parse_harga");

        Harga.setText("" +get_Price);
    }
}












