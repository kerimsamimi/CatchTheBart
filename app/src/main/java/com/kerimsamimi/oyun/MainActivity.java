package com.kerimsamimi.oyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView mod;
    TextView zorOyna;
    TextView ortaOyna;

    ImageView imageView10,imageView21;

    TextView mod2;
    TextView zorOyna2;
    TextView ortaOyna2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView10=(ImageView)findViewById(R.id.imageView10);
        imageView21=(ImageView)findViewById(R.id.imageView10);

    }

    public void modTikla(View view){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }

    public void mod2Tikla(View view){
        Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
        startActivity(intent);
    }

    public void button(View view){
        Intent intent=new Intent(getApplicationContext(),Main5Activity.class);
        startActivity(intent);
    }



}
