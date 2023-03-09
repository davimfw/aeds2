public class TP01Q14 {
    public static void main(String[] args) {
        String word = MyIO.readLine();
        do {
            algebra(word);
            word = MyIO.readLine();
        } while (word.length() > 1);
    }

    public static void algebra(String word) {
        String newWord = word.replaceAll(" ", "");
        String quant = newWord.charAt(0) + "";
        newWord = replaceLetters(newWord, Integer.parseInt(quant));

        MyIO.println(test(newWord));
    }

    public static char test(String word) {
        String expression;
        String result = "";
        while (word.length() >= 4) {
            expression = word.substring(word.lastIndexOf('(') - 2);
            if (expression.charAt(1) == 'd' || expression.charAt(1) == 't') {
                expression = word.substring(word.lastIndexOf('(') - 3);
            }
            if (expression.length() < 1)
                expression = word;
            if (expression.charAt(2) == 'd') {
                if (!expression.contains(")")) {
                    result = and(expression + result);
                } else {
                    result += and(expression);
                }
                word = word.substring(0, word.lastIndexOf(expression));
            } else if (expression.charAt(0) == 'o') {
                if (!expression.contains(")")) {
                    result = or(expression + result);
                } else {
                    result += or(expression);
                }
                word = word.substring(0, word.lastIndexOf(expression));
            } else if (expression.charAt(2) == 't') {
                if (!expression.contains(")")) {
                    result = not(expression + result);
                } else {
                    result += not(expression);
                }
                word = word.substring(0, word.lastIndexOf(expression));
            }
            if (!word.contains(")")) {
                word += result;
            }
        }

        return word.charAt(0);
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
        return newWord.substring(quant + 1);
    }
}
