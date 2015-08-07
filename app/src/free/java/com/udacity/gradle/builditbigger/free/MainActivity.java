package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import joke.telling.JokeTeller;
import joke.telling.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    JokeTeller nJoke;
    public static Button btnJoke;
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJoke = (Button)findViewById(R.id.button);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intestitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
    }

    public void tellJoke(View view){
        btnJoke.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        requestNewInterstitial();

        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.onSuccess() {
            @Override
            public void onSuccess(String joke) {
                if (!joke.equals("")) {
                    btnJoke.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(), JokeActivity.class);
                    i.putExtra("JOKE", joke);
                    startActivity(i);
                }
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}