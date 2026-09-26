/**
 * Clase que dirige el flujo del juego, manteniendo el turno actual y apoyándose
 * en el Tablero para las reglas físicas.
 */
public class Partida {
    // Almacena a quién le toca jugar en este instante (X o O)
    private Ficha turno;
    // Almacena la referencia al objeto tablero donde se juega
    private Tablero tablero;

    /**
     * Constructor que inicia una nueva partida.
     * Crea un tablero con la dimensión dada y le da el primer turno a la X.
     */
    public Partida(int dimension) {
        this.tablero = new Tablero(dimension);
        this.turno = Ficha.X;
    }

    /**
     * Intenta realizar una jugada en la fila y columna dadas por el usuario.
     */
    public void jugar(int fila, int columna) {
        // Solo permite jugar si la partida sigue activa
        if (!terminada()) {
            // Le pide al tablero que coloque la ficha del turno actual
            boolean exito = tablero.jugar(turno, fila, columna);
            
            if (exito) {
                // Si la jugada fue válida y no hemos terminado, cambiamos el turno al rival
                if (!terminada()) {
                    turno = turno.siguiente();
                }
            } else {
                // Si la jugada fue inválida (ocupada/rango), muestra un error en rojo
                System.out.println("\u001B[31mMovimiento inválido. Casilla ocupada o fuera de rango.\u001B[0m");
            }
        }
    }

    /**
     * Comprueba si el juego ha concluido, ya sea porque alguien ganó o porque el tablero se llenó.
     */
    public boolean terminada() {
        return tablero.gana(Ficha.X) || tablero.gana(Ficha.O) || tablero.estaLleno();
    }

    /**
     * Devuelve qué ficha ha ganado, o null si ha sido un empate.
     */
    public Ficha ganador() {
        if (tablero.gana(Ficha.X)) return Ficha.X;
        if (tablero.gana(Ficha.O)) return Ficha.O;
        return null; // Retorna null indicando empate
    }

    /**
     * Muestra el estado global de la partida, incluyendo el dibujo del tablero 
     * y mensajes informativos sobre el turno o el ganador final.
     */
    @Override
    public String toString() {
        // StringBuilder es mucho más eficiente que concatenar Strings con el símbolo +
        StringBuilder sb = new StringBuilder();
        // Primero añade el dibujo de todo el tablero
        sb.append(tablero.toString());
        
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";

        if (terminada()) {
            Ficha g = ganador();
            if (g != null) {
                // Mensaje en verde avisando del ganador
                sb.append("\n").append(green).append("¡La partida ha terminado! Ganador: ").append(reset).append(g.toString()).append("\n");
            } else {
                // Mensaje en amarillo avisando del empate
                sb.append("\n").append(yellow).append("¡La partida ha terminado en empate!").append(reset).append("\n");
            }
        } else {
            // Si la partida sigue, mostramos a quién le toca mover
            sb.append("\nTurno actual: ").append(turno.toString()).append("\n");
        }
        return sb.toString();
    }
}
