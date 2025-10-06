# TDE: Árvore 2-3-4
## Implementação de uma Árvore Multiway em Java

Este repositório contém a implementação de uma **Árvore 2-3-4**, desenvolvida como Trabalho Discente Efetivo (TDE) para a disciplina de **Resolução de Problemas Estruturados em Computação**.

---

## Sobre o Projeto

O objetivo deste trabalho é aplicar os conceitos de estruturas de dados avançadas através da implementação de uma árvore multiway. A estrutura escolhida foi a **Árvore 2-3-4**, um tipo de Árvore B auto-balanceada que garante eficiência em operações de busca, inserção e remoção.

### O que é uma Árvore 2-3-4?

A Árvore 2-3-4 é uma árvore de busca balanceada onde cada nó pode ter as seguintes características:
- **2-nó**: 1 chave e 2 filhos.
- **3-nó**: 2 chaves e 3 filhos.
- **4-nó**: 3 chaves e 4 filhos.

Graças a essas regras, a árvore se mantém perfeitamente balanceada (todas as folhas ficam no mesmo nível), o que garante um desempenho de complexidade **O(log n)** para as principais operações. A principal característica de sua implementação é a **divisão preventiva**: sempre que o algoritmo encontra um 4-nó (cheio) durante uma inserção, ele o divide, simplificando o processo e evitando rebalanceamentos complexos.



---

##  Estrutura do Código

Os arquivos principais do projeto estão localizados dentro da pasta `TDE2/`.

* **`TDE2/Arvore234.java`**: Contém a lógica principal da árvore, incluindo os métodos de inserção, remoção, busca e as operações auxiliares de `dividirFilho` (split) e `fundir` (merge).
* **`TDE2/No234.java`**: Define a estrutura do nó da árvore, responsável por armazenar as chaves e as referências aos nós filhos.
* **`TDE2/Main.java`** (Opcional): Classe utilizada para instanciar a árvore e testar suas funcionalidades.

---

##  Integrantes

* Anna Navarro
* Ana Carolia Afonso Meiado
* Ana Carolina Curi De Sales
* Pedro Joslin Cavalli
