package org.java8.inaction.chapter8.chaiofResposibility;

public class SpellingCheckerProcessing extends ProcessObject<String> {
    @Override
    protected String handleWork(String input) {
        return new StringBuffer(input).append("  第二个方法  ").toString();
    }
}
