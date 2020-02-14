package org.java8.inaction.chapter8.strategy;

/**
 * 策略模式3要素： 策略接口，接口实现，使用接口的客户端
 *
 * 策略模式实现类
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
