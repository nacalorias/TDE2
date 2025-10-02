
public class Arvore234 {
    private No234 raiz;


    public Arvore234() {
        raiz = null;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new No234(true);
            raiz.inserirChaveNo(valor);
            return;
        }


        if (raiz.getNumChaves() == 3) {
            No234 novaRaiz = new No234(false);
            novaRaiz.setFilho(0, raiz);
            dividirFilho(novaRaiz, 0, raiz);
            raiz = novaRaiz;
        }

        inserirNaoCheio(raiz, valor);
    }


    private void dividirFilho(No234 pai, int indice, No234 cheio) {
        No234 novoNo = new No234(cheio.isFolha());
        novoNo.setNumChaves(1);

        novoNo.setChave(0, cheio.getChave(2));

        if (!cheio.isFolha()) {
            for (int j = 0; j < 2; j++) {
                novoNo.setFilho(j, cheio.getFilho(j + 2));
            }
        }

        cheio.setNumChaves(1);

        for (int j = pai.getNumChaves(); j > indice; j--) {
            pai.setFilho(j + 1, pai.getFilho(j));
            pai.setChave(j, pai.getChave(j - 1));
        }

        pai.setFilho(indice + 1, novoNo);
        pai.setChave(indice, cheio.getChave(1));
        pai.setNumChaves(pai.getNumChaves() + 1);
    }


    private void inserirNaoCheio(No234 no, int valor) {
        int i = no.getNumChaves() - 1;

        if (no.isFolha()) {
            while (i >= 0 && valor < no.getChave(i)) {
                no.setChave(i + 1, no.getChave(i));
                i--;
            }
            no.setChave(i + 1, valor);
            no.setNumChaves(no.getNumChaves() + 1);
        } else {
            while (i >= 0 && valor < no.getChave(i)) {
                i--;
            }
            i++;
            if (no.getFilho(i).getNumChaves() == 3) {
                dividirFilho(no, i, no.getFilho(i));
                if (valor > no.getChave(i)) {
                    i++;
                }
            }
            inserirNaoCheio(no.getFilho(i), valor);
        }
    }


    public boolean buscar(int valor) {
        if (estaVazia()) {
            System.out.println("A árvore está vazia.");
            return false;
        }
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(No234 no, int valor) {
        int i = 0;
        while (i < no.getNumChaves() && valor > no.getChave(i)) {
            i++;
        }
        if (i < no.getNumChaves() && valor == no.getChave(i)) {
            return true;
        }
        if (no.isFolha()) {
            return false;
        }
        return buscarRec(no.getFilho(i), valor);
    }


    public void imprimir() {
        if (estaVazia()) {
            System.out.println("A árvore está vazia.");
        } else {
            imprimirRec(raiz, 0);
        }
    }

    private void imprimirRec(No234 no, int nivel) {
        for (int i = 0; i < no.getNumChaves(); i++) {
            if (!no.isFolha()) {
                imprimirRec(no.getFilho(i), nivel + 1);
            }
            System.out.println("Nível " + nivel + ": " + no.getChave(i));
        }
        if (!no.isFolha()) {
            imprimirRec(no.getFilho(no.getNumChaves()), nivel + 1);
        }
    }


    public void remover(int valor) {
        if (estaVazia()) {
            System.out.println("Árvore vazia. Nada a remover.");
            return;
        }

        removerRec(raiz, valor);

        if (raiz.getNumChaves() == 0) {
            raiz = raiz.isFolha() ? null : raiz.getFilho(0);
        }
    }

    private void removerRec(No234 no, int valor) {
        int i = 0;
        while (i < no.getNumChaves() && valor > no.getChave(i)) {
            i++;
        }

        if (i < no.getNumChaves() && no.getChave(i) == valor) {
            if (no.isFolha()) {
                no.removerChaveNo(i);
            } else {
                removerInterno(no, i);
            }
        } else {
            if (no.isFolha()) {
                System.out.println("Valor " + valor + " não encontrado.");
                return;
            }

            boolean ultimoFilho = (i == no.getNumChaves());

            if (no.getFilho(i).getNumChaves() == 1) {
                preencher(no, i);
            }

            if (ultimoFilho && i > no.getNumChaves()) {
                removerRec(no.getFilho(i - 1), valor);
            } else {
                removerRec(no.getFilho(i), valor);
            }
        }
    }

    private void removerInterno(No234 no, int i) {
        int valor = no.getChave(i);
        No234 filhoEsq = no.getFilho(i);
        No234 filhoDir = no.getFilho(i + 1);

        if (filhoEsq.getNumChaves() >= 2) {
            int predecessor = getMaior(filhoEsq);
            no.setChave(i, predecessor);
            removerRec(filhoEsq, predecessor);
        } else if (filhoDir.getNumChaves() >= 2) {
            int sucessor = getMenor(filhoDir);
            no.setChave(i, sucessor);
            removerRec(filhoDir, sucessor);
        } else {
            fundir(no, i);
            removerRec(filhoEsq, valor);
        }
    }

    private int getMaior(No234 no) {
        while (!no.isFolha()) {
            no = no.getFilho(no.getNumChaves());
        }
        return no.getChave(no.getNumChaves() - 1);
    }

    private int getMenor(No234 no) {
        while (!no.isFolha()) {
            no = no.getFilho(0);
        }
        return no.getChave(0);
    }

    private void preencher(No234 no, int i) {
        if (i != 0 && no.getFilho(i - 1).getNumChaves() >= 2) {
            pegarDoAnterior(no, i);
        } else if (i != no.getNumChaves() && no.getFilho(i + 1).getNumChaves() >= 2) {
            pegarDoProximo(no, i);
        } else {
            if (i != no.getNumChaves()) {
                fundir(no, i);
            } else {
                fundir(no, i - 1);
            }
        }
    }

    private void pegarDoAnterior(No234 no, int i) {
        No234 filho = no.getFilho(i);
        No234 irmao = no.getFilho(i - 1);

        for (int j = filho.getNumChaves() - 1; j >= 0; j--) {
            filho.setChave(j + 1, filho.getChave(j));
        }

        if (!filho.isFolha()) {
            for (int j = filho.getNumChaves(); j >= 0; j--) {
                filho.setFilho(j + 1, filho.getFilho(j));
            }
        }

        filho.setChave(0, no.getChave(i - 1));

        if (!filho.isFolha()) {
            filho.setFilho(0, irmao.getFilho(irmao.getNumChaves()));
        }

        no.setChave(i - 1, irmao.getChave(irmao.getNumChaves() - 1));

        filho.setNumChaves(filho.getNumChaves() + 1);
        irmao.setNumChaves(irmao.getNumChaves() - 1);
    }

    private void pegarDoProximo(No234 no, int i) {
        No234 filho = no.getFilho(i);
        No234 irmao = no.getFilho(i + 1);

        filho.setChave(filho.getNumChaves(), no.getChave(i));

        if (!filho.isFolha()) {
            filho.setFilho(filho.getNumChaves() + 1, irmao.getFilho(0));
        }

        no.setChave(i, irmao.getChave(0));

        for (int j = 1; j < irmao.getNumChaves(); j++) {
            irmao.setChave(j - 1, irmao.getChave(j));
        }

        if (!irmao.isFolha()) {
            for (int j = 1; j <= irmao.getNumChaves(); j++) {
                irmao.setFilho(j - 1, irmao.getFilho(j));
            }
        }

        filho.setNumChaves(filho.getNumChaves() + 1);
        irmao.setNumChaves(irmao.getNumChaves() - 1);
    }

    private void fundir(No234 no, int i) {
        No234 filho = no.getFilho(i);
        No234 irmao = no.getFilho(i + 1);

        filho.setChave(1, no.getChave(i));
        filho.setChave(2, irmao.getChave(0));

        if (!filho.isFolha()) {
            filho.setFilho(2, irmao.getFilho(0));
            filho.setFilho(3, irmao.getFilho(1));
        }

        for (int j = i + 1; j < no.getNumChaves(); j++) {
            no.setChave(j - 1, no.getChave(j));
            no.setFilho(j, no.getFilho(j + 1));
        }

        filho.setNumChaves(3);
        no.setNumChaves(no.getNumChaves() - 1);
    }
}
