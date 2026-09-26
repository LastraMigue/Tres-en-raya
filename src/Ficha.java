/**
 * Enumeración que representa las fichas del juego (X o O).
 * Cada ficha tiene asignado un color para la consola.
 */
public enum Ficha {
    X("\u001B[31m"), // Rojo
    O("\u001B[36m"); // Cyan

    // Código ANSI para resetear el color de la consola al valor por defecto
    public static final String RESET = "\u001B[0m";
    // Atributo que almacena el código de color de la ficha
    private final String color;

    /**
     * Constructor de la ficha.
     * @param color Código ANSI del color.
     */
    Ficha(String color) {
        this.color = color;
    }

    /**
     * Devuelve la ficha que debe jugar en el siguiente turno.
     * Si el turno actual es X, devuelve O, y viceversa.
     */
    public Ficha siguiente() {
        if (this == X) {
            return O;
        }
        return X;
    }

    /**
     * Sobrescribe el método toString para imprimir la ficha (X o O) 
     * con su respectivo color en la terminal.
     */
    @Override
    public String toString() {
        return color + this.name() + RESET;
    }
}
