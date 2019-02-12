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


    }

}
