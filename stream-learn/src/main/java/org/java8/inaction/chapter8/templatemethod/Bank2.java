package org.java8.inaction.chapter8.templatemethod;

public class Bank2 extends OnlineBank{
    @Override
    void makeCustomerHappy(Customer customer) {
        System.out.println("Bank2 为您服务");
    }
}
