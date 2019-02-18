import java.util.Scanner;

public class Main {
    public static void main( String[] args ){

        // Print the string "Hello, " on screen
        System.out.println("I am saying Hello to the people below.. ");

        // Check if a command line argument exists
        /*if(args.length == 0)
            System.exit(0);

        // Display the arguments from the command line
        for(int counter = 0; counter < args.length; counter++){
            System.out.println("argument index " + counter + ": " + args[counter]);
        }*/
        /*String tablero;
        System.out.println("Ingrese la configuracion inicial del tablero ");
        Scanner teclado = new Scanner(System.in);
        tablero= teclado.nextLine();*/

        System.out.println("Prueba");
        AlgoritmoA algoritmoA=new AlgoritmoA();
        System.out.println("resultado" + algoritmoA.crear_EstadoInicial(".4.13.4.1..4.21."));
        System.out.println(algoritmoA.calcular_heuristica(algoritmoA.estadoInicial, 4));
        //algoritmoA.estado_siguiente(algoritmoA.estadoInicial, 0,0);
        Estado nuevo = algoritmoA.construir_estado(algoritmoA.estadoInicial, 2, 0,0);

        System.out.println("empieza " + nuevo.tablero.size());
        for (int i=0; i< nuevo.tablero.size();i++){
            for (int j=0; j< nuevo.tablero.get(i).size();j++){
                System.out.print("|"+nuevo.tablero.get(i).get(j));
            }
            System.out.print("|\n---------\n");
        }
        System.out.println("termina");



    }

}
