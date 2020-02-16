package org.java8.inaction.chapter8.chaiofResposibility;

public class CustomObject extends ProcessObject<String> {
    @Override
    protected String handleWork(String input) {
        return new StringBuffer(input).append("  第三个方法  ").toString();
    }
}
