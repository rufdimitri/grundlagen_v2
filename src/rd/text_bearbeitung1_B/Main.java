package rd.text_bearbeitung1_B;



public class Main {
    static String text = "Sein oder Nichtsein, das; ist hier die Frage: \n" +   //8
            "Obs edler im Gemüt, die Pfeil und Schleudern \n" +         //8
            "Des wütenden Geschicks erdulden oder, \n" +                //5
            "Sich waffnend gegen eine See von Plagen, \n" +             //7
            "Durch Widerstand sie enden? Sterben schlafen \n" +         //6
            "Nichts weiter! Und zu wissen, daß ein Schlaf \n" +         //8
            "Das Herzweh und die tausend Stöße endet, \n" +             //7
            "Die unsers Fleisches Erbteil, 's ist ein Ziel, \n" +       //8
            "Aufs innigste zu wünschen. Sterben schlafen \n" +          //6
            "Schlafen! Vielleicht auch träumen! \n"; 			        //4

    //Buchstaben 437
    //Worte 67
    //Zeilen 10
    //Worte mit w/W = 6
    //Worte mit d/D = 5

    public static void main(String[] args) {
        System.out.format("Worte %s\n", words(text));
        System.out.format("Zeilen %s\n", lines(text));

        String buchstabenAmAnfang = "wW";
        System.out.format("Worte, die mit %s anfangen: %s\n", buchstabenAmAnfang, wordsStartingWith(text, buchstabenAmAnfang));
    }

    static int words(String text) {
        boolean word = false;
        int count = 0;
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != ' ') {
                word = true;
            } else if (word) { //Ende des Wortes
                word = false;
                count++;
            }
        }
        return count;
    }

    static int wordsStartingWith(String text, String letters) {
        boolean word = false;
        int count = 0;
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != ' ') {
                if (!word) {
                    for (int j = 0; j < letters.length(); j++) {
                        if (c == letters.charAt(j)) {
                            count++;
                            break;
                        }
                    }
                }
                word = true;
            } else if (word) { //Ende des Wortes
                word = false;
            }
        }
        return count;
    }

    static int lines(String text) {
        boolean line = false;
        int count = 0;
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != '\n') {
                line = true;
            } else if (line) { //Ende der Zeile
                line = false;
                count++;
            }
        }
        return count;
    }


}
