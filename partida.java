
public class partida {

    private tablero tablero;
    private jugador[] jugadores;
    private jugador ganador;
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

    public boolean realizarJugada(int i, int j) {

        return tablero.colocar_ficha(i, j, jugadores[turno].getFicha());
    }

    public void cambiarTurno() {

        turno = (turno + 1) % 2;
    }

    public void finalizarPartida() {

        if (tablero.hay_ganador(jugadores[turno].getFicha())) {

            ganador = jugadores[turno];

            jugadores[turno].registrarVictoria();
            jugadores[(turno + 1) % 2].registrarDerrota();

        } else if (tablero.esta_lleno()) {

            jugadores[0].registrarEmpate();
            jugadores[1].registrarEmpate();
        }
    }

    public void jugar() {

        elegirInicial();

        while (!tablero.esta_lleno() &&
               !tablero.hay_ganador(jugadores[0].getFicha()) &&
               !tablero.hay_ganador(jugadores[1].getFicha())) {

            // Pendiente: recibir coordenadas desde Inicio.

            if (realizarJugada(i, j)) {
                cambiarTurno();
            }
        }

        finalizarPartida();
    }
}
