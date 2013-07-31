package battletech;

import java.util.ArrayList;

public class FaseMovimiento {
	
	

	static Casilla Destino;
	static Casilla Inicial;
	static ArrayList<Casilla> Tablero = new ArrayList<Casilla>();
	static Mech Enemigo = new Mech();
	static Mech Activo = new Mech();
	static int[] Costes;
	static int PuntDesnivel;
	static ArrayList<Casilla> Cerrados = new ArrayList<Casilla>();


	
	@SuppressWarnings("static-access")
	public  ArrayList<String> IniciaFaseMovimiento(Mech Yo, ArrayList<Mech> Enemigos, ArrayList<Casilla> Tablero){

		/* EscogeEnemigo();
			Fase de pruebas, por ahora escogemos el primer Mech de la lista de enemigos
				
		*/
		
		FaseMovimiento.Tablero = Tablero;
		FaseMovimiento.Activo = Yo;
		Enemigo = EscogeEnemigo(Enemigos);
		
		Inicial = Activo.CasillaPos;
		Destino = Enemigo.CasillaPos;
		
		AlgoritmoA();
		System.out.print("\nPosicion Mech Activo, Alto "+Inicial.Alto +" ancho: "+Inicial.Ancho );
		System.out.print("\nPosicion Enemigo, Alto "+Enemigo.CasillaPos.Alto +" ancho: "+Enemigo.CasillaPos.Ancho );
		
		System.out.print("\n\nCamino Elegido");
		for(Casilla Cas:Cerrados){	
			System.out.print("\nAlto: "+Cas.Alto+" Ancho: "+Cas.Ancho);
			
		}
	//	
		//Como se trata de un fichero est‡tico, calculamos los costes H
	//	CalculaCosteG();
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
	@SuppressWarnings("static-access")
	static void AlgoritmoA(){

		ArrayList<Casilla> Abiertos = new ArrayList<Casilla>();
		
		boolean CaminoEncontrado = false;
		boolean LimiteTiempo = false;
		
		CalculaCostes(Inicial);
		Abiertos.add(Inicial);
		
		while(!CaminoEncontrado && !LimiteTiempo){
			//A–adir alguna constante que controle el tiempo
			
			if(Abiertos.isEmpty()){
				CaminoEncontrado = true;
			}else{
				Casilla Mejor = Abiertos.get(0);
				for(Casilla Temp : Abiertos){
				   if(Temp.CosteTotal < Mejor.CosteTotal){
				      Mejor = Temp; 
				   }
				}
				
				if(Mejor == Destino){
					CaminoEncontrado = true;
				}else{
							
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
		
	}
	
	@SuppressWarnings("static-access")
	static boolean EsMejor(Casilla Mejor, ArrayList<Casilla> Lista){
		boolean EsMejor = false;
		for(Casilla Temp : Lista){
			   if(Mejor.CosteTotal < Temp.CosteTotal){
			      EsMejor = true; 
			   }
		}
		return EsMejor;		
	}
	
	@SuppressWarnings("static-access")
	static ArrayList<Casilla> GetSucesores(Casilla Padre){
		ArrayList<Casilla> Sucesores = new ArrayList<Casilla>();
		int Alto = Padre.Alto;
		int Ancho = Padre.Ancho;
		Casilla Hijo = new Casilla();
		
		//Obtenemos la casilla vecina para los 8 extremos del hex‡gono
		Hijo = Padre.getHijo(Alto-1, Ancho, Tablero, Padre);
		Hijo.EncaramientoCasilla = 1;
		Sucesores.add(Hijo);
		
		Hijo = Padre.getHijo(Alto, Ancho+1, Tablero, Padre);
		Hijo.EncaramientoCasilla = 2;
		Sucesores.add(Hijo);
		
		Hijo = Padre.getHijo(Alto+1, Ancho+1, Tablero, Padre);
		Hijo.EncaramientoCasilla = 3;
		Sucesores.add(Hijo);
		
		Hijo = Padre.getHijo(Alto+1, Ancho, Tablero, Padre);
		Hijo.EncaramientoCasilla = 4;
		Sucesores.add(Hijo);
	
		Hijo = Padre.getHijo(Alto+1, Ancho-1, Tablero, Padre);
		Hijo.EncaramientoCasilla = 5;
		Sucesores.add(Hijo);
		
		Hijo = Padre.getHijo(Alto, Ancho-1, Tablero, Padre);
		Hijo.EncaramientoCasilla = 6;
		Sucesores.add(Hijo);
		
		
		//Asignamos el padre a la casilla sucesora obtenida
		for(Casilla Temp: Sucesores){
			//Si es una casilla vac’a (extremos del tablero) la eliminamos de los sucesores.
			if(Hijo.vacia == true){ 
				Sucesores.remove(Temp);
			}
			Temp.Padre = Padre;	
			CalculaCostes(Temp);
		}
		
		return Sucesores;
	}
	
	@SuppressWarnings("static-access")
	static void CalculaCostes(Casilla Cas){
		int CosteEncaramiento = Cas.EncaramientoCasilla - Activo.Encaramiento;
		if(CosteEncaramiento <0) CosteEncaramiento = -CosteEncaramiento;
		
		if(CosteEncaramiento == 1){
			Cas.CosteEncaramiento++; 
		}
		if(CosteEncaramiento == 2){
			Cas.CosteEncaramiento = Cas.CosteEncaramiento + 2;
		}
		if(CosteEncaramiento == 3){
			Cas.CosteEncaramiento = Cas.CosteEncaramiento + 3;
		}
		
		Cas.CosteTotal = Cas.CosteH +  Cas.CosteDesnivel +  Cas.CosteEncaramiento;
		
		if(Cas.ocupada == true || Cas.accesible==true){
			Cas.CosteTotal = 9999;
		}
	
	//	if(Cas.equals(Destino)){
	//		Cas.CosteTotal = Cas.CosteH +  Cas.CosteDesnivel +  Cas.CosteEncaramiento;
	//	}
	}
}
