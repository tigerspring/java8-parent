package org.java8.inaction.chapter8.observer;

/**
 * 主体接口
 *  1.注册观察者
 *  2.发送消息
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
