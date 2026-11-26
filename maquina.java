
// Jugador automatico. Hereda los datos basicos de Jugador y elige movimientos por prioridades.
public class Maquina extends Jugador {

    // Cuenta cuantas jugadas validas ha realizado la maquina en esta partida.
    private int movimientos;

    // Constructor: usa el constructor de Jugador y comienza el contador en cero.
    public Maquina(int codigo, String nombre, char ficha) {
        super(codigo, nombre, ficha);
        movimientos = 0;
    }

    // Aumenta el contador cada vez que la maquina coloca una ficha correctamente.
    // Registrar un movimiento realizado por la Maquina
    public void registrarMovimiento() {
        movimientos++;
    }

    // Busca una linea con dos fichas propias y una casilla vacia: devuelve donde ganar.
    // Las coordenadas de cada linea estan guardadas en la matriz lineas.
    public int[] hallarGanadora(Tablero Tablero) {

        // Cada fila contiene tres pares (fila, columna) que forman una linea ganadora.
        // Se incluyen las tres filas, tres columnas y dos diagonales.
        int[][] lineas = {
            {0,0, 0,1, 0,2},
            {1,0, 1,1, 1,2},
            {2,0, 2,1, 2,2},
            {0,0, 1,0, 2,0},
            {0,1, 1,1, 2,1},
            {0,2, 1,2, 2,2},
            {0,0, 1,1, 2,2},
            {0,2, 1,1, 2,0}
        };

        // Revisa una por una las ocho lineas posibles.
        for (int i = 0; i < 8; i++) {

            int propias = 0;
            int vacias = 0;

            int filaVacia = -1;
            int columnaVacia = -1;

            // Avanza de dos en dos porque los datos estan en pares: fila y columna.
            for (int j = 0; j < 6; j += 2) {

                int fila = lineas[i][j];
                int columna = lineas[i][j + 1];

                char ficha = Tablero.obtenerFicha(fila, columna);

                if (ficha == getFicha()) {
                    propias++;

                } else if (ficha == ' ') {
                    vacias++;

                    filaVacia = fila;
                    columnaVacia = columna;
                }
            }

            // Si tiene dos fichas propias y solo una vacia, esa posicion permite ganar.
            if (propias == 2 && vacias == 1) {
                return new int[]{filaVacia, columnaVacia};
            }
        }

        return null;
    }

    // Busca una linea donde el rival tenga dos fichas y una vacia para bloquearlo.
    public int[] hallarBloqueo(Tablero Tablero, char fichaBuscada) {

        // Reutiliza las mismas ocho posibles lineas del tablero.
        int[][] lineas = {
            {0,0, 0,1, 0,2},
            {1,0, 1,1, 1,2},
            {2,0, 2,1, 2,2},
            {0,0, 1,0, 2,0},
            {0,1, 1,1, 2,1},
            {0,2, 1,2, 2,2},
            {0,0, 1,1, 2,2},
            {0,2, 1,1, 2,0}
        };

        // Analiza cada linea buscando una amenaza del rival.
        for (int i = 0; i < 8; i++) {

            int propias = 0;
            int vacias = 0;

            int filaVacia = -1;
            int columnaVacia = -1;

            // Lee las coordenadas de las tres casillas de la linea.
            for (int j = 0; j < 6; j += 2) {

                int fila = lineas[i][j];
                int columna = lineas[i][j + 1];

                char ficha = Tablero.obtenerFicha(fila, columna);

                if (ficha == fichaBuscada) {
                    propias++;

                } else if (ficha == ' ') {
                    vacias++;

                    filaVacia = fila;
                    columnaVacia = columna;
                }
            }

            // Dos fichas rivales y una libre indican que hay que bloquear.
            if (propias == 2 && vacias == 1) {
                return new int[]{filaVacia, columnaVacia};
            }
        }

        return null;
    }

