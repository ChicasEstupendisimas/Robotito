package battletech;

import java.util.ArrayList;

/*!
 * Lectura y almacenaje de la configuraci—n de la partida.
 */
public class Configuracion {
	
	boolean IncendiosPermitidos;
	boolean Viento;
	int DireccionViento;
	boolean AtaquesFisicos;
	boolean FaseCalor;
	boolean DevastarBosques;
	boolean DerrumbarEdificios;
	boolean ChequeoPilotaje;
	boolean ChequeoDato;
	boolean ChequeoDesconexion;
	boolean ImpactoCritico;
	boolean ExplosionMunicion;
	boolean ApagarRadiadores;
	boolean LimTiempoRespuesta;
	int LimiteTiempo;

	/*!
	 * Constructor por defecto
	 */
	Configuracion(){
		
	}
	
	/*!
	 * Lectura del archivo de configuraci—n
	 */
	void LeerConfiguracion(ArrayList<String> leido){
		this.IncendiosPermitidos = Boolean.parseBoolean(leido.get(1));
		this.Viento  = Boolean.parseBoolean(leido.get(2));
		this.DireccionViento = Integer.parseInt(leido.get(3));
		this.AtaquesFisicos = Boolean.parseBoolean(leido.get(4));
		this.FaseCalor = Boolean.parseBoolean(leido.get(5));
		this.DevastarBosques = Boolean.parseBoolean(leido.get(6));
		this.DerrumbarEdificios= Boolean.parseBoolean(leido.get(7));
		this.ChequeoPilotaje = Boolean.parseBoolean(leido.get(8));
		this.ChequeoDato = Boolean.parseBoolean(leido.get(9));
		this.ChequeoDesconexion= Boolean.parseBoolean(leido.get(10));
		this.ImpactoCritico = Boolean.parseBoolean(leido.get(11));
		this.ExplosionMunicion = Boolean.parseBoolean(leido.get(12));
		this.ApagarRadiadores = Boolean.parseBoolean(leido.get(13));
		this.LimTiempoRespuesta = Boolean.parseBoolean(leido.get(14));
		this.LimiteTiempo = Integer.parseInt(leido.get(15));

		
		/* TODO: eliminar esto
		 * se supone que funciona pero dejo la comprobaci—n por si hace falta
		 * no tener que escribir todo ese co–azo otra vez
		 	System.out.print("\n1 " + IncendiosPermitidos );
			System.out.print("\n2 " +Viento );
			System.out.print("\n3 " +DireccionViento );
			System.out.print("\n4 " +AtaquesFisicos );
			System.out.print("\n5 " +FaseCalor );
			System.out.print("\n6 " +DevastarBosques );
			System.out.print("\n7 " +DerrumbarEdificios);
			System.out.print("\n8 " +ChequeoPilotaje );
			System.out.print("\n9 " +ChequeoDa–o);
			System.out.print("\n10 " +ChequeoDesconexion);
			System.out.print("\n11 " +ImpactoCritico);
			System.out.print("\n12 " +ExplosionMunicion);
			System.out.print("\n13 " +ApagarRadiadores);
			System.out.print("\n14 " +LimTiempoRespuesta );
			System.out.print("\n15 " +LimiteTiempo);
		 */
		
	}
	
	
}
