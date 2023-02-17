public class TP01Q05 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            algebra(word);
            word = MyIO.readLine();
        } while (!isFim(word));
    }

    public static void algebra(String word) {
        String newWord = word.replaceAll(" ", "");
        String quant = newWord.charAt(0) + "";
        newWord = replaceLetters(newWord, Integer.parseInt(quant));

        MyIO.println(test(newWord));
    }

    public static String test(String word) {
        String newWord = "";

        while (word.length() != 1) {
            String result = "";
            newWord = word.substring(word.lastIndexOf('(') - 1);
            if (newWord.charAt(0) == 'D') {
                result = and(newWord);
            } else if (newWord.charAt(0) == 'R') {
                result = or(newWord);
            } else if (newWord.charAt(0) == 'T') {
                result = not(newWord);
            }
            word = word.replace(newWord, result);
            MyIO.println(word);
        }

        return newWord == "1" ? "SIM" : "NAO";
    }

    public static String and(String word) {
        return !word.contains("0") ? "1" : "0";
    }

    public static String or(String word) {
        return word.contains("1") ? "1" : "0";
    }

    public static String not(String word) {
        return word.contains("0") ? "1" : "0";
    }

    public static String replaceLetters(String word, int quant) {
        String newWord = "";
        for (int i = 0; i < quant; i++) {
            char charToReplace = (char) (65 + i);
            if (newWord == "") {
                newWord = word.replaceAll(charToReplace + "", word.charAt(i + 1) + "");
            } else {
                newWord = newWord.replaceAll(charToReplace + "", newWord.charAt(i + 1) + "");
            }
        }
        return newWord;
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}
