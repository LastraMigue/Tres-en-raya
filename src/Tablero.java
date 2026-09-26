/**
 * Representa el tablero físico del juego, encargado de gestionar la matriz 
 * de posiciones y comprobar las condiciones de victoria o empate.
 */
public class Tablero {
    // Matriz bidimensional que guarda las fichas colocadas
    private Ficha[][] tablero;
    // Tamaño del tablero (generalmente 3 para 3x3)
    private int dimension;

    /**
     * Constructor que inicializa el tablero vacío con la dimensión indicada.
     */
    public Tablero(int dimension) {
        this.dimension = dimension;
        this.tablero = new Ficha[dimension][dimension];
    }

    /**
     * Intenta colocar una ficha en la fila y columna especificadas.
     * @return true si el movimiento es válido y se colocó, false si la casilla está ocupada o fuera de rango.
     */
    public boolean jugar(Ficha ficha, int fila, int columna) {
        // Primero comprueba que las coordenadas no se salgan de los límites del tablero
        if (fila < 0 || fila >= dimension || columna < 0 || columna >= dimension) {
            return false;
        }
        // Si la casilla está libre (null), coloca la ficha
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = ficha;
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el tablero no tiene ninguna casilla libre (empate).
     */
    public boolean estaLleno() {
        // Recorre todas las posiciones de la matriz
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j] == null) {
                    return false; // Si encuentra un hueco, no está lleno
                }
            }
        }
        return true;
    }

    /**
     * Comprueba si una ficha específica (X u O) ha ganado la partida 
     * validando todas las direcciones posibles.
     */
    public boolean gana(Ficha ficha) {
        return ganaHorizontal(ficha) || ganaVertical(ficha) || 
               ganaDiagonalDirecta(ficha) || ganaDiagonalIndirecta(ficha);
    }

    /**
     * Verifica si la ficha ha hecho 3 en raya en alguna de las filas horizontales.
     */
    protected boolean ganaHorizontal(Ficha ficha) {
        for (int i = 0; i < dimension; i++) {
            boolean gana = true;
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j] != ficha) {
                    gana = false; // Si hay una ficha distinta, esta fila no es ganadora
                    break;
                }
            }
            if (gana) return true;
        }
        return false;
    }

    /**
     * Verifica si la ficha ha hecho 3 en raya en alguna de las columnas verticales.
     */
    protected boolean ganaVertical(Ficha ficha) {
        for (int j = 0; j < dimension; j++) {
            boolean gana = true;
            for (int i = 0; i < dimension; i++) {
                if (tablero[i][j] != ficha) {
                    gana = false; // Si hay una ficha distinta, esta columna no es ganadora
                    break;
                }
            }
            if (gana) return true;
        }
        return false;
    }

    /**
     * Verifica si la ficha ha hecho 3 en raya en la diagonal principal (\).
     */
    protected boolean ganaDiagonalDirecta(Ficha ficha) {
        // Comprueba casillas donde fila y columna coinciden: [0][0], [1][1], [2][2]
        for (int i = 0; i < dimension; i++) {
            if (tablero[i][i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si la ficha ha hecho 3 en raya en la diagonal secundaria (/).
     */
    protected boolean ganaDiagonalIndirecta(Ficha ficha) {
        // Comprueba casillas transversales: [0][2], [1][1], [2][0]
        for (int i = 0; i < dimension; i++) {
            // "dimension - 1 - i" calcula la columna de derecha a izquierda
            if (tablero[i][dimension - 1 - i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Convierte una posición del tablero en un texto imprimible. 
     * Devuelve espacio en blanco si está vacío o la ficha coloreada si está ocupada.
     */
    private Object valueOf(Ficha ficha) {
        if (ficha == null) {
            return " ";
        }
        return ficha.toString(); // Esto llamará al toString() coloreado de la ficha
    }

    /**
     * Dibuja el tablero en forma de cuadrícula de texto para mostrarlo en la terminal.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Imprime los índices superiores de las columnas
        sb.append("    1   2   3\n");
        for (int i = 0; i < dimension; i++) {
            // Imprime el índice izquierdo de la fila actual
            sb.append((i + 1)).append("  ");
            
            // Imprime las casillas de la fila
            for (int j = 0; j < dimension; j++) {
                sb.append(" ").append(valueOf(tablero[i][j])).append(" ");
                // Imprime separadores verticales salvo al final de la fila
                if (j < dimension - 1) sb.append("|");
            }
            sb.append("\n");
            
            // Imprime la línea divisoria horizontal entre filas
            if (i < dimension - 1) {
                sb.append("   ---+---+---\n");
            }
        }
        return sb.toString();
    }
}
