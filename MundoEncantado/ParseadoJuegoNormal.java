import java.util.*;
import java.io.*;

public class ParseadoJuegoNormal {
	private ParseadoJugadores leerJugadores;
	private ParseadoCriaturas leerCriaturas;
	private String archivo_reparto="ALEATORIO";
	private String archivo_salida="PANTALLA";
	
	public ParseadoJuegoNormal(){
	}
	
	public ParseadoJuegoNormal(String f_jugadores,String f_criaturas){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
		
		this.leerFicheros();
	}
	
	public ParseadoJuegoNormal(String f_jugadores,String f_criaturas,String f_reparto){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
		this.archivo_reparto=f_reparto;
		
		this.leerFicheros();
	}
	
	public ParseadoJuegoNormal(String f_jugadores,String f_criaturas,String f_reparto,String f_salida){
		this.leerJugadores=new ParseadoJugadores(f_jugadores);
		this.leerCriaturas=new ParseadoCriaturas(f_criaturas);
		this.archivo_reparto=f_reparto;
		this.archivo_salida=f_salida;
		
		this.leerFicheros();
		
		new Salida(archivo_salida).crearFichero();
	}
	
	public void setSalida(String f_salida){ //Para los casos en los que no se introduce el reparto pero si la salida
		this.archivo_salida=f_salida;
		new Salida(archivo_salida).crearFichero();
	}
	
	public String getSalida(){
		return (archivo_salida);
	}
	
	public String getReparto(){
		return (archivo_reparto);
	}
	
	public void leerFicheros(){
		leerFicheroCriaturas();
		leerFicheroJugadores();
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
	
}

