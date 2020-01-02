package thread.pool.invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        InvokeTest invokeTest = new InvokeTest();
        invokeTest.testInvokeAny();
//        streamThreadTest.testInvokeAll();
    }

    /**
     * invokeAny返回某一个完成的任务，不确定返回的额是哪一个，也许是第一个完成的
     * 使用场景：只要拿到结果就可以往下执行，不必等到所有任务都返回结果
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private void testInvokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> list = new ArrayList<>();
        for(int i = 0; i < 10 ; i++){
            final int tmp = i;
            Callable callAble = ()-> {
               if(tmp == 5){
                    TimeUnit.SECONDS.sleep(5);
                }else{
                    TimeUnit.SECONDS.sleep(10);
                }
                System.out.println(Thread.currentThread().getName()+"===> tmp : "+tmp);
                return tmp;
            };
            list.add(callAble);
        }

        Integer result = executorService.invokeAny(list);
        System.out.println(result);

        executorService.shutdown();

    }

    /**
     * invokeAll等待所有的任务结束，获取所有任务的结果
     *
     * 缺点如果有个任务执行时间很长，需要一直等下去，直到那个任务结束。
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private void testInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorService);

        List<Callable<Integer>> list = new ArrayList<>();
        for(int i = 0; i < 10 ; i++){
            final int tmp = i;
            Callable callAble = ()-> {
                TimeUnit.SECONDS.sleep(tmp);
                System.out.println(Thread.currentThread().getName()+"===> tmp : "+tmp);
                return tmp;
            };
            list.add(callAble);
        }

        List<Future<Integer>> result = executorService.invokeAll(list);
        for(Future future : result){
            System.out.println(future.get());
        }

        executorService.shutdown();
    }


}

