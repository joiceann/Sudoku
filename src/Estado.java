import java.lang.reflect.Array;
import java.util.ArrayList;

public class Estado {
    int columnas=4;
    int filas=4;
    ArrayList<ArrayList<Integer>> tablero = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Integer>> tablero) {
        this.tablero = tablero;
    }
}
