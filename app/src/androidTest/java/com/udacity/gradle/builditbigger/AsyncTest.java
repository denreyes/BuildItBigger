package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by DJ on 8/7/2015.
 */
public class AsyncTest  extends ApplicationTestCase<Application> implements EndpointsAsyncTask.onSuccess {
    CountDownLatch latch;
    String joke;

    public AsyncTest() {
        super(Application.class);
    }

    public void testJokeEndpoint() {
        try {
            latch = new CountDownLatch(1);
            new EndpointsAsyncTask().execute(this);
            latch.await(30, TimeUnit.SECONDS);
            assertFalse("Joke is empty", joke.length() == 0);
        } catch (Exception e){
            fail("Connection timed out");
        }
    }

    @Override
    public void onSuccess(String joke) {
        this.joke = joke;
        latch.countDown();
    }
}