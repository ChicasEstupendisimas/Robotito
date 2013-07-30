package battletech;

import java.util.ArrayList;

public class Mech {

	//Atributos generales
	int NumJugador;
	boolean Operativo;
	boolean Desconectado;
	boolean Atascado;
	boolean EnSuelo;
	static Casilla Casilla;
	static String PosicionHex; //Por ahora
	int Encaramiento;
	int EncaramientoTorso;
	int Temperatura;
	boolean Ardiendo;
	boolean Garrote;
	int TipoGarrote;
	
	int[] Blindaje = new int[11];
	int [] EstructuraInterna = new int[8];
	
	boolean[] Narc;
	boolean[] iNarc;
	

	
	
	//Atributos s—lo para el jugador activo
	int PAndar;
	int PCorrer;
	int PSaltar;
	int RadiadoresOn;
	int RadiadoresOff;
	int Heridas;
	boolean Consciente;
	boolean[] Slots = new boolean[78];
	boolean []LocDisparos = new boolean[8];
	int NumMuniciones;
	ArrayList<Municion> Municiones = new ArrayList<Municion>();
	
	
	

	Mech(){}
	
	static Mech LeerMech(ArrayList<String> Leido, int Activo, int NumJugadores, ArrayList<Casilla> Tablero){
		
		Mech relleno = new Mech();
		//System.out.print("\n\n Inicio mech  \n\n");
		relleno.NumJugador = Integer.parseInt(Leido.get(0));
		relleno.Operativo = Boolean.parseBoolean(Leido.get(1));
		relleno.Desconectado = Boolean.parseBoolean(Leido.get(2));
		relleno.Atascado = Boolean.parseBoolean(Leido.get(3));
		relleno.EnSuelo = Boolean.parseBoolean(Leido.get(4));
		relleno.PosicionHex = Leido.get(5);
		relleno.Encaramiento = Integer.parseInt(Leido.get(6));
		relleno.EncaramientoTorso = Integer.parseInt(Leido.get(7));
		relleno.Temperatura = Integer.parseInt(Leido.get(8));
		relleno.Ardiendo = Boolean.parseBoolean(Leido.get(9));
		relleno.Garrote = Boolean.parseBoolean(Leido.get(10));
		relleno.TipoGarrote = Integer.parseInt(Leido.get(11));
		
		for(int i=0; i<11; i++){
			relleno.Blindaje[i] = Integer.parseInt(Leido.get(i+12));
		}
		for(int i=0; i<8;i++){
			relleno.EstructuraInterna[i] = Integer.parseInt(Leido.get(i+23));
		} 
		
		for(int i=0; i<31; i++){
			Leido.remove(0);
		}

		/*
		System.out.print("\n0 " + relleno.NumJugador);
		System.out.print("\n1 " +relleno.Operativo);
		System.out.print("\n2 " +relleno.Desconectado);
		System.out.print("\n3 " +relleno.Atascado);
		System.out.print("\n4 " +relleno.EnSuelo);
		System.out.print("\n5 " +relleno.PosicionHex);
		System.out.print("\n6 " +relleno.Encaramiento);
		System.out.print("\n7 " +relleno.EncaramientoTorso);
		System.out.print("\n8 " +relleno.Temperatura);
		System.out.print("\n9 " +relleno.Ardiendo);
		System.out.print("\n10 " +relleno.Garrote);
		System.out.print("\n11 " +relleno.TipoGarrote);
		
		for(int i=0; i<11; i++){
			System.out.print("\n0 " + relleno.Blindaje[i]);
		}
		
		for(int i=0; i<8; i++){
			System.out.print("\n0 " + relleno.EstructuraInterna[i]);
		}
		*/
		
		if(relleno.NumJugador == Activo){
			LeerMechActivo(Leido, relleno);	
		}
		
		relleno.Narc = new boolean[NumJugadores];
		relleno.iNarc = new boolean[NumJugadores];
		
		for(int i=0; i<NumJugadores*2; i++){
			
			if(i<NumJugadores){
				relleno.Narc[i] = Boolean.parseBoolean(Leido.get(i)); 
			}else{
				relleno.iNarc[i-NumJugadores] = Boolean.parseBoolean(Leido.get(i));
			}		
		}
		
		for(int i=0; i<NumJugadores*2; i++){
			Leido.remove(0);
		}
		
		/*
		for(int i=0; i<NumJugadores; i++){
			System.out.print("\nNarc " +relleno.Narc[i]);
			System.out.print("\niNarc " +relleno.iNarc[i]);
		
		}
		*/
	
		relleno.AddCasilla(Tablero);

		
		return relleno;
	}
	
	
	static void LeerMechActivo(ArrayList<String> Leido, Mech relleno){
		
		int elementos=0;
		
		relleno.PAndar = Integer.parseInt(Leido.get(0));
		relleno.PCorrer = Integer.parseInt(Leido.get(1));
		relleno.PSaltar = Integer.parseInt(Leido.get(2));
		relleno.RadiadoresOn = Integer.parseInt(Leido.get(3));
		relleno.RadiadoresOff = Integer.parseInt(Leido.get(4));
		relleno.Heridas = Integer.parseInt(Leido.get(5));
		relleno.Consciente = Boolean.parseBoolean(Leido.get(6));
		
		for(int i=0; i<78; i++){
			relleno.Slots[i] = Boolean.parseBoolean(Leido.get(7+i));
		}
		for(int i=0; i<8; i++){
			relleno.LocDisparos[i] = Boolean.parseBoolean(Leido.get(85+i));
		}
		
		relleno.NumMuniciones = Integer.parseInt(Leido.get(93));
		Municion tempmun = new Municion();
		for(int i=0; i<relleno.NumMuniciones; i++){
			
			tempmun.LocMunicion=Leido.get(93+i);
			tempmun.SlotMunicion= Integer.parseInt(Leido.get(94+i));
			relleno.Municiones.add(tempmun);
			elementos = elementos+2;
		}
		
		elementos = elementos + 93;
		for(int i=0; i<elementos; i++){
			Leido.remove(0);
		}
		
		/*
		System.out.print("\n0 " + relleno.PAndar);
		System.out.print("\n0 " + relleno.PCorrer);
		System.out.print("\n0 " + relleno.PSaltar);
		System.out.print("\n0 " + relleno.RadiadoresOn);
		System.out.print("\n0 " + relleno.RadiadoresOff);
		System.out.print("\n0 " + relleno.Heridas);
		System.out.print("\n0 " + relleno.Consciente);
		
		for(int i=0; i<78; i++){
			System.out.print("\n0 " + relleno.Slots[i]);
		}
		
		for(int i=0; i<8; i++){
			System.out.print("\n0 " + relleno.LocDisparos[i]);
		}
		System.out.print("\n0 " + relleno.NumMuniciones);
		
		for(Municion d :relleno.Municiones)
			System.out.print("\n0 " + d);
		*/
	}
	
	static void AddCasilla(ArrayList<Casilla> Tablero){
		
		String SAncho = PosicionHex.substring(0, 2);
		String SAlto = PosicionHex.substring(2, 4);
		
		int Alto =  Integer.parseInt(SAlto);
		int Ancho = Integer.parseInt(SAncho);
		
		/*
		System.out.println("\nCoordenadas iniciales: "+PosicionHex);
		System.out.println("\nSubstrings: "+SAlto+" "+SAncho);
		System.out.println("\nAncho: "+Ancho+" Alto:"+Alto);
		*/
		
		for(Casilla Temp:Tablero){
			if(Temp.Alto == Alto && Temp.Ancho == Ancho){
				Casilla = Temp;
				break;
			}
		}
		
		
	}

}

