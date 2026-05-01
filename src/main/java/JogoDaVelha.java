import java.util.Random;
import java.util.Scanner;

public class JogoDaVelha {

    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private Scanner sc;

    public JogoDaVelha(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro, Scanner sc) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
        this.sc = sc;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        JogoDaVelha jogoDaVelha = new JogoDaVelha(jogador1, jogador2, tabuleiro, sc);
        jogoDaVelha.iniciar();

        sc.close();
    }

    public void iniciar() {
        Random random = new Random();

        Jogador atual = jogador1;
        boolean jogoAcabou = false;

        while (!jogoAcabou) {
            System.out.println("\nTurno de " + atual.getNome() + " (" + atual.getSimbolo() + ")");
            tabuleiro.exibir();

            int linha, coluna;
            boolean jogadaValida = false;

            if (atual == jogador1) {
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
            } else {
                while (!jogadaValida) {
                    linha = random.nextInt(3);
                    coluna = random.nextInt(3);
                    if (tabuleiro.fazerJogada(linha, coluna, atual.getSimbolo())) {
                        jogadaValida = true;
                    }
                }
            }

            if (tabuleiro.verificarVitoria(atual.getSimbolo())) {
                tabuleiro.exibir();
                if (atual.getNome().equals("Jogador 1")) {
                    System.out.println("O jogador 1 ganhou");
                } else {
                    System.out.println("O jogador 2 ganhou");
                }
                jogoAcabou = true;
            } else if (tabuleiro.cheio()) {
                tabuleiro.exibir();
                System.out.println("O jogo terminou empatado.");
                jogoAcabou = true;
            } else {
                atual = (atual == jogador1) ? jogador2 : jogador1;
            }
        }

        System.out.println("Criado por Bianca Teixeira");
    }
}