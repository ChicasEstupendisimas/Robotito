package battletech;

import java.util.ArrayList;

public class Mech {

	//Atributos generales
	int NumJugador;
	boolean Operativo;
	boolean Desconectado;
	boolean Atascado;
	boolean EnSuelo;
	String PosicionHex; //Por ahora
	int Encaramiento;
	int EncaramientoTorso;
	int Temperatura;
	boolean Ardiendo;
	boolean Garrote;
	int TipoGarrote;
	
	int[] Blindaje = new int[11];
	int [] EstructuraInterna = new int[8];
	
	boolean Narc;
	boolean iNarc;
	
	
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
	ArrayList<String> LocMuniciones = new ArrayList<String>();
	

	Mech(){}
	
	static Mech LeerMech(ArrayList<String> Leido, int Activo, int NumJugadores ){
		
		Mech relleno = new Mech();

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
			relleno.EstructuraInterna[i] = Integer.parseInt(Leido.get(i+13));
			
		} 
		
		for(int i=0; i<31; i++){
			Leido.remove(0);
		}

		
		if(relleno.NumJugador == Activo){
			LeerMechActivo(Leido, relleno);	
		}
		
		for(int i=0; i<NumJugadores*2; i++){
			if(i==relleno.NumJugador){
				relleno.Narc = Boolean.parseBoolean(Leido.get(i)); 
			}
			if(i== relleno.NumJugador+NumJugadores){
				relleno.iNarc = Boolean.parseBoolean(Leido.get(i));
			}		
		}
		
		for(int i=0; i<NumJugadores*2; i++){
			Leido.remove(0);
		}
		
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
		System.out.print("\nNarc " +relleno.Narc);
		System.out.print("\niNarc " +relleno.iNarc);
		
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
		for(int i=0; i<relleno.NumMuniciones; i++){
			relleno.LocMuniciones.add(Leido.get(93+i));
			relleno.LocMuniciones.add(Leido.get(94+i));
			elementos = elementos+2;
		}
		
		elementos = elementos + 93;
		for(int i=0; i<elementos; i++){
			Leido.remove(0);
		}
		
	}
/*
	Mech LeerMech(ArrayList<String> leido){
		
		
	}
*/

}

