import java.util.*;
import java.io.*;

public class Parseado {
	private ParseadoJugadores leerJugadores;
	private ParseadoCriaturas leerCriaturas;
	private String archivo_reparto=null;
	private String archivo_salida="PANTALLA";
	
	private LinkedHashMap <Integer,String> reparto=new LinkedHashMap <Integer,String>();
	
	
	public Parseado(String f_jugadores,String f_criaturas){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
	}
	
	public Parseado(String f_jugadores,String f_criaturas,String f_reparto){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
		this.archivo_reparto=f_reparto;
	}
	
	public Parseado(String f_jugadores,String f_criaturas,String f_reparto,String f_salida){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
		this.archivo_reparto=f_reparto;
		this.archivo_salida=f_salida;
	}
	
	public void setSalida(String f_salida){ //Para los casos en los que no se introduce el reparto pero si la salida
		this.archivo_salida=f_salida;
	}
	
	
	public void leerFicheroCriaturas(){
		leerCriaturas.leerFicheroCriaturas();
	}
	
	public LinkedHashMap <String,Criatura> getMapaCriaturas(){
		return (leerCriaturas.getMapaCriaturas());
	}


	public void leerFicheroJugadores(){
		leerJugadores.leerFicheroJugadores();
	}
	
	public LinkedHashMap <String,Jugador> getMapaJugadores(){
		return(leerJugadores.getMapaJugadores());
	}
	
	
	public void leerFicheroReparto(){
		Scanner entrada = null;
		try {
			entrada = new Scanner(new FileInputStream(archivo_reparto));
		} catch (FileNotFoundException e) {
			System.out.println("Error abriendo el fichero de criaturas");
			System.exit(-1);
		}
		String linea = null;
		int contador=1;
		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();
			reparto.put(contador,linea);
			contador++;
		}
		entrada.close();
		return;
	}

	public String getLineaReparto(int i){
		return(reparto.get(i));
	}
	
	public LinkedHashMap<Integer,String> getReparto(){
		return(reparto);
	}
}

