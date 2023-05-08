import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

public class TP04Q02 {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis();
        Locale.setDefault(Locale.US);
        MyIO.setCharset("UTF-8");
        String word = MyIO.readLine();
        ArvoreArvore personagens = new ArvoreArvore();
        do {
            personagens.inserir(new Personagem(getFileData(word)));
            word = MyIO.readLine();
        } while (!isFim(word));

        word = MyIO.readLine();
        do {
            System.out.println(personagens.pesquisar(word) ? " SIM" : " NÃO");
            word = MyIO.readLine();
        } while (!isFim(word));
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        String conteudo = "matricula 1321401 \t número de comparações " + personagens.comp
                + "\t Tempo de execução " + tempo
                + " milisegundos";
        Arq.openWriteClose("matricula_arvoreArvore.txt", "UTF-8", conteudo);
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

class ArvoreArvore {
    private No raiz; // Raiz da arvore.
    public int comp = 0;

    /**
     * Construtor da classe.
     */
    public ArvoreArvore() {
        raiz = null;
        // 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13 e 14
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(12);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(13);
        inserir(14);
    }

    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if (i == null) {
            i = new No(x);
            comp++;
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
            comp++;
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
            comp++;
        }

        return i;
    }

    public void inserir(Personagem x) {
        inserir(x, raiz);
    }

    public void inserir(Personagem x, No i) {
        if (i == null) {
            comp++;
        } else if (x.getAltura() % 15 < i.elemento) {
            inserir(x, i.esq);
            comp++;
        } else if (x.getAltura() % 15 > i.elemento) {
            inserir(x, i.dir);
            comp++;
        } else {
            i.outro = inserir(x, i.outro);
        }
    }

    private No2 inserir(Personagem x, No2 i) {
        if (i == null) {
            i = new No2(x);
            comp++;
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(x, i.esq);
            comp++;
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(x, i.dir);
            comp++;
        }

        return i;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String nome) {
        System.out.print(nome + " raiz");
        return pesquisar(raiz, nome);
    }

    private boolean pesquisar(No no, String x) {
        boolean resp = false;
        if (no == null) {
            resp = false;
            comp++;
        } else {
            if (no.outro != null) {
                comp++;
                resp = pesquisarSegundaArvore(no.outro, x);
            }
            if (resp == false) {
                comp++;
                System.out.print(" esq");
                resp = pesquisar(no.esq, x);                
            }
            if (resp == false) {
                System.out.print(" dir");
                resp = pesquisar(no.dir, x);
                comp++;
            }
        }
        return resp;
    }

    private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;
        if (no == null) {
            // System.out.println(" é null");
            resp = false;
            comp++;
        } else if (x.compareTo(no.elemento.getNome()) == 0) {
            resp = true;
            comp++;
        } else {
            System.out.print(" ESQ");
            resp = pesquisarSegundaArvore(no.esq, x);
            System.out.print(" DIR");
            resp = pesquisarSegundaArvore(no.dir, x);
            comp++;
        }
        return resp;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    public void mostrar(No i) {
        if (i != null) {
            System.out.println("Numero: " + i.elemento);
            mostrar(i.esq);
            System.out.println("Numero: " + i.elemento);
            mostrar(i.outro);
            mostrar(i.dir);
        }
    }

    public void mostrar(No2 i) {
        if (i != null) {
            mostrar(i.esq);
            i.elemento.imprimir();
            mostrar(i.dir);
        }
    }

}

class No {
    public int elemento; // Conteudo do no.
    public No esq; // No da esquerda.
    public No dir; // No da direita.
    public No2 outro;

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No(int elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No da esquerda.
     * @param dir      No da direita.
     */
    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

class No2 {
    public Personagem elemento; // Conteudo do no.
    public No2 esq; // No da esquerda.
    public No2 dir; // No da direita.

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No2(Personagem elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No2 da esquerda.
     * @param dir      No2 da direita.
     */
    No2(Personagem elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
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