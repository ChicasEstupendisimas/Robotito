package battletech;

import java.util.ArrayList;

public class Casilla extends Tablero {

	int colum;
	int fila;
	int index;
	
	int Alto;
	int Ancho;
	
	static int Nivel;
	static int TipoTerreno;
	static int Objeto;
	int FCEEdificio;
	boolean EdificioDown;
	boolean Fuego;
	boolean Humo;
	int NumGarrotes;
	
	boolean[] RioCaras = new boolean[6];
	boolean[] CarreteraCaras = new boolean[6];

	//Para el c‡lculo de costes en la Fase de Movimiento (A*)
	static int CosteH;
	static int CosteG;
	static int CosteTotal;
	static int CosteDesnivel;
	static int CosteEncaramiento;
	static int EncaramientoCasilla;
	static boolean ocupada;
	
	Casilla Padre;
	boolean vacia;
	static boolean accesible;
	static boolean NeedChequeo;

	Casilla(){
		Alto = 0;
		Ancho = 0;
		vacia = true; //Variable para comprobar que no es una casilla vac’a (Funci—n getHijo)
	}
	
	@SuppressWarnings("static-access")
	static Casilla LeerCasilla(ArrayList<String> Leido){
		Casilla temp = new Casilla();
		temp.vacia = false;
		temp.NeedChequeo = false;
		temp.accesible = true;
		temp.CosteG = 0;
		temp.CosteH = 0;
		temp.CosteTotal = 0;
		temp.CosteDesnivel = 0;
		temp.ocupada = false;
		temp.CosteEncaramiento =0;

		temp.Nivel = Integer.parseInt(Leido.get(0));
		temp.TipoTerreno = Integer.parseInt(Leido.get(1));
		temp.Objeto = Integer.parseInt(Leido.get(2));
		temp.FCEEdificio = Integer.parseInt(Leido.get(3));
		temp.EdificioDown = Boolean.parseBoolean(Leido.get(4));
		temp.Fuego = Boolean.parseBoolean(Leido.get(5));
		temp.Humo = Boolean.parseBoolean(Leido.get(6));
		temp.NumGarrotes = Integer.parseInt(Leido.get(7));
		
		
/*		System.out.print("\nCASILLA\n");
		System.out.print("\nNivel: " + temp.Nivel);
		System.out.print("\nTipoTerreno: " + temp.TipoTerreno);
		System.out.print("\nObjeto: " + temp.Objeto);
		System.out.print("\nFCEEdificio: " + temp.FCEEdificio);
		System.out.print("\nEdificioDown: " + temp.EdificioDown);
		System.out.print("\nFuego: " + temp.Fuego);
		System.out.print("\nHumo: " + temp.Humo);
		System.out.print("\nNumGarrotes: " + temp.NumGarrotes);
*/		
		for(int i=0; i<6; i++){
			temp.RioCaras[i] = Boolean.parseBoolean(Leido.get(8+i));
		}
		
		for(int i=0; i<6; i++){
			temp.CarreteraCaras[i] = Boolean.parseBoolean(Leido.get(13+i));
		}
		for(int i=0; i<20; i++){
			Leido.remove(0);
		}
		
		ProcesaCasilla();
		return temp;
	}
	
	public Casilla getHijo(int Ancho, int Alto, ArrayList<Casilla> Tablero, Casilla Padre){
		Casilla Hijo = new Casilla();
		
		for(Casilla Temp:Tablero){
			if(Temp.Ancho == Ancho && Temp.Alto == Alto){
				Hijo = Temp;
				break;
			}
		}
		CalculaDesnivel(Hijo, Padre);
		return Hijo;
	}
	
	/**
	 * Funci—n que establece los costes est‡ticos de las casillas
	 * (Por tipo de terreno, objeto, profundidad, etc.)
	 */
	private static void ProcesaCasilla(){
		switch(TipoTerreno){
			case 0: //Terreno abierto +1PM
				CosteH++;
			break;
			case 1: //Terreno pavimentado +1PM
				CosteH++;
			break;
			case 2: //Terreno con agua
				switch(Nivel){
					case 0:
						CosteH++;
					break;
					case -1:
						NeedChequeo = true;
						CosteH=CosteH+2;
					break;
					case -2:
						NeedChequeo = true;
						CosteH=CosteH+4;
					break;
				}
			break;
			case 3: //Terreno pantanoso +2PM
				NeedChequeo = true;
				CosteH=CosteH+2;
			break;
		}
		
		switch(Objeto){
			case 1://Bosque Disperso +2PM
				CosteH=CosteH+2;
			break;
			case 2://Bosque Denso +3PM
				CosteH=CosteH+3;
			break;
			case 255: //Terreno sin objeto
				
			break;
			default:
				accesible = false;
			break;
			
			
		}	
	}
	
	/**
	 * Asigna el coste del desnivel para cada casilla en funci—n
	 * de la casilla padre desde la que se parta.
	 * @param Hijo
	 * @param Padre
	 */
	@SuppressWarnings("static-access")
	static void CalculaDesnivel(Casilla Hijo, Casilla Padre){
		int desnivel = Hijo.Nivel - Padre.Nivel;
		
		if(desnivel == -1 || desnivel == 1){
			Hijo.CosteDesnivel++;
		}
		if(desnivel == -2 || desnivel == 2){
			Hijo.CosteDesnivel = CosteDesnivel +2;
		}
		else{
			Hijo.CosteDesnivel = CosteDesnivel + 100;
			Hijo.accesible = false;
		}	
	}
	
}
