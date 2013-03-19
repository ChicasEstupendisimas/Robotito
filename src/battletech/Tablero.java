package battletech;

import java.util.ArrayList;

public class Tablero {
	int Alto;
	int Ancho;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();

	
	
	void LeerMapa(ArrayList<String> Leido){
		Alto  = Integer.parseInt(Leido.get(1));
		Ancho = Integer.parseInt(Leido.get(2));
		
		for(int i=0; i<3; i++){
			Leido.remove(0);
		}
		for(int i=0; i<Alto*Ancho ; i++){
			Casilla temp = Casilla.LeerCasilla(Leido);
			Casillas.add(temp);
		}
		
	}
}
