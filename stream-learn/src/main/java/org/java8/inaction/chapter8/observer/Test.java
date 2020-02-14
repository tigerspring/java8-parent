package org.java8.inaction.chapter8.observer;

public class Test {
    public static void main(String[] args) {
        Subject subject = new Feed();


        Observer o = new Guardian();
        subject.registerObserver(o);
        o = new LeMonde();
        subject.registerObserver(o);
        o = new NYTime();
        subject.registerObserver(o);

        subject.notifyObserver("The queen said her favourite book is Java 8 in Action!");

    }
}
