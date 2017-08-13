package com.ammolitestudio.pemesanan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Global Variable
    int quantity = 0;
    int price = 0;
    String var_Price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button show = (Button) findViewById(R.id.btn_show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var_Price = Integer.toString(price);

                // Intent pindah activity
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                intent.putExtra("parse_harga",var_Price);
                startActivity(intent);

            }
        });

    }

    public void decrement (View view){
        quantity = quantity - 1;
        display(quantity);
    }

   public  void increment(View view){
       quantity = quantity + 1;
       display(quantity);

   }

   public void display(int quantity){
       TextView tvQuantity = (TextView) findViewById(R.id.tvQuantity);
       tvQuantity.setText("" +quantity);
   }

   public int calculatePrice(boolean addwhipped , boolean addChoco){
       int baseprice = 3500;

       if (addwhipped){
            baseprice = baseprice + 500;
       }

       if (addChoco){
           baseprice = baseprice + 500;
       }

       return quantity *baseprice;
   }

   public void submitOrder(View view){
        String topping = "";

       //inisialisasi checkbox
       CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.cbWCream);
       CheckBox Choco = (CheckBox) findViewById(R.id.cbChoco);

       // bernilai benar jika dicentang (isChecked)
       boolean hasWhippedCream = whippedCreamCB.isChecked();
       boolean hasChoco = Choco.isChecked();

       if (hasWhippedCream == true && hasChoco == true ){
           topping = "Whipped Cream & Chocolate";
       } else if (hasWhippedCream == true){
           topping = "Whipped Cream";
       } else if (hasChoco == true){
           topping = "Chocolate";
       } else {
           topping = "tanpa Topping";
       }

       //inisialisasi edittext nama
       EditText name_editText = (EditText) findViewById(R.id.name_editText);

       // mengambil nilai inputan dari layout
       String name = name_editText.getText().toString();

       price = calculatePrice(hasWhippedCream, hasChoco);

       String priceMessage = createOrderSummary(price,name,topping);

       displayPrice(price,priceMessage);

       Intent intent = new Intent(Intent.ACTION_SENDTO);
       // alamat email tujuan
       intent.setData(Uri.parse("mailto: ZaenProduction@gmail.com"));
       // subject email
       intent.putExtra(Intent.EXTRA_SUBJECT, " Rekap Order " +name );
       // isi email
       intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

       if (intent.resolveActivity(getPackageManager()) != null ){
           startActivity(intent);
       }


   }

   public void displayPrice(int price , String priceMessage ){
       TextView price_text_view = (TextView) findViewById(R.id.price_text_view);
       price_text_view.setText("Rp " +price);

       TextView textSummary = (TextView) findViewById(R.id.sumary_text_view);
       textSummary.setText(priceMessage);
   }

   public String createOrderSummary(int price, String name, String topping){
       String priceMessage = "Nama : " + name +
               "\nQuantity : " +quantity +
               "\nTopping : " + topping +
               "\nTotal Harga : " + price ;

       return priceMessage;
   }
}


































