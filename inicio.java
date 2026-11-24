import java.util.Scanner;

public class inicio {

   
    private Scanner teclado;

    public inicio(){
        this.teclado = new Scanner(System.in);
    }
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }
    private String leerNombre(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }
    private void mostrarPortada() {

        String cyan = "\u001B[36m";
        String amarillo = "\u001B[33m";
        String verde = "\u001B[32m";
        String blanco = "\u001B[37m";
        String negrita = "\u001B[1m";
        String reset = "\u001B[0m";

        System.out.println(cyan + negrita);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║                                  ║");
        System.out.println("║          " + amarillo + "T R I Q U I" + cyan + "              ║");
        System.out.println("║                                  ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.println(verde + "\n       ¡BIENVENIDO AL JUEGO!" + reset);
        System.out.println(blanco + "   Estrategia | Diversión | Competencia\n" + reset);

        System.out.println(amarillo + "   Presiona ENTER para comenzar..." + reset);
        teclado.nextLine();
    }

    
}

