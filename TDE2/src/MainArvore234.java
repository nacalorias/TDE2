import java.util.Scanner;

public class MainArvore234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore234 arvore = new Arvore234();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Buscar elemento");
            System.out.println("3 - Remover elemento");
            System.out.println("4 - Imprimir árvore");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.print("Digite um número para inserir: ");
                int valor = sc.nextInt();
                arvore.inserir(valor);
                System.out.println("Número inserido: " + valor);
            } else if (opcao == 2) {
                System.out.print("Digite o número para buscar: ");
                int valor = sc.nextInt();
                if (arvore.buscar(valor)) {
                    System.out.println("Elemento " + valor + " encontrado.");
                } else {
                    System.out.println("Elemento " + valor + " não encontrado.");
                }
            } else if (opcao == 3) {
                System.out.print("Digite o número para remover: ");
                int valor = sc.nextInt();
                arvore.remover(valor);
            } else if (opcao == 4) {
                arvore.imprimir();
            } else if (opcao == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
