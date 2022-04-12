import java.util.*;

public interface TemploMaldito {
	public static TreeMap <String,Integer> libroVisitasTemplo=new TreeMap<String,Integer>();
	
	public void orar();
	public static void anadirVisita(UsuarioLugarSagrado criatura){
			libroVisitasTemplo.put(criatura.getID(),criatura.getVisitasLugarSagrado());
	
	}
	
	public static String leerLibro(){
		Iterator itTemplo=libroVisitasTemplo.keySet().iterator();
		String resultado="Templo Maldito: {";
		while(itTemplo.hasNext()){
			String id=(String)itTemplo.next();
			resultado=resultado+id+"="+libroVisitasTemplo.get(id)+", ";
		}
		resultado=resultado.substring(0,resultado.length()-2)+"}";
		return(resultado);
	}
}

