
public class TP01Q15 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            is(word.toLowerCase());
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static void is(String word) {
        MyIO.print(vogal(word, 0) ? "SIM " : "NAO ");
        MyIO.print(consoante(word, 0) ? "SIM " : "NAO ");
        MyIO.print(isInteger(word, 0) ? "SIM " : "NAO ");
        MyIO.print(isDouble(word, 0) ? "SIM\n" : "NAO\n");
    }

    public static boolean vogal(String word, int i) {
        if(i < word.length()) {
            if (!(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u'))
                return false;
            return true && vogal(word, ++i);
        }

        return true;
    }

    public static boolean consoante(String word, int i) {
        if (vogal(word, i))
            return false;
        if (i < word.length()) {
            if (!(word.charAt(i) >= 'a' && word.charAt(i) <= 'z'))
                return false;
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u')
                return false;
            return true && consoante(word, ++i);
        }
        return true;
    }

    public static boolean isInteger(String word, int i) {
        if (i < word.length()) {
            if (!(word.charAt(i) < '0' && word.charAt(i) > '9'))
                return false;
            return true && isInteger(word, ++i);
        }
        return true;
    }

    public static boolean isDouble(String word, int i) {
        int comma = 0;
        char characterer = ' ';

        if (i < word.length()) {
            if (word.charAt(i) == ',' || word.charAt(i) == '.') {
                characterer = word.charAt(i);
                comma++;
            }
            if (comma > 1)
                return false;
            return true && isDouble(word, ++i);
        }

        return isInteger(word.replaceFirst("" + characterer, ""), 0);
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}
