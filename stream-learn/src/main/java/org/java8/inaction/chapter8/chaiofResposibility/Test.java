package org.java8.inaction.chapter8.chaiofResposibility;

public class Test {
    public static void main(String[] args) {

        ProcessObject<String> p1 = new HeaderTextProcessing();
        ProcessObject<String> p2 = new SpellingCheckerProcessing();
        ProcessObject<String> p3 = new CustomObject();
        //将两个对象链接起来
        p1.setSuccessor(p2);

        String result = p1.handle("开始责任链 ");
        p2.setSuccessor(p3);
        result = p2.handle(result);
        System.out.println(result);
    }
}
