package battletech;

import java.util.ArrayList;

public class FaseMovimiento {
	
	

	static Casilla Destino;
	static Casilla Inicial;
	static ArrayList<Casilla> Tablero = new ArrayList<Casilla>();
	static Mech Enemigo = new Mech();
	static Mech Yo = new Mech();
	static int[] Costes;
//s	int i = 0;

	
	public  ArrayList<String> IniciaFaseMovimiento(Mech Yo, ArrayList<Mech> Enemigos, ArrayList<Casilla> Tablero){

		/* EscogeEnemigo();
			Fase de pruebas, por ahora escogemos el primer Mech de la lista de enemigos
				
		*/
		
		this.Tablero = Tablero;
		this.Yo = Yo;
		Enemigo = EscogeEnemigo(Enemigos);
		
		this.Inicial = Yo.Casilla;
		this.Destino = Enemigo.Casilla;
	//	
		
		/* Formato de fichero:
		 * 
		 * Tipo de Movimiento: "Inmovil", "Andar" "Correr" "Saltar"
		 * Hexagono destino: XXXX
		 * Lado destino: 1-6  
		 * Usar MASC: true/false
		 * Nœmero pasos: int > 0
		 * 
		 *   para cada paso:
		 *   	Tipo de paso: "Adelante" "Atras" "Izquierda" "Derecha" "Levantarse" "Cuerpo a Tierra"
		 *   	Numero de veces (el tipo de paso) : int >0/ 1-6 (lado) si levantarse/ 1 si Cuerpo a tierra
		 *   
		 *
		 * Si inmovil, solo hasta -> tipo movimiento
		 * 
		 * si salto: solo hasta ->lado destino
		 * 
		 * 
		 */
		
		
		return null;
		
	}
	
	/*
	 * Funci—n para escoger el mech Enemigo id—neo
	 */
	static Mech EscogeEnemigo(ArrayList<Mech> Enemigos){
		Mech EnemigoMech = new Mech();
		/*	
		int i=0;
		Costes = new int[Enemigos.size()];
		for(Mech Enemech : Enemigos){
			Costes[i] = CalculaCostes(Enemech);
			i++;
		}
		*/
		
		EnemigoMech = Enemigos.get(0);
		return EnemigoMech;
	}
	
	/*
	 * Funci—n para calcular los costes del enemigo actual
	 */
	static int CalculaCostes(Mech Enemigo){
		return 0;	
	}
	
	/*
	 * Funci—n que selecciona el objetivo a atacar
	 */
	static void SelectObjetivo(){
		
	}

	/*
	 * Funci—n base del algoritmo A*
	 */
	static void AlgoritmoA(){

		ArrayList<Casilla> Abiertos = new ArrayList<Casilla>();
		ArrayList<Casilla> Cerrados = new ArrayList<Casilla>();
		boolean CaminoEncontrado = false;
		boolean LimiteTiempo = false;
		
		Abiertos.add(Inicial);
		
		while(!CaminoEncontrado && !LimiteTiempo){
			
			if(Abiertos.isEmpty()){
				CaminoEncontrado = true;
			}else{
				Casilla Mejor = Abiertos.get(0);
				for(Casilla Temp : Abiertos){
				   if(Temp.Coste > Mejor.Coste){
				      Mejor = Temp; 
				   }
				}
				
				if(Mejor == Destino){
					CaminoEncontrado = true;
				}
						
				ArrayList<Casilla> Sucesores = GetSucesores(Mejor);
				for(Casilla Suc : Sucesores){
					if(Cerrados.contains(Suc)){
						
					}else if (!Abiertos.contains(Suc)){
						Abiertos.add(Suc);		
					}else if ( Abiertos.contains(Suc) && EsMejor(Suc, Abiertos)){
						Abiertos.remove(Suc);
						Cerrados.add(Suc);
					}
					
				}			
				
					
			}
	   }	
		
	}
	
	static boolean EsMejor(Casilla Mejor, ArrayList<Casilla> Lista){
		boolean EsMejor = false;
		for(Casilla Temp : Lista){
			   if(Mejor.Coste > Temp.Coste){
			      EsMejor = true; 
			   }
		}
		return EsMejor;		
	}
	
	static ArrayList<Casilla> GetSucesores(Casilla Padre){
		ArrayList<Casilla> Sucesores = new ArrayList<Casilla>();
		int Alto = Padre.Alto;
		int Ancho = Padre.Ancho;
		
		//Obtenemos la casilla vecina para los 8 extremos del hex‡gono
		Sucesores.add(Padre.getHijo(Alto, Ancho-1, Tablero));
		Sucesores.add(Padre.getHijo(Alto, Ancho+1, Tablero));
		
		Sucesores.add(Padre.getHijo(Alto-1, Ancho, Tablero));
		Sucesores.add(Padre.getHijo(Alto+1, Ancho, Tablero));
	
		Sucesores.add(Padre.getHijo(Alto-1, Ancho-1, Tablero));
		Sucesores.add(Padre.getHijo(Alto+1, Ancho-1, Tablero));
		
		Sucesores.add(Padre.getHijo(Alto-1, Ancho+1, Tablero));
		Sucesores.add(Padre.getHijo(Alto+1, Ancho+1, Tablero));
		
		//Asignamos el padre a la casilla sucesora obtenida
		for(Casilla Hijo: Sucesores){
			//Si es una casilla vac’a (extremos del tablero) la eliminamos de los sucesores.
			if(Hijo.vacia == true){ 
				Sucesores.remove(Hijo);
			}
			Hijo.Padre = Padre;		
		}
		
		
		return Sucesores;
	}
}
