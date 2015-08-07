package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import joke.telling.JokeTeller;
import joke.telling.jokedisplay.JokeActivity;


public class MainActivity extends AppCompatActivity {
    JokeTeller nJoke;
    public static Button btnJoke;
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJoke = (Button)findViewById(R.id.button);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void tellJoke(View view){
        btnJoke.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.onSuccess() {
            @Override
            public void onSuccess(String joke) {
                if(!joke.equals("")) {
                    btnJoke.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(), JokeActivity.class);
                    i.putExtra("JOKE", joke);
                    startActivity(i);
                }
            }
        });
    }
}