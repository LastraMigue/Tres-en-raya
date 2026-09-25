public class Tablero {
    private Ficha[][] tablero;
    private int dimension;

    public Tablero(int dimension) {
        this.dimension = dimension;
        this.tablero = new Ficha[dimension][dimension];
    }

    public boolean jugar(Ficha ficha, int fila, int columna) {
        if (fila < 0 || fila >= dimension || columna < 0 || columna >= dimension) {
            return false;
        }
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = ficha;
            return true;
        }
        return false;
    }

    public boolean estaLleno() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean gana(Ficha ficha) {
        return ganaHorizontal(ficha) || ganaVertical(ficha) || 
               ganaDiagonalDirecta(ficha) || ganaDiagonalIndirecta(ficha);
    }

    protected boolean ganaHorizontal(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            boolean gana = true;
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j] != ficha) {
                    gana = false;
                    break;
                }
            }
            if (gana) return true;
        }
        return false;
    }

    protected boolean ganaVertical(Ficha ficha) {
        for (int j = 0; j < dimension; j++) {
            boolean gana = true;
            for (int i = 0; i < dimension; i++) {
                if (tablero[i][j] != ficha) {
                    gana = false;
                    break;
                }
            }
            if (gana) return true;
        }
        return false;
    }

    protected boolean ganaDiagonalDirecta(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            if (tablero[i][i] != ficha) {
                return false;
            }
        }
        return true;
    }

    protected boolean ganaDiagonalIndirecta(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            if (tablero[i][dimension - 1 - i] != ficha) {
                return false;
            }
        }
        return true;
    }

    private Object valueOf(Ficha ficha) {
        if (ficha == null) {
            return " ";
        }
        return ficha.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    1   2   3\n");
        for (int i = 0; i < dimension; i++) {
            sb.append((i + 1)).append("  ");
            for (int j = 0; j < dimension; j++) {
                sb.append(" ").append(valueOf(tablero[i][j])).append(" ");
                if (j < dimension - 1) sb.append("|");
            }
            sb.append("\n");
            if (i < dimension - 1) {
                sb.append("   ---+---+---\n");
            }
        }
        return sb.toString();
    }
}
