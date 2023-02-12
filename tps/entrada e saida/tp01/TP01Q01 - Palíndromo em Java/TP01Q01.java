// import java.util.Scanner;

class TP01Q01 {
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        String word = MyIO.readLine();
        do {
            MyIO.println(isPalindromo(word) ? "SIM" : "NAO");
            word = MyIO.readLine();
        } while(!isFim(word));
        // in.close();
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

    public static boolean isPalindromo(String word) {
        if (word.length() % 2 == 0) {
            for (int i = 0; i < word.length() / 2; i++) {
                if (word.charAt(i) != word.charAt((word.length() - 1 - i)))
                    return false;
            }
            return true;
        }
        return false;
    }
}