package rd.files;

import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ListUtil {
    static public java.util.List<String> readFromFile(String filename, String valuesSeparator) {
        int buffSize = 1_000_000;
        char[] buffer = new char[buffSize];
        java.util.List<String> values = new ArrayList<>();
        try (Reader r = new FileReader(filename, StandardCharsets.UTF_8) ) {
            int count;
            StringBuilder oldWordPart = new StringBuilder();
            while ((count = r.read(buffer)) > 0) {
                int begin = 0;
                int separatorCharPointer = 0;
                for (int i = 0; i < count; i++) {
                    if (buffer[i] == valuesSeparator.charAt(separatorCharPointer)) {
                        separatorCharPointer++;
                        if (separatorCharPointer == valuesSeparator.length()) {
                            String word = String.copyValueOf(buffer, begin, i - separatorCharPointer - begin + 1);
                            values.add(oldWordPart.toString() + word);
                            oldWordPart.setLength(0);
                            begin = i + 1;
                            separatorCharPointer = 0;
                        }
                    } else {
                        separatorCharPointer = 0;
                        if (i == count - 1) {
                            oldWordPart.append(String.copyValueOf(buffer, begin, i-begin+1));
                        }
                    }
                }

//                print(values, System.out, "|");
            }
            values.add(oldWordPart.toString());
//            print(values, System.out, "|");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    /**
     *
     * @param array can be an array of objects (not primitives), those should implement toString() method.
     *          Also it can be an object implementing java.lang.Iterable (List, Set...)
     * @param os
     * @param separator
     */
    static public void print(Object array, PrintStream os, String separator) {
        StringBuilder sb = new StringBuilder();
        if (array instanceof Object[] stringArray) {
            boolean first = true;
            for (Object element : stringArray) {
                if (!first) {
                    sb.append(separator);
                }
                sb.append(element.toString());
                first = false;
            }
        } else if (array instanceof Iterable<?> iterable) {
            boolean first = true;
            for (Object element : iterable) {
                if (!first) {
                    sb.append(separator);
                }
                sb.append(element.toString());
                first = false;
            }
        } else {
            throw new IllegalArgumentException("unsupported object type: " + array.getClass().getName());
        }
        os.println(sb.toString());
    }
}
