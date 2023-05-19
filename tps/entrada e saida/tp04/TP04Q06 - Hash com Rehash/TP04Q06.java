import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

public class TP04Q06 {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis();
        Locale.setDefault(Locale.US);
        MyIO.setCharset("UTF-8");
        String word = MyIO.readLine();
        Hash personagens = new Hash();
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
        Arq.openWriteClose("matricula_hashRehash.txt", "UTF-8", conteudo);
    }

    public static String getFileData(String word) {
        BufferedReader br = null;
        FileReader fr = null;
        String fileData = "";
        try {
            fr = new FileReader(word.substring(1));
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

class Hash {
    Personagem tabela[];
    int m;
    final Personagem NULO = null;
    int comp = 0;

    public Hash() {
        this(25);
    }

    public Hash(int m) {
        this.m = m;
        this.tabela = new Personagem[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = NULO;
        }
    }

    public int h(Personagem elemento) {
        return elemento.getAltura() % m;
    }

    public int reh(Personagem elemento) {
        return (elemento.getAltura() + 1)% m;
     }
  

    public boolean inserir(Personagem elemento) {
        boolean resp = false;
        if (elemento != NULO) {
            comp++;
            int pos = h(elemento);
            if (tabela[pos] == NULO) {
                comp++;
                tabela[pos] = elemento;
                resp = true;
            } else {
                comp++;
                pos = reh(elemento);
                if(tabela[pos] == NULO) {
                    comp++;
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        System.out.print(elemento);
        boolean resp = false;
        for (int i = 0; i < m; i++) {
            if (tabela[i] != null && tabela[i].getNome().equals(elemento)) {
                resp = true;
                i = m;
                comp++;
            }
        }
        return resp;
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
