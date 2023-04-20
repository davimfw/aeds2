class TP03Q05 {
    public static void main(String[] args) {
        Matriz m1, m2, soma, m3, m4, multiplicao;

        m1 = new Matriz(MyIO.readInt("Digite o numero de linhas (M1): "),
                MyIO.readInt("Digite o numero de colunas (M1): "));
        m2 = new Matriz(MyIO.readInt("Digite o numero de linhas (M2): "),
                MyIO.readInt("Digite o numero de colunas (M2): "));
        m3 = new Matriz(MyIO.readInt("Digite o numero de linhas (M3): "),
                MyIO.readInt("Digite o numero de colunas (M3): "));
        m4 = new Matriz(MyIO.readInt("Digite o numero de linhas (M4): "),
                MyIO.readInt("Digite o numero de colunas (M4): "));

        m1.ler();
        m2.ler();
        m3.ler();
        m4.ler();

        // Somar as duas matrizes e salvar o resultado na matriz soma
        soma = m1.soma(m2); // verifique se eh possivel somar

        // Imprimir a matriz 1
        soma.print();

        // Multiplicar duas matrizes e salvar o resultado na matriz multiplicacao
        multiplicacao = m3.multiplicacao(m4); // verifique se eh possivel multiplicar

        // Imprimir a matriz 1
        multiplicacao.print();

    }
}

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        Matriz(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        // alocar a matriz com this.linha linhas e this.coluna colunas
    }

    public void iniciarMatriz() {
        Celula tmp = inicio;
        for (int i = 0; i < coluna; i++, tmp = tmp.dir) {
            tmp.dir = new Celula(0, null, null, tmp, null);
        }
        tmp = inicio;
        for (int i = 0; i < linha; i++, tmp = tmp.inf) {
            tmp.inf = new Celula(0, null, tmp, null, null);
        }

        iniciarMatrizMeio();
    }

    private iniciarMatrizMeio() {
        for(Celula tmp=inicio.dir; tmp.dir != null; tmp = tmp.dir) {
            tmp.inf = new Celula(0, null, tmp, tmp.esq.inf, null);
        }
    }

    public Matriz soma (Matriz m) {
       Matriz resp = null;
 
       if(this.linha == m.linha && this.coluna == m.coluna){
          resp = new Matriz(this.linha, this.coluna);
          for(){
             for(){
                //sendo c (pont em resp), a (em this) e b (em m)
                c.elemento = a.elemento + b.elemento;
             }
          }
          //...
       }
 
       return resp;
    }

    public Matriz multiplicacao (Matriz m) {
       Matriz resp = null;
 
       if(){
          //...
       }
 
       return resp;
    }

    public boolean isQuadrada(){
       boolean (this.linha == this.coluna);
    }

    public void mostrarDiagonalPrincipal() {
        if (isQuadrada() == true) {

        }
    }

    public void mostrarDiagonalSecundaria() {
        if (isQuadrada() == true) {
        }
    }
}
