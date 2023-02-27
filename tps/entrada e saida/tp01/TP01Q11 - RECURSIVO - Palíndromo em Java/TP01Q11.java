public class TP01Q11 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            System.out.println(isPalindromo(word, 0) ? "SIM" : "NAO");
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

    public static boolean isPalindromo(String word, int position) {
        if (position < word.length() / 2) {
            if (word.charAt(position) != word.charAt((word.length() - 1 - position))) {
                return false;
            } else {
                return true && isPalindromo(word, ++position);
            }
        }
        return true;
    }

}
