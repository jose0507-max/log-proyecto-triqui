// Coordina una partida: participantes, turno, movimientos y resultado final.
public class Partida {

    // Tablero que se usa en esta partida.
    private Tablero tableroJuego;
    // Dos participantes: posicion 0 es el primer jugador y posicion 1 el segundo.
    private Jugador[] jugadores;
    // Guarda el ganador cuando la partida termina; null si hubo empate o aun no termina.
    private Jugador ganador;
    // Indice del arreglo que indica quien tiene el turno (0 o 1).
    private int turno; // 0: jugador1, 1: jugador2
    // Define si juegan dos personas o una persona contra la maquina.
    private int modalidad; // 1: Jugador vs Jugador, 2: Jugador vs Computadora

    // Constructor: prepara tablero, participantes y valores iniciales.
    public Partida(int modalidad, Jugador jugador1, Jugador jugador2) {

        this.modalidad = modalidad;
        this.turno = 0;
        this.tableroJuego = new Tablero();
        this.ganador = null;

        this.jugadores = new Jugador[2];
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }

    // Genera 0 o 1 al azar y guarda ese valor como turno inicial.
    // Elegir aleatoriamente quien comienza
    public int elegirInicial() {

        int numero = (int) (Math.random() * 2);

        this.turno = numero;

        return numero;
    }

    // Intenta colocar la ficha del jugador actual; si juega la maquina, cuenta su movimiento.
    // Realizar una jugada
    public boolean realizarJugada(int i, int j) {

        boolean realizada = tableroJuego.colocarFicha(
            i, j, jugadores[turno].getFicha()
        );

        if (realizada) {

            if (modalidad == 2 && turno == 1) {
                ((Maquina) jugadores[turno]).registrarMovimiento();
            }
        }

        return realizada;
    }

    // Pide a la maquina que decida coordenadas y realiza esa jugada en el tablero.
    // Ejecutar el turno de la Maquina
    public boolean jugarMaquina() {

        if (modalidad != 2 || turno != 1) {
            return false;
        }

        Maquina computadora = (Maquina) jugadores[turno];

        char fichaRival = jugadores[0].getFicha();

        int[] jugada = computadora.elegirJugada(tableroJuego, fichaRival);

        if (jugada == null) {
            return false;
        }

        System.out.println("La maquina coloca su ficha en fila " +
            (jugada[0] + 1) + ", columna " + (jugada[1] + 1) + ".");

        return realizarJugada(jugada[0], jugada[1]);
    }

    // Cambia de 0 a 1 o de 1 a 0 usando el resto de la division.
    // Cambiar el turno
    public void cambiarTurno() {

        turno = (turno + 1) % 2;
    }

    // Devuelve al participante cuyo indice coincide con el turno.
    // Obtener el Jugador actual
    public Jugador getJugadorActual() {

        return jugadores[turno];
    }

    // Llama al tablero para mostrar su estado actual.
    // Mostrar el Tablero
    public void mostrarTablero() {

        tableroJuego.mostrarTablero();
    }

    // Consulta al tablero si las coordenadas estan dentro de sus limites.
    // Comprobar si las coordenadas estan dentro del tablero
    public boolean posicionValida(int i, int j) {
        return tableroJuego.posicionValida(i, j);
    }

    // Consulta al tablero si esa posicion ya fue ocupada.
    // Comprobar si la casilla ya tiene una ficha
    public boolean casillaOcupada(int i, int j) {
        return tableroJuego.casillaOcupada(i, j);
    }

    // Termina si hay una linea ganadora o si todas las casillas estan ocupadas.
    // Comprobar si la Partida termino
    public boolean estaTerminada() {

        return tableroJuego.estaLleno() ||
               tableroJuego.hayGanador(jugadores[0].getFicha()) ||
               tableroJuego.hayGanador(jugadores[1].getFicha());
    }

    // Actualiza victorias/derrotas del ganador o empates de ambos participantes.
    // Registrar el resultado de la Partida
    public void finalizarPartida() {

        if (tableroJuego.hayGanador(jugadores[turno].getFicha())) {

            ganador = jugadores[turno];

            jugadores[turno].registrarVictoria();
            jugadores[(turno + 1) % 2].registrarDerrota();

        } else if (tableroJuego.estaLleno()) {

            jugadores[0].registrarEmpate();
            jugadores[1].registrarEmpate();
        }
    }

    // Devuelve el ganador guardado; si hubo empate, devuelve null.
    // Obtener el ganador
    public Jugador getGanador() {

        return ganador;
    }
}