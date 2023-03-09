import java.io.*;
import java.util.Scanner;

public class TP01Q09 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File arquivo = new File("arquivo.txt");
        FileWriter escritor = new FileWriter(arquivo);
        String word = sc.nextLine();
        do {
            word = sc.nextLine();
            escritor.write(word + "\n");
        } while (sc.hasNext());
        sc.close();
        escritor.close();
        printFile(arquivo);
    }

    public static void printFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        printLinesBackwards(reader);
        reader.close();
    }

    private static void printLinesBackwards(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            printLinesBackwards(reader);
            System.out.println(line);
        }
    }
}
