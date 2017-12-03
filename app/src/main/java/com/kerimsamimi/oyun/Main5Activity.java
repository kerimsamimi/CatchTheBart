package com.kerimsamimi.oyun;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    ListView listView;
    ListView listView2;
    Button button4;
    Button button5;



    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);

        button5.setTextColor(0xff808080);



        //this.deleteDatabase("Scores");
        listView=(ListView)findViewById(R.id.listView);
        listView2=(ListView)findViewById(R.id.listView2);

        textView=(TextView)findViewById(R.id.textView);

        ArrayList<String> skorName=new ArrayList<String>();

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,skorName);
        listView.setAdapter(arrayAdapter);

        try {
            Main2Activity.database=this.openOrCreateDatabase("Scores",MODE_PRIVATE,null);
            Main2Activity.database.execSQL("CREATE TABLE IF NOT EXISTS Scores(score INTEGER)");


            Cursor cursor = Main2Activity.database.rawQuery("SELECT * FROM Scores Order By length(score)desc,score desc limit 10",null);
           // Cursor cursor = Main2Activity.database.rawQuery("SELECT * FROM Scores Order By cast(score as unsigned)",null);

            //String orderBy ="SELECT * FROM Scores Order By score desc limit 10";
          //  Cursor cursor=Main2Activity.database.rawQuery(orderBy, null);


            int scoreIx=cursor.getColumnIndex("score");
            cursor.moveToFirst();

            while (cursor!=null){
                skorName.add(cursor.getString(scoreIx));
                cursor.moveToNext();
                arrayAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        ArrayList<String> skorName2=new ArrayList<String>();

        ArrayAdapter arrayAdapter2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,skorName2);
        listView2.setAdapter(arrayAdapter2);

        try {
            Main4Activity.database2=this.openOrCreateDatabase("Scores2",MODE_PRIVATE,null);
            Main4Activity.database2.execSQL("CREATE TABLE IF NOT EXISTS Scores2(score2 INTEGER)");


            Cursor cursor2 = Main4Activity.database2.rawQuery("SELECT * FROM Scores2 Order By length(score2)desc,score2 desc limit 10",null);
            // Cursor cursor = Main2Activity.database.rawQuery("SELECT * FROM Scores Order By cast(score as unsigned)",null);

            //String orderBy ="SELECT * FROM Scores Order By score desc limit 10";
            //  Cursor cursor=Main2Activity.database.rawQuery(orderBy, null);


            int scoreIx2=cursor2.getColumnIndex("score2");
            cursor2.moveToFirst();

            while (cursor2!=null){
                skorName2.add(cursor2.getString(scoreIx2));
                cursor2.moveToNext();
                arrayAdapter2.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void mayinliClick(View view){
        button4=(Button)findViewById(R.id.button4);
        listView.setVisibility(View.INVISIBLE);
        listView2.setVisibility(View.VISIBLE);
        button4.setTextColor(0xff000000);
        button5.setTextColor(0xff808080);
    }
    public void mayinsizClick(View view){
        listView2.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        button4.setTextColor(0xff808080);
        button5.setTextColor(0xff000000);
    }

    public void goHome(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
