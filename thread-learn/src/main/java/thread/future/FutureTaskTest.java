package thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {
    /**
     * FutureTask 和 callable 需要结合使用
     * 可以获取线程运行的结果
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long starttime = System.currentTimeMillis();

        FutureTask<Integer> input2_task = new FutureTask<>(
                ()->{
                    TimeUnit.SECONDS.sleep(5);
                    return 5;
                }
        );

        new Thread(input2_task).start();

        FutureTask<Integer> input1_task = new FutureTask<>(
                ()->{
                    TimeUnit.SECONDS.sleep(3);
                    return 3;
                }
        );
        new Thread(input1_task).start();
        System.out.println("===>"+input1_task.isDone());

        Integer input1 = input1_task.get();
        Integer input2 = input2_task.get();

        System.out.println(input1_task.isDone());
        System.out.println(input1_task.isCancelled());

        System.out.println("结果："+(input1+input2));

        System.out.println("用时 ： "+(System.currentTimeMillis()-starttime));

    }
}
