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
        while (word.length() > 4) {
            String expression;
            try {
                System.out.println("nao deu ruim try antes " + word);
                expression = word.substring(word.lastIndexOf('(') - 3, word.lastIndexOf(')') - 1);
                System.out.println("nao deu ruim try pos " + expression);
            } catch(Error e) {
                System.out.println("nao deu ruim catch " + word);
                expression = word.substring(word.lastIndexOf('(') - 3);
            }
            if(expression.length() < 1) expression = word;
            System.out.println("nao deu ruim expression " + expression);
            String result = "";
            if (expression.charAt(2) == 'd') {
                System.out.println("nao deu ruim expression " + expression);
                result = and(expression);
                word = word.replace(expression, result);
            } else if (expression.charAt(2) == 'o') {
                result = or(expression);
                word = word.replace(expression, result);
            } else if (expression.charAt(2) == 't') {
                result = not(expression);
                word = word.replace(expression, result);
            }
            System.out.println("nao deu ruim fora " + word);
        }

        return word;
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

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}
