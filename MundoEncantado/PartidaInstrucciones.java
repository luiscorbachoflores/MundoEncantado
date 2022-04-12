import java.util.*;
import java.io.*;

public class PartidaInstrucciones {
	private LinkedHashMap <String,Jugador> jugadores;
	private LinkedHashMap <String,Criatura> bosque;
	private String archivo_instrucciones;
	private String archivo_salida;
	
	public PartidaInstrucciones(String archivoInstrucciones){
		this.jugadores=new LinkedHashMap<String,Jugador>();
		this.bosque=new LinkedHashMap<String,Criatura>();
		this.archivo_instrucciones=archivoInstrucciones;
		this.archivo_salida="PANTALLA";
	}
	
	public PartidaInstrucciones(String archivoInstrucciones,String archivoSalida){
		this.jugadores=new LinkedHashMap<String,Jugador>();
		this.bosque=new LinkedHashMap<String,Criatura>();
		this.archivo_instrucciones=archivoInstrucciones;
		this.archivo_salida=archivoSalida;
		new Salida(archivoSalida).crearFichero();
	}
	
	public void leerFicheroInstrucciones(){
		Scanner entrada = null;
		try {
			entrada = new Scanner(new FileInputStream(archivo_instrucciones));
		} catch (FileNotFoundException e) {
			System.out.println("Error abriendo el fichero de instrucciones");
			System.exit(-1);
		}
		String linea = null;
		int contador=1;
		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();
			this.leerInstruccion(linea);
		}
		entrada.close();
		return;
	}

	public void leerInstruccion(String linea){
		
		ParseadoInstrucciones parseado=new ParseadoInstrucciones();
		ArrayList <String> instruccionParseada=parseado.parseadoInstruccion(linea);
		
		String orden=instruccionParseada.get(0);
		InterpreteInstrucciones interprete=new InterpreteInstrucciones(jugadores,bosque);
		
		Salida salida=new Salida(archivo_salida);
		boolean instruccion;
		Criatura resultado;
		
		try{
		switch(orden){
			case "CargarJugadores":
				instruccion=comprobarFicheroEntrada(instruccionParseada.get(1));
				if(instruccion)	interprete.cargarJugadores(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearJugador":
				instruccion=interprete.crearJugador(instruccionParseada.get(1),instruccionParseada.get(2));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "BorrarJugador":
				instruccion=interprete.borrarJugador(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "VolcarJugadores":
				instruccion=comprobarFicheroSalida(instruccionParseada.get(1));
				if(instruccion) interprete.volcarJugadores(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CargarCriaturas":
				instruccion=comprobarFicheroEntrada(instruccionParseada.get(1));
				if(instruccion) interprete.cargarCriaturas(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearNinfa":
				instruccion=interprete.crearNinfa(instruccionParseada.get(1),instruccionParseada.get(2),instruccionParseada.get(3),instruccionParseada.get(4),instruccionParseada.get(5),instruccionParseada.get(6),instruccionParseada.get(7));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearOrco":
				instruccion=interprete.crearOrco(instruccionParseada.get(1),instruccionParseada.get(2),instruccionParseada.get(3),instruccionParseada.get(4),instruccionParseada.get(5));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearBruja":
				instruccion=interprete.crearBruja(instruccionParseada.get(1),instruccionParseada.get(2),instruccionParseada.get(3),instruccionParseada.get(4),instruccionParseada.get(5),instruccionParseada.get(6));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearElfo":
				instruccion=interprete.crearElfo(instruccionParseada.get(1),instruccionParseada.get(2),instruccionParseada.get(3),instruccionParseada.get(4),instruccionParseada.get(5));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "CrearLamia":
				instruccion=interprete.crearLamia(instruccionParseada.get(1),instruccionParseada.get(2),instruccionParseada.get(3),instruccionParseada.get(4),instruccionParseada.get(5),instruccionParseada.get(6));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "BorrarCriatura":
				instruccion=interprete.borrarCriatura(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "VolcarCriaturas":
				instruccion=comprobarFicheroSalida(instruccionParseada.get(1));
				if(instruccion) interprete.volcarCriaturas(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "MostrarCriatura":
				resultado=interprete.mostrarCriatura(instruccionParseada.get(1));
				if(resultado==null) instruccion=false;
				else instruccion=true;
				salida.imprimirInstruccionCriatura(linea,instruccion,resultado);
				break;
			case "CriaturaMasOfensiva":
				resultado=interprete.mostrarCriaturaMasOfensiva();
				if(resultado==null) instruccion=false;
				else instruccion=true;
				salida.imprimirInstruccionCriatura(linea,instruccion,resultado);
				break;
			case "CriaturaMasDefensiva":
				resultado=interprete.mostrarCriaturaMasDefensiva();
				if(resultado==null) instruccion=false;
				else instruccion=true;
				salida.imprimirInstruccionCriatura(linea,instruccion,resultado);
				break;
			case "Atacar":
				instruccion=interprete.ataque(instruccionParseada.get(1),instruccionParseada.get(2));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "VisitarLugarSagrado":
				instruccion=interprete.visitarLugarSagrado(instruccionParseada.get(1));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "GenerarAsignacionCriaturas":
				instruccion=comprobarFicheroSalida(instruccionParseada.get(1));
				if(instruccion)interprete.generarAsignacionCriaturas(instruccionParseada.get(1),instruccionParseada.get(2));
				salida.imprimirInstruccion(linea,instruccion);
				break;
			case "JugarPartida":
				instruccion=this.comprobarFicheros(instruccionParseada.get(1),instruccionParseada.get(2));
				salida.imprimirInstruccion(linea,instruccion);
				interprete.jugarPartida(instruccionParseada.get(1),instruccionParseada.get(2));
				break;
			}
			this.jugadores=interprete.getMapaJugadores();
			this.bosque=interprete.getMapaCriaturas();
		}catch(IndexOutOfBoundsException e){
			System.out.println("Hay un error con los parametros");
		}	
		//NUEVA CLASE CON EL ARRAY COMO PARAMETRO Y AMBOS LINKED HASH MAPS
	}
	
	public boolean comprobarFicheros(String fichero_entrada,String fichero_salida){
		if(comprobarFicheroEntrada(fichero_entrada) && comprobarFicheroSalida(fichero_salida)){
			return true;
		}
		else return false;
	}
	
	public boolean comprobarFicheroEntrada(String fichero_entrada){
		Scanner entrada=null;
		try {
			entrada = new Scanner(new FileInputStream(fichero_entrada));
		} catch (FileNotFoundException e) {
			return(false);
		}
		
		return(true);
	}
	
	public boolean comprobarFicheroSalida(String fichero_salida){
		PrintWriter salida=null;
		try {
			salida = new PrintWriter(new FileOutputStream(fichero_salida));
		} catch (FileNotFoundException e) {
			return(false);
		}
		
		return(true);
	}
}

