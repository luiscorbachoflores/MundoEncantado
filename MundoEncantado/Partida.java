import java.util.*;
public class Partida {

	private LinkedHashMap<String,Jugador> jugadores;
	private LinkedHashMap<String,Criatura> bosque;
	private String salida;
	private String tipoReparto;
	
	public Partida(LinkedHashMap<String,Jugador> players, LinkedHashMap<String,Criatura> monsters,String deal,String out){
		jugadores=players;
		bosque=monsters;
		tipoReparto=deal;
		salida=out;				
	}
	
	public void realizarPartida(){
		Salida modoSalida=new Salida(salida,jugadores);
		int contador_lineas=0;
		while(!hayGanador() && !todasCriaturasNeutralizadas()){
			Batalla nuevaBatalla=new Batalla(jugadores,bosque,contador_lineas,tipoReparto,modoSalida);
			nuevaBatalla.reparto();
			nuevaBatalla.imprimirComienzo();
			nuevaBatalla.batalla();
			nuevaBatalla.restaurarVidaCriaturas();
			nuevaBatalla.desligarCriaturas();
			contador_lineas++;
		}
		this.finalizarPartida(modoSalida);
	}
	
	public boolean hayGanador(){
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Object clave=itJugadores.next();
			Jugador jugador=jugadores.get(clave);
			if(jugador.getPuntuacion()>=10) return true;
		}
		return false;
	}
	
	public boolean todasCriaturasNeutralizadas(){
		Iterator itCriaturas=bosque.keySet().iterator();
		int contadorCriaturasNeutralizadas=0;
		while(itCriaturas.hasNext()){
			Object clave=itCriaturas.next();
			Criatura criatura=bosque.get(clave);
			if(criatura.getSalud()>0) contadorCriaturasNeutralizadas++;
		}
		if (contadorCriaturasNeutralizadas>0) return false;
		else return true;
	}

	public int getPuntuacionMaxima(){
		Iterator itJugadores=jugadores.keySet().iterator();
		int maximo=0;
		while(itJugadores.hasNext()){
			Object clave=itJugadores.next();
			Jugador jugador=jugadores.get(clave);
			if(jugador.getPuntuacion()>=maximo) maximo=jugador.getPuntuacion();
		}
		return maximo;
	}
	
	public void finalizarPartida(Salida salida){
		salida.imprimirVisitasLugaresSagrados();
		salida.imprimirPuntosFinales(getPuntuacionMaxima());
	}	
}


