import java.util.Scanner;

// Clase principal: muestra menus, solicita datos y conecta las demas clases.
public class Inicio {

    // Objeto que mantiene el registro de jugadores durante la ejecucion.
    private GestorJugadores gestor;
    // Permite leer lo que el usuario escribe en la consola.
    private Scanner teclado;

    // Constructor: crea el gestor y el lector de teclado.
    public Inicio() {
        gestor = new GestorJugadores();
        teclado = new Scanner(System.in);
    }

    // Lee texto y lo convierte a entero; si no es numerico, vuelve a preguntar.
    // Leer un numero entero
    public int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine();

            // Primero comprobamos si el usuario escribio algun espacio.
            // No usamos trim(), porque queremos rechazar los espacios, no eliminarlos.
            if (entrada.contains(" ")) {
                System.out.println("Error: no se aceptan espacios. Ingresa un numero entero.");
            } else {
                // parseInt convierte el texto a entero.
                try {
                    numero = Integer.parseInt(entrada);
                    valido = true;
                // Si escribe letras, decimales u otro valor no entero, se vuelve a pedir.
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un numero entero valido.");
                }
            }
        }

        return numero;
    }

    // Lee un nombre, elimina espacios al inicio/final y evita nombres vacios o con numeros.
    // Leer un nombre
    public String leerNombre(String mensaje) {
        String nombre;
        boolean valido;

        do {
            System.out.print(mensaje);
            nombre = teclado.nextLine().trim();
            valido = nombre.length() > 0;

            // Revisa cada caracter del nombre para detectar digitos.
            for (int i = 0; i < nombre.length(); i++) {
                if (nombre.charAt(i) >= '0' &&
                    nombre.charAt(i) <= '9') {
                    valido = false;
                }
            }

            if (!valido) {
                System.out.println("Error: ingresa un nombre valido, sin numeros.");
            }

        } while (!valido);

        return nombre;
    }

    // Muestra el mensaje de bienvenida al iniciar el programa.
    public void mostrarPortada() {
        System.out.println("=================================");
        System.out.println("          JUEGO DE TRIQUI        ");
        System.out.println("=================================");
        System.out.println("             Bienvenido          ");
        System.out.println();
    }

    // Imprime las opciones disponibles en el menu principal.
    public void mostrarMenu() {
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1. Registrar Jugador");
        System.out.println("2. Mostrar jugadores");
        System.out.println("3. Iniciar Partida");
        System.out.println("4. Ver reporte");
        System.out.println("5. Salir");
        System.out.println("====================================");
    }

    // Mantiene el menu activo hasta que el usuario elige salir (opcion 5).
    public void ejecutarPrograma() {
        int opcion;

        mostrarPortada();

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opcion: ");

            // Ejecuta la accion que corresponde a la opcion elegida.
            switch (opcion) {
                case 1:
                    registrarJugadores();
                    break;

                case 2:
                    if (gestor.estaVacio()) {
                        System.out.println("No hay jugadores registrados.");
                    } else {
                        gestor.mostrarJugadores();
                    }
                    break;

                case 3:
                    iniciarPartida();
                    break;

                case 4:
                    verReporte();
                    break;

                case 5:
                    System.out.println("Gracias por jugar. Hasta pronto.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

            System.out.println();

        } while (opcion != 5);
    }

    // Solicita codigo y nombre, valida los datos y registra el nuevo jugador.
    public void registrarJugadores() {
        if (gestor.estaLleno()) {
            System.out.println("No es posible registrar mas jugadores.");
            return;
        }

        int codigo = leerEntero("Codigo del Jugador: ");

        while (codigo <= 0) {
            System.out.println("Error: el codigo debe ser un numero positivo.");
            codigo = leerEntero("Codigo del Jugador: ");
        }

        if (gestor.existeCodigo(codigo)) {
            System.out.println("Error: ese codigo ya esta registrado.");
            return;
        }

        String nombre = leerNombre("Nombre del Jugador: ");

        Jugador nuevoJugador = new Jugador(codigo, nombre, ' ');

        if (gestor.registrar(nuevoJugador)) {
            System.out.println("Jugador registrado correctamente.");
        }
    }

    // Organiza la seleccion de participantes, fichas y el ciclo completo de juego.
    public void iniciarPartida() {

        if (gestor.estaVacio()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        System.out.println("========== MODALIDAD ==========");
        System.out.println("1. Jugador vs Jugador");
        System.out.println("2. Jugador vs Maquina");

        int modalidad = leerEntero("Selecciona la modalidad (1 o 2): ");

        while (modalidad != 1 && modalidad != 2) {
            System.out.println("Opcion no valida. Selecciona 1 o 2.");
            modalidad = leerEntero("Selecciona la modalidad: ");
        }

        // En PvP se necesitan dos personas registradas.
        // Validar cantidad de jugadores para PvP
        if (modalidad == 1 && gestor.cantidadJugadores() < 2) {
            System.out.println("Se necesitan al menos 2 jugadores registrados.");
            return;
        }

        // Elegir quien sera el primer participante de esta partida.
        // Seleccionar primer Jugador
        System.out.println("Selecciona el primer Jugador.");
        gestor.mostrarJugadores();

        int codigo1 = leerEntero("Codigo del Jugador: ");
        Jugador jugador1 = gestor.buscar(codigo1);

        if (jugador1 == null) {
            System.out.println("No existe un Jugador con ese codigo.");
            return;
        }

        Jugador jugador2 = null;
        Maquina computadora = null;

        // En PvP se busca al segundo jugador; en PvC se creara la maquina mas adelante.
        // Seleccionar segundo Jugador o crear Maquina
        if (modalidad == 1) {

            System.out.println("Selecciona el segundo Jugador.");
            gestor.mostrarJugadores();
            int codigo2 = leerEntero("Codigo del segundo Jugador: ");

            if (codigo1 == codigo2) {
                System.out.println("No puedes seleccionar el mismo Jugador dos veces.");
                return;
            }

            jugador2 = gestor.buscar(codigo2);

            if (jugador2 == null) {
                System.out.println("No existe un Jugador con ese codigo.");
                return;
            }
        }

        // Solicita X u O y valida que se ingrese una sola ficha permitida.
        // Seleccionar ficha del primer Jugador
        char ficha1 = ' ';
        char ficha2 = ' ';
        boolean valida = false;

        while (!valida) {
            System.out.print("Ficha para " + jugador1.getNombre() + " (X/O): ");
            String entrada = teclado.nextLine().trim();

            if (entrada.length() == 1 &&
                (entrada.charAt(0) == 'X' || entrada.charAt(0) == 'O' ||
                 entrada.charAt(0) == 'x' || entrada.charAt(0) == 'o')) {

                ficha1 = entrada.charAt(0);

                if (ficha1 == 'x') {
                    ficha1 = 'X';
                } else if (ficha1 == 'o') {
                    ficha1 = 'O';
                }

                valida = true;

            } else {
                System.out.println("Error: selecciona X u O.");
            }
        }

        // El segundo participante recibe la ficha opuesta a la del primero.
        // Asignar ficha restante
        if (ficha1 == 'X') {
            ficha2 = 'O';
        } else {
            ficha2 = 'X';
        }

        jugador1.setFicha(ficha1);

        if (modalidad == 1) {
            jugador2.setFicha(ficha2);
        } else {
            computadora = new Maquina(0, "Computadora", ficha2);
        }

        // Construye la partida con los dos participantes elegidos.
        // Crear Partida
        Partida nuevaPartida;

        if (modalidad == 1) {
            nuevaPartida = new Partida(modalidad, jugador1, jugador2);
        } else {
            nuevaPartida = new Partida(modalidad, jugador1, computadora);
        }

        System.out.println("Partida creada correctamente.");

        nuevaPartida.elegirInicial();

        System.out.println("Comienza: " +
            nuevaPartida.getJugadorActual().getNombre());

        // Repite turnos hasta que alguien gane o el tablero quede lleno.
        // Ciclo de juego
        while (!nuevaPartida.estaTerminada()) {

            nuevaPartida.mostrarTablero();

            Jugador actual = nuevaPartida.getJugadorActual();

            System.out.println("Turno de: " + actual.getNombre());
            System.out.println("Ficha: " + actual.getFicha());

            boolean realizada = false;

            if (modalidad == 2 && actual == computadora) {

                System.out.println("La Maquina esta pensando...");
                realizada = nuevaPartida.jugarMaquina();

            } else {

                int fila = leerEntero("Ingresa la fila (1-3): ");
                int columna = leerEntero("Ingresa la columna (1-3): ");

                if (!nuevaPartida.posicionValida(fila - 1, columna - 1)) {
                    System.out.println("Error: fila y columna deben estar entre 1 y 3.");
                } else if (nuevaPartida.casillaOcupada(fila - 1, columna - 1)) {
                    System.out.println("Error: esa casilla ya esta ocupada. Elige otra.");
                } else {
                    realizada = nuevaPartida.realizarJugada(fila - 1, columna - 1);
                }
            }

            if (realizada) {

                if (nuevaPartida.estaTerminada()) {
                    nuevaPartida.finalizarPartida();
                } else {
                    nuevaPartida.cambiarTurno();
                }

            } else if (modalidad == 2 && actual == computadora) {
                System.out.println("La Maquina no pudo realizar una jugada.");
                return;
            }
        }

        // Mostrar el tablero final y comunicar si hubo ganador o empate.
        nuevaPartida.mostrarTablero();

        if (nuevaPartida.getGanador() != null) {
            System.out.println("¡Tenemos un ganador!");
            System.out.println("Ganador: " +
                nuevaPartida.getGanador().getNombre());
        } else {
            System.out.println("La Partida termino en empate.");
        }
    }

    // Muestra la clasificacion, comprobando primero si hay jugadores registrados.
    public void verReporte() {
        if (gestor.estaVacio()) {
            System.out.println("No hay jugadores para mostrar en el reporte.");
        } else {
            gestor.mostrarReporte();
        }
    }

    // Punto de entrada del programa Java.
    public static void main(String[] args) {
        Inicio programa = new Inicio();
        programa.ejecutarPrograma();
    }
}