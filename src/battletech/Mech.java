package battletech;

import java.util.ArrayList;

import sun.tools.tree.ThisExpression;

public class Mech {

	//Atributos generales
	static int NumJugador;
	static boolean Operativo;
	static boolean Desconectado;
	static boolean Atascado;
	static boolean EnSuelo;
	static public Casilla CasillaPos = new Casilla();
	static String PosicionHex; //Por ahora
	static int Encaramiento;
	static int EncaramientoTorso;
	static int Temperatura;
	static boolean Ardiendo;
	static boolean Garrote;
	static int TipoGarrote;
	
	static int[] Blindaje = new int[11];
	static int [] EstructuraInterna = new int[8];
	
	static boolean[] Narc;
	static boolean[] iNarc;
	

	
	
	//Atributos s—lo para el jugador activo
	static int PAndar;
	static int PCorrer;
	static int PSaltar;
	static int RadiadoresOn;
	static int RadiadoresOff;
	static int Heridas;
	static boolean Consciente;
	static boolean[] Slots = new boolean[78];
	static boolean []LocDisparos = new boolean[8];
	static int NumMuniciones;
	static ArrayList<Municion> Municiones = new ArrayList<Municion>();
	
	
	

	Mech(){
		CasillaPos = new Casilla();
		PosicionHex = "";
		
	}
	
	@SuppressWarnings("static-access")
	static void LeerMech(ArrayList<String> Leido, int Activo, int NumJugadores, ArrayList<Casilla> Tablero){
		
	//	Mech relleno = new Mech();
		//System.out.print("\n\n Inicio mech  \n\n");
		NumJugador = Integer.parseInt(Leido.get(0));
		Operativo = Boolean.parseBoolean(Leido.get(1));
		Desconectado = Boolean.parseBoolean(Leido.get(2));
		Atascado = Boolean.parseBoolean(Leido.get(3));
		EnSuelo = Boolean.parseBoolean(Leido.get(4));
		PosicionHex = Leido.get(5);
		Encaramiento = Integer.parseInt(Leido.get(6));
		EncaramientoTorso = Integer.parseInt(Leido.get(7));
		Temperatura = Integer.parseInt(Leido.get(8));
		Ardiendo = Boolean.parseBoolean(Leido.get(9));
		Garrote = Boolean.parseBoolean(Leido.get(10));
		TipoGarrote = Integer.parseInt(Leido.get(11));
		
		for(int i=0; i<11; i++){
			Blindaje[i] = Integer.parseInt(Leido.get(i+12));
		}
		for(int i=0; i<8;i++){
			EstructuraInterna[i] = Integer.parseInt(Leido.get(i+23));
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
		
		if(NumJugador == Activo){
			LeerMechActivo(Leido);	
		}
		
		Narc = new boolean[NumJugadores];
		iNarc = new boolean[NumJugadores];
		
		for(int i=0; i<NumJugadores*2; i++){
			
			if(i<NumJugadores){
				Narc[i] = Boolean.parseBoolean(Leido.get(i)); 
			}else{
				iNarc[i-NumJugadores] = Boolean.parseBoolean(Leido.get(i));
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
	
		CasillaPos = AddCasilla(Tablero);
		
	//	System.out.print("\n\n--RELLENO " +relleno.CasillaPos.Alto + "  ---"+relleno.CasillaPos.Ancho);

		
		//return relleno;
	}
	
	
	static void LeerMechActivo(ArrayList<String> Leido){
		
		int elementos=0;
		
		PAndar = Integer.parseInt(Leido.get(0));
		PCorrer = Integer.parseInt(Leido.get(1));
		PSaltar = Integer.parseInt(Leido.get(2));
		RadiadoresOn = Integer.parseInt(Leido.get(3));
		RadiadoresOff = Integer.parseInt(Leido.get(4));
		Heridas = Integer.parseInt(Leido.get(5));
		Consciente = Boolean.parseBoolean(Leido.get(6));
		
		for(int i=0; i<78; i++){
			Slots[i] = Boolean.parseBoolean(Leido.get(7+i));
		}
		for(int i=0; i<8; i++){
			LocDisparos[i] = Boolean.parseBoolean(Leido.get(85+i));
		}
		
		NumMuniciones = Integer.parseInt(Leido.get(93));
		Municion tempmun = new Municion();
		for(int i=0; i<NumMuniciones; i++){
			
			tempmun.LocMunicion=Leido.get(93+i);
			tempmun.SlotMunicion= Integer.parseInt(Leido.get(94+i));
			Municiones.add(tempmun);
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
	
	@SuppressWarnings("static-access")
	static Casilla AddCasilla(ArrayList<Casilla> Tablero){
		
		String SAncho = PosicionHex.substring(0, 2);
		String SAlto = PosicionHex.substring(2, 4);
		
		int FAlto =  Integer.parseInt(SAlto);
		int FAncho = Integer.parseInt(SAncho);
		
	/*	
		System.out.println("\nCoordenadas iniciales: "+PosicionHex);
		System.out.println("\nSubstrings: "+SAlto+" "+SAncho);
		System.out.println("\nAncho: "+FAncho+" Alto:"+FAlto);
	*/
		Casilla Pos = new Casilla();
		for(Casilla Temp:Tablero){
			if(Temp.Alto == FAlto && Temp.Ancho == FAncho){
				Pos = Temp;
				//Pos.ocupada = true;
				break;
			}
		}
		
		//System.out.println("ALTO: ASIGNACASILLA: "+Mech.CasillaPos.Alto);
		return Pos;
	}

	
}

