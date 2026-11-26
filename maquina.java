public class maquina extends jugador {
    private  int movimientos;

    public maquina(int codigo, String nombre, char ficha) {
        super(codigo, nombre, ficha);
        movimientos = 0;
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
}