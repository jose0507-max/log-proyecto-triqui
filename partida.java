public class partida {

    private tablero tablero;
    private jugador[] jugadores;
    private int turno; // 0: jugador1, 1: jugador2

    private int modalidad; // 1: Jugador vs Jugador, 2: Jugador vs Computadora

    public partida(int modalidad, jugador jugador1, jugador jugador2) {

        this.modalidad = modalidad;
        this.turno = 0;
        this.tablero = new tablero();

        this.jugadores = new jugador[2];
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }

    public int elegirInicial() {

        int numero = (int) (Math.random() * 2);

        this.turno = numero;

        return numero;
    }

    public boolean realizarJugada(int i, int j, char ficha) {

        return tablero.colocar_ficha(i, j, ficha);
    }
}