package org.java8.inaction.chapter8.strategy;

public class Test {
    public static void main(String[] args) {
        String sss = "avsdfasdfa";

        Validator validator = new Validator(new IsAllLowerCase());
        System.out.println("验证是否为小写 "+validator.validate(sss));

        validator = new Validator(new IsNumeric());
        System.out.println("验证是否为数字 "+validator.validate(sss));
    }
}
