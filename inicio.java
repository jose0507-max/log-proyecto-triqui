
import java.util.Scanner;

public class inicio {

    private gestorjugadores gestor;
    private Scanner teclado;

    public inicio() {
        gestor = new gestorjugadores();
        teclado = new Scanner(System.in);
    }

    // Leer un numero entero
    public int leerEntero(String mensaje) {

        int numero = 0;
        boolean valido = false;

        while (!valido) {

            System.out.print(mensaje);
            String entrada = teclado.nextLine();

            try {
                numero = Integer.parseInt(entrada);
                valido = true;

            } catch (NumberFormatException e) {
                System.out.println("Error: debes ingresar un numero entero.");
            }
        }

        return numero;
    }

    // Leer un nombre
    public String leerNombre(String mensaje) {

        String nombre;
        boolean valido;

        do {

            System.out.print(mensaje);
            nombre = teclado.nextLine().trim();

            valido = nombre.length() > 0;

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

    // Mostrar portada
    public void mostrarPortada() {

        System.out.println("=================================");
        System.out.println("          JUEGO DE TRIQUI        ");
        System.out.println("=================================");
        System.out.println("             Bienvenido          ");
        System.out.println();
    }

    // Mostrar menu principal
    public void mostrarMenu() {

        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1. Registrar jugador");
        System.out.println("2. Mostrar jugadores");
        System.out.println("3. Iniciar partida");
        System.out.println("4. Ver reporte");
        System.out.println("5. Salir");
        System.out.println("====================================");
    }

    // Ejecutar programa
    public void ejecutarPrograma() {

        int opcion;

        mostrarPortada();

        do {

            mostrarMenu();
            opcion = leerEntero("Selecciona una opcion: ");

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

    // Registrar jugadores
    public void registrarJugadores() {

        if (gestor.estaLleno()) {
            System.out.println("No es posible registrar mas jugadores.");
            return;
        }

        int codigo = leerEntero("Codigo del jugador: ");

        if (gestor.existeCodigo(codigo)) {
            System.out.println("Error: ese codigo ya esta registrado.");
            return;
        }

        String nombre = leerNombre("Nombre del jugador: ");

        // El jugador se registra sin una ficha asignada
        jugador nuevoJugador = new jugador(codigo, nombre, ' ');

        if (gestor.registrar(nuevoJugador)) {
            System.out.println("Jugador registrado correctamente.");
        }
    }

    // Iniciar partida
    public void iniciarPartida() {

        if (gestor.estaVacio()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        System.out.println("========== MODALIDAD ==========");
        System.out.println("1. Jugador vs Jugador");
        System.out.println("2. Jugador vs Maquina");

        int modalidad = leerEntero("Selecciona la modalidad: ");

        if (modalidad == 1) {

            if (gestor.cantidadJugadores() < 2) {
                System.out.print(" Deben haber más de 2 jugadores registrados ");
            }else{
                
                
                // Seleccionar primer jugador
                System.out.println("Selecciona el primer jugador.");
                gestor.mostrarJugadores();

                int codigo1 = leerEntero("Codigo del primer jugador: ");
                jugador jugador1 = gestor.buscar(codigo1);

                if (jugador1 == null) {
                    System.out.println("No existe un jugador con ese codigo.");
                    return;
                }

                // Seleccionar segundo jugador
                System.out.println("Selecciona el segundo jugador.");

                int codigo2 = leerEntero("Codigo del segundo jugador: ");

                if (codigo1 == codigo2) {
                    System.out.println("No puedes seleccionar el mismo jugador dos veces.");
                    return;
                }

                jugador jugador2 = gestor.buscar(codigo2);

                if (jugador2 == null) {
                    System.out.println("No existe un jugador con ese codigo.");
                    return;
                }

                // Elegir ficha del primer jugador
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

                // Asignar la ficha contraria al segundo jugador
                if (ficha1 == 'X') {
                    ficha2 = 'O';
                } else {
                    ficha2 = 'X';
                }

                // Asignar fichas para esta partida
                jugador1.setFicha(ficha1);
                jugador2.setFicha(ficha2);

                System.out.println(jugador1.getNombre() + ": " + ficha1);
                System.out.println(jugador2.getNombre() + ": " + ficha2);

                // Crear partida
                partida nuevaPartida = new partida(modalidad, jugador1, jugador2);

                System.out.println("Partida creada correctamente.");

                // Elegir quien comienza
                nuevaPartida.elegirInicial();

                System.out.println("Comienza: " +
                    nuevaPartida.getJugadorActual().getNombre());

                // Desarrollo de la partida
                while (!nuevaPartida.estaTerminada()) {

                    nuevaPartida.mostrarTablero();

                    jugador actual = nuevaPartida.getJugadorActual();

                    System.out.println("Turno de: " + actual.getNombre());
                    System.out.println("Ficha: " + actual.getFicha());

                    // Solicitar coordenadas
                    int fila = leerEntero("Ingresa la fila (1-3): ");
                    int columna = leerEntero("Ingresa la columna (1-3): ");

                    // Intentar realizar la jugada
                    if (nuevaPartida.realizarJugada(fila - 1, columna - 1)) {

                        if (nuevaPartida.estaTerminada()) {

                            nuevaPartida.finalizarPartida();

                        } else {

                            nuevaPartida.cambiarTurno();
                        }

                    } else {

                        System.out.println("Posicion invalida u ocupada. Intenta nuevamente.");
                    }
                }

                // Mostrar resultado final
                nuevaPartida.mostrarTablero();

                if (nuevaPartida.getGanador() != null) {

                    System.out.println("¡Tenemos un ganador!");
                    System.out.println("Ganador: " +
                        nuevaPartida.getGanador().getNombre());

                } else {

                    System.out.println("La partida termino en empate.");
                }
            }    
        } else if (modalidad == 2) {

            System.out.println("La modalidad contra la maquina esta pendiente.");

        } else {

            System.out.println("Modalidad no valida.");
        }
    }

    // Mostrar reporte
    public void verReporte() {

        if (gestor.estaVacio()) {
            System.out.println("No hay jugadores para mostrar en el reporte.");
        } else {
            gestor.mostrarReporte();
        }
    }

    // Metodo principal
    public static void main(String[] args) {

        inicio programa = new inicio();

        programa.ejecutarPrograma();
    }
}
