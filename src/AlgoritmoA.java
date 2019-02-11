public class AlgoritmoA {

    public boolean crear_EstadoInicial(String cadena){
        //verificar la cantidad de elementos del tablero
        if (cadena.length()==16){
            int pos=0;
            Estado estadoInicial = new Estado();
            for (int x=0; x< 4;x++){
                for (int y=0;y<4;y++){
                    estadoInicial.tablero[x][y]= cadena.charAt(pos);
                    pos++;
                }
            }

            for (int i=0;i<4;i++){
                for (int j=0; j<4;j++){
                    System.out.print("|"+estadoInicial.tablero[i][j]);
                }
                System.out.print("|\n---------\n");
            }
            return true;
        }
        else{
            return false;
        }

    }
}
