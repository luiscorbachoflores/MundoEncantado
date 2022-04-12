import java.util.*;

public class MapaCriaturas {
	private LinkedHashMap<String,Criatura> mapaCriaturas;
	
	public MapaCriaturas(LinkedHashMap<String,Criatura> mapa){
		this.mapaCriaturas=mapa;
	}
	
	public LinkedHashMap<String,Criatura> getMapaCriaturas(){
		return(mapaCriaturas);
	}
	
	public void ordenarPorFortaleza(){
		LinkedHashMap<String, Criatura> nuevaLista = new LinkedHashMap<String, Criatura>();
		
		Iterator it = mapaCriaturas.entrySet().iterator();
		ArrayList<Criatura> listaClaves = new ArrayList<Criatura>();
		while (it.hasNext()){
			Map.Entry entrada = (Map.Entry)it.next();
			listaClaves.add((Criatura)entrada.getValue());
		}
		
		Collections.sort(listaClaves,Criatura.FortalezaComparator);	// Ordenamos el ArrayList de claves
		
		listaClaves.stream().forEach(clave -> nuevaLista.put(((String)clave.getID()),clave));
		mapaCriaturas=nuevaLista;
	}
	
	public void ordenarPorPoderOfensivo(){
		LinkedHashMap<String, Criatura> nuevaLista = new LinkedHashMap<String, Criatura>();
		
		Iterator it = mapaCriaturas.entrySet().iterator();
		ArrayList<Criatura> listaClaves = new ArrayList<Criatura>();
		while (it.hasNext()){
			Map.Entry entrada = (Map.Entry)it.next();
			listaClaves.add((Criatura)entrada.getValue());
		}
		
		Collections.sort(listaClaves,Criatura.PoderOfensivoComparator);	// Ordenamos el ArrayList de claves
		
		listaClaves.stream().forEach(clave -> nuevaLista.put(((String)clave.getID()),clave));
		mapaCriaturas=nuevaLista;
	}
	
	public void ordenarPorCapacidadDefensiva(){
		LinkedHashMap<String, Criatura> nuevaLista = new LinkedHashMap<String, Criatura>();
		
		Iterator it = mapaCriaturas.entrySet().iterator();
		ArrayList<Criatura> listaClaves = new ArrayList<Criatura>();
		while (it.hasNext()){
			Map.Entry entrada = (Map.Entry)it.next();
			listaClaves.add((Criatura)entrada.getValue());
		}
		
		Collections.sort(listaClaves,Criatura.CapacidadDefensivaComparator);	// Ordenamos el ArrayList de claves
		
		listaClaves.stream().forEach(clave -> nuevaLista.put(((String)clave.getID()),clave));
		mapaCriaturas=nuevaLista;
	}

	public Criatura getPrimeraCriatura(){
		Criatura criaturaRetorno=null;
		Iterator itCriaturas = mapaCriaturas.keySet().iterator();
		if(itCriaturas.hasNext()) criaturaRetorno=mapaCriaturas.get(itCriaturas.next());
		return (criaturaRetorno);
	}

	public void ordenarPorID(){
		LinkedHashMap<String, Criatura> nuevaLista = new LinkedHashMap<String, Criatura>();
		
		Iterator it = mapaCriaturas.entrySet().iterator();
		ArrayList<Criatura> listaClaves = new ArrayList<Criatura>();
		while (it.hasNext()){
			Map.Entry entrada = (Map.Entry)it.next();
			listaClaves.add((Criatura)entrada.getValue());
		}
		
		Collections.sort(listaClaves,Criatura.IDComparator);	// Ordenamos el ArrayList de claves
		
		listaClaves.stream().forEach(clave -> nuevaLista.put(((String)clave.getID()),clave));
		mapaCriaturas=nuevaLista;
	}
}

