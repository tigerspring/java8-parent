package org.java8.inaction.chapter8.observer;

/**
 * 观察者接口，只有通知方法
 */
public interface Observer {
    void notify(String tweet);
}
