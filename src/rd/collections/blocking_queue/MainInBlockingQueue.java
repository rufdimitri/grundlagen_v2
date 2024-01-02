package rd.collections.blocking_queue;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MainInBlockingQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);

        //write-Thread
        new Thread(new Runnable() {
            Random random = new Random();
            @Override
            public void run() {
                while (true) {
                    try {
                        int value = random.nextInt(1000)+1000;
                        queue.offer(value, 1000, TimeUnit.MILLISECONDS);
                        System.out.println("write " + value + " @ time " + System.currentTimeMillis() + " size: " + queue.size());
                    } catch (Exception e) {
                        System.out.println("interrupted at write");
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        //read-Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Integer value = queue.poll(2500, TimeUnit.MILLISECONDS);
                        if (Objects.isNull(value)) {
                            throw new InterruptedException();
                        }
                        System.out.println("read " + value + " @ time " + System.currentTimeMillis() + " size: " + queue.size());

                    } catch (Exception e) {
                        System.out.println("interrupted at read" + " @ time " + System.currentTimeMillis());
                    }
                }
            }
        }).start();

    }
}
