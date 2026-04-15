import java.util.Random;
import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // 1) Perguntar ao jogador humano qual é o símbolo que ele quer usar
        char simboloHumano;
        char simboloMaquina;
        while (true) {
            System.out.print("Escolha seu símbolo (X ou O): ");
            String input = sc.next().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                simboloHumano = input.charAt(0);
                simboloMaquina = (simboloHumano == 'X') ? 'O' : 'X';
                break;
            } else {
                System.out.println("Símbolo inválido. Por favor, escolha 'X' ou 'O'.");
            }
        }

        Jogador jogador1 = new Jogador("Jogador 1", simboloHumano);
        Jogador jogador2 = new Jogador("Jogador 2", simboloMaquina);
        Tabuleiro tabuleiro = new Tabuleiro();

        Jogador atual = jogador1;
        boolean jogoAcabou = false;

        while (!jogoAcabou) {
            // 3) O programa deve imprimir o nome do jogador antes de mostrar o tabuleiro
            System.out.println("\nTurno de " + atual.getNome() + " (" + atual.getSimbolo() + ")");
            tabuleiro.exibir();

            int linha, coluna;
            boolean jogadaValida = false;

            if (atual == jogador1) {
                // 2) A cada partida, o programa deve perguntar ao jogador humano em qual casa ele quer colocar seu símbolo
                while (!jogadaValida) {
                    System.out.print("Digite a linha (0-2): ");
                    linha = sc.nextInt();
                    System.out.print("Digite a coluna (0-2): ");
                    coluna = sc.nextInt();

                    if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3) {
                        if (tabuleiro.fazerJogada(linha, coluna, atual.getSimbolo())) {
                            jogadaValida = true;
                        } else {
                            System.out.println("Posição ocupada ou inválida! Tente novamente.");
                        }
                    } else {
                        System.out.println("Coordenadas fora do limite (0-2)! Tente novamente.");
                    }
                }
            } else { // Jogada da máquina
                while (!jogadaValida) {
                    linha = random.nextInt(3);
                    coluna = random.nextInt(3);
                    if (tabuleiro.fazerJogada(linha, coluna, atual.getSimbolo())) {
                        System.out.println("Jogador 2 escolheu: " + linha + ", " + coluna);
                        jogadaValida = true;
                    }
                }
            }

            // Verifica vitória
            if (tabuleiro.verificarVitoria(atual.getSimbolo())) {
                tabuleiro.exibir();
                // 4) O programa deverá ao final exibir uma destas mensagens
                if (atual.getNome().equals("Jogador 1")) {
                    System.out.println("O jogador 1 ganhou");
                } else {
                    System.out.println("O jogador 2 ganhou");
                }
                jogoAcabou = true;
            } else if (tabuleiro.cheio()) { // Verifica empate
                tabuleiro.exibir();
                // 4) O programa deverá ao final exibir uma destas mensagens
                System.out.println("O jogo terminou empatado.");
                jogoAcabou = true;
            } else {
                // Troca de jogador
                atual = (atual == jogador1) ? jogador2 : jogador1;
            }
        }

        // IMPORTANTE: O programa deverá terminar imprimindo a mensagem: "Criado por [SEU NOME]".
        System.out.println("Criado por Bianca Teixeira");
        sc.close();
    }
}
