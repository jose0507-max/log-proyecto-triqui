public class casilla {

    private char ficha;

    public casilla() {
        this.ficha = ' ';
    }

    public char getficha() {
        return ficha;
    }

    public void setficha(char ficha) {
        this.ficha = ficha;
    }

    public boolean esta_vacia() {
        return ficha == ' ';
    }

    public void liberar() {
        ficha = ' ';
    }
}