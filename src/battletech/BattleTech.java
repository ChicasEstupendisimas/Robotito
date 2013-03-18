package battletech;
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
			config.LeerConfiguracion();
		
			//De esto se encarga Ana
			//mech.LeerMech();
			
			
			
			
			
		//}
		
		
		
		
		
		
		
	}

}
