public class tablero {

    private casilla[][] casillas;

    public tablero() {

        casillas = new casilla[3][3];

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                casillas[i][j] = new casilla();

            }
        }
    }

    public void mostrar_tablero() {

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

                char ficha = casillas[i][j].getficha();

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

    public boolean posicion_valida(int i, int j) {

        return (i >= 0 && i < 3 && j >= 0 && j < 3);
    }

    // Obtener la ficha de una posicion
    public char obtener_ficha(int i, int j) {

        if (posicion_valida(i, j)) {
            return casillas[i][j].getficha();
        }

        return ' ';
    }

    public boolean colocar_ficha(int i, int j, char ficha) {

        if (posicion_valida(i, j) && casillas[i][j].esta_vacia()) {

            casillas[i][j].setficha(ficha);

            return true;

        } else {

            return false;
        }
    }

    public boolean esta_lleno() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (casillas[i][j].getficha() == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hay_ganador(char ficha) {

        // Comprobar filas
        for (int i = 0; i < 3; i++) {

            if (casillas[i][0].getficha() == ficha &&
                casillas[i][1].getficha() == ficha &&
                casillas[i][2].getficha() == ficha) {

                return true;
            }
        }

    // Comprobar columnas
        for (int i = 0; i < 3; i++) {

            if (casillas[0][i].getficha() == ficha &&
                casillas[1][i].getficha() == ficha &&
                casillas[2][i].getficha() == ficha) {

                return true;
            }
        }

        // Comprobar diagonal principal
        if (casillas[0][0].getficha() == ficha &&
            casillas[1][1].getficha() == ficha &&
            casillas[2][2].getficha() == ficha) {

            return true;
        }

        // Comprobar diagonal secundaria
        if (casillas[0][2].getficha() == ficha &&
            casillas[1][1].getficha() == ficha &&
            casillas[2][0].getficha() == ficha) {

            return true;
        }

        return false;
    }
}