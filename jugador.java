
// Guarda los datos y estadisticas de una persona que participa en el juego.
public class Jugador {

    // Codigo unico que permite identificar al jugador.
    private int codigo;
    // Nombre que se muestra durante el juego y en el reporte.
    private String nombre;
    // Ficha asignada para la partida: X u O.
    private char ficha;
    // Puntaje acumulado: victoria suma 2, empate suma 1 y derrota suma 0.
    private int puntos;
    private int victorias;
    private int derrotas;
    private int empates;

    // Constructor: recibe los datos iniciales y deja las estadisticas en cero.
    // Constructor
    public Jugador(int codigo, String nombre, char ficha) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.ficha = ficha;
        this.puntos = 0;
        this.victorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }

    // Getters: permiten consultar los atributos sin acceder directamente a ellos.
    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public char getFicha() {
        return ficha;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    // Setters: permiten actualizar los atributos que pueden cambiar.
    // Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFicha(char ficha) {
        this.ficha = ficha;
    }

    // Registra una victoria y agrega 2 puntos al jugador.
    // Registrar victoria
    public void registrarVictoria() {

        victorias++;
        puntos += 2;
    }

    // Registra una derrota; no modifica el puntaje.
    // Registrar derrota
    public void registrarDerrota() {

        derrotas++;
    }

    // Registra un empate y agrega 1 punto.
    // Registrar empate
    public void registrarEmpate() {

        empates++;
        puntos += 1;
    }

    // Suma victorias, derrotas y empates para saber cuantas partidas ha jugado.
    // Calcular partidas jugadas
    public int partidasJugadas() {

        return victorias + derrotas + empates;
    }

    // Imprime los datos y estadisticas del jugador en consola.
    // Mostrar información
    public void mostrarInformacion() {

        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Puntos: " + puntos);
        System.out.println("Victorias: " + victorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Empates: " + empates);
        System.out.println("Partidas jugadas: " + partidasJugadas());
    }
}
