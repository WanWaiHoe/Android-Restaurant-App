package com.example.fbloginwanwaihoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    int quantityCoke = 0;
    int quantitySevenUP = 0;
    int quantityPepsi = 0;
    int quantityMilo = 0;
    int quantityOrangejuice = 0;

    double priceCoke = 3.00;
    double priceSevenUP = 3.00;
    double pricePepsi = 3.00;
    double priceMilo = 3.00;
    double priceOrangeJuice = 5.00;
    double totalPrice = 0;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle b = getIntent().getExtras();
        totalPrice = b.getDouble("KEY");

        TextView Total = findViewById(R.id.textViewTotal);
        Total.setText(NumberFormat.getCurrencyInstance().format(totalPrice));

        setupLocale();
    }

    public void setupLocale() {
        locale = new Locale("en", "MY");
        Locale.setDefault(locale);
    }

    public void calculateTotalPrice(){
        TextView Total = findViewById(R.id.textViewTotal);
        Total.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    public void increaseCoke(View view){
        quantityCoke++;
        totalPrice += priceCoke;
        TextView QuantityCoke = findViewById(R.id.quantityCoke);
        QuantityCoke.setText(String.valueOf(quantityCoke));
        calculateTotalPrice();
    }

    public void decreaseCoke(View view){
        if(quantityCoke >= 1){
            quantityCoke--;
            totalPrice -= priceCoke;
        }
        TextView QuantityCoke = findViewById(R.id.quantityCoke);
        QuantityCoke.setText(String.valueOf(quantityCoke));
        calculateTotalPrice();
    }

    public void increaseSevenUP(View view){
        quantitySevenUP++;
        totalPrice += priceSevenUP;
        TextView QuantitySevenUP = findViewById(R.id.quantitySevenUP);
        QuantitySevenUP.setText(String.valueOf(quantitySevenUP));
        calculateTotalPrice();
    }

    public void decreaseSevenUP(View view){
        if(quantitySevenUP >= 1){
            quantitySevenUP--;
            totalPrice -= priceSevenUP;
        }
        TextView QuantitySevenUP = findViewById(R.id.quantitySevenUP);
        QuantitySevenUP.setText(String.valueOf(quantitySevenUP));
        calculateTotalPrice();
    }

    public void increasePepsi(View view){
        quantityPepsi++;
        totalPrice += pricePepsi;
        TextView QuantityPepsi = findViewById(R.id.quantityPepsi);
        QuantityPepsi.setText(String.valueOf(quantityPepsi));
        calculateTotalPrice();
    }

    public void decreasePepsi(View view){
        if(quantityPepsi >= 1){
            quantityPepsi--;
            totalPrice -= pricePepsi;
        }
        TextView QuantityPepsi = findViewById(R.id.quantityPepsi);
        QuantityPepsi.setText(String.valueOf(quantityPepsi));
        calculateTotalPrice();
    }

    public void increaseMilo(View view){
        quantityMilo++;
        totalPrice += priceMilo;
        TextView QuantityMilo = findViewById(R.id.quantityMilo);
        QuantityMilo.setText(String.valueOf(quantityMilo));
        calculateTotalPrice();
    }

    public void decreaseMilo(View view){
        if(quantityMilo >= 1){
            quantityMilo--;
            totalPrice -= priceMilo;
        }
        TextView QuantityMilo = findViewById(R.id.quantityMilo);
        QuantityMilo.setText(String.valueOf(quantityMilo));
        calculateTotalPrice();
    }

    public void increaseOrangeJuice(View view){
        quantityOrangejuice++;
        totalPrice += priceOrangeJuice;
        TextView QuantityOrangeJuice = findViewById(R.id.quantityOrarngeJuice);
        QuantityOrangeJuice.setText(String.valueOf(quantityOrangejuice));
        calculateTotalPrice();
    }

    public void decreaseOrangeJuice(View view){
        if(quantityOrangejuice >= 1){
            quantityOrangejuice--;
            totalPrice -= priceOrangeJuice;
        }
        TextView QuantityOrangeJuice = findViewById(R.id.quantityOrarngeJuice);
        QuantityOrangeJuice.setText(String.valueOf(quantityOrangejuice));
        calculateTotalPrice();
    }

    public void sendMessage(View view){
        Intent intent1 = new Intent(this,Check_Out.class);
        Bundle b = new Bundle();
        b.putDouble("KEY", totalPrice);
        intent1.putExtras(b);
        startActivity(intent1);
    }
}
