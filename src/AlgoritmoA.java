import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlgoritmoA {
    Estado estadoInicial;
    ArrayList<Estado> openSet = new ArrayList<>();
    ArrayList<Estado> closeSet = new ArrayList<>();
    Integer posibles_numeros[]= new Integer[4];

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public  AlgoritmoA(){
        //Posibles valores que puede tomar una casilla
        for (int i=0; i< posibles_numeros.length; i++ ){
            posibles_numeros[i]=i+1;
        }
    }

    public boolean crear_EstadoInicial(String cadena){
        //verificar la cantidad de elementos del tablero
        ArrayList<ArrayList<Integer>> finnal = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if (cadena.length()==16){
            int pos=0;
            estadoInicial = new Estado();

            for (int x=0; x<4; x++){
                temp = new ArrayList<>();
                for (int y=0;y<4;y++){
                    if (cadena.charAt(pos)=='.'){
                        temp.add(0);
                    }
                    else{
                        temp.add(Character.getNumericValue(cadena.charAt(pos)));
                    }
                    pos++;
                }
                finnal.add(temp);
            }
            estadoInicial.setTablero(finnal);
            estadoInicial.setHeuristica(0);
            estadoInicial.setManhattan(0);
            System.out.println(estadoInicial.tablero.size());
            for (int i=0;i<estadoInicial.tablero.size();i++){
                for (int j=0; j<estadoInicial.tablero.get(i).size();j++){
                    System.out.print("|"+estadoInicial.tablero.get(i).get(j));
                }
                System.out.print("|\n---------\n");
            }
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList buscar_vacios(Estado estado){
        ArrayList<Posicion> vacios = new ArrayList<>();
        for (int i=0; i< estado.tablero.size(); i++){
            for (int j=0; j<estado.tablero.get(i).size();j++){
                if (estado.tablero.get(i).get(j) == 0) {
                    Posicion pos = new Posicion(j, i);
                    vacios.add(pos);
                }
            }
        }
        return vacios;
    }

    public int calcular_manhattan(Posicion pos){
      return (pos.getX()+pos.getY());

    }

    public int calcular_heuristica(Estado estado, Integer num){
        int cont =0;
        for (int i=0; i<estado.tablero.size(); i++){
            for (int j=0; j<estado.tablero.get(i).size(); j++){
              if (estado.tablero.get(i).get(j)==num){
                  cont++;
              }
            }
        }
        return cont;
    }


    public void imprimir_estado( Estado estado){
        for (int i=0;i<estado.tablero.size();i++){
            for (int j=0; j<estado.tablero.get(i).size();j++){
                System.out.print("|"+estado.tablero.get(i).get(j));
            }
            System.out.print("|\n---------\n");
        }
    }


    public ArrayList<Estado> estado_siguiente(Estado estado_actual, int x, int y) {
        ArrayList<Integer> posibles = new ArrayList<>();
        ArrayList<Integer> finales = new ArrayList<>();
        ArrayList<Integer> temp_y = new ArrayList<>();
        ArrayList<Integer> temp_x = new ArrayList<>();
        ArrayList<Estado> posibles_estados= new ArrayList<>();
        //verificar columnas y filas y guarda en arreglos los numeros que existen en ella
        for (int i = 0; i < posibles_numeros.length; i++) {
            for (int j = 0; j < estado_actual.tablero.size(); j++) {
                if (!temp_y.contains(estado_actual.tablero.get(j).get(y))) {
                    temp_y.add(estado_actual.tablero.get(j).get(y));
                }

                System.out.println(estado_actual.tablero.get(x).get(j));
                if (!temp_x.contains(estado_actual.tablero.get(x).get(j))) {
                    temp_x.add(estado_actual.tablero.get(x).get(j));
                }
            }
        }
        System.out.println("temp x " + temp_x );
        System.out.println("temp y " + temp_y );

        //verifica si existen los numeros en columnas y filas
        for (int j = 0; j < posibles_numeros.length; j++) {
            if (!temp_x.contains(posibles_numeros[j]) && !temp_y.contains(posibles_numeros[j])) {
                finales.add(posibles_numeros[j]);
            }
        }
        System.out.println("Posibles \n"+ finales);

        //Calcula la heuristica y la distancia manhattan de todos los estados
        for (int i=0;i< finales.size();i++){
            Estado estado_agregar= construir_estado(estado_actual, finales.get(i), x, y);
            Posicion pos = new Posicion(x,y);
            guardar_pesos(estado_agregar,finales.get(i),pos);
            posibles_estados.add(estado_agregar);
        }
        return  posibles_estados;

    }

    public Estado construir_estado(Estado estado_anterior, int nuevo_num, int x, int y){
        Estado nuevo_estado = new Estado();
        ArrayList<ArrayList<Integer>> tablero = estado_anterior.getTablero();
        tablero.get(x).set(y, nuevo_num);
        nuevo_estado.setTablero(tablero);
        return nuevo_estado;
    }

    public void guardar_pesos(Estado estado, int numero, Posicion pos){
        int heuristica = calcular_heuristica(estado, numero);
        int manhattan = calcular_manhattan(pos);
        estado.setHeuristica(heuristica);
        estado.setManhattan(manhattan);
        estado.setTotal(manhattan+heuristica);
    }

    public void A_Star(Estado estado_inicial){
        ArrayList<Estado> cerrados= new ArrayList<>();
        ArrayList<Estado> abiertos= new ArrayList<>();
        abiertos.add(estado_inicial);

        /*while (!abiertos.isEmpty()){
            Estado estado_actual =
        }*/
    }

   /* public void check_sudoku(Estado estadoInicial){
        Estado estado_actual = estadoInicial;
        boolean col=false;
        boolean fil=false;
        for (int x=0;x<estado_actual.columnas; x++){
            for (int y=0; y<estado_actual.filas;y++){
                if (estado_actual.tablero[x][y]==0){
                    for (int j=0; j<posibles_numeros.length;j++){
                        col= check_columns(estado_actual.tablero[x], posibles_numeros[j]);

                    }

                }
            }


        }

    }*/
}
