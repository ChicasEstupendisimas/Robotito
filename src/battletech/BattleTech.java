package battletech;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ana Ferreira Blanco y Mar’a Jesœs Platero S‡nchez
 * Robotito de la muerte versi—n: Vamos a aprobar
 * Clase principal desde la que se hace de t—.
 * 
 */



public class BattleTech {

	//Objetos del programa
	private static Configuracion config = new Configuracion();
	private static Mech mech = new Mech();

	
	//Variables
	private static int jugador;
	private static String fase;
	private static ArrayList<String> resultado_lectura;
	
	//ESTO ES PARA CUANDO EMPECEMOS A PONERNOS CON LAS FASES
	String fase_Ana = "Movimiento"; 
	String fase_Mary = "Configuraci—n";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		/*
		 *	TODO: Posteriormente tendremos que comprobar que el nœmero de argumentos sea correcto
		 *	2: nœmero de jugador asignado y fase correspondiente
		 *	Ahora mismo lo vamos a meter a mano.
		 *	if(args.length!=2){
		 *		Nœmero de argumentos incorrecto: que se hace?
		 * 		}else{
		 * 			jugador = Integer.parseInt(args[0]);
		 *			fase = args[1];
		 *			System.out.print("Argumentos insertados: " + jugador + fase);
		 */		
			jugador = 1;
			
			//De esto se encarga Mary
			resultado_lectura = LecturaArchivo("config");
			config.LeerConfiguracion(resultado_lectura);
		
			//De esto se encarga Ana
			//resultado_lectura = LecturaArchivo("mechs");
			//mech.LeerMech();
			
			//De esto ya veremos quien se encarga
			//resultado_lectura = LecturaArchivo("mapa");
			//mapa.LeerMapa();
			
			//TODO: entrar a las fases y todo eso
			
		//}
	
	}
	
	
	
	/*!
	 * Funci—n para leer los archivos de informaci—n de la partida
	 \	param tipo_archivo puede ser: config, mechs o mapa
	 \	return ArrayList<String> con los datos le’dos del archivo
	 */
	private static ArrayList<String> LecturaArchivo(String tipo_archivo){
		
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      ArrayList<String> ArrayLectura = new ArrayList<String>();

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("./InputFiles/"+tipo_archivo+"J"+jugador+".sbt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null){
	        	 ArrayLectura.add(linea);
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }

	    resultado_lectura = ArrayLectura;
		return resultado_lectura;
	}

}
