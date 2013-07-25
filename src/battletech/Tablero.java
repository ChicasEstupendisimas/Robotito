package battletech;

import java.util.ArrayList;

public class Tablero {
	int Alto;
	int Ancho;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();

	
	
	void LeerMapa(ArrayList<String> Leido){
		
		Alto  = Integer.parseInt(Leido.get(1));
		Ancho = Integer.parseInt(Leido.get(2));
		
	//	System.out.print("\nALTO: " + Alto);
	//	System.out.print("\nANCHO: " + Ancho);
		
		for(int i=0; i<3; i++){
			Leido.remove(0);
		}
		for(int i=0; i<Alto*Ancho ; i++){
		//	System.out.println("\nCASILLAS NUMERO: "+i);
			Casilla temp = Casilla.LeerCasilla(Leido);
			Casillas.add(temp);
		//	System.out.print("\n Casilla : "+i+" leido: "+temp);
		}
		
	}
}
