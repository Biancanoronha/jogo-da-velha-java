public class Tabuleiro {
    private char[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new char[3][3];
        inicializar();
    }

    public void inicializar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void exibir() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    public boolean fazerJogada(int linha, int coluna, char simbolo) {
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = simbolo;
            return true;
        }
        return false;
    }

    public boolean verificarVitoria(char s) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == s && tabuleiro[i][1] == s && tabuleiro[i][2] == s)
                return true;
            if (tabuleiro[0][i] == s && tabuleiro[1][i] == s && tabuleiro[2][i] == s)
                return true;
        }
        if (tabuleiro[0][0] == s && tabuleiro[1][1] == s && tabuleiro[2][2] == s)
            return true;
        if (tabuleiro[0][2] == s && tabuleiro[1][1] == s && tabuleiro[2][0] == s)
            return true;

        return false;
    }

    public boolean cheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean acabouOJogo() {
        return cheio() || verificarVitoria('X') || verificarVitoria('O');
    }
}