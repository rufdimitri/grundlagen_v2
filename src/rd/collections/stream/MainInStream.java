package rd.collections.stream;

import java.util.Collections;
import java.util.List;

public class MainInStream {

    public static void main(String[] args) {
        List<String> list = List.of("BMW", "Audi", "Mercedes");

        System.out.println(" ------- sum --------- ");
        int sum = list.stream()
                .map(String::length)
                .peek(length -> System.out.println("peek length: " + length))
                .filter(length -> length >= 4)
                .peek(length -> System.out.println("peek length filtered: " + length))
                .reduce(Integer::sum)
                .orElse(0);

        System.out.println("--------- min -----------");
//        list = Collections.emptyList();
        Integer min = list.stream()
                .map(String::length)
                .peek(length -> System.out.println("peek length: " + length))
                .min(Integer::compareTo)
                .orElse(null);

        System.out.println("min = " + min);

        System.out.println("--------- max -----------");
//        list = Collections.emptyList();
        Integer max = list.stream()
                .map(String::length)
                .peek(length -> System.out.println("peek length: " + length))
                .max(Integer::compareTo)
                .orElse(null);

        System.out.println("max = " + max);
    }
}
