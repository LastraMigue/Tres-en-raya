import java.util.Scanner;

public class Main {
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        System.out.println(PURPLE + "=== TRES EN RAYA ===" + RESET);
        Partida partida = new Partida(3);
        Scanner scanner = new Scanner(System.in);

        while (!partida.terminada()) {
            System.out.println(partida.toString());
            System.out.print("Introduce fila (1, 2, 3): ");
            int fila = -1;
            int columna = -1;
            
            try {
                fila = Integer.parseInt(scanner.nextLine());
                System.out.print("Introduce columna (1, 2, 3): ");
                columna = Integer.parseInt(scanner.nextLine());
                
                // Restamos 1 para que internamente sea de 0 a 2
                partida.jugar(fila - 1, columna - 1);
                System.out.println("-------------------------");
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce números válidos (1, 2 o 3).");
                System.out.println("-------------------------");
            }
        }

        System.out.println(partida.toString());
        scanner.close();
    }
}
