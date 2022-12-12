package com.example.demo;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ConcurrentTest {

    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FutureTask<String> futureTask = new FutureTask<>(() -> "测试异步返回结果");
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
        executorService.shutdown();
    }

    /**
     * 在SimpleDateFormat的父类DateFormat中，存在一个Calender的全局变量，在并发场景下，被不同的线程共同使用
     * 在调用parse方法或format方法的时候，都需要使用同一个Calendar，而使用的时候，Calendar有个清理和重新设置的动作，
     * 清理与重新设置都没加锁，意味着在并发场景下，Calendar可能会经常被其他线程修改，导致出现问题
     * 修改方案：
     *   1. 使用SimpleDateFormat局部变量，缺点是创建大量对象，不适合高并发调用
     *   2. 加synchronized锁或者Lock锁，缺点是加了锁，不适合高并发调用
     *   3. 使用ThreadLocal，每个线程都创建一个自己的SimpleDateFormat对象，解决了高并发场景下共享对象，以及锁的问题，可以在高并发场景使用
     * 修改方案总结：
     *   1. 加锁（如修改方案2）
     *   2. 创建副本，不同的线程拿不同的副本（如修改方案1,3）
     *   3. 局部变量（如DateTimeFormatter since 1.8）
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void testSimpleDateFormat() throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Semaphore semaphore = new Semaphore(1000);
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        simpleDateFormat.parse("2020-01-01");
                    } catch (ParseException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }catch (NumberFormatException e){
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }


    /**
     * 从源码中看出，DateTimeFormatter中的全局变量都用final修饰，被初始化之后，无法进行修改，
     * 此外，在parse方法内部，用的都是局部变量，所以没有并发情况下共享参数的问题，
     * 而且没有锁，可以在高并发场景下使用
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void testDateTimeFormatter() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final Semaphore semaphore = new Semaphore(1000);
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        LocalDate.parse("2020-01-01", formatter);
                    }catch (Exception e){
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }

}
