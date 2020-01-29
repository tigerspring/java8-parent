package thread.pool.forkjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.LongStream;

public class ForkJoinTest {
    public static void main(String[] args) {
        /*final int SIZE = 100000000;
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
        System.out.println(System.currentTimeMillis()-start+" ms");*/

        List<Integer> tmp = new ArrayList<>();
        for(int i = 0 ; i < 200 ; i++){
            tmp.add(i);
        }
        long start = System.currentTimeMillis() ;
        CounterList counter = new CounterList(0,tmp.size());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        List<Map> list = counter.join();
        System.out.println(counter.join());
        System.out.println(System.currentTimeMillis()-start);
        for(Map map : list){
            System.out.println(map);
        }
    }
}

/*class Counter extends RecursiveTask<Long>{

    private static final int THRESHOLD=10;
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
}*/


class CounterList extends RecursiveTask<List<Map>>{

    private static final int THRESHOLD=10;

    private int from;
    private int to;
//    private DoublePredicate filter;

    public CounterList(int from ,int to ){
        this.from = from;
        this.to = to;
//        this.filter = filter;
    }

    @Override
    protected List<Map> compute() {
        if(to - from < THRESHOLD){
            List<Map> sum = new ArrayList<>();
            // 处理请求
            for(int i = from ; i < to ; i++){
                Map<String,Integer> map = new HashMap<>();
                map.put(Thread.currentThread().getName(),i);
                sum.add(map);
            }
            new RequestConsumer().doRuest(sum,
                    (List list)-> {
                        try {
                            TimeUnit.MILLISECONDS.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return list;
                    }
            );
            return sum;
        }else{
            int mid = (from + to)/2;
            CounterList first = new CounterList(from,mid);
            CounterList second = new CounterList(mid,to);
            invokeAll(first, second);
            List<Map> firstList = first.join();
            firstList.addAll(second.join());
            return firstList;
        }
    }
}

class RequestConsumer{
    public List doRuest(List list,Function<List,List> function){
        return function.apply(list);
    }
}

