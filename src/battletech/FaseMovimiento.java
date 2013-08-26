package battletech;

import java.util.ArrayList;

public class FaseMovimiento {
	
	

	Casilla Destino;
	Casilla Inicial;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();
	Tablero Mapa = new Tablero();
	Mech Enemigo = new Mech();
	Mech Activo = new Mech();
	int[] Costes;
	int PuntDesnivel;
	ArrayList<Casilla> Cerrados = new ArrayList<Casilla>();
	
	
	@SuppressWarnings("static-access")
	public  ArrayList<String> IniciaFaseMovimiento(Mech Yo, ArrayList<Mech> Enemigos, Tablero Mapa){

		this.Mapa = Mapa;
		this.Casillas = Mapa.Casillas;
		this.Activo = Yo;
		Enemigo = EscogeEnemigo(Enemigos);
		
		Inicial = Activo.CasillaPos;
		Destino = Enemigo.CasillaPos;
		
		System.out.print("\nEncaramiento Enemigo: "+Enemigo.Encaramiento); 
		
		
		System.out.print("\nPosicion Mech Activo, Alto "+Inicial.Alto +" ancho: "+Inicial.Ancho );
		System.out.print("\nCasilla Destino: "+Enemigo.CasillaPos.Alto +" ancho: "+Enemigo.CasillaPos.Ancho );
		
		System.out.print("\nPuntos Andar: "+ Activo.PAndar);
		System.out.print("\nPuntos Correr: "+Activo.PCorrer);
		AlgoritmoA();
		System.out.print("\n\nCamino Elegido");
		for(Casilla Cas:Cerrados){	
			System.out.print("\n "+Cas.Alto+Cas.Ancho);
			
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
	Mech EscogeEnemigo(ArrayList<Mech> Enemigos){

		Mech Objetivo = Enemigos.get(0);
		int coste = 0;
		int mejor_coste = 0;
		
		for(Mech Temp:Enemigos){
			
			 Activo.ObtActitud(Temp);
			switch(Activo.Actitud){
				case Defensiva:
					coste = 1;
				break;
				case Equilibrada:
					coste = 3;
				break;
				case Ofensiva:
					coste = 4;
				break;
				case MuyOfensiva:
					coste = 6;
				break;
			}	
			
			coste *= PuntosDistancia(Activo.CasillaPos, Temp.CasillaPos);
			if(coste > mejor_coste){
				Objetivo = Temp;
				mejor_coste = coste;
			}
				
		}
		System.out.println("\nObjetivo: "+Objetivo.CasillaPos.Alto+", "+Objetivo.CasillaPos.Ancho);
		return Objetivo;
	}
	

	/*
	 * Funci—n base del algoritmo A*
	 */
	@SuppressWarnings("static-access")
	void AlgoritmoA(){

		ArrayList<Casilla> Abiertos = new ArrayList<Casilla>();
		
		boolean CaminoEncontrado = false;
		boolean LimiteTiempo = false;
		Inicial.ProcesaCasilla();
		
		CalculaCostes(Inicial);
		Abiertos.add(Inicial);
		System.out.print("\nCasilla Inicial: "+Inicial.Ancho+" , "+Inicial.Alto );
		System.out.print("\nCostes Inicial: "+Inicial.CosteTotal);
		int i=0;
		
		while(!CaminoEncontrado ){
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
				
				
				//if(Mejor.Alto == Destino.Alto && Mejor.Ancho == Destino.Ancho){
				//	Cerrados.add(Mejor);
				//	CaminoEncontrado = true;
				//}else{
					
					ArrayList<Casilla> Sucesores = GetSucesores(Mejor);
					System.out.println("\nSucesores size: "+Sucesores.size());
					
					if(Sucesores.contains(Destino)){
						Cerrados.add(Mejor);
						for(Casilla A:Sucesores){
							System.out.println("\nUltimos sucesores: "+A.Alto+","+A.Ancho);
						}
						System.out.print("\nCONJUNTO OBJETIVO ENCONTRADO");
						CaminoEncontrado = true;
						
						
						// TODO: Elegir la que est‡ a la espalda del objetivo
					}else{
					for(Casilla Suc : Sucesores){
							
						//Tratar Sucesores
						
						if(Cerrados.contains(Suc)){
							
						}else if (!Abiertos.contains(Suc)){
							Abiertos.add(Suc);	
							Suc.ProcesaCasilla();
							CalculaCostes(Suc);
							System.out.println("\nSucesores: "+Suc.EncaramientoCasilla+" "+Suc.Ancho+","+Suc.Alto+" costes: "+Suc.CosteTotal);
						}
						/*else if ( Abiertos.contains(Suc) && EsMejor(Suc, Abiertos)){
							Abiertos.remove(Suc);
							Cerrados.add(Suc);
						}*/
						
						
					}
					Cerrados.add(Mejor);
					Abiertos.remove(Mejor);
					}
					
				//}
					
			}
			//CaminoEncontrado = true;
	   }	
		
		//TipoMovimiento();
	}

	@SuppressWarnings("static-access")
	boolean EsMejor(Casilla Mejor, ArrayList<Casilla> Lista){
		boolean EsMejor = false;
		for(Casilla Temp : Lista){
			   if(Mejor.CosteTotal < Temp.CosteTotal){
			      EsMejor = true; 
			   }
		}
		return EsMejor;		
	}
	

	ArrayList<Casilla> GetSucesores(Casilla Padre){
		ArrayList<Casilla> Hijos = new ArrayList<Casilla>();
		int Alto = Padre.Alto;
		int Ancho = Padre.Ancho;
		Casilla Hijo = new Casilla();
		
		if(Ancho % 2 !=0){ //Casilla impar
		//Obtenemos la casilla vecina para los 8 extremos del hex‡gono
		Hijo = Padre.getHijo(Alto-1, Ancho, Casillas, Padre);
		Hijo.EncaramientoCasilla = 1;
		Hijos.add(Hijo);
		
		Hijo = Padre.getHijo(Alto-1, Ancho+1, Casillas, Padre);
		Hijo.EncaramientoCasilla = 2;
		Hijos.add(Hijo);
		
		Hijo = Padre.getHijo(Alto, Ancho+1, Casillas, Padre);
		Hijo.EncaramientoCasilla = 3;
		Hijos.add(Hijo);
		
		Hijo = Padre.getHijo(Alto+1, Ancho, Casillas, Padre);
		Hijo.EncaramientoCasilla = 4;
		Hijos.add(Hijo);
	
		Hijo = Padre.getHijo(Alto, Ancho-1, Casillas, Padre);
		Hijo.EncaramientoCasilla = 5;
		Hijos.add(Hijo);
		
		Hijo = Padre.getHijo(Alto-1, Ancho-1, Casillas, Padre);
		Hijo.EncaramientoCasilla = 6;
		Hijos.add(Hijo);
		}else{
			Hijo = Padre.getHijo(Alto-1, Ancho, Casillas, Padre);
			Hijo.EncaramientoCasilla = 1;
			Hijos.add(Hijo);
			
			Hijo = Padre.getHijo(Alto, Ancho+1, Casillas, Padre);
			Hijo.EncaramientoCasilla = 2;
			Hijos.add(Hijo);
			
			Hijo = Padre.getHijo(Alto+1, Ancho+1, Casillas, Padre);
			Hijo.EncaramientoCasilla = 3;
			Hijos.add(Hijo);
			
			Hijo = Padre.getHijo(Alto+1, Ancho, Casillas, Padre);
			Hijo.EncaramientoCasilla = 4;
			Hijos.add(Hijo);
		
			Hijo = Padre.getHijo(Alto+1, Ancho-1, Casillas, Padre);
			Hijo.EncaramientoCasilla = 5;
			Hijos.add(Hijo);
			
			Hijo = Padre.getHijo(Alto, Ancho-1, Casillas, Padre);
			Hijo.EncaramientoCasilla = 6;
			Hijos.add(Hijo);
			
		}
		
		
		//System.out.print("\nPadre : "+Padre.Alto + ", "+Padre.Ancho);
		//Asignamos el padre a la casilla sucesora obtenida
		for(Casilla Cas:Hijos){
			//Si es una casilla vac’a (extremos del tablero) la eliminamos de los sucesores.
			if(Cas.vacia == true){ 
				Hijos.remove(Cas);
			}else{
				Cas.Padre = Padre;	
				Cas.CalculaDesnivel(Cas, Padre);
				CalculaCostes(Cas);
				
			}	
			
			//System.out.print("\nHijo : "+Temp.Alto + ", "+Temp.Ancho);
		}
		
		return Hijos;
	}
	
	@SuppressWarnings("static-access")
 	void CalculaCostes(Casilla Cas){

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
		
		Cas.CostePuntos = Cas.CosteH +  Cas.CosteDesnivel +  Cas.CosteEncaramiento;
		//System.out.print("\nCoste H: "+Cas.CosteH);
		//System.out.print("\nCoste Des: "+Cas.CosteDesnivel);
		//System.out.print("\nCoste Encaramiento: "+Cas.CosteEncaramiento);
		//System.out.print("\nCoste Puntos: "+Cas.CostePuntos);

		
		Cas.CosteTotal = (Cas.CostePuntos + PuntosDistancia(Cas, Destino));
	//	System.out.print("\nCoste Total: "+Cas.CosteTotal);	
		
		if(Cas.ocupada == true && Cas.accesible==false){
			Cas.CosteTotal = 9999;
		}
	
	//	if(Cas.equals(Destino)){
	//		Cas.CosteTotal = Cas.CosteH +  Cas.CosteDesnivel +  Cas.CosteEncaramiento;
	//	}
	}
	
	float Distancia(Casilla Origen, Casilla Destino){
		float Resultado = 0;
		float Alto = Origen.Alto-Destino.Alto;
		float Ancho = Origen.Ancho-Destino.Ancho;
		
	   Resultado = (float) Math.sqrt((Alto*Alto) + (Ancho*Ancho));
	        
	    return Resultado;
	}
	
	float PuntosDistancia(Casilla Origen, Casilla Destino){ 
		float PDistancia = 0;
		float Dist = Distancia(Origen, Destino);
		

		//if(Dist >= Activo.DistanciaOptima){
			PDistancia =  (float)((10 / this.Mapa.Diagonal))*Dist + 10;
		//}else{ 
	     //   PDistancia = (float) (5 /this.Mapa.Diagonal) * Activo.DistanciaOptima;
		//}
		
		//System.out.print("\nDistancia entre casillas: "+ Dist);
		//System.out.print("\nPuntuaci—n Distancia: "+ PDistancia);
		return PDistancia;
	}
}