    // Prueba cada casilla vacia como movimiento y cuenta si deja dos posibles victorias.
    // Una bifurcacion significa que el rival no podria bloquear ambas amenazas a la vez.
    public int[] hallarBifurcacion(Tablero Tablero) {

        // Coordenadas de filas, columnas y diagonales para evaluar posibles victorias.
        int[][] lineas = {
            {0,0, 0,1, 0,2},
            {1,0, 1,1, 1,2},
            {2,0, 2,1, 2,2},
            {0,0, 1,0, 2,0},
            {0,1, 1,1, 2,1},
            {0,2, 1,2, 2,2},
            {0,0, 1,1, 2,2},
            {0,2, 1,1, 2,0}
        };

        // Examina cada casilla como posible movimiento de la maquina.
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {

                if (Tablero.obtenerFicha(fila, columna) == ' ') {

                    // Marca en la matriz las casillas que serian una victoria en el siguiente turno.
                    boolean[][] jugadasGanadoras = new boolean[3][3];

                    // Revisa las lineas como si la maquina ya hubiera jugado en esta casilla.
                    for (int i = 0; i < 8; i++) {

                        int propias = 0;
                        int vacias = 0;

                        int filaVacia = -1;
                        int columnaVacia = -1;

                        for (int j = 0; j < 6; j += 2) {

                            int f = lineas[i][j];
                            int c = lineas[i][j + 1];

                            char ficha = Tablero.obtenerFicha(f, c);

                            // Simula la ficha en la casilla candidata sin modificar el tablero real.
                            if (f == fila && c == columna) {
                                ficha = getFicha();
                            }

                            if (ficha == getFicha()) {
                                propias++;

                            } else if (ficha == ' ') {
                                vacias++;

                                filaVacia = f;
                                columnaVacia = c;
                            }
                        }

                        // Marca una posible jugada ganadora que quedaria disponible.
                        if (propias == 2 && vacias == 1) {
                            jugadasGanadoras[filaVacia][columnaVacia] = true;
                        }
                    }

                    // Cuenta cuantas casillas diferentes permitirian ganar despues.
                    int cantidadGanadoras = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            if (jugadasGanadoras[i][j]) {
                                cantidadGanadoras++;
                            }
                        }
                    }

                    // Si quedan al menos dos amenazas distintas, la jugada crea bifurcacion.
                    if (cantidadGanadoras >= 2) {
                        return new int[]{fila, columna};
                    }
                }
            }
        }

        return null;
    }

    // Decide el movimiento aplicando prioridades: ganar, bloquear, bifurcar y ocupar posiciones estrategicas.
    // Elegir la jugada de la Maquina
    public int[] elegirJugada(Tablero Tablero, char fichaRival) {

        int[] jugada;

        // Prioridad 1: si puede ganar en este turno, lo hace.
        // 1. Buscar una jugada ganadora
        jugada = hallarGanadora(Tablero);

        if (jugada != null) {
            return jugada;
        }

        // Prioridad 2: evitar que el rival gane en su siguiente movimiento.
        // 2. Bloquear al rival
        jugada = hallarBloqueo(Tablero, fichaRival);

        if (jugada != null) {
            return jugada;
        }

        // Prioridad 3: desde que ya ha jugado una vez, intenta crear dos amenazas.
        // 3. Buscar bifurcacion desde el segundo movimiento
        if (movimientos >= 1) {

            jugada = hallarBifurcacion(Tablero);

            if (jugada != null) {
                return jugada;
            }
        }

        // Prioridad 4: ocupar el centro, una posicion que participa en varias lineas.
        // 4. Buscar el centro
        if (Tablero.obtenerFicha(1, 1) == ' ') {
            return new int[]{1, 1};
        }

        // Prioridad 5: intentar una esquina si sigue libre.
        // 5. Buscar una esquina
        if (Tablero.obtenerFicha(0, 0) == ' ') {
            return new int[]{0, 0};
        }

        if (Tablero.obtenerFicha(0, 2) == ' ') {
            return new int[]{0, 2};
        }

        if (Tablero.obtenerFicha(2, 0) == ' ') {
            return new int[]{2, 0};
        }

        if (Tablero.obtenerFicha(2, 2) == ' ') {
            return new int[]{2, 2};
        }

        // Prioridad 6: si no quedan mejores opciones, ocupar un borde libre.
        // 6. Buscar un borde
        if (Tablero.obtenerFicha(0, 1) == ' ') {
            return new int[]{0, 1};
        }

        if (Tablero.obtenerFicha(1, 0) == ' ') {
            return new int[]{1, 0};
        }

        if (Tablero.obtenerFicha(1, 2) == ' ') {
            return new int[]{1, 2};
        }

        if (Tablero.obtenerFicha(2, 1) == ' ') {
            return new int[]{2, 1};
        }

        return null;
    }
}
