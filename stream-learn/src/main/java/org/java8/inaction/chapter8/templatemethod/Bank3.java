package org.java8.inaction.chapter8.templatemethod;

public class Bank3 extends OnlineBank {
    @Override
    public void makeCustomerHappy(Customer customer) {
        System.out.println("bank3 为您服务");
    }
}
