package com.appbrewerylk.cookingtimecalculatorformicrowaveovens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    AdView mAdView;

    LinearLayout resultPannel;

EditText et_food_wattage,et_food_cooking_time,et_food_power;
EditText et_your_oven_wattage;
EditText et_oven_power_id_1,et_oven_power_id_2,et_oven_power_id_3,et_oven_power_id_4,et_oven_power_id_5;


TextView tv_your_result_power_percentage_1,tv_your_result_power_percentage_2,tv_your_result_power_percentage_3,tv_your_result_power_percentage_4,tv_your_result_power_percentage_5;

TextView tv_result_power_percentage_5,tv_result_power_percentage_4,tv_result_power_percentage_3,tv_result_power_percentage_2,tv_result_power_percentage_1;

Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /////Ad Mob
        //Testing app ID & Unit ID
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }

        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /////


        resultPannel = findViewById(R.id.result_pannel);

        et_food_wattage = findViewById(R.id.et_food_wattage);
        et_food_cooking_time = findViewById(R.id.et_food_cooking_time);
        et_food_power = findViewById(R.id.et_food_power);

        et_your_oven_wattage = findViewById(R.id.et_your_oven_wattage);

        et_oven_power_id_1 = findViewById(R.id.et_oven_power_id_1);
        et_oven_power_id_2 = findViewById(R.id.et_oven_power_id_2);
        et_oven_power_id_3 = findViewById(R.id.et_oven_power_id_3);
        et_oven_power_id_4 = findViewById(R.id.et_oven_power_id_4);
        et_oven_power_id_5 = findViewById(R.id.et_oven_power_id_5);

        tv_your_result_power_percentage_1 = findViewById(R.id.tv_your_result_power_percentage_1);
        tv_your_result_power_percentage_2 = findViewById(R.id.tv_your_result_power_percentage_2);
        tv_your_result_power_percentage_3 = findViewById(R.id.tv_your_result_power_percentage_3);
        tv_your_result_power_percentage_4 = findViewById(R.id.tv_your_result_power_percentage_4);
        tv_your_result_power_percentage_5 = findViewById(R.id.tv_your_result_power_percentage_5);

        tv_result_power_percentage_5 = findViewById(R.id.tv_result_power_percentage_5);
        tv_result_power_percentage_4 = findViewById(R.id.tv_result_power_percentage_4);
        tv_result_power_percentage_3 = findViewById(R.id.tv_result_power_percentage_3);
        tv_result_power_percentage_2 = findViewById(R.id.tv_result_power_percentage_2);
        tv_result_power_percentage_1 = findViewById(R.id.tv_result_power_percentage_1);

        calculate = findViewById(R.id.calculate_btn);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                calculate();


            }
        });


    }

    public static boolean hasText(EditText editText,String error_message) {



        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(error_message);
            return false;
        }

        return true;
    }

    private boolean Validate() {

        if (!hasText(et_food_wattage,"Required")) return false;
        if (!hasText(et_food_cooking_time,"Required")) return false;
        if (!hasText(et_food_power,"Required")) return false;

        if (!hasText(et_your_oven_wattage,"Required")) return false;

        if (!hasText(et_oven_power_id_1,"Required")) return false;
        if (!hasText(et_oven_power_id_2,"Required")) return false;
        if (!hasText(et_oven_power_id_3,"Required")) return false;
        if (!hasText(et_oven_power_id_4,"Required")) return false;
        if (!hasText(et_oven_power_id_5,"Required")) return false;



        return true;
    }



    public void calculate() {

        if (Validate()) {

            ////Animation
            Animation animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
            resultPannel.startAnimation(animation);
            resultPannel.setVisibility(View.VISIBLE);
            ////Animation

        Double et_food_wattaged = Double.parseDouble(String.valueOf(et_food_wattage.getText()));
        Double et_food_cooking_timed = Double.parseDouble(String.valueOf(et_food_cooking_time.getText()));

        String et_food_powerddd = et_food_power.getText().toString();
        String[] parts_food_powerd = et_food_powerddd.split("%");
        Double et_food_powerd = Double.parseDouble(parts_food_powerd[0]);
            //Double et_food_powerd = Double.parseDouble(String.valueOf(et_food_power.getText()));

        ////Heat Generated
        Double Heat_Generated = (et_food_wattaged * (et_food_powerd / 100)) * et_food_cooking_timed;
        ////Heat Generated

            ////Your own oven wattage
        Integer et_your_oven_wattaged = Integer.parseInt(String.valueOf(et_your_oven_wattage.getText()));


        String et_oven_power_id_1dd = et_oven_power_id_1.getText().toString();
        String[] parts_1 = et_oven_power_id_1dd.split("%");
        Double et_oven_power_id_1d = Double.parseDouble(parts_1[0]);

        String et_oven_power_id_2dd = et_oven_power_id_2.getText().toString();
        String[] parts_2 = et_oven_power_id_2dd.split("%");
        Double et_oven_power_id_2d = Double.parseDouble(parts_2[0]);

        String et_oven_power_id_3dd = et_oven_power_id_3.getText().toString();
        String[] parts_3 = et_oven_power_id_3dd.split("%");
        Double et_oven_power_id_3d = Double.parseDouble(parts_3[0]);

        String et_oven_power_id_4dd = et_oven_power_id_4.getText().toString();
        String[] parts_4 = et_oven_power_id_4dd.split("%");
        Double et_oven_power_id_4d = Double.parseDouble(parts_4[0]);

        String et_oven_power_id_5dd = et_oven_power_id_5.getText().toString();
        String[] parts_5 = et_oven_power_id_5dd.split("%");
        Double et_oven_power_id_5d = Double.parseDouble(parts_5[0]);


        tv_your_result_power_percentage_1.setText(et_oven_power_id_1d.intValue() + "%");
        tv_your_result_power_percentage_2.setText(et_oven_power_id_2d.intValue() + "%");
        tv_your_result_power_percentage_3.setText(et_oven_power_id_3d.intValue() + "%");
        tv_your_result_power_percentage_4.setText(et_oven_power_id_4d.intValue() + "%");
        tv_your_result_power_percentage_5.setText(et_oven_power_id_5d.intValue() + "%");


            ///////For et_oven_power_id_1 ///////
            Double timeToCookFoodInMinutes_1 = Heat_Generated / ((et_oven_power_id_1d / 100) * et_your_oven_wattaged);
            Integer wholeMinute_1 = timeToCookFoodInMinutes_1.intValue();

            Double secondsInMinuts_1 = timeToCookFoodInMinutes_1 - wholeMinute_1;
            Double secondsInSeconds_1 = secondsInMinuts_1 * 60;
            Integer wholeSeconds_1 = secondsInSeconds_1.intValue();

            tv_result_power_percentage_1.setText(wholeMinute_1.toString()+":"+wholeSeconds_1.toString());
            ////////

            ///////For et_oven_power_id_2 ///////
            Double timeToCookFoodInMinutes_2 = Heat_Generated / (((et_oven_power_id_2d / 100) * et_your_oven_wattaged));
            Integer wholeMinute_2 = timeToCookFoodInMinutes_2.intValue();

            Double secondsInMinuts_2 = timeToCookFoodInMinutes_2 - wholeMinute_2;
            Double secondsInSeconds_2 = secondsInMinuts_2 * 60;
            Integer wholeSeconds_2 = secondsInSeconds_2.intValue();

            tv_result_power_percentage_2.setText(wholeMinute_2.toString()+":"+wholeSeconds_2.toString());
            ////////


            ///////For et_oven_power_id_3 ///////
            Double timeToCookFoodInMinutes_3 = Heat_Generated / ((et_oven_power_id_3d / 100) * et_your_oven_wattaged);
            Integer wholeMinute_3 = timeToCookFoodInMinutes_3.intValue();

            Double secondsInMinuts_3 = timeToCookFoodInMinutes_3 - wholeMinute_3;
            Double secondsInSeconds_3 = secondsInMinuts_3 * 60;
            Integer wholeSeconds_3 = secondsInSeconds_3.intValue();

            tv_result_power_percentage_3.setText(wholeMinute_3.toString()+":"+wholeSeconds_3.toString());
            ////////

            ///////For et_oven_power_id_4 ///////
            Double timeToCookFoodInMinutes_4 = Heat_Generated / ((et_oven_power_id_4d / 100) * et_your_oven_wattaged);
            Integer wholeMinute_4 = timeToCookFoodInMinutes_4.intValue();

            Double secondsInMinuts_4 = timeToCookFoodInMinutes_4 - wholeMinute_4;
            Double secondsInSeconds_4 = secondsInMinuts_4 * 60;
            Integer wholeSeconds_4 = secondsInSeconds_4.intValue();

            tv_result_power_percentage_4.setText(wholeMinute_4.toString()+":"+wholeSeconds_4.toString());
            ////////

            ///////For et_oven_power_id_5 ///////
            Double timeToCookFoodInMinutes_5 = Heat_Generated / ((et_oven_power_id_5d / 100) * et_your_oven_wattaged);
            Integer wholeMinute_5 = timeToCookFoodInMinutes_5.intValue();

            Double secondsInMinuts_5 = timeToCookFoodInMinutes_5 - wholeMinute_5;
            Double secondsInSeconds_5 = secondsInMinuts_5 * 60;
            Integer wholeSeconds_5 = secondsInSeconds_5.intValue();

            tv_result_power_percentage_5.setText(wholeMinute_5.toString()+":"+wholeSeconds_5.toString());
            ////////


    }


    }



}
