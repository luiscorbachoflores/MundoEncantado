import java.util.*;

public class Batalla {
	private LinkedHashMap<String,Jugador> jugadores=new LinkedHashMap<String,Jugador>();
	private LinkedHashMap<String,Criatura> criaturas=new LinkedHashMap<String,Criatura>();
	private String tipoReparto;
	private Salida salida;
	private int contador_lineas;
	
	public Batalla(LinkedHashMap<String,Jugador> players, LinkedHashMap<String,Criatura> monsters,int contador_lineas,String reparto,Salida salida){
		this.jugadores=players;
		this.criaturas=monsters;
		this.tipoReparto=reparto;
		this.contador_lineas=contador_lineas;
		this.salida=salida;
	}
	
	public void reparto(){
		RepartoCriaturas reparto=new RepartoCriaturas(jugadores,criaturas,tipoReparto);
		if (tipoReparto.equals("ALEATORIO")){
			reparto.asignarCriaturasAleatoriamente();
		}
		else{
			String linea=reparto.leerLineaFicheroReparto(contador_lineas);
			reparto.parsearLineaReparto(linea); //Asigna las criaturas segun la linea
		}
	}
	
	public String retornarBatallaActual(Jugador atacante,Jugador defensor,int numeroLucha){
		String resultado="LUCHA "+numeroLucha+":"+atacante.getID()+"-"+atacante.getAtacante().toStringBatalla();
		return resultado;
	}
	
	public int jugadoresVivos(){  //retorna la cantidad de jugadores vivos en el linked hash map
		int noNeutralizados=0;
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
				Object jugador=itJugadores.next();
				if(jugadores.get(jugador).algunaCriaturaViva()){
					noNeutralizados++;
				}
		}
		return noNeutralizados;
	}
	
	public void batalla(){
		int contador=0;
		Jugador atacante=null;
		Jugador defensor=null;
		int tipoFin=1;	
		while(jugadoresVivos()>1 && contador<10){
			
			tipoFin=0;
			
			atacante=getJugadorAtacante(defensor);
			defensor=getJugadorDefensor(atacante);
			
			Lucha lucha=new Lucha(atacante,defensor,salida);
			
			lucha.imprimirLucha(contador+1); //jA - O --> jB - E
			lucha.ejecutarLucha();
			salida.imprimirLinea("  "+this.toString()); //
			
			if(jugadoresVivos()<1){
				tipoFin=2;
				break;
			}
			
			if(jugadoresVivos()<2){
				tipoFin=1;
				break;
			}
			contador++;
		}
		salida.imprimirLinea();
		finalizarBatalla(tipoFin);
		salida.imprimirLinea();		
	}

	public Jugador getJugadorAtacante(Jugador ultimo){ //Se le manda el jugador que fue defensor
		Iterator itJugadores=jugadores.keySet().iterator();
		if(ultimo==null){
			while(itJugadores.hasNext()){				//Busca el primero con vida si el parametro es null
				Object jugador=itJugadores.next();
				if(jugadores.get(jugador).algunaCriaturaViva()){
					return(jugadores.get(jugador));
				}
			}
		}		
		else if(!ultimo.algunaCriaturaViva()){ 			//Si el parametro es un jugador sin criaturas vivas
			boolean next=false;
			while(true){
				while(itJugadores.hasNext()){
					Object jugador=itJugadores.next();
					if(jugadores.get(jugador).algunaCriaturaViva() && next){
						return(jugadores.get(jugador));
					}
					if(jugadores.get(jugador).getID().equals(ultimo.getID())){
						next=true;
					}
				}
				itJugadores=jugadores.keySet().iterator();
				}
			}
		return(ultimo);
		}

	public Jugador getJugadorDefensor(Jugador atacante){
		Iterator itJugadores=jugadores.keySet().iterator();
		boolean next=false;
		while(true){
			while(itJugadores.hasNext()){
				Object jugador=itJugadores.next();
				if(jugadores.get(jugador).algunaCriaturaViva() && next){
					return(jugadores.get(jugador));
				}
				if(jugadores.get(jugador).getID().equals(atacante.getID())){
					next=true;
				}
			}
			itJugadores=jugadores.keySet().iterator();
		}
	}
	
	public void sumarPuntuacion(int cuantos){
		int puntosSumar=1;
		if (cuantos==0) puntosSumar=0;
		else if(cuantos==1)	puntosSumar=2;
		Iterator itJugadores=jugadores.keySet().iterator();
		String lineaImprimir="";
		while(itJugadores.hasNext()){
			Object llave=itJugadores.next();
			Jugador jugador=jugadores.get(llave);
			int puntos=jugador.cuantasCriaturasVivas()*puntosSumar;
				
			lineaImprimir=lineaImprimir+jugador.getID()+"="+puntos;
			if(itJugadores.hasNext()) lineaImprimir=lineaImprimir+",";
			jugador.sumarPuntos(puntos);
		}
		salida.imprimirPuntosBatalla(lineaImprimir);
	}

	public void desligarCriaturas(){
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Object llave=itJugadores.next();
			Jugador jugador=jugadores.get(llave);
			LinkedHashMap<String,Criatura> equipoCriaturas=new LinkedHashMap<String,Criatura>();
			jugador.setEquipo(equipoCriaturas);
		}
	}

	public void restaurarVidaCriaturas(){
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Jugador jugador=jugadores.get(itJugadores.next());
			Iterator itCriaturas=jugador.getEquipo().keySet().iterator();
			while(itCriaturas.hasNext()){
				Criatura criatura=jugador.getEquipo().get(itCriaturas.next());
				if(criatura instanceof UsuarioLugarSagrado && criatura.getSalud()>0){
					UsuarioLugarSagrado usuarioSagrado=(UsuarioLugarSagrado)criatura;
					usuarioSagrado.recuperarse();
				}
			}
		}
	}

	public void imprimirComienzo(){
		salida.imprimirLinea("BATALLA_"+(contador_lineas+1)+" "+this.toString());
	}
	
	public String toString(){	//pA:(o1,b10),pb..
		Iterator itJugadores=jugadores.keySet().iterator();
		String resultado="";
		while(itJugadores.hasNext()){
			Jugador jugador=jugadores.get(itJugadores.next());
			resultado=resultado+jugador.toStringBatalla()+",";
		}
		resultado=resultado.substring(0,resultado.length()-1);
		return(resultado);
	}
	
	public void finalizarBatalla(int tipoFin){
		if(tipoFin==1) salida.imprimirLinea("  FIN BATALLA: Solo hay un jugador activo.");
		else if(tipoFin==0) salida.imprimirLinea("  FIN BATALLA: Ya se han producido 10 luchas.");
		else if(tipoFin==2) salida.imprimirLinea("  FIN BATALLA: No hay jugadores activos.");
		this.sumarPuntuacion(jugadoresVivos());
		
	}
}

