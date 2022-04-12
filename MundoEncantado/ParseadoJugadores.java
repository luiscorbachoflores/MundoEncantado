import java.util.*;
import java.io.*;

public class ParseadoJugadores {
	private String archivo_jugadores=null;
	private LinkedHashMap <String,Jugador> jugadores=new LinkedHashMap <String,Jugador>();
	
	public ParseadoJugadores(String f_jugadores){
		this.archivo_jugadores=f_jugadores;
	}
	
	public void leerFicheroJugadores(){
		Scanner entrada = null;
		try {
			entrada = new Scanner(new FileInputStream(archivo_jugadores));
		} catch (FileNotFoundException e) {
			System.out.println("Error abriendo el fichero de jugadores");
			System.exit(-1);
		}
		String linea = null;
		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();
			Jugador nuevoJugador=parseadoJugador(linea);
			jugadores.put(nuevoJugador.getID(),nuevoJugador);
		}
		entrada.close();
		return;
	}

	public Jugador parseadoJugador(String info){
		String ID=info.split(":")[0];
		String nombre=info.split(":")[1];
		nombre=nombre.replace("{","");
		nombre=nombre.replace("}","");
		Jugador nuevoJugador=new Jugador(ID,nombre);
		return (nuevoJugador);
		}
	
	public LinkedHashMap<String,Jugador> getMapaJugadores(){
		return(jugadores);
	}
}

