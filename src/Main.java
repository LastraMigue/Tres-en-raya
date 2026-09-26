import java.util.Scanner;

/**
 * Clase principal (punto de entrada) encargada de interactuar con el usuario a través de la terminal.
 */
public class Main {
    // Definimos colores para estilizar el título del juego
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    /**
     * Método main que arranca al iniciar el programa. Contiene el bucle principal del juego.
     */
    public static void main(String[] args) {
        // Imprimimos el título en morado
        System.out.println(PURPLE + "=== TRES EN RAYA ===" + RESET);
        // Creamos una partida con un tablero clásico de 3x3
        Partida partida = new Partida(3);
        // Usamos Scanner para leer lo que el usuario teclea en consola
        Scanner scanner = new Scanner(System.in);

        // Bucle que se repetirá constantemente mientras la partida no haya acabado
        while (!partida.terminada()) {
            // 1. Mostrar estado visual del tablero actual
            System.out.println(partida.toString());
            // 2. Pedir la fila al usuario
            System.out.print("Introduce fila (1, 2, 3): ");
            int fila = -1;
            int columna = -1;
            
            try {
                // Leer la línea completa de la terminal e intentar convertirla a número entero
                fila = Integer.parseInt(scanner.nextLine());
                
                // 3. Pedir la columna al usuario
                System.out.print("Introduce columna (1, 2, 3): ");
                columna = Integer.parseInt(scanner.nextLine());
                
                // 4. Intentar realizar el movimiento.
                // Importante: Restamos 1 porque el usuario ve números del 1 al 3 en el tablero, 
                // pero las matrices internas en Java siempre van de la posición 0 a la 2.
                partida.jugar(fila - 1, columna - 1);
                
                // Separador estético para dejar clara la transición de turnos
                System.out.println("-------------------------");
            } catch (NumberFormatException e) {
                // Si el parseInt() falla porque el usuario escribió letras (como "g" o "q") 
                // o caracteres extraños, lo capturamos aquí e imprimimos el error en rojo.
                System.out.println("\u001B[31mPor favor, introduce números válidos (1, 2 o 3).\u001B[0m");
                System.out.println("-------------------------");
            }
        }

        // Cuando la partida termina (alguien gana o hay empate) y el bucle termina, 
        // volvemos a imprimir el tablero final para que se vea la última jugada 
        // junto al texto definitivo de victoria.
        System.out.println(partida.toString());
        
        // Cerramos el scanner para liberar recursos del sistema operativo y no dejar procesos abiertos
        scanner.close();
    }
}
