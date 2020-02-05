package org.java8.inaction.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinCaculator extends RecursiveTask<Long> {

    public static void main(String[] args) {
        long numbers[] = LongStream.rangeClosed(1,100_000_000).toArray();
        ForkJoinTask<Long> task = new ForkJoinCaculator(numbers);
//        long numbers1[] = LongStream.rangeClosed(1,10000).toArray();
//        ForkJoinTask<Long> task1 = new ForkJoinCaculator(numbers1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        new Thread(
                ()-> System.out.println(Thread.currentThread().getName() +"==> sum : " +forkJoinPool.invoke(task))
        ).start();
        new Thread(
//                ()-> System.out.println(Thread.currentThread().getName() +"==> sum : " +forkJoinPool.invoke(task1))
        ).start();

    }


    private final long[] numbers;
    private final int start;
    private final int end;

    private static final long THRESHOLD=10;

    public ForkJoinCaculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinCaculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length < THRESHOLD){
            long sum = this.computeSequentially();
//            System.out.println(Thread.currentThread().getName()+"==>sum : "+sum);
            return sum;
        }

        ForkJoinCaculator leftTask =
                new ForkJoinCaculator(numbers,start,start+length/2);
        leftTask.fork();

        ForkJoinCaculator rigthTask =
                new ForkJoinCaculator(numbers,start+length/2,end);

        Long rightRs = rigthTask.compute();
        Long leftRs = leftTask.join();

        return rightRs+leftRs;
    }

    private long computeSequentially(){
        long sum = 0;
        for(int i = start ; i<end ; i++){
            sum += numbers[i];
        }
        return sum;
    }
}
