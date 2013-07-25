package battletech;

import java.util.ArrayList;

public class Casilla extends Tablero {

	int Nivel;
	int TipoTerreno;
	int Objeto;
	int FCEEdificio;
	boolean EdificioDown;
	boolean Fuego;
	boolean Humo;
	int NumGarrotes;
	
	boolean[] RioCaras = new boolean[6];
	boolean[] CarreteraCaras = new boolean[6];


	static Casilla LeerCasilla(ArrayList<String> Leido){
		Casilla temp = new Casilla();

		temp.Nivel = Integer.parseInt(Leido.get(0));
		temp.TipoTerreno = Integer.parseInt(Leido.get(1));
		temp.Objeto = Integer.parseInt(Leido.get(2));
		temp.FCEEdificio = Integer.parseInt(Leido.get(3));
		temp.EdificioDown = Boolean.parseBoolean(Leido.get(4));
		temp.Fuego = Boolean.parseBoolean(Leido.get(5));
		temp.Humo = Boolean.parseBoolean(Leido.get(6));
		temp.NumGarrotes = Integer.parseInt(Leido.get(7));;
		
		System.out.print("\nCASILLA\n");
		System.out.print("\nNivel: " + temp.Nivel);
		System.out.print("\nTipoTerreno: " + temp.TipoTerreno);
		System.out.print("\nObjeto: " + temp.Objeto);
		System.out.print("\nFCEEdificio: " + temp.FCEEdificio);
		System.out.print("\nEdificioDown: " + temp.EdificioDown);
		System.out.print("\nFuego: " + temp.Fuego);
		System.out.print("\nHumo: " + temp.Humo);
		System.out.print("\nNumGarrotes: " + temp.NumGarrotes);
		
		for(int i=0; i<6; i++){
			temp.RioCaras[i] = Boolean.parseBoolean(Leido.get(8+i));
		}
		
		for(int i=0; i<6; i++){
			temp.CarreteraCaras[i] = Boolean.parseBoolean(Leido.get(13+i));
		}
		for(int i=0; i<20; i++){
			Leido.remove(0);
		}
		
		return temp;
	}
}
