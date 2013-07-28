package battletech;

import java.util.ArrayList;

public class FaseMovimiento {
	
	

	static ArrayList<Casilla> Abiertos = new ArrayList<Casilla>();
	static ArrayList<Casilla> Cerrados = new ArrayList<Casilla>();
	int[] Costes;
	int i = 0;

	
	
	
	public  ArrayList<String> IniciaFaseMovimiento(Mech yo, ArrayList<Mech> enemigos){

		Costes = new int[enemigos.size()];
		for(Mech Enemech : enemigos){
			Costes[i] = CalculaCostes(Enemech);
			i++;
		}
		
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
		
		
		
		
		/*		
			ABIERTOS := [INICIAL] //inicializacin 
			CERRADOS := [] 
			f'(INICIAL) := h'(INICIAL) 
			repetir 
			si ABIERTOS = [] entonces FALLO 
			si no // quedan nodos 
			extraer MEJORNODO de ABIERTOS con f' mnima 
			// cola de prioridad 
			mover MEJORNODO de ABIERTOS a CERRADOS 
			si MEJORNODO contiene estado_objetivo entonces 
			SOLUCION_ENCONTRADA := TRUE 
			si no 
			generar SUCESORES de MEJORNODO 
			para cada SUCESOR hacer TRATAR_SUCESOR 
			hasta SOLUCION_ENCONTRADA o FALLO
		
		
						Aade nodo inicial a ABIERTOS
				2. SI ABIERTOS no contiene nodos:
				a. Finaliza habiendo comprobado todas las posibilidades.
				3. Sino:
				a. Extrae el nodo con ms puntuacin en ABIERTOS.
				
				b. Si el tiempo maximo ha expirado:
					i. Finaliza habiendo comprobado parcialmente las posibilidades. c. Sino:
					i. Genera los sucesores del nodo extrado.
					ii. Para cada sucesor:
				1. Si est en CERRADOS: a. No hacer nada.
				2. Si no, si no est en ABIERTOS: a. Insertar en ABIERTOS.
				3. Si no, si est en ABIERTOS y es mejor: a. Actualizar nodo en ABIERTOS.
				4. Volver al punto 2.
		*/
	//	Abiertos.add(Casilla.get(0));
		if(Abiertos.isEmpty()){
			//Finaliza habiendo comprobado todas las posibilidades
		}else{
			Casilla Mejor = Abiertos.get(0);
			for(Casilla Temp : Abiertos){
			   if(Temp.Coste > Mejor.Coste){
			      Mejor = Temp; 
			   }
			}
			
			
			
			
		}
		
		
	}
}
