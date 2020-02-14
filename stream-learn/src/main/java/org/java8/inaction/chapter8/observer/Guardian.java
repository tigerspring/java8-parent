package org.java8.inaction.chapter8.observer;

import java.util.Objects;

public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if(!Objects.isNull(tweet) && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
