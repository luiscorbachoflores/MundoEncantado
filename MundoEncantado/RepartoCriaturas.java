import java.util.*;
import java.io.*;

public class RepartoCriaturas {
	private LinkedHashMap<String,Jugador> jugadores;
	private LinkedHashMap<String,Criatura> criaturas;
	private String archivo_reparto;
	
	public RepartoCriaturas(LinkedHashMap<String,Jugador> players, LinkedHashMap<String,Criatura> criatures){
		this.jugadores=players;
		this.criaturas=criatures;
		this.archivo_reparto="ALEATORIO";
	}
	
	public RepartoCriaturas(LinkedHashMap<String,Jugador> players, LinkedHashMap<String,Criatura> criatures,String archivo_reparto){
		this.jugadores=players;
		this.criaturas=criatures;
		this.archivo_reparto=archivo_reparto;
	}
	
	public void asignarCriaturasAleatoriamente(){ 				//asignar criaturas a los jugadores aleatoriamente
		
		Iterator itCriaturas=criaturas.keySet().iterator();
		Iterator itJugadores=jugadores.keySet().iterator();
		int numJugadores=jugadores.size();
		int contador=0;
		int restantes=criaturas.size()-contador;
		boolean vueltaAcabada=false;
		ArrayList<String> claves=new ArrayList<String>();
		
		Random aleatorio=new Random();
		while(itCriaturas.hasNext()){ 					//genera un array con los ids de todas las criaturas
			Object clave=itCriaturas.next();
			claves.add(criaturas.get(clave).getID());
		}
		itCriaturas=criaturas.keySet().iterator();
		
		if(numJugadores>restantes){ //hay mas jugadores que criaturas
			System.out.println("La batalla no puede celebrarse porque no hay suficientes criaturas");
		}
		while(itCriaturas.hasNext()){
								
			Object jugador=itJugadores.next();
			Object bicho = itCriaturas.next();
			
			int numAleatorio=aleatorio.nextInt(claves.size());
			String IDnext=claves.get(numAleatorio);
			Criatura aAñadir=criaturas.get(IDnext);
			this.asignarCriatura(jugadores.get(jugador),aAñadir);
			contador++;
			claves.remove(numAleatorio);
			
			if(!itJugadores.hasNext()){
				itJugadores=jugadores.keySet().iterator();
				vueltaAcabada=true;
			}
			restantes=criaturas.size()-contador;
			if((restantes<numJugadores && vueltaAcabada) || (contador/numJugadores==3)){
				return;
			}
			vueltaAcabada=false;
		}
	}
	
	public String leerLineaFicheroReparto(int lineaRequerida){
		Scanner entrada = null;
		int contadorLineas=0;
		while(true){
			try {
				entrada = new Scanner(new FileInputStream(archivo_reparto));
			} catch (FileNotFoundException e) {
				System.out.println("Error abriendo el fichero de jugadores");
				System.exit(-1);
			}
			String linea = null;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				if(linea.equals("")){
					continue;
				}
				if(contadorLineas==lineaRequerida){
					return(linea);
				}
				contadorLineas++;
			}
			entrada.close();
		}
	}
	
	public void parsearLineaReparto(String linea){		//pA:{o1,n1,e3},pB:{n0,w2,e1},pC:{o2,e2,o0},pD:{n3,w3,w1}
		while(linea!=null){
			String IDjugador=null;
			String IDcriaturas=null;
			IDjugador=linea.split(":")[0];
			int siguiente=linea.indexOf(":")+2;
			linea=linea.substring(siguiente);
			IDcriaturas=linea.split("}")[0];
			if(linea.contains(":")){
				siguiente=linea.indexOf("}")+2;
				linea=linea.substring(siguiente);
			}
			else{
				linea=null;
			}
			asignarVariasCriaturas(IDjugador,IDcriaturas);
		}
	}
	

	public void asignarVariasCriaturas(String IDJugador,String IDsCriaturas){	//pA | o1,n1,e3
		Jugador jugador=jugadores.get(IDJugador);
		ArrayList<Criatura> criaturasParaAnadir=new ArrayList <Criatura>();
		String IDcriat=null;
		do{
			if(IDsCriaturas.contains(",")){
				IDcriat=IDsCriaturas.split(",")[0];
				IDsCriaturas=IDsCriaturas.substring(IDsCriaturas.indexOf(",")+1);
			}
			else{
				IDcriat=IDsCriaturas;
				IDsCriaturas=null;
			}
			Criatura anadida=criaturas.get(IDcriat);
			criaturasParaAnadir.add(anadida);		
		}while (IDsCriaturas!=null);
		asignarCriaturas(jugador,criaturasParaAnadir);
	}
	
	public void asignarCriaturas(Jugador player,ArrayList<Criatura> selected){	//Jugador | Array Criaturas
		for(int i=0;i<selected.size();i++){
			Criatura anadida=selected.get(i);
			asignarCriatura(player,anadida);
		}
	}
	
	public void asignarCriatura(Jugador player,Criatura selected){				//Jugador | Criatura
		player.anadirCriatura(selected);
	}

	public void desligarCriaturas(){
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Jugador player=jugadores.get(itJugadores.next());
			Iterator itCriaturas=player.getEquipo().keySet().iterator();
			while(itCriaturas.hasNext()){
				Criatura criatura=player.getEquipo().get(itCriaturas.next());
			}
			LinkedHashMap <String,Criatura> equipoVacio=new LinkedHashMap<String,Criatura>();
			player.setEquipo(equipoVacio);
		}
		return;
	}

	public String exportarReparto(){
		String linea="";
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Jugador player=jugadores.get(itJugadores.next());
			linea=linea+player.getID()+":{";
			Iterator itCriaturas=player.getEquipo().keySet().iterator();
			while(itCriaturas.hasNext()){
				Criatura criatura=player.getEquipo().get(itCriaturas.next());
				linea=linea+criatura.getID()+",";
			}
			linea=linea.substring(0,linea.length()-1);	//Borrar el caracter ',' sobrante
			linea=linea+"},";
		}
		linea=linea.substring(0,linea.length()-1);
		return(linea);
	}
}

