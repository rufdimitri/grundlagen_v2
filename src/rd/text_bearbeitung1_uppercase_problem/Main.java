package rd.text_bearbeitung1_uppercase_problem;

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

    static void test() {
        int worte = 0;
        int zeilen = 0;
        int mitWworte = 0;
        for (int s=0;s!=text.length();s++){
            if(text.charAt(s)=='\n') zeilen++;
            if(text.charAt(s)==' '){
                worte++;
                //System.out.println(text.toUpperCase().charAt(s+1));
                if(text.toLowerCase().charAt(s+1)=='w') {
                    mitWworte++;
                    System.out.print("xxx"+text.toUpperCase().charAt(s+1));
                }
            }
        }
        System.out.println();
        System.out.format("Worte: %s\n", worte);
        System.out.format("Zeilen: %s\n", zeilen);
        System.out.format("mitWworte: %s\n", mitWworte);
        System.out.println(text.toLowerCase());
        System.out.println(text.toUpperCase());
    }
    public static void main(String[] args) {
        test();
        System.out.println("ß upperCase: " + "ß".toUpperCase() + " - String::toUpperCase()");
        System.out.println("ß upperCase: " + Character.toUpperCase('ß') + " - Character.toUpperCase()");
    }
}
