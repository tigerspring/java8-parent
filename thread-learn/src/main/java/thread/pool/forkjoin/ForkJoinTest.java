package thread.pool.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 100000000;
        List<Long> doubleList = new ArrayList<>();
        for(long i=0 ; i < SIZE ; i++) doubleList.add(i);
        Long start = System.currentTimeMillis();
        Counter counter = new Counter(doubleList,0,doubleList.size());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println("并行sum : "+counter.join());
        System.out.println(System.currentTimeMillis()-start+" ms");

        start = System.currentTimeMillis();
        long sum = 0;
        for(Long d : doubleList){
            sum = sum+d;
        }
        System.out.println("单线程sum : "+sum);
        System.out.println(System.currentTimeMillis()-start+" ms");

        start = System.currentTimeMillis();
        Long sum1 = LongStream.rangeClosed(0,SIZE-1).sum();
        System.out.println("流多线程 : "+sum1);
        System.out.println(System.currentTimeMillis()-start+" ms");
    }
}

class Counter extends RecursiveTask<Long>{

    private static final int THRESHOLD=100000;
    private List<Long> values;
    private int from;
    private int to;
//    private DoublePredicate filter;

    public Counter(List<Long> values,int from ,int to ){
        this.values=values;
        this.from = from;
        this.to = to;
//        this.filter = filter;
    }

    @Override
    protected Long compute() {
        if(to - from < THRESHOLD){
            long sum = 0;
            for(int i = from ; i < to ; i++){
                sum = sum + values.get(i);
            }
            return sum;
        }else{
            int mid = (from + to)/2;
            Counter first = new Counter(values,from,mid);
            Counter second = new Counter(values,mid,to);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}
