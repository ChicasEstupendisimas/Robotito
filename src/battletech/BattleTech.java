package battletech;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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
	private static ArrayList<Mech> enemigos = new ArrayList<Mech>();
	private static Tablero mapa = new Tablero();

	
	//Variables
	private static int Jugador;
	private static int NumMechs;
	private static String Fase;
	private static String Fase_Mov = "Movimiento";
	private static String Fase_AA = "AtaqueArmas";
	private static String Fase_AF = "AtaqueFisico";
	private static String Fase_Reac = "Reaccion";
	private static String Fase_Fin = "FinalTurno";
	
	//ESTO ES PARA CUANDO EMPECEMOS A PONERNOS CON LAS FASES
	static String fase_Ana = "Movimiento"; 
	static String fase_Mary = "Configuraci—n";
	
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
			Jugador = 1;
			
			LeerFicheros();
			
			
			//TODO: entrar a las fases y todo eso
			if(fase_Ana == Fase_Mov || fase_Mary == Fase_Mov){
				FaseMovimiento movimiento = new FaseMovimiento();
				movimiento.IniciaFaseMovimiento();	
			}
			if(fase_Ana == Fase_AA || fase_Mary == Fase_AA){
			//	IniciaFaseMovimiento();	
			}
			
		//}
	
	}
	
	private static void LeerFicheros(){
		
		
		ArrayList<String> lectura_config;
		ArrayList<String> lectura_mapa;
		ArrayList<String> lectura_mechs;
		
		//FUNCIONA
		lectura_config = LecturaArchivo("config");
		config.LeerConfiguracion(lectura_config);
	
		//Se supone que funciona (Ask Plary)
		lectura_mapa = LecturaArchivo("mapa");
		mapa.LeerMapa(lectura_mapa);
		
		//Faltan Comprobaciones
		lectura_mechs = LecturaArchivo("mechs");
		NumMechs = Integer.parseInt(lectura_mechs.get(1));
		lectura_mechs.remove(0);
		lectura_mechs.remove(0);
	
		System.out.print("\nJUGADOR ACTIVO: "+ Jugador);
		for(int i=0; i<NumMechs; i++){
			Mech temp = Mech.LeerMech(lectura_mechs, Jugador, NumMechs);;

			if(temp.NumJugador == Jugador){
				mech = temp;
			}else{
				enemigos.add(temp);
			}
		}
		
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
	         archivo = new File ("./InputFiles/"+tipo_archivo+"J"+Jugador+".sbt");
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

	   
		return ArrayLectura;
	}

}
