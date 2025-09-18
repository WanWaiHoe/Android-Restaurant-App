package com.example.fbloginwanwaihoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class Check_Out extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String CHANNEL_ID="Channel1";

    Locale locale;
    double totalPrice;
    double extra = 0;
    double discount = 0;
    double change = 0;
    double pay = totalPrice;

    CheckBox checkBoxDiscount;

    String[] area = {"Kuala Lumpur", "Johor", "Selangor", "Peneng", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__out);

        checkBoxDiscount = findViewById(R.id.checkBoxDiscount);

        Bundle b = getIntent().getExtras();
        totalPrice = b.getDouble("KEY");

        TextView Total = findViewById(R.id.Total);
        Total.setText(NumberFormat.getCurrencyInstance().format(totalPrice));

        setupLocale();

        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,area);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);

        createNotificationChannel();
    }

    public void setupLocale() {
        locale = new Locale("en", "MY");
        Locale.setDefault(locale);
    }

    public void calculate(){
        if(checkBoxDiscount.isChecked()){
            pay = totalPrice*0.85;
        }
    }

    public void onClickCheckBox(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBoxDiscount:
                if(checked) {
                    calculate();
                    TextView Discount = findViewById(R.id.Total);
                    Discount.setText(NumberFormat.getCurrencyInstance().format(pay));
                } else {
                    pay = totalPrice;
                    TextView Discount = findViewById(R.id.Total);
                    Discount.setText(NumberFormat.getCurrencyInstance().format(pay));
                }
                break;
        }
    }

    public void onRadioClick(View view){
        switch(view.getId()){
            case R.id.radioButtonCarryOut:
                pay = totalPrice;
                calculate();
                TextView CarryOut = findViewById(R.id.Total);
                CarryOut.setText(NumberFormat.getCurrencyInstance().format(pay));
                break;
            case R.id.radioButtonDelivery:
                calculate();
                pay = pay + 3;
                TextView Delivery = findViewById(R.id.Total);
                Delivery.setText(NumberFormat.getCurrencyInstance().format(pay));
                break;
        }
    }

    public void calculateChange(View view){
        EditText editText = findViewById(R.id.editTextPayment);
        double payAmount = Double.parseDouble(editText.getText().toString());

        change = payAmount - pay;

        TextView textViewChange = findViewById(R.id.textViewChange);
        textViewChange.setText(NumberFormat.getCurrencyInstance().format(change));

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "Channel1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Purchase Successful")
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.ic_launcher_background))
                .setContentText("You have purchase a item");

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);
        mNotificationManager.notify(001, mBuilder.build());
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID, name, importance);
            channel1.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),area[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
