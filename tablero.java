// Controla la matriz 3x3: mostrarla, colocar fichas y revisar el resultado.
public class Tablero {

    // Matriz de objetos Casilla; cada posicion representa un espacio del juego.
    private Casilla[][] casillas;

    // Constructor: crea las nueve casillas y las deja vacias.
    public Tablero() {

        casillas = new Casilla[3][3];

        // Ciclo externo recorre las filas de la matriz.
        for (int i = 0; i < 3; i++) {

            // Ciclo interno recorre columnas y crea una Casilla en cada lugar.
            for (int j = 0; j < 3; j++) {

                casillas[i][j] = new Casilla();

            }
        }
    }

    // Dibuja el tablero y muestra las fichas actuales en consola.
    public void mostrarTablero() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("              TRIQUI");
        System.out.println("=================================");
        System.out.println();
        System.out.println("             1       2       3");
        System.out.println("         +-------+-------+-------+");

        for (int i = 0; i < 3; i++) {

            System.out.print("      " + (i + 1) + "  ");

            for (int j = 0; j < 3; j++) {

                char ficha = casillas[i][j].getFicha();

                System.out.print("|");

                if (ficha == ' ') {
                    System.out.print("       ");
                } else {
                    System.out.print("   " + ficha + "   ");
                }
            }

            System.out.println("|");
            System.out.println("         +-------+-------+-------+");
        }

        System.out.println();
    }

    // Verifica que fila y columna esten dentro de los indices 0, 1 y 2.
    public boolean posicionValida(int i, int j) {

        return (i >= 0 && i < 3 && j >= 0 && j < 3);
    }

    // Devuelve la ficha de una coordenada; si no es valida, devuelve espacio.
    // Obtener la ficha de una posicion
    public char obtenerFicha(int i, int j) {

        if (posicionValida(i, j)) {
            return casillas[i][j].getFicha();
        }

        return ' ';
    }


    // Comprueba si la coordenada es valida y contiene una ficha.
    // Comprobar si una posicion esta ocupada
    public boolean casillaOcupada(int i, int j) {
        if (posicionValida(i, j)) {
            return !casillas[i][j].estaVacia();
        }
        return false;
    }

    // Coloca la ficha solo si la coordenada existe y la casilla esta libre.
    public boolean colocarFicha(int i, int j, char ficha) {

        if (posicionValida(i, j) && casillas[i][j].estaVacia()) {

            casillas[i][j].setFicha(ficha);

            return true;

        } else {

            return false;
        }
    }

    // Recorre todas las casillas; basta encontrar una vacia para decir que no esta lleno.
    public boolean estaLleno() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (casillas[i][j].getFicha() == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Revisa si la ficha indicada completo alguna de las ocho lineas posibles.
    public boolean hayGanador(char ficha) {

        // Comprobar filas: en cada fila, las tres casillas deben tener la ficha.
        // Comprobar filas
        for (int i = 0; i < 3; i++) {

            if (casillas[i][0].getFicha() == ficha &&
                casillas[i][1].getFicha() == ficha &&
                casillas[i][2].getFicha() == ficha) {

                return true;
            }
        }

        // Comprobar columnas: misma logica, pero recorriendo verticalmente.
    // Comprobar columnas
        for (int i = 0; i < 3; i++) {

            if (casillas[0][i].getFicha() == ficha &&
                casillas[1][i].getFicha() == ficha &&
                casillas[2][i].getFicha() == ficha) {

                return true;
            }
        }

        // Diagonal principal: desde la esquina superior izquierda hasta abajo derecha.
        // Comprobar diagonal principal
        if (casillas[0][0].getFicha() == ficha &&
            casillas[1][1].getFicha() == ficha &&
            casillas[2][2].getFicha() == ficha) {

            return true;
        }

        // Diagonal secundaria: desde arriba derecha hasta abajo izquierda.
        // Comprobar diagonal secundaria
        if (casillas[0][2].getFicha() == ficha &&
            casillas[1][1].getFicha() == ficha &&
            casillas[2][0].getFicha() == ficha) {

            return true;
        }

        return false;
    }
}