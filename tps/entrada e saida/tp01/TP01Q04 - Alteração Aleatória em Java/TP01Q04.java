import java.util.Random;

public class TP01Q04 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            random(word);
            word = MyIO.readLine();
        } while(!isFim(word));
    }

    public static void random(String word) {
        Random generator = new Random();
        generator.setSeed(4);
        char sorted = (char) (Math.abs(generator.nextInt()) % 26);
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}