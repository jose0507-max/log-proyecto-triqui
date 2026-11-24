
public class jugador {

    private int codigo;
    private String nombre;
    private char ficha;
    private int puntos;
    private int victorias;
    private int derrotas;
    private int empates;

    // Constructor
    public jugador(int codigo, String nombre, char ficha) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.ficha = ficha;
        this.puntos = 0;
        this.victorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }

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

    // Registrar victoria
    public void registrarVictoria() {

        victorias++;
        puntos += 3;
    }

    // Registrar derrota
    public void registrarDerrota() {

        derrotas++;
    }

    // Registrar empate
    public void registrarEmpate() {

        empates++;
        puntos += 1;
    }

    // Calcular partidas jugadas
    public int partidasJugadas() {

        return victorias + derrotas + empates;
    }

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
