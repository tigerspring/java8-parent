package org.java8.inaction.chapter8.chaiofResposibility.lambda;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test {
    public static void main(String[] args) {
        UnaryOperator<String> headerProcessing = (String text)-> new StringBuffer(text).append(" 第一个方法 ").toString();
        UnaryOperator<String> spellingCheckerProcessing = (String text)-> new StringBuffer(text).append(" 第二个方法 ").toString();
        UnaryOperator<String> sp2 = (String text)->new StringBuffer(text).append(" 第三个方法 ").toString();
        Function<String,String> pipeLine=headerProcessing.andThen(spellingCheckerProcessing).andThen(sp2);
        String result = pipeLine.apply(" 开始责任链 ");
        System.out.println( result );
    }
}
