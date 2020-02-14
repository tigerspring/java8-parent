package org.java8.inaction.chapter8.strategy;

/**
 * 策略模式3要素： 策略接口，接口实现，使用接口的客户端
 *
 * 使用策略的客户
 */
public class Validator {
    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s){
        return this.validationStrategy.execute(s);
    }
}
