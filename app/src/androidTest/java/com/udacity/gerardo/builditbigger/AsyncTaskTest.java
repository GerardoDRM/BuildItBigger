package com.udacity.gerardo.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;

/**
 * Created by gerardo on 2/03/16.
 */
public class AsyncTaskTest extends AndroidTestCase{
    public void testVerifyNull() {
        try {
            assertNotNull(new EndpointsAsyncTask(getContext()).execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
