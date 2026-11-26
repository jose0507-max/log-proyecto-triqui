
public class gestorjugadores {

    private jugador[] jugadores;
    private int cantidad;
    private final int MAX_JUGADORES = 100;

    // Constructor
    public gestorjugadores() {

        jugadores = new jugador[MAX_JUGADORES];
        cantidad = 0;
    }

    // Registrar un jugador
    public boolean registrar(jugador j) {

        if (estaLleno()) {
            System.out.println("No hay espacio para registrar mas jugadores.");
            return false;
        }

        if (existeCodigo(j.getCodigo())) {
            System.out.println("Ya existe un jugador con ese codigo.");
            return false;
        }

        jugadores[cantidad] = j;
        cantidad++;

        return true;
    }

    // Comprobar si existe un codigo
    public boolean existeCodigo(int codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (jugadores[i].getCodigo() == codigo) {
                return true;
            }
        }

        return false;
    }

    // Buscar un jugador por su codigo
    public jugador buscar(int codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (jugadores[i].getCodigo() == codigo) {
                return jugadores[i];
            }
        }

        return null;
    }

    // Comprobar si el arreglo esta lleno
    public boolean estaLleno() {

        return cantidad == MAX_JUGADORES;
    }

    public int cantidadJugadores(){
        return cantidad;
    }

    // Comprobar si el arreglo esta vacio
    public boolean estaVacio() {

        return cantidad == 0;
    }

    // Mostrar todos los jugadores
    public void mostrarJugadores() {

        if (estaVacio()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        System.out.println("========== JUGADORES REGISTRADOS ==========");

        for (int i = 0; i < cantidad; i++) {

            System.out.println();
            jugadores[i].mostrarInformacion();
            System.out.println("-------------------------------------------");
        }
    }

    // Crear una copia ordenada por puntos
    public jugador[] copiarOrdenada() {

        jugador[] copia = new jugador[cantidad];

        // Copiar los jugadores
        for (int i = 0; i < cantidad; i++) {
            copia[i] = jugadores[i];
        }

        // Ordenar la copia de mayor a menor puntaje
        for (int i = 0; i < cantidad - 1; i++) {

            for (int j = 0; j < cantidad - 1 - i; j++) {

                if (copia[j].getPuntos() < copia[j + 1].getPuntos()) {

                    jugador auxiliar = copia[j];
                    copia[j] = copia[j + 1];
                    copia[j + 1] = auxiliar;
                }
            }
        }

        return copia;
    }

    // Mostrar clasificacion y reporte
    public void mostrarReporte() {

        if (estaVacio()) {
            System.out.println("No hay jugadores para generar el reporte.");
            return;
        }

        jugador[] ordenados = copiarOrdenada();

        System.out.println("=============== CLASIFICACION ===============");
        System.out.println("POS | CODIGO | NOMBRE | PUNTOS | V | D | E");

        for (int i = 0; i < cantidad; i++) {

            System.out.println(
                (i + 1) + " | " +
                ordenados[i].getCodigo() + " | " +
                ordenados[i].getNombre() + " | " +
                ordenados[i].getPuntos() + " | " +
                ordenados[i].getVictorias() + " | " +
                ordenados[i].getDerrotas() + " | " +
                ordenados[i].getEmpates()
            );
        }

        System.out.println("=============================================");
    }
}
