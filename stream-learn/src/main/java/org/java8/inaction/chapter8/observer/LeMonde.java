package org.java8.inaction.chapter8.observer;

import com.sun.deploy.util.StringUtils;

import java.util.Objects;

public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if(!Objects.isNull(tweet) && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
