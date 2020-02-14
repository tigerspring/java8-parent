package org.java8.inaction.chapter8.templatemethod.lamada;

import org.java8.inaction.chapter8.templatemethod.Customer;

import java.util.function.Consumer;

/**
 * 模板方法传递consumer函数是接口，解决代码僵化问题
 *
 */
public class OnlineBankLamada {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer customer = new Customer(id);
        makeCustomerHappy.accept(customer);
    }
}
