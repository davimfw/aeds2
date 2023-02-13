import java.util.Scanner;

public class TP01Q03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        // String word = MyIO.readLine();
        do {
            ciframento(word);
            word = in.nextLine();
            // word = MyIO.readLine();
        } while(!isFim(word));
        in.close();
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

    public static void ciframento(String word) {
        String cifra = "";
        char letter;
        for(int i = 0; i < word.length(); i++) {
            letter =  (char) (word.charAt(i) + 3);
            cifra += letter;
        }
        System.out.println(cifra);
    }
}
