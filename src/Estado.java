import java.lang.reflect.Array;
import java.util.ArrayList;

public class Estado {
    int columnas=4;
    int filas=4;
    int heuristica=1000;
    int manhattan;
    int total;
    ArrayList<ArrayList<Integer>> tablero = new ArrayList<ArrayList<Integer>>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }

    public ArrayList<ArrayList<Integer>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Integer>> tablero) {
        this.tablero = tablero;
    }
}
