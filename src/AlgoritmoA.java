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
    public Estado obtener_menor(ArrayList<Estado> estados){
        Estado estado_menor= new Estado();
        int costo=1000;
        for(int i=0; i< estados.size();i++){
            if (costo> (estados.get(i).getCosto()+ estados.get(i).getHeuristica())){
                estado_menor= estados.get(i);
                costo= (estados.get(i).getCosto()+ estados.get(i).getHeuristica());
            }
        }
        return estado_menor;
    }




    public ArrayList<Estado> estado_siguiente(Estado estado_actual, int x, int y) {
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
        /*System.out.println("temp x " + temp_x );
        System.out.println("temp y " + temp_y );*/

        //verifica si existen los numeros en columnas y filas
        for (int j = 0; j < posibles_numeros.length; j++) {
            if (!temp_x.contains(posibles_numeros[j]) && !temp_y.contains(posibles_numeros[j])) {
                finales.add(posibles_numeros[j]);
            }
        }
        /*System.out.println("Posibles \n"+ finales);*/

        //Calcula la heuristica y la distancia manhattan de todos los estados
        for (int i=0;i< finales.size();i++){
            Estado estado_agregar= construir_estado(estado_actual, finales.get(i), x, y);
            Posicion pos = new Posicion(x,y);
            guardar_pesos(estado_agregar,finales.get(i),pos);
            posibles_estados.add(estado_agregar);
        }
        return  posibles_estados;

    }

    public ArrayList<Integer> buscar_opciones(Estado estado, int x, int y){
        ArrayList<Integer> finales = new ArrayList<>();
        ArrayList<Integer> temp_y = new ArrayList<>();
        ArrayList<Integer> temp_x = new ArrayList<>();
        //verificar columnas y filas y guarda en arreglos los numeros que existen en ella
        for (int i = 0; i < posibles_numeros.length; i++) {
            for (int j = 0; j < estado.tablero.size(); j++) {
                if (!temp_y.contains(estado.tablero.get(y).get(j))) {
                    temp_y.add(estado.tablero.get(y).get(j));
                }
                if (!temp_x.contains(estado.tablero.get(j).get(x))) {
                    temp_x.add(estado.tablero.get(j).get(x));
                }
            }
        }

        //verifica si existen los numeros en columnas y filas
        for (int j = 0; j < posibles_numeros.length; j++) {
            if (!temp_x.contains(posibles_numeros[j]) && !temp_y.contains(posibles_numeros[j])) {
                finales.add(posibles_numeros[j]);
            }
        }
        return finales;
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

    public boolean goal(Estado estado){
        for (int i=0; i< estado.tablero.size();i++){
            if (estado.tablero.get(i).contains(0)){
                return  false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> copiar_tablero(ArrayList<ArrayList<Integer>> tablero){
        ArrayList<ArrayList<Integer>> tablero_nuevo = new ArrayList<>();
        for (int i=0; i< tablero.size(); i++){
            ArrayList<Integer> individual= new ArrayList<>();
            for (int j=0; j<tablero.get(i).size(); j++){
                individual.add(tablero.get(i).get(j));
            }
            tablero_nuevo.add(individual);
        }
        return tablero_nuevo;
    }

    public ArrayList<Estado> obtener_hijos (ArrayList<Posicion> vacios, Estado estado_padre){
        int costo=10000;
        int index=0;
        int opc=1000;

        //buscar la casilla que tenga menos opciones para ser llenadas
        for(int i=0; i< vacios.size();i++){
            if (vacios.get(i).getOpciones().size()<costo){
                costo= vacios.get(i).getOpciones().size();
                index=i;
            }
        }

        Posicion pos = vacios.get(index);

        //obtener los estados posibles en esa casilla
        ArrayList<Estado> hijos= new ArrayList<>();
        for (int i=0;i< pos.getOpciones().size();i++){
            ArrayList<ArrayList<Integer>> tablero = copiar_tablero( estado_padre.getTablero());
            tablero.get(pos.getX()).set(pos.getY(), pos.getOpciones().get(i));
            Estado temp= new Estado();
            temp.setTablero(tablero);
            temp.setCosto(estado_padre.getCosto()+1);
            hijos.add(temp);
        }

        //calcular la heuristica de los hijos
        for (int j=0; j<hijos.size();j++){
            int heuristica = calcular_heuristica(hijos.get(j),pos.getOpciones().get(j));
            hijos.get(j).setHeuristica(heuristica);
        }

        return hijos;

    }

    public void eliminar_estado(ArrayList<Estado> arreglo, Estado estado){
        for (int i=0;i<arreglo.size(); i++){
            if (arreglo.get(i).compareTo(estado)==1){

            }
        }
    }

    public void A_Star(){
        ArrayList<Estado> cerrados= new ArrayList<>();
        ArrayList<Estado> abiertos= new ArrayList<>();
        abiertos.add(estadoInicial);

        //while (!abiertos.isEmpty()){
            Estado estado_actual= obtener_menor(abiertos);
            cerrados.add(estado_actual);

            ArrayList<Posicion> vacios =buscar_vacios(estado_actual);
            for (int i=0;i< vacios.size();i++){
                ArrayList<Integer> opciones = buscar_opciones(estado_actual, vacios.get(i).getX(), vacios.get(i).getY());
                vacios.get(i).setOpciones(opciones);
            }


            //if (goal(estado_actual)){
                //termina
            //}
            //else{

                //revisa los hijos
                ArrayList<Estado> hijos = obtener_hijos(vacios, estado_actual);

                for (int i=0; i<hijos.size();i++){
                    //imprimir_estado(hijos.get(i));
                    buscar_vacios(hijos.get(i));
                    //if (obtener_hijos(vacios, hijos.get(i)).size()>0 || goal(hijos.get(i))){
                        abiertos.add(hijos.get(i));
                    //}
                }

                for (int m=0; m<abiertos.size();m++){
                    System.out.println("_________________________________________");
                    imprimir_estado(abiertos.get(1));
                }


            //}
        //}


    }


}
