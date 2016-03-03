package com.udacity;

import java.util.Random;

public class JokeSource {

    private static String [] jokes = {"An Android app walks into a bar. Bartender asks, \"Can I get you a drink?\" \n The app looks disappointed and says, \"That wasn't my intent.\"",
            "Your first Android app walks into a hotel and asks for a room with an upright bed. \n\"Why?\" asks the concierge. \"I haven't implemented my horizontal yet\".",
    "Another Joke", "Last Joke"};


    public static String getRandomJoke(){
        return jokes[new Random().nextInt(jokes.length)];
    }
}

