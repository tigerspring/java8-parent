package thread.pool;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService fixExecutorService = Executors.newFixedThreadPool(5);


        FutureTask<Integer> input2_task = new FutureTask<>(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"===>input2_task 5s");
                    TimeUnit.SECONDS.sleep(5);
                    return 5;
                }
        );

        FutureTask<Integer> input1_task = new FutureTask<>(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"===>input1_task 1s");
                    TimeUnit.SECONDS.sleep(1);
                    return 1;
                }
        );

        FutureTask<Integer> input3_task = new FutureTask<>(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"===>input3_task 3s");
                    TimeUnit.SECONDS.sleep(3);
                    return 3;
                }
        );

        Thread t = new Thread(() -> {
            System.out.println("runnable t");
            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(10);
                endTime = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(startTime-endTime < -5000){
                throw new RuntimeException("时间过长！");
            }
        });

        //submit返回的future，可以捕Runnable获线程运行时抛出的异常
        Future f1 = fixExecutorService.submit(t);
        try {
            f1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //submit返回的future，可以捕获线程运行时抛出的异常
        fixExecutorService.submit(input1_task);
        System.out.println(input1_task);
        int input1 = input1_task.get();
        System.out.println(input1_task.isDone());
        fixExecutorService.submit(input2_task);
        int input2 = input2_task.get();
        System.out.println(input2_task.isDone());
        fixExecutorService.submit(input3_task);
        int input3 = input3_task.get();
        System.out.println(input3_task.isDone());
        System.out.println(input1+input2+input3);

        fixExecutorService.shutdown();

    }

}
