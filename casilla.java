// Representa una sola casilla del tablero. Cada casilla guarda X, O o espacio si esta vacia.
public class Casilla {

    // La ficha que ocupa esta casilla. El espacio (' ') significa que esta libre.
    private char ficha;

    // Al crear una casilla, comienza vacia.
    public Casilla() {
        this.ficha = ' ';
    }

    // Devuelve la ficha que esta guardada en la casilla.
    public char getFicha() {
        return ficha;
    }

    // Cambia la ficha almacenada (por ejemplo, cuando un jugador hace su movimiento).
    public void setFicha(char ficha) {
        this.ficha = ficha;
    }

    // Devuelve true si no hay X ni O en esta casilla.
    public boolean estaVacia() {
        return ficha == ' ';
    }


}