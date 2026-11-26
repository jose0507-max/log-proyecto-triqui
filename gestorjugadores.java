
// Administra el arreglo de jugadores: registra, busca, muestra y genera la clasificacion.
public class GestorJugadores {

    // Arreglo donde se guardan los jugadores registrados.
    private Jugador[] jugadores;
    // Indica cuantas posiciones del arreglo ya estan ocupadas.
    private int cantidad;
    // Limite de jugadores que puede almacenar el arreglo.
    private final int MAX_JUGADORES = 100;

    // Constructor: crea el arreglo y comienza con cero jugadores.
    // Constructor
    public GestorJugadores() {

        jugadores = new Jugador[MAX_JUGADORES];
        cantidad = 0;
    }

    // Agrega el jugador al final del arreglo si hay espacio y su codigo no se repite.
    // Registrar un Jugador
    public boolean registrar(Jugador j) {

        if (estaLleno()) {
            System.out.println("No hay espacio para registrar mas jugadores.");
            return false;
        }

        if (existeCodigo(j.getCodigo())) {
            System.out.println("Ya existe un Jugador con ese codigo.");
            return false;
        }

        jugadores[cantidad] = j;
        cantidad++;

        return true;
    }

    // Recorre los jugadores registrados buscando el codigo indicado.
    // Comprobar si existe un codigo
    public boolean existeCodigo(int codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (jugadores[i].getCodigo() == codigo) {
                return true;
            }
        }

        return false;
    }

    // Devuelve el objeto jugador si lo encuentra; si no, devuelve null.
    // Buscar un Jugador por su codigo
    public Jugador buscar(int codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (jugadores[i].getCodigo() == codigo) {
                return jugadores[i];
            }
        }

        return null;
    }

    // Compara la cantidad actual con el limite maximo.
    // Comprobar si el arreglo esta lleno
    public boolean estaLleno() {

        return cantidad == MAX_JUGADORES;
    }

    // Devuelve la cantidad de jugadores registrados, no el tamaño total del arreglo.
    public int cantidadJugadores(){
        return cantidad;
    }

    // Indica si todavia no se ha registrado ningun jugador.
    // Comprobar si el arreglo esta vacio
    public boolean estaVacio() {

        return cantidad == 0;
    }

    // Muestra los datos de cada jugador registrado.
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

    // Copia solo los jugadores registrados para poder ordenarlos sin alterar el arreglo original.
    // Crear una copia ordenada por puntos
    public Jugador[] copiarOrdenada() {

        Jugador[] copia = new Jugador[cantidad];

        // Copiar referencias de jugadores a un arreglo nuevo.
        // Copiar los jugadores
        for (int i = 0; i < cantidad; i++) {
            copia[i] = jugadores[i];
        }

        // Ordenamiento burbuja: compara vecinos y los intercambia si estan en orden incorrecto.
        // Ordenar la copia de mayor a menor puntaje
        for (int i = 0; i < cantidad - 1; i++) {

            for (int j = 0; j < cantidad - 1 - i; j++) {

                if (copia[j].getPuntos() < copia[j + 1].getPuntos()) {

                    Jugador auxiliar = copia[j];
                    copia[j] = copia[j + 1];
                    copia[j + 1] = auxiliar;
                }
            }
        }

        return copia;
    }

    // Muestra la tabla de posiciones usando la copia ordenada.
    // Mostrar clasificacion y reporte
    public void mostrarReporte() {

        if (estaVacio()) {
            System.out.println("No hay jugadores para generar el reporte.");
            return;
        }

        Jugador[] ordenados = copiarOrdenada();

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
