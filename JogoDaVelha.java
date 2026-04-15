import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Escolha do símbolo
        System.out.print("Escolha seu símbolo (X ou O): ");
        char simboloHumano = sc.next().toUpperCase().charAt(0);

        char simboloMaquina;
        if (simboloHumano == 'X') {
            simboloMaquina = 'O';
        } else {
            simboloMaquina = 'X';
        }

        // Nomes alinhados com o enunciado (blindagem total)
        Jogador jogador1 = new Jogador("Jogador 1", simboloHumano);
        Jogador jogador2 = new Jogador("Jogador 2", simboloMaquina);

        Tabuleiro tabuleiro = new Tabuleiro();

        Jogador atual = jogador1;
        boolean jogoAcabou = false;

        while (!jogoAcabou) {

            System.out.println(atual.getNome() + " (" + atual.getSimbolo() + ")");
            tabuleiro.exibir();

            int linha, coluna;

            // Jogada do humano
            if (atual == jogador1) {
                System.out.print("Linha (0-2): ");
                linha = sc.nextInt();

                System.out.print("Coluna (0-2): ");
                coluna = sc.nextInt();
            } 
            // Jogada da máquina
            else {
                linha = (int)(Math.random() * 3);
                coluna = (int)(Math.random() * 3);
                System.out.println("Jogador 2 escolheu: " + linha + ", " + coluna);
            }

            if (!tabuleiro.fazerJogada(linha, coluna, atual.getSimbolo())) {
                System.out.println("Posição ocupada! Tente novamente.");
                continue;
            }

            // Vitória
            if (tabuleiro.verificarVitoria(atual.getSimbolo())) {
                tabuleiro.exibir();

                if (atual == jogador1) {
                    System.out.println("O jogador 1 ganhou");
                } else {
                    System.out.println("O jogador 2 ganhou");
                }

                jogoAcabou = true;
            } 
            // Empate
            else if (tabuleiro.cheio()) {
                tabuleiro.exibir();
                System.out.println("O jogo terminou empatado.");
                jogoAcabou = true;
            } 
            // Troca de jogador
            else {
                atual = (atual == jogador1) ? jogador2 : jogador1;
            }
        }

        // Obrigatório
        System.out.println("Criado por Bianca Teixeira");

        sc.close();
    }
}
