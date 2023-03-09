public class TP01Q13 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            MyIO.println(ciframento(word, 0, ""));
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

    public static String ciframento(String word, int position, String cifra) {
        if (position < word.length()) {
            int letter = (word.charAt(position) + 3);
            cifra += (char) letter;
            return "" + ciframento(word, ++position, cifra);
        } else {
            return cifra;
        }
    }

}
