package org.java8.inaction.chapter8.observer.lamada;

import org.java8.inaction.chapter8.observer.Feed;
import org.java8.inaction.chapter8.observer.Subject;

import java.util.Objects;

/**
 * 使用了lamada，观察者模式只需要
 * 1.主体接口及其实现类
 * 2.观察者接口
 */

public class Test {
    public static void main(String[] args) {
        Subject s =  new Feed();

        s.registerObserver((String tweet)->{
            if(!Objects.isNull(tweet) && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        s.registerObserver((String tweet)->{
            if(!Objects.isNull(tweet) && tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });

        s.registerObserver((String tweet)->{
            if(!Objects.isNull(tweet) && tweet.contains("money")){
                System.out.println("Breaking news in NY : "+tweet);
            }
        });

        s.notifyObserver("The queen said her favourite book is Java 8 in Action!");
    }
}
