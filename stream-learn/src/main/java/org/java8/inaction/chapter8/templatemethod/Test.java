package org.java8.inaction.chapter8.templatemethod;

import org.java8.inaction.chapter8.templatemethod.lamada.OnlineBankLamada;

/**
 * 模板方法测试客户端
 */
public class Test {
    public static void main(String[] args) {
        OnlineBank onlineBank = new Bank1();
        onlineBank.processCustomer(1);

        onlineBank = new Bank2();
        onlineBank.processCustomer(1);


        onlineBank = new Bank3();
        onlineBank.processCustomer(1);

        OnlineBankLamada  onlineBankLamada = new OnlineBankLamada();
        onlineBankLamada.processCustomer(1, (Customer c) -> System.out.println("Bank1 为您服务"));
        onlineBankLamada.processCustomer(1, (Customer c) -> System.out.println("Bank2 为您服务"));
        onlineBankLamada.processCustomer(1, (Customer c) -> System.out.println("Bank3 为您服务"));

    }
}
