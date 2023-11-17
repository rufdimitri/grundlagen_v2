package rd.text_bearbeitung1_A;



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

    public static void main(String[] args) {
        String[] words = getWords(text);
        System.out.format("_Worte_ Anzahl: %s Beispiel: %s \n", words.length, showFirstElements(words, 3));
        String[] lines = getLines(text);
        System.out.format("_Zeilen_ Anzahl: %s Beispiel: %s \n", lines.length, showFirstElements(lines, 1));
        String abcLetters = text.replaceAll("[^A-Za-z]+", "");
        System.out.format("_abcBuchstaben_ Anzahl: %s Beispiel: %s \n", abcLetters.length(), showFirstElements(abcLetters, 20));
        System.out.format("_Characters_ Anzahl: %s Beispiel: %s \n", text.length(), showFirstElements(text, 20));
    }

    static String[] getWords(String text) {
        String[] words = text.split("[ |\n]+");
        return words;
    }

    static String[] getLines(String text) {
        String[] lines = text.split("\n");
        return lines;
    }

    static String showFirstElements(String text, int count) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++) {
            sb.append("'").append(text.charAt(i)).append("', ");
        }
        return sb.toString();
    }

    static String showFirstElements(String[] text, int count) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(String element : text) {
            if (i >= count) {
                break;
            }
            sb.append("'").append(element).append("', ");
            i++;
        }
        return sb.toString();
    }

}
