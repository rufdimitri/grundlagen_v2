package rd.recursion;

public class Main {
    static int recurse(int value) {
        return recurse(value+1);
    }
    public static void main(String[] args) {
        recurse(1);
    }
}
