import java.util.*;
import java.io.*;

public class ParseadoInstrucciones {
	
	public ParseadoInstrucciones(){
	}
	
	public ArrayList<String> parseadoInstruccion(String instruccion){
		ArrayList<String> instruccionParseada=new ArrayList<String>();
		String splits[]=instruccion.split(" ");
		for (String i:splits){
			instruccionParseada.add(i);
		}
		if(instruccionParseada.get(0).equals("CrearJugador") && instruccionParseada.size()>3){
			for(int i=3;i<instruccionParseada.size();i++){
				String nombreEntero=instruccionParseada.get(2)+" "+instruccionParseada.get(i);
				instruccionParseada.set(2,nombreEntero);
			}
			for(int i=instruccionParseada.size()-1;i>2;i--){
				instruccionParseada.remove(i);
			}
		}
		return (instruccionParseada);
	}
}

