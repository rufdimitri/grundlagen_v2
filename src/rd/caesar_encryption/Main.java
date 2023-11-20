package rd.caesar_encryption;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class Main {
    static int startGrossen = 65;
    static int endeGrossen = 90;
    static int startKleinen = 97;
    static int endeKleinen = 122;
    static int rotation = 1;

    public static void main(String[] args) {
        String s = "aAOttozZ"; //char 2 byte
        byte[] bytes = s.getBytes();
        System.out.println(Arrays.toString(bytes));
        char[] chars = new char[bytes.length];

        //Durchlaufe das bytearray, schiebe analog Ascii den Buchstaben + 1
        for(int i = 0; i < bytes.length; i++) {
            int byte1 = bytes[i];
            int ende = 0;
            int start = 0;
            if (byte1 >= startKleinen && byte1 <= endeKleinen) { //a-z
                start = startKleinen;
                ende = endeKleinen;
            } else if (byte1 >= startGrossen && byte1 <= endeGrossen) { //A-Z
                start = startKleinen;
                ende = endeKleinen;
            } else { //andere zeichen
            }
            byte1 += rotation;
            if (byte1 > endeKleinen) {
                byte1 -= startKleinen;
            }
            chars[i] = (char)byte1;
        }

        String output = String.valueOf(chars);
        System.out.println(output);

    }
}
