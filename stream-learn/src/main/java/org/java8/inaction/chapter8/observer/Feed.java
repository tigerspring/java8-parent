package org.java8.inaction.chapter8.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息源
 */
public class Feed implements Subject {

    List<Observer> list = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void notifyObserver(String tweet) {
        for(Observer o : list){
            o.notify(tweet);
        }
    }
}
