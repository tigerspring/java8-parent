package org.java8.inaction.chapter8.templatemethod;

/**
 * 模板方法传统模式
 *
 */
abstract class OnlineBank {

    public void processCustomer(int id){
        Customer customer = new Customer(id);
        this.makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Customer customer);
}
