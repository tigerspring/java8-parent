package org.java8.inaction.chapter8.templatemethod;

public class Bank1 extends OnlineBank{
    @Override
    public void makeCustomerHappy(Customer customer) {
        System.out.println("Bank1 为您服务");
    }
}
