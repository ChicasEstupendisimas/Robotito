package battletech;

import java.util.ArrayList;

public class Tablero {
	int Alto;
	int Ancho;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();

	
	
	void LeerMapa(ArrayList<String> Leido){
		
		Alto  = Integer.parseInt(Leido.get(1));
		Ancho = Integer.parseInt(Leido.get(2));
			
		System.out.print("\nALTO: " + Alto);
		System.out.print("\nANCHO: " + Ancho);
		
		for(int i=0; i<3; i++){
			Leido.remove(0);
		}

		for(int i=1; i<=Ancho ; i++){
			for(int j=1; j<=Alto; j++){	
				Casilla temp = Casilla.LeerCasilla(Leido);
				temp.Ancho = j;
				temp.Alto = i;
				Casillas.add(temp);
			//	System.out.println("\nFila:"+temp.fila+" "+temp.colum);
			}
		}		
	}
	
	
}
