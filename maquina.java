
public class maquina extends jugador {

    private int movimientos;

    public maquina(int codigo, String nombre, char ficha) {
        super(codigo, nombre, ficha);
        movimientos = 0;
    }

    // Registrar un movimiento realizado por la maquina
    public void registrarMovimiento() {
        movimientos++;
    }

    public int[] hallarGanadora(tablero tablero) {

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

        for (int i = 0; i < 8; i++) {

            int propias = 0;
            int vacias = 0;

            int filaVacia = -1;
            int columnaVacia = -1;

            for (int j = 0; j < 6; j += 2) {

                int fila = lineas[i][j];
                int columna = lineas[i][j + 1];

                char ficha = tablero.obtener_ficha(fila, columna);

                if (ficha == getFicha()) {
                    propias++;

                } else if (ficha == ' ') {
                    vacias++;

                    filaVacia = fila;
                    columnaVacia = columna;
                }
            }

            if (propias == 2 && vacias == 1) {
                return new int[]{filaVacia, columnaVacia};
            }
        }

        return null;
    }

    public int[] hallarBloqueo(tablero tablero, char fichaBuscada) {

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

        for (int i = 0; i < 8; i++) {

            int propias = 0;
            int vacias = 0;

            int filaVacia = -1;
            int columnaVacia = -1;

            for (int j = 0; j < 6; j += 2) {

                int fila = lineas[i][j];
                int columna = lineas[i][j + 1];

                char ficha = tablero.obtener_ficha(fila, columna);

                if (ficha == fichaBuscada) {
                    propias++;

                } else if (ficha == ' ') {
                    vacias++;

                    filaVacia = fila;
                    columnaVacia = columna;
                }
            }

            if (propias == 2 && vacias == 1) {
                return new int[]{filaVacia, columnaVacia};
            }
        }

        return null;
    }

    public int[] hallarBifurcacion(tablero tablero) {

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

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {

                if (tablero.obtener_ficha(fila, columna) == ' ') {

                    boolean[][] jugadasGanadoras = new boolean[3][3];

                    for (int i = 0; i < 8; i++) {

                        int propias = 0;
                        int vacias = 0;

                        int filaVacia = -1;
                        int columnaVacia = -1;

                        for (int j = 0; j < 6; j += 2) {

                            int f = lineas[i][j];
                            int c = lineas[i][j + 1];

                            char ficha = tablero.obtener_ficha(f, c);

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

                        if (propias == 2 && vacias == 1) {
                            jugadasGanadoras[filaVacia][columnaVacia] = true;
                        }
                    }

                    int cantidadGanadoras = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            if (jugadasGanadoras[i][j]) {
                                cantidadGanadoras++;
                            }
                        }
                    }

                    if (cantidadGanadoras >= 2) {
                        return new int[]{fila, columna};
                    }
                }
            }
        }

        return null;
    }

    // Elegir la jugada de la maquina
    public int[] elegirJugada(tablero tablero, char fichaRival) {

        int[] jugada;

        // 1. Buscar una jugada ganadora
        jugada = hallarGanadora(tablero);

        if (jugada != null) {
            return jugada;
        }

        // 2. Bloquear al rival
        jugada = hallarBloqueo(tablero, fichaRival);

        if (jugada != null) {
            return jugada;
        }

        // 3. Buscar bifurcacion desde el segundo movimiento
        if (movimientos >= 1) {

            jugada = hallarBifurcacion(tablero);

            if (jugada != null) {
                return jugada;
            }
        }

        // 4. Buscar el centro
        if (tablero.obtener_ficha(1, 1) == ' ') {
            return new int[]{1, 1};
        }

        // 5. Buscar una esquina
        if (tablero.obtener_ficha(0, 0) == ' ') {
            return new int[]{0, 0};
        }

        if (tablero.obtener_ficha(0, 2) == ' ') {
            return new int[]{0, 2};
        }

        if (tablero.obtener_ficha(2, 0) == ' ') {
            return new int[]{2, 0};
        }

        if (tablero.obtener_ficha(2, 2) == ' ') {
            return new int[]{2, 2};
        }

        // 6. Buscar un borde
        if (tablero.obtener_ficha(0, 1) == ' ') {
            return new int[]{0, 1};
        }

        if (tablero.obtener_ficha(1, 0) == ' ') {
            return new int[]{1, 0};
        }

        if (tablero.obtener_ficha(1, 2) == ' ') {
            return new int[]{1, 2};
        }

        if (tablero.obtener_ficha(2, 1) == ' ') {
            return new int[]{2, 1};
        }

        return null;
    }
}
