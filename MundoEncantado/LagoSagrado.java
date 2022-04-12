import java.util.*;

public interface LagoSagrado {
	public static TreeMap <String,Integer> libroVisitasLago=new TreeMap<String,Integer>();
	
	public void meterseEnLago();
	public static void anadirVisita(UsuarioLugarSagrado criatura){
			libroVisitasLago.put(criatura.getID(),criatura.getVisitasLugarSagrado());
	
	}
	
	public static String leerLibro(){
		Iterator itLago=libroVisitasLago.keySet().iterator();
		String resultado="Lago Sagrado: {";
		while(itLago.hasNext()){
			String id=(String)itLago.next();
			resultado=resultado+id+"="+libroVisitasLago.get(id)+", ";
		}
		resultado=resultado.substring(0,resultado.length()-2)+"}";
		return(resultado);
	}
}

