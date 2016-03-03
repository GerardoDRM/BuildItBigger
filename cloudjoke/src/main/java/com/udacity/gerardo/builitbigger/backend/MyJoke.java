package com.udacity.gerardo.builitbigger.backend;

import com.udacity.JokeSource;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyJoke {

    public String getData() {
        return JokeSource.getRandomJoke();
    }
}