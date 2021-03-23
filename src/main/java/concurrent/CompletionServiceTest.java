package concurrent;

import java.util.concurrent.*;

/**
 * CompletionService 学习
 * Future get()方法的致命缺陷:如果Future结果没有完成，调用get()方法，程序会阻塞在那里，直至获取返回结果
 * 要彻底理解 ExecutorCompletionService ，我们只需要知道一个问题的答案就可以了：它是如何将异步任务结果放到这个阻塞队列中的？
 *
 * 参考：
 * https://www.jianshu.com/p/c4a31f914cc7
 * https://segmentfault.com/a/1190000023587881
 */
public class CompletionServiceTest {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String args[]) {
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executor);
        int num = 100;
        final int groupNum = 100000000 / num;
        for (int i = 1; i <= num; i++) {
            int start = (i - 1) * groupNum + 1, end = i * groupNum;
            completionService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Long sum = 0L;
                    for (int j = start; j <= end; j++) {
                        sum += j;
                    }
                    return sum;
                }
            });
        }

        long result = 0l;
        try {
            for (int i = 0; i < num; i++) {
                // 从队列里面获取结果 避免future get的问题
                result += completionService.take().get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("the result is:" + result);
        System.exit(0);
    }
}
