package battletech;

import java.util.ArrayList;

public class Mech {

	//Atributos generales
	int NumJugador;
	boolean Operativo;
	boolean Desconectado;
	boolean Atascado;
	boolean EnSuelo;
	public Casilla CasillaPos = new Casilla();
	String PosicionHex; //Por ahora
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
	
	//Valores para la actitud del mech
	EnumActitud Actitud;
	float Puntos;
	float DistanciaOptima;

	

	Mech(){
		this.NumJugador = -1;
		this.CasillaPos = new Casilla();
		this.PosicionHex = "";
		this.Puntos = 0;
		this.Actitud = Actitud.Equilibrada;
		this.DistanciaOptima = 0;
		
	}
	
	@SuppressWarnings("static-access")
	Mech(ArrayList<String> Leido, int Activo, int NumJugadores, ArrayList<Casilla> Tablero){
		
	//	Mech relleno = new Mech();
		//System.out.print("\n\n Inicio mech  \n\n");
		this.NumJugador = Integer.parseInt(Leido.get(0));
		this.Operativo = Boolean.parseBoolean(Leido.get(1));
		this.Desconectado = Boolean.parseBoolean(Leido.get(2));
		this.Atascado = Boolean.parseBoolean(Leido.get(3));
		this.EnSuelo = Boolean.parseBoolean(Leido.get(4));
		this.PosicionHex = Leido.get(5);
		this.Encaramiento = Integer.parseInt(Leido.get(6));
		this.EncaramientoTorso = Integer.parseInt(Leido.get(7));
		this.Temperatura = Integer.parseInt(Leido.get(8));
		this.Ardiendo = Boolean.parseBoolean(Leido.get(9));
		this.Garrote = Boolean.parseBoolean(Leido.get(10));
		this.TipoGarrote = Integer.parseInt(Leido.get(11));
		
		for(int i=0; i<11; i++){
			this.Blindaje[i] = Integer.parseInt(Leido.get(i+12));
		}
		for(int i=0; i<8;i++){
			this.EstructuraInterna[i] = Integer.parseInt(Leido.get(i+23));
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
		
		if(this.NumJugador == Activo){
			LeerMechActivo(Leido);	
		}
		
		this.Narc = new boolean[NumJugadores];
		this.iNarc = new boolean[NumJugadores];
		
		for(int i=0; i<NumJugadores*2; i++){
			
			if(i<NumJugadores){
				this.Narc[i] = Boolean.parseBoolean(Leido.get(i)); 
			}else{
				this.iNarc[i-NumJugadores] = Boolean.parseBoolean(Leido.get(i));
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
	
		this.CasillaPos = AddCasilla(Tablero);
		this.DistanciaOptima();
		
	//	System.out.print("\n\n--RELLENO " +relleno.CasillaPos.Alto + "  ---"+relleno.CasillaPos.Ancho);

		
		//return relleno;
	}
	
	
	void LeerMechActivo(ArrayList<String> Leido){
		
		int elementos=0;
		
		this.PAndar = Integer.parseInt(Leido.get(0));
		this.PCorrer = Integer.parseInt(Leido.get(1));
		this.PSaltar = Integer.parseInt(Leido.get(2));
		this.RadiadoresOn = Integer.parseInt(Leido.get(3));
		this.RadiadoresOff = Integer.parseInt(Leido.get(4));
		this.Heridas = Integer.parseInt(Leido.get(5));
		this.Consciente = Boolean.parseBoolean(Leido.get(6));
		
		for(int i=0; i<78; i++){
			this.Slots[i] = Boolean.parseBoolean(Leido.get(7+i));
		}
		for(int i=0; i<8; i++){
			this.LocDisparos[i] = Boolean.parseBoolean(Leido.get(85+i));
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
	Casilla AddCasilla(ArrayList<Casilla> Tablero){
		
		String SAncho = this.PosicionHex.substring(0, 2);
		String SAlto = this.PosicionHex.substring(2, 4);
		
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

	@SuppressWarnings("static-access")
	public EnumActitud ObtActitud (Mech Enemigo){
        
		//C‡lculo de puntos del mech activo
		for(int i=0; i<Blindaje.length; i++ ){
			this.Puntos += Blindaje[i];	
		}
		for(int i=0; i<EstructuraInterna.length; i++ ){
			this.Puntos += EstructuraInterna[i] * 1.25f;	
		}
        
        // C‡lculo de puntos del enemigo
		for(int i=0; i<Enemigo.Blindaje.length; i++ ){
			Enemigo.Puntos += Enemigo.Blindaje[i];	
		}
		for(int i=0; i<Enemigo.EstructuraInterna.length; i++ ){
			Enemigo.Puntos += Enemigo.EstructuraInterna[i] * 1.25f;	
		}
        
		if(this.Puntos <= (Enemigo.Puntos*1.25f)){
			this.Actitud = Actitud.Equilibrada;
		}else if(this.Puntos <= (Enemigo.Puntos*0.75f)){
			this.Actitud = Actitud.Defensiva;
		}else if(this.Puntos <= (Enemigo.Puntos*1.75f)){
			this.Actitud = Actitud.Ofensiva;
		}else{		
			this.Actitud = Actitud.MuyOfensiva;
		}

		return Actitud;
	}
	
	 
    public void DistanciaOptima(){
    	float temp = 0;
    	
    	if(Municiones.size() == 0){
    		this.DistanciaOptima = 0;
    	}
          
    	for(Municion Mun:Municiones){
    		temp += Mun.SlotMunicion;
    	}
    	
    	this.DistanciaOptima = (float) temp / Municiones.size();
    	if(this.DistanciaOptima == 0){
    		this.DistanciaOptima = 0.1f;
    	}
          
      }
 
	
}

