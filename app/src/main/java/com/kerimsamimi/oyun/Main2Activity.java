package com.kerimsamimi.oyun;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {



    TextView scoreText;
    TextView sureText;
    TextView cikti;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView20;

    int score,a;
    CountDownTimer countDownTimer;
    ImageButton pausebutton;
    ImageButton resumebutton;
    ImageButton playAgainbutton;
    ImageButton resumebutton2;

    long s1=15000;
    ImageView [] imageDizi;
    Handler handler;
    Runnable runnable;
    long timerValue;

    static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);
        imageView20=(ImageView)findViewById(R.id.imageView20);

        playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
        resumebutton2=(ImageButton)findViewById(R.id.resumebutton2);
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        imageView20.setVisibility(View.INVISIBLE);

        imageDizi= new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        imageSakla();
        score=0;
        timer();

    }

    public void skorArtir(View view){
        scoreText=(TextView) findViewById(R.id.textScore);
        score++;
        scoreText.setText("Puan: "+ score);
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

    public void pause(View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        resumebutton=(ImageButton)findViewById(R.id.resumeButton);
        handler.removeCallbacks(runnable);
        countDownTimer.cancel();
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
        imageTrue();
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        timer();
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
    }
    public void timer(){
        countDownTimer =new CountDownTimer(s1,1000) {
            public void onTick(long millisUntilFinished){

                sureText= (TextView) findViewById(R.id.textSure);
                sureText.setText("Sure: "+ millisUntilFinished/1000);
                s1=millisUntilFinished;
            }
            public void onFinish(){
                cikti=(TextView)findViewById(R.id.cikti);
                playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
                pausebutton=(ImageButton)findViewById(R.id.pauseButton);
                sureText= (TextView) findViewById(R.id.textSure);
                sureText.setText("Sure Bitti");
                handler.removeCallbacks(runnable);
                imageFalse();
                playAgainbutton.setVisibility(View.VISIBLE);
                pausebutton.setVisibility(View.INVISIBLE);
                imageView20.setVisibility(View.VISIBLE);


                String skorName= scoreText.getText().toString();
                try {
                    database=openOrCreateDatabase("Scores",MODE_PRIVATE,null);
                    database.execSQL("CREATE TABLE IF NOT EXISTS Scores(score INTEGER)");

                    String sqlString= "INSERT INTO Scores (score) VALUES (?)";
                    SQLiteStatement statement=database.compileStatement(sqlString);
                    statement.bindString(1,skorName);
                    statement.execute();

                }catch (Exception e){

                }
                cikti.setText("Your score :"+score);
                int skorName2;
                skorName2=Integer.parseInt(skorName);
                if(score>skorName2){cikti.setText("Rekor :"+score);}


            }
        }.start();
    }

    public void resume2(View view){
        pausebutton=(ImageButton)findViewById(R.id.pauseButton);
        resumebutton=(ImageButton)findViewById(R.id.resumeButton);
        pausebutton.setVisibility(View.VISIBLE);
        resumebutton.setVisibility(View.INVISIBLE);
        handler.post(runnable);
        imageTrue();
        playAgainbutton.setVisibility(View.INVISIBLE);
        resumebutton2.setVisibility(View.INVISIBLE);
        timer();
        imageView20.setVisibility(View.INVISIBLE);
    }

    public void goHome(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void playAgain(View view){
      //  playAgainbutton=(ImageButton)findViewById(R.id.playAgainButton);
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
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

