public enum Ficha {
    X("\u001B[31m"), // Rojo
    O("\u001B[36m"); // Cyan

    public static final String RESET = "\u001B[0m";
    private final String color;

    Ficha(String color) {
        this.color = color;
    }

    public Ficha siguiente() {
        if (this == X) {
            return O;
        }
        return X;
    }

    @Override
    public String toString() {
        return color + this.name() + RESET;
    }
}
