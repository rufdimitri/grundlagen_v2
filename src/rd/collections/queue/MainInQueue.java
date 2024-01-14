package rd.collections.queue;

import java.util.LinkedList;
import java.util.Queue;

public class MainInQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        System.out.println("peek: " + queue.peek());
        queue.offer(1);
        System.out.println("peek: " + queue.peek());
        queue.offer(2);
        System.out.println("peek: " + queue.peek());
        queue.add(3);
        System.out.println("peek: " + queue.peek());
        queue.add(4);
        System.out.println("peek: " + queue.peek());
        System.out.println(queue);

    }
}
