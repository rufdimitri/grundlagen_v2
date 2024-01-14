package rd.collections.deque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MainInDequeue {
    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedList<>();

        System.out.println("peek: " + queue.peek());
//        System.out.println("element: " + queue.element()); //throws NoSuchElementException
        System.out.println("peekLast: " + queue.peekLast());
        queue.offer(1);
        System.out.println("peek: " + queue.peek());
        System.out.println("element: " + queue.element());
        System.out.println("peekLast: " + queue.peekLast());
        queue.offer(2);
        System.out.println("peek: " + queue.peek());
        System.out.println("peekLast: " + queue.peekLast());
        queue.add(3);
        System.out.println("peek: " + queue.peek());
        System.out.println("peekLast: " + queue.peekLast());
        queue.add(4);
        System.out.println("peek: " + queue.peek());
        System.out.println("peekLast: " + queue.peekLast());
        System.out.println(queue);

    }
}
