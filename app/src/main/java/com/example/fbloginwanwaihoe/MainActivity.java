package com.example.fbloginwanwaihoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    Food food = new Food();
    double totalAddOn = 0;
    int quantityBurger = 0;
    int quantityIceCream = 0;
    int quantityPizza = 0;
    int quantityPotato = 0;
    int quantityFries = 0;

    double priceBurger = 10.00;
    double priceIceCream = 8.00;
    double pricePizza = 20.00;
    double pricePotato = 5.00;
    double priceFries = 5.00;
    double totalPrice = 0;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        setupLocale();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Login:
                startActivity(new Intent(this,LoginPage.class));
                return true;
            case R.id.About:
                startActivity(new Intent(this,About.class));
                return true;
            case R.id.Logout:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupLocale() {
        locale = new Locale("en", "MY");
        Locale.setDefault(locale);
    }

    public void calculateTotalPrice(){
        TextView Total = findViewById(R.id.Total);
        Total.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    public void increaseBurger(View view){
        quantityBurger++;
        totalPrice += priceBurger;
        food.setQuantity(quantityBurger);
        TextView QuantityBurger = findViewById(R.id.quantityBurger);
        QuantityBurger.setText(String.valueOf(quantityBurger));
        calculateTotalPrice();
    }

    public void decreaseBurger(View view){
        if(quantityBurger >= 1){
            quantityBurger--;
            totalPrice -= priceBurger;
        }
        TextView QuantityBurger = findViewById(R.id.quantityBurger);
        QuantityBurger.setText(String.valueOf(quantityBurger));
        calculateTotalPrice();
    }

    public void increaseIceCream(View view){
        totalPrice += priceIceCream;
        quantityIceCream++;
        food.setQuantity(quantityIceCream);
        TextView QuantityIceCream = findViewById(R.id.quantityIceCream);
        QuantityIceCream.setText(String.valueOf(quantityIceCream));
        calculateTotalPrice();
    }

    public void decreaseIceCream(View view){
        if(quantityIceCream >= 1){
            quantityIceCream--;
            totalPrice -= priceIceCream;
        }
        TextView QuantityIceCream = findViewById(R.id.quantityIceCream);
        QuantityIceCream.setText(String.valueOf(quantityIceCream));
        calculateTotalPrice();
    }

    public void increasePizza(View view){
        totalPrice += pricePizza;
        quantityPizza++;
        food.setQuantity(quantityPizza);
        TextView QuantityPizza = findViewById(R.id.quantityPizza);
        QuantityPizza.setText(String.valueOf(quantityPizza));
        calculateTotalPrice();
    }

    public void decreasePizza(View view){
        if(quantityPizza >= 1){
            quantityPizza--;
            totalPrice -= pricePizza;
        }
        TextView QuantityPizza = findViewById(R.id.quantityPizza);
        QuantityPizza.setText(String.valueOf(quantityPizza));
        calculateTotalPrice();
    }

    public void increasePotato(View view){
        totalPrice += pricePotato;
        quantityPotato++;
        food.setQuantity(quantityPotato);
        TextView QuantityPotato = findViewById(R.id.quantityPotato);
        QuantityPotato.setText(String.valueOf(quantityPotato));
        calculateTotalPrice();
    }

    public void decreasePotato(View view){
        if(quantityPotato >= 1){
            quantityPotato--;
            totalPrice -= pricePotato;
        }
        TextView QuantityPotato = findViewById(R.id.quantityPotato);
        QuantityPotato.setText(String.valueOf(quantityPotato));
        calculateTotalPrice();
    }

    public void increaseFries(View view){
        totalPrice += priceFries;
        quantityFries++;
        food.setQuantity(quantityFries);
        TextView QuantityFries = findViewById(R.id.quantityFries);
        QuantityFries.setText(String.valueOf(quantityFries));
        calculateTotalPrice();
    }

    public void decreaseFries(View view){
        if(quantityFries >= 1){
            quantityFries--;
            totalPrice -= priceFries;
        }
        TextView QuantityFries = findViewById(R.id.quantityFries);
        QuantityFries.setText(String.valueOf(quantityFries));
        calculateTotalPrice();
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle b = new Bundle();
        b.putDouble("KEY", totalPrice);
        intent.putExtras(b);
        startActivity(intent);
    }
}