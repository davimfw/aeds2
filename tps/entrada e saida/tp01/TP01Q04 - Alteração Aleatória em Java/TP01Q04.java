import java.util.Random;

public class TP01Q04 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        Random generator = new Random();
        generator.setSeed(4);
        do {
            char letter1 = (char) ('a' + (Math.abs(generator.nextInt()) % 26));
            char letter2 = (char) ('a' + (Math.abs(generator.nextInt()) % 26));
            random(word, letter1, letter2);
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static void random(String word, char letter1, char letter2) {
        MyIO.println(word.replaceAll(letter1 + "", letter2 + ""));
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}