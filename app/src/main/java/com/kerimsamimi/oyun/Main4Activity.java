package com.kerimsamimi.oyun;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main4Activity extends AppCompatActivity {


    Random r = new Random();


    TextView scoreText;
    TextView sureText;
    TextView canText;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;

    ImageView imageView20;

    int score,a,b,canSkor;
    long timeWhenStopped = 0;
    private long lastPause;
    Chronometer crono;

    CountDownTimer countDownTimer;
    ImageButton pausebutton;
    ImageButton resumebutton;
    ImageButton playAgainbutton;
    ImageButton resumebutton2;

    long s1=22000;
    ImageView [] imageDizi;
    ImageView [] imageDizi2;

    Handler handler;
    Runnable runnable;

    Handler handler2;
    Runnable runnable2;

    long timerValue;

    static SQLiteDatabase database2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);

        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        imageView13=(ImageView)findViewById(R.id.imageView13);
        imageView14=(ImageView)findViewById(R.id.imageView14);
        imageView15=(ImageView)findViewById(R.id.imageView15);
        imageView16=(ImageView)findViewById(R.id.imageView16);
        imageView17=(ImageView)findViewById(R.id.imageView17);
        imageView18=(ImageView)findViewById(R.id.imageView18);
        imageView19=(ImageView)findViewById(R.id.imageView19);
        imageView20=(ImageView)findViewById(R.id.imageView20);


        imageView20.setVisibility(View.INVISIBLE);
        playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
        resumebutton2=(ImageButton)findViewById(R.id.resumebutton2);
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        imageDizi2=new ImageView[500];
        imageDizi= new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19};
        imageDizi2= new ImageView[]{imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19};
      imageSakla();
      imageSakla2();

        canSkor=3;
        score=0;
        startChronometer();


    }

    public void startChronometer() {
        //((Chronometer) findViewById(R.id.chronometer1)).start();
        crono=(Chronometer) findViewById(R.id.chronometer1);

        crono.setBase(SystemClock.elapsedRealtime());

        crono.start();

    }
    public void stopChronometer() {
       // ((Chronometer) findViewById(R.id.chronometer1)).stop();
        lastPause = SystemClock.elapsedRealtime();

        crono.stop();
    }

    public void resumeChronometer(){
        crono.setBase(crono.getBase() + SystemClock.elapsedRealtime() - lastPause);

        crono.start();
    }

    public void skorArtir(View view){
        scoreText=(TextView) findViewById(R.id.textScore);
        score++;
        scoreText.setText("Puan: "+ score);
    }
    public void canAzalt(View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        canText=(TextView)findViewById(R.id.can);
        canSkor--;
        canText.setText("Kalan Can: "+ canSkor);
        if (canSkor==0)
        {
            stopChronometer();
            handler.removeCallbacks(runnable);
            handler2.removeCallbacks(runnable2);
            imageFalse();
            playAgainbutton.setVisibility(View.VISIBLE);
            pausebutton.setVisibility(View.INVISIBLE);
            imageView20.setVisibility(View.VISIBLE);


            String skorName2= scoreText.getText().toString();
            // Long skorName= Long.valueOf((scoreText.getText()).toString());
            try {
                database2=openOrCreateDatabase("Scores2",MODE_PRIVATE,null);
                database2.execSQL("CREATE TABLE IF NOT EXISTS Scores(score INTEGER)");

                String sqlString= "INSERT INTO Scores2 (score2) VALUES (?)";
                SQLiteStatement statement=database2.compileStatement(sqlString);
                statement.bindString(1,skorName2);
                statement.execute();

            }catch (Exception e){

            }
        }
    }

    public void imageSakla(){

        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageDizi){
                    image.setVisibility(View.INVISIBLE);
                }
                Random r = new Random();
                a= r.nextInt(8-0);
                imageDizi[a].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }

    public void imageSakla2(){

        handler2 = new Handler();
        runnable2= new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageDizi2){
                    image.setVisibility(View.INVISIBLE);
                }
                //Random r = new Random();
                b= r.nextInt(62-0);
                if (b<=8) {
                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    imageView4.setVisibility(View.INVISIBLE);
                    imageView5.setVisibility(View.INVISIBLE);
                    imageView6.setVisibility(View.INVISIBLE);
                    imageView7.setVisibility(View.INVISIBLE);
                    imageView8.setVisibility(View.INVISIBLE);
                    imageView9.setVisibility(View.INVISIBLE);
                    imageDizi2[b].setVisibility(View.VISIBLE);
                }
                handler2.postDelayed(this,500);
            }
        };
        handler2.post(runnable2);
    }

    public void goHome(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void pause(View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        resumebutton=(ImageButton)findViewById(R.id.resumeButton);
        handler.removeCallbacks(runnable);
        handler2.removeCallbacks(runnable2);
        //countDownTimer.cancel();
        stopChronometer();
        imageFalse();
        pausebutton.setVisibility(View.INVISIBLE);
        resumebutton.setVisibility(View.VISIBLE);
        playAgainbutton.setVisibility(View.VISIBLE);
        resumebutton2.setVisibility(View.VISIBLE);
        imageView20.setVisibility(View.VISIBLE);
    }

    public void resume (View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        resumebutton=(ImageButton)findViewById(R.id.resumeButton);
        pausebutton.setVisibility(View.VISIBLE);
        resumebutton.setVisibility(View.INVISIBLE);
        handler.post(runnable);
        handler2.post(runnable2);
        imageTrue();
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        //timer();
        resumeChronometer();
        imageView20.setVisibility(View.INVISIBLE);
    }
    public void resume2(View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        resumebutton=(ImageButton)findViewById(R.id.resumeButton);
        pausebutton.setVisibility(View.VISIBLE);
        resumebutton.setVisibility(View.INVISIBLE);
        handler.post(runnable);
        handler2.post(runnable2);
        imageTrue();
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        //timer();
        resumeChronometer();
        imageView20.setVisibility(View.INVISIBLE);
    }

    public void imageFalse(){
        imageView1.setEnabled(false);
        imageView2.setEnabled(false);
        imageView3.setEnabled(false);
        imageView4.setEnabled(false);
        imageView5.setEnabled(false);
        imageView6.setEnabled(false);
        imageView7.setEnabled(false);
        imageView8.setEnabled(false);
        imageView9.setEnabled(false);
        imageView11.setEnabled(false);
        imageView12.setEnabled(false);
        imageView13.setEnabled(false);
        imageView14.setEnabled(false);
        imageView15.setEnabled(false);
        imageView16.setEnabled(false);
        imageView17.setEnabled(false);
        imageView18.setEnabled(false);
        imageView19.setEnabled(false);
    }
    public void imageTrue(){
        imageView1.setEnabled(true);
        imageView2.setEnabled(true);
        imageView3.setEnabled(true);
        imageView4.setEnabled(true);
        imageView5.setEnabled(true);
        imageView6.setEnabled(true);
        imageView7.setEnabled(true);
        imageView8.setEnabled(true);
        imageView9.setEnabled(true);
        imageView11.setEnabled(true);
        imageView12.setEnabled(true);
        imageView13.setEnabled(true);
        imageView14.setEnabled(true);
        imageView15.setEnabled(true);
        imageView16.setEnabled(true);
        imageView17.setEnabled(true);
        imageView18.setEnabled(true);
        imageView19.setEnabled(true);
    }
    public void timer(){
        countDownTimer =new CountDownTimer(s1,1000) {
            public void onTick(long millisUntilFinished){

                sureText= (TextView) findViewById(R.id.textSure);
                sureText.setText("Sure: "+ millisUntilFinished/1000);
                s1=millisUntilFinished;
            }
            public void onFinish(){
                playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
                pausebutton=(ImageButton)findViewById(R.id.pauseButton);
                sureText= (TextView) findViewById(R.id.textSure);
                sureText.setText("Sure Bitti");
                handler.removeCallbacks(runnable);
                handler2.removeCallbacks(runnable2);
                imageFalse();
                playAgainbutton.setVisibility(View.VISIBLE);
                pausebutton.setVisibility(View.INVISIBLE);
            }
        }.start();
    }



    public void playAgain(View view){
        //  playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
        Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
        startActivity(intent);
        // scoreText= (TextView) findViewById(R.id.textScore);
        // score=0;
        //  scoreText.setText("Puan: "+ score);

        //  countDownTimer.start();
        //  handler.post(runnable);

        // playAgainbutton.setVisibility(View.INVISIBLE);
        // resumebutton2.setVisibility(View.INVISIBLE);
        // imageTrue();
        // resumebutton.setVisibility(View.INVISIBLE);
        // pausebutton.setVisibility(View.VISIBLE);
    }
}

