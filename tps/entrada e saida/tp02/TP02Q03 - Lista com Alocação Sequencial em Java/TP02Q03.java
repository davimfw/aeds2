import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

public class TP02Q03 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        MyIO.setCharset("UTF-8");
        String word = MyIO.readLine();
        Lista personagens = new Lista();
        do {
            personagens.inserirFim(new Personagem(getFileData(word)));
            word = MyIO.readLine();
        } while (!isFim(word));
        int mov = Integer.parseInt(MyIO.readLine());
        String removidos[] = new String[mov];
        int quantRemovidos = 0;
        for (int i = 0; i < mov; i++) {
            String expression = MyIO.readLine();
            switch (expression.substring(0, 2)) {
                case "II":
                    personagens.inserirInicio(new Personagem(getFileData(expression.substring(3))));
                    break;
                case "I*":
                    String pos = expression.substring(3, expression.lastIndexOf(" "));
                    personagens.inserir(new Personagem(getFileData(expression.substring(4 + pos.length()))),
                            Integer.parseInt(pos));
                    break;
                case "IF":
                    personagens.inserirFim(new Personagem(getFileData(expression.substring(3))));
                    break;
                case "RI":
                    removidos[quantRemovidos++] = personagens.removerInicio().getNome();
                    break;
                case "R*":
                    int posi = Integer.parseInt(expression.substring(3));
                    removidos[quantRemovidos++] = personagens.remover(posi).getNome();
                    break;
                case "RF":
                    removidos[quantRemovidos++] = personagens.removerFim().getNome();
                    break;

            }
        }

        /**
         * @TODO: imprimir
         */

        for (int i = 0; i < quantRemovidos; i++) {
            System.out.println("(R) " + removidos[i]);
        }

        for (int i = 0; i < personagens.n; i++) {
            System.out.print("[" + i + "]  ");
            personagens.array[i].imprimir();
        }

    }

    public static String getFileData(String word) {
        BufferedReader br = null;
        FileReader fr = null;
        String fileData = "";
        try {
            fr = new FileReader(word);
            br = new BufferedReader(fr);

            // Ler cada linha do arquivo
            fileData = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fechar o BufferedReader e FileReader
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileData;
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }

}

class Lista {
    int n;
    Personagem[] array;

    Lista() {
        this(100);
    }

    Lista(int tamanho) {
        array = new Personagem[tamanho];
        n = 0;
    }

    public void inserirInicio(Personagem personagem) {
        if (n < array.length) {
            Personagem temp = new Personagem();
            for (int i = n; i > 0; i--) {
                temp = array[i - 1].clone();
                array[i] = temp;
            }
            array[0] = personagem.clone();
            n++;
        }
    }

    public void inserir(Personagem personagem, int pos) {
        if (pos <= n) {
            for (int i = n; i > pos; i--) {
                array[i] = array[i-1].clone();
            }
            n++;
            array[pos] = personagem.clone();
        }
    }

    public void inserirFim(Personagem personagem) {
        array[n++] = personagem.clone();
    }

    public Personagem removerInicio() {
        Personagem temp = array[0].clone();
        for (int i = 1; i < n; i++) {
            array[i - 1] = array[i].clone();
        }
        n--;
        return temp;
    }

    public Personagem remover(int pos) {
        Personagem temp = array[pos].clone();
        for (int i = pos; i < n - 1; i++) {
            array[i] = array[i + 1].clone();
        }
        n--;
        return temp;
    }

    public Personagem removerFim() {
        Personagem temp = array[n-1].clone();
        n--;
        return temp;
    }
}

class Personagem {
    private String nome;
    private int altura;
    private Double peso;
    private String corDoCabelo;
    private String codDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeWorld;

    public Personagem() {
        this.nome = "";
        this.altura = 0;
        this.peso = 0.0;
        this.corDoCabelo = "";
        this.codDaPele = "";
        this.corDosOlhos = "";
        this.anoNascimento = "";
        this.genero = "";
        this.homeWorld = "";
    }

    public Personagem(String fileData) {
        ler(fileData);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPeso() {
        return peso;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDoCabelo() {
        return corDoCabelo;
    }

    public void setCodDaPele(String codDaPele) {
        this.codDaPele = codDaPele;
    }

    public String getCodDaPele() {
        return codDaPele;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }

    public String getCorDosOlhos() {
        return corDosOlhos;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public Personagem clone() {
        Personagem temp = new Personagem();
        temp.nome = this.nome;
        temp.altura = this.altura;
        temp.peso = this.peso;
        temp.corDoCabelo = this.corDoCabelo;
        temp.codDaPele = this.codDaPele;
        temp.corDosOlhos = this.corDosOlhos;
        temp.anoNascimento = this.anoNascimento;
        temp.genero = this.genero;
        temp.homeWorld = this.homeWorld;
        return temp;
    }

    public void imprimir() {
        DecimalFormat decimalFormat = new DecimalFormat("#.################");
        MyIO.println("## " + this.nome + " ## " + this.altura + " ## " + decimalFormat.format(this.peso) + " ## "
                + this.corDoCabelo + " ## "
                + this.codDaPele + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero
                + " ## " + this.homeWorld + " ## ");
    }

    public void ler(String fileData) {
        String temp, value;
        // readNome
        fileData = fileData.replace("{", "");
        temp = fileData.replaceFirst("'name': '", "");
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("height': '", "");
        setNome(value);
        // readAltura
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("mass': '", "");
        if (value.equals("unknown"))
            setAltura(0);
        else
            setAltura(Integer.parseInt(value));
        // readPeso
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("hair_color': '", "");
        if (value.equals("unknown"))
            setPeso(0.0);
        else
            setPeso(Double.parseDouble(value.replaceAll(",", ".")));
        // readCorDoCabelo
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("skin_color': '", "");
        setCorDoCabelo(value);
        // readCodDaPele
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("eye_color': '", "");
        setCodDaPele(value);
        // readCorDosOlhos
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("birth_year': '", "");
        setCorDosOlhos(value);
        // readAnoNascimento
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("gender': '", "");
        setAnoNascimento(value);
        // readGender
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        temp = temp.replaceFirst("homeworld': '", "");
        setGenero(value);
        // readHomeWorld
        value = temp.substring(0, temp.indexOf("'"));
        temp = temp.replaceFirst(value + "', '", "");
        setHomeWorld(value);
    }
}