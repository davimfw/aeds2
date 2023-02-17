public class TP01Q06 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            is(word.toLowerCase());
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static void is(String word) {
        MyIO.print(vogal(word) ? "SIM " : "NAO ");
        MyIO.print(consoante(word) ? "SIM " : "NAO ");
        MyIO.print(isInteger(word) ? "SIM " : "NAO ");
        MyIO.print(isDouble(word) ? "SIM\n" : "NAO\n");
    }

    public static boolean vogal(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u'))
                return false;
        }

        return true;
    }

    public static boolean consoante(String word) {
        if (vogal(word))
            return false;
        for (int i = 0; i < word.length(); i++) {
            if (!(word.charAt(i) >= 'a' && word.charAt(i) <= 'z'))
                return false;
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u')
                return false;
        }
        return true;
    }

    public static boolean isInteger(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!(word.charAt(i) < '0' && word.charAt(i) > '9'))
                return false;
        }
        return true;
    }

    public static boolean isDouble(String word) {
        int comma = 0;
        char characterer = ' ';

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ',' || word.charAt(i) == '.') {
                characterer = word.charAt(i);
                comma++;
            }
            if (comma > 1)
                return false;
        }

        return isInteger(word.replaceFirst("" + characterer, ""));
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}