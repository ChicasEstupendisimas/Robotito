package battletech;

import java.util.ArrayList;

public class Tablero {
	int AltoT;
	int AnchoT;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();

	
	
	void LeerMapa(ArrayList<String> Leido){
		
		AltoT  = Integer.parseInt(Leido.get(1));
		AnchoT = Integer.parseInt(Leido.get(2));
			
		System.out.print("\nALTO: " + AltoT);
		System.out.print("\nANCHO: " + AnchoT);
		
		for(int i=0; i<3; i++){
			Leido.remove(0);
		}

		for(int i=1; i<=AnchoT ; i++){
			for(int j=1; j<=AltoT; j++){	
				Casilla temp = Casilla.LeerCasilla(Leido);
				temp.Ancho = j;
				temp.Alto = i;
				Casillas.add(temp);
			//	System.out.println("\nFila:"+temp.fila+" "+temp.colum);
			}
		}		
	}
	
	
}
