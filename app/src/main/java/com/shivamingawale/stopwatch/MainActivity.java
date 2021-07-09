package com.shivamingawale.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button start,stop,reset;
    boolean run=false;
    Runnable r;
    TextView time;
    int seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        start=findViewById(R.id.start);
        reset=findViewById(R.id.reset);
        stop=findViewById(R.id.stop);
        time=findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post(r = new Runnable() {
            @Override
            public void run() {
                int hr = (seconds/100)/(60*60);
                int min = ((seconds/100)%3600)/60;
                int sec = (seconds/100)%60;
                int mil = (seconds)%100;
                String timeText = String.format(Locale.getDefault(),"%d:%02d:%02d:%02d",hr,min,sec,mil);
                time.setText(timeText);
                if (run) {
                    seconds++;
                }
                handler.postDelayed(this,10);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(view.INVISIBLE);
                stop.setVisibility(view.VISIBLE);
                time.setVisibility(view.VISIBLE);
                run = true;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setVisibility(view.INVISIBLE);
                start.setVisibility(view.VISIBLE);
                run=false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setVisibility(view.INVISIBLE);
                start.setVisibility(view.VISIBLE);
                seconds=0;
                run= false;
            }
        });
    }
}