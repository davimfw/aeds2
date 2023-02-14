public class TP01Q03 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            ciframento(word);
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

    public static void ciframento(String word) {
        String cifra = "";
        for (int i = 0; i < word.length(); i++) {
            int letter = (word.charAt(i) + 3);
            cifra += (char) letter;
        }
        MyIO.println(cifra);
    }
}
