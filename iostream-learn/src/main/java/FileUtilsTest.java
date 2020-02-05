import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilsTest {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
//        FileUtilsTest.writeFile();
//        FileUtilsTest.cutFile();
        long start = System.currentTimeMillis();
        ExecutorService fixExecutorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> list = new ArrayList<>();
        for(int i = 0 ; i<10; i++){
            final int dirIdx = i;
            Callable<String> callAble = ()-> {
                String[] sss = new String[1];
                sss[0]=".txt";
                List<File> fileList = Stream.of( new File("d:\\online\\"+ dirIdx+"\\").listFiles()).collect(Collectors.toList()) ;
                String names = fileList.stream().map(e->e.getName()).collect(Collectors.joining(","));
                for(File file : fileList){
                    TimeUnit.MILLISECONDS.sleep(20);
                }

                return Thread.currentThread().getName()+"  dir: "+ dirIdx+"  done "+names;
            };
            list.add(callAble);
        }
        List<Future<String>> result = fixExecutorService.invokeAll(list);
        for(Future future : result){
            System.out.println(future.get());
        }

        fixExecutorService.shutdown();

        System.out.println("执行完成" + (System.currentTimeMillis()-start));

    }

    public static void  writeFile() throws IOException {
        List<Double> list = new ArrayList<>();
        for(int i =0 ; i<10000000 ;i++){
            list.add(Math.random());
        }
        FileUtils.writeLines(new File("d:\\online\\image.txt"),list);
    }

    public static void cutFile() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:\\online\\image.txt"),"UTF-8");
        int total = list.size();

        int dirIndx = 0;

        List<String> list_1000 = new ArrayList<>();
        for(int i=0 ; i < total ; i++){
            list_1000.add(list.get(i));
            if(list_1000.size()==1000){
                dirIndx = dirIndx % 10;
//                System.out.println("i: "+i+"   dir: "+dirIndx);
                FileUtils.writeLines(new File("d:\\online\\"+dirIndx+"\\"+System.currentTimeMillis()+"-"+i/100+".txt"),list_1000);
                list_1000.clear();
                ++dirIndx;
            }
        }
        if(!list_1000.isEmpty()){
            FileUtils.writeLines(new File("d:\\online\\"+dirIndx+"\\"+System.currentTimeMillis()+".txt"),list_1000);
        }
    }
}
