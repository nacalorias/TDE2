
public class No234 {
    private int[] chaves;
    private No234[] filhos;
    private int numChaves;
    private boolean folha;

    // Construtor
    public No234(boolean folha) {
        this.chaves = new int[3];      // até 3 valores
        this.filhos = new No234[4];    // até 4 filhos
        this.numChaves = 0;
        this.folha = folha;
    }


    public int getNumChaves() {
        return numChaves;
    }

    public void setNumChaves(int n) {
        this.numChaves = n;
    }

    public int getChave(int i) {
        return chaves[i];
    }

    public void setChave(int i, int valor) {
        chaves[i] = valor;
    }

    public No234 getFilho(int i) {
        return filhos[i];
    }

    public void setFilho(int i, No234 filho) {
        filhos[i] = filho;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }


    public void inserirChaveNo(int valor) {
        int i = numChaves - 1;
        while (i >= 0 && chaves[i] > valor) {
            chaves[i + 1] = chaves[i];
            i--;
        }
        chaves[i + 1] = valor;
        numChaves++;
    }


    public void removerChaveNo(int indice) {
        for (int i = indice; i < numChaves - 1; i++) {
            chaves[i] = chaves[i + 1];
        }
        numChaves--;
    }
}

