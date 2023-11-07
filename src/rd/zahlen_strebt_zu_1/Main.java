package rd.zahlen_strebt_zu_1;

/*
    Eingabe eine Zahl n
     • wenn n gerade ist, dann ergibt sich das neue n aus n=n/2
     • wenn n ungerade ist,  dann n=n*3+1
     • terminieren bei n=1
 */
public class Main {
    public static void main(String[] args) {
        long n = Integer.MAX_VALUE-1;
        while (n != 1) {
            System.out.print("n = " + n);
            if (n % 2 == 0) {
                n = n/2;
                System.out.println(" | /2 = " + n);
            } else {
                n = n*3 + 1;
                System.out.println(" | *3+1 = " + n);
            }

        }
    }
}
