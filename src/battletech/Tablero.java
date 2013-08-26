package battletech;

import java.util.ArrayList;

public class Tablero {
	int AltoT;
	int AnchoT;
	float Diagonal;
	ArrayList<Casilla> Casillas = new ArrayList<Casilla>();

	
	
	void LeerMapa(ArrayList<String> Leido){
		
		AltoT  = Integer.parseInt(Leido.get(1));
		AnchoT = Integer.parseInt(Leido.get(2));
		Diagonal = (float) Math.sqrt((AltoT*AltoT) + (AnchoT*AnchoT));
		
		System.out.print("\nALTO: " + AltoT);
		System.out.print("\nANCHO: " + AnchoT);
		
		for(int i=0; i<3; i++){
			Leido.remove(0);
		}

		for(int i=1; i<=AnchoT ; i++){
			for(int j=1; j<=AltoT; j++){	
				Casilla temp = new Casilla();
				temp.LeerCasilla(Leido);
				temp.Ancho = i;
				temp.Alto = j;
				Casillas.add(temp);
			//	System.out.println("\nFila:"+temp.fila+" "+temp.colum);
			}
		}		
	}
	
	
}
