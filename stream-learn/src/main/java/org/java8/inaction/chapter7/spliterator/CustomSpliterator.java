package org.java8.inaction.chapter7.spliterator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomSpliterator {

    public static final String testStr = "hello world jin xuefeng";

    public static void main(String[] args) {
        System.out.println(CustomSpliterator.countWordsIteratively(CustomSpliterator.testStr) );
        //测试例子2
        Stream<Character> characterStream = IntStream.range(0,testStr.length()).mapToObj(testStr::charAt);
        WordCounter wordCounter = characterStream.reduce(new WordCounter(0,true),WordCounter::accumulate,WordCounter::combine);
        System.out.println(wordCounter.getCounter());
    }

    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c:s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace= true;
            }else{
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
/**
 * 例子2：试用函数式编程实现字符统计
 */
class WordCounter{
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ? this : new WordCounter(counter,true);
        }else{
            return lastSpace ? new WordCounter(counter+1,false):this;
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter+wordCounter.counter,wordCounter.lastSpace);
    }

    public int getCounter(){
        return counter;
    }
}