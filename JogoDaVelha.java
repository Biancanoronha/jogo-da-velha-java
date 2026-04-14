import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Jogador jogador1 = new Jogador("Jogador 1", 'X');
        Jogador jogador2 = new Jogador("Jogador 2", 'O');

        Tabuleiro tabuleiro = new Tabuleiro();

        Jogador atual = jogador1;
        boolean jogoAcabou = false;

        while (!jogoAcabou) {
            System.out.println(atual.getNome() + " (" + atual.getSimbolo() + "), sua vez:");
            tabuleiro.exibir();

            System.out.print("Linha (0-2): ");
            int linha = sc.nextInt();

            System.out.print("Coluna (0-2): ");
            int coluna = sc.nextInt();

            if (!tabuleiro.fazerJogada(linha, coluna, atual.getSimbolo())) {
                System.out.println("Posição ocupada! Tente novamente.");
                continue;
            }

            if (tabuleiro.verificarVitoria(atual.getSimbolo())) {
                tabuleiro.exibir();
                System.out.println(atual.getNome() + " venceu!");
                jogoAcabou = true;
            } else if (tabuleiro.cheio()) {
                tabuleiro.exibir();
                System.out.println("Empate!");
                jogoAcabou = true;
            } else {
                atual = (atual == jogador1) ? jogador2 : jogador1;
            }
        }

        sc.close();
    }
}
