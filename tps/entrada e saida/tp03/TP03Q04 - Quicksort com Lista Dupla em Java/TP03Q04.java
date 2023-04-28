import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

public class TP03Q04 {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis();
        Locale.setDefault(Locale.US);
        MyIO.setCharset("UTF-8");
        String word = MyIO.readLine();
        Lista personagens = new Lista();
        do {
            personagens.inserirFim(new Personagem(getFileData(word)));
            word = MyIO.readLine();
        } while (!isFim(word));

        personagens.sort();
        personagens.mostrar();
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        String conteudo = "matricula 1321401 \t número de comparações " + Lista.comp
                + "\t número de movimetações " + Lista.mov + "\t Tempo de execução " + tempo
                + " milisegundos";
        Arq.openWriteClose("matricula_quicksort2.txt", "UTF-8", conteudo);
    }

    public static String getFileData(String word) {
        BufferedReader br = null;
        FileReader fr = null;
        String fileData = "";
        try {
            fr = new FileReader(word.substring(0));
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

class Celula {
    public Personagem elemento;
    public Celula ant;
    public Celula prox;

    /**
     * Construtor da classe.
     */
    public Celula() {
        this(null);
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento int inserido na celula.
     */
    public Celula(Personagem elemento) {
        this.elemento = elemento;
        this.ant = this.prox = null;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;
    public static int mov = 0;
    public static int comp = 0;

    /**
     * Construtor da classe que cria uma lista dupla sem elementos (somente no
     * cabeca).
     */
    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void sort() {
        quicksort(primeiro.prox, ultimo, 0, tamanho() - 1);
    }

    public boolean compareMenor(Personagem p1, Personagem p2) {
        if (p1.getCorDoCabelo().charAt(0) < p2.getCorDoCabelo().charAt(0) ||
                (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) < p2.getCorDoCabelo().charAt(1))
                ||
                (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) == p2.getCorDoCabelo().charAt(1)
                        && p1.getCorDoCabelo().charAt(2) < p2.getCorDoCabelo().charAt(2))
                || (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) == p2.getCorDoCabelo().charAt(1)
                        && p1.getCorDoCabelo().charAt(2) == p2.getCorDoCabelo().charAt(2) &&
                        p1.getNome().compareTo(p2.getNome()) < 0)) {
            return true;
        }
        return false;
    }

    public boolean compareMaior(Personagem p1, Personagem p2) {
        if (p1.getCorDoCabelo().charAt(0) > p2.getCorDoCabelo().charAt(0) ||
                (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) > p2.getCorDoCabelo().charAt(1))
                ||
                (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) == p2.getCorDoCabelo().charAt(1)
                        && p1.getCorDoCabelo().charAt(2) > p2.getCorDoCabelo().charAt(2))
                || (p1.getCorDoCabelo().charAt(0) == p2.getCorDoCabelo().charAt(0)
                        && p1.getCorDoCabelo().charAt(1) == p2.getCorDoCabelo().charAt(1)
                        && p1.getCorDoCabelo().charAt(2) == p2.getCorDoCabelo().charAt(2) &&
                        p1.getNome().compareTo(p2.getNome()) > 0)) {
            return true;
        }
        return false;
    }

    public void quicksort(Celula inicio, Celula fim, int esq, int dir) {
        Celula in = inicio;
        Celula fi = fim;
        int i = esq, j = dir;
        Personagem pivo = fim.elemento;
        while (i <= j) {
            comp++;
            while (compareMenor(in.elemento, pivo)) {
                comp++;
                in = in.prox;
                i++;
            }
            while (compareMaior(fi.elemento, pivo)) {
                comp++;
                fi = fi.ant;
                j--;
            }
            if (i <= j) {
                comp++;
                Personagem tmp = in.elemento.clone();
                in.elemento = fi.elemento.clone();
                fi.elemento = tmp.clone();
                in = in.prox;
                fi = fi.ant;
                i++;
                j--;
                mov++;
            }
        }
        comp += 2;
        if (esq < j)
            quicksort(inicio, fi, esq, j);
        if (i < dir)
            quicksort(in, fim, i, dir);
    }

    public void swap(Celula i, Celula j) {
        Personagem tmp = i.elemento.clone();
        i.elemento = j.elemento.clone();
        j.elemento = tmp.clone();
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param x int elemento a ser inserido.
     */
    public void inserirFim(Personagem x) {
        ultimo.prox = new Celula(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            i.elemento.imprimir();
        }
    }

    /**
     * Mostra os elementos da lista de forma invertida
     * e separados por espacos.
     */
    public void mostrarInverso() {
        System.out.print("[ ");
        for (Celula i = ultimo; i != primeiro; i = i.ant) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("] "); // Termina de mostrar.
    }

    /**
     * Procura um elemento e retorna se ele existe.
     * 
     * @param x Elemento a pesquisar.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Personagem x) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    public Personagem pesquisar(int pos) {
        Celula tmp = this.primeiro;
        for (int i = 0; i < pos; i++, tmp = tmp.prox)
            ;
        return tmp.elemento;
    }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * 
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
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
        MyIO.println(" ## " + this.nome + " ## " + this.altura + " ## " + decimalFormat.format(this.peso) + " ## "
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