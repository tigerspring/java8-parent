package org.java8.inaction.chapter8.strategy.lamada;

import org.java8.inaction.chapter8.strategy.Validator;

/**
 * 策略模式直接传lamada表达式
 * 无需写使用实现接口的样板代码
 */
public class Test {
    public static void main(String[] args) {
        String sss = "asfasdf";

        Validator vv = new Validator((s)->s.matches("[a-z]+"));
        System.out.println("验证是否为小写 "+vv.validate(sss));
        vv = new Validator((s)->s.matches("\\d+"));
        System.out.println("验证是否为数字 "+vv.validate(sss));

    }
}
