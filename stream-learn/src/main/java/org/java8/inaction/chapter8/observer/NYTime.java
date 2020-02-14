package org.java8.inaction.chapter8.observer;

import java.util.Objects;

public class NYTime implements  Observer {
    @Override
    public void notify(String tweet) {
        if(!Objects.isNull(tweet) && tweet.contains("money")){
            System.out.println("Breaking news in NY : "+tweet);
        }
    }
}
