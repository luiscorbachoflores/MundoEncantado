import java.util.*;
import java.io.*;

public class Salida {
	private String archivo_salida;
	private LinkedHashMap <String,Jugador> jugadores;
	
	public Salida(String salida){
		this.archivo_salida=salida;
	}
	
	public Salida(String salida,LinkedHashMap <String,Jugador> mapaJugadores){
		this.archivo_salida=salida;
		this.jugadores=mapaJugadores;
	}
	
	public void crearFichero(){
		if(archivo_salida.equals("PANTALLA")){
			return;
		}
		else{
			PrintWriter salida = null; // a�adirla
			try {
				salida = new PrintWriter(new FileOutputStream(archivo_salida));
			} catch (FileNotFoundException e) {
				System.out.println("Error abriendo el fichero");
				System.exit(-1);
			}
			salida.close();
		}
	}
	
	public void imprimirLinea(String linea){
		if(archivo_salida.equals("PANTALLA")){
			System.out.println(linea);
			return;
		}
		else{
			PrintWriter salida = null; // a�adirla
			try {
				salida = new PrintWriter(new FileOutputStream(archivo_salida,true));
			} catch (FileNotFoundException e) {
				System.out.println("Error abriendo el fichero");
				System.exit(-1);
			}
			salida.write(linea+"\n");
			salida.close();	
			return;	
		}
	}
	
	public void imprimirInstruccion(String instruccion,boolean boolResultado){
		String resultado;
		if(boolResultado){
			resultado="OK.";
		}
		else{
			resultado="FAIL.";
		}
		String linea=instruccion+": "+resultado;
		imprimirLinea(linea);
	}
	
	public void imprimirInstruccionCriatura(String instruccion,boolean boolResultado,Criatura criaturaResultante){
		String resultado;
		if(boolResultado){
			resultado=criaturaResultante.toString();
		}
		else{
			resultado="FAIL.";
		}
		String linea=instruccion+": "+resultado;
		imprimirLinea(linea);
	}

	public void imprimirPuntosBatalla(String datosPuntuacion){
		imprimirLinea("  PUNTOS CONSEGUIDOS: "+datosPuntuacion);
	}

	public void imprimirVisitasLugaresSagrados(){
		String rotulo="VISITAS A LOS LUGARES SAGRADOS:";
		imprimirLinea(rotulo);
		imprimirLinea("  "+LagoSagrado.leerLibro());
		imprimirLinea("  "+TemploMaldito.leerLibro());
		imprimirLinea();
	}
	
	public void imprimirPuntosFinales(int puntuacionMaxima){
		String rotulo="PUNTUACIONES:";
		imprimirLinea(rotulo);
		String etiquetaGanador="";
		Iterator itJugadores=jugadores.keySet().iterator();
		while(itJugadores.hasNext()){
			Jugador entrenador=jugadores.get(itJugadores.next());
			if(entrenador.getPuntuacion()==puntuacionMaxima) etiquetaGanador=" (VENCEDOR)";
			else etiquetaGanador="";
			imprimirLinea("  "+entrenador.getNombre()+" ("+entrenador.getID()+") = "+entrenador.getPuntuacion()+etiquetaGanador);
		}
	}
	
	public void imprimirLinea(){
		this.imprimirLinea("");
	}
}

