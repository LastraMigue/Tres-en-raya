public class Partida {
    private Ficha turno;
    private Tablero tablero;

    public Partida(int dimension) {
        this.tablero = new Tablero(dimension);
        this.turno = Ficha.X;
    }

    public void jugar(int fila, int columna) {
        if (!terminada()) {
            boolean exito = tablero.jugar(turno, fila, columna);
            if (exito) {
                if (!terminada()) {
                    turno = turno.siguiente();
                }
            } else {
                System.out.println("\u001B[31mMovimiento inválido. Casilla ocupada o fuera de rango.\u001B[0m");
            }
        }
    }

    public boolean terminada() {
        return tablero.gana(Ficha.X) || tablero.gana(Ficha.O) || tablero.estaLleno();
    }

    public Ficha ganador() {
        if (tablero.gana(Ficha.X)) return Ficha.X;
        if (tablero.gana(Ficha.O)) return Ficha.O;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tablero.toString());
        
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";

        if (terminada()) {
            Ficha g = ganador();
            if (g != null) {
                sb.append("\n").append(green).append("¡La partida ha terminado! Ganador: ").append(reset).append(g.toString()).append("\n");
            } else {
                sb.append("\n").append(yellow).append("¡La partida ha terminado en empate!").append(reset).append("\n");
            }
        } else {
            sb.append("\nTurno actual: ").append(turno.toString()).append("\n");
        }
        return sb.toString();
    }
}
