import java.util.*;

public class Jugador{
	private String ID;
	private String nombre;
	private LinkedHashMap<String,Criatura> equipoCriaturas=new LinkedHashMap<String,Criatura>();
	private int puntos;
	
	public Jugador(String id,String name){
		ID=id;
		nombre=name;
		puntos=0;
	}

	public String getNombre(){
		return(nombre);
	}
	
	public void setEquipo(LinkedHashMap<String,Criatura> equipo){
		this.equipoCriaturas=equipo;
	}

	public void sumarPuntos(int puntosSumados){
		puntos=puntos+puntosSumados;
	}

	public String getID(){
		return(ID);
	}
	
	public LinkedHashMap<String,Criatura> getEquipo(){
		return(equipoCriaturas);
	}
	
	public boolean algunaCriaturaViva(){
		Iterator itCriaturas=equipoCriaturas.keySet().iterator();
		while(itCriaturas.hasNext()){
				Object criatura=itCriaturas.next();
				if(equipoCriaturas.get(criatura).getSalud()>0){
					return true;
				}
		}
		return false;
	}
	
	public int cuantasCriaturasVivas(){
		int vivos=0;
		Iterator itCriaturas=equipoCriaturas.keySet().iterator();
		while(itCriaturas.hasNext()){
				Object criatura=itCriaturas.next();
				if(equipoCriaturas.get(criatura).getSalud()>0){
					vivos++;
				}
		}
		return vivos;
	}
	
	public String toString(){
		return(ID+","+nombre);
	}
	
	public void anadirCriatura(Criatura bicho){
		this.equipoCriaturas.put(bicho.getID(),bicho);
	}
	
	public Criatura getAtacante(){
		try{
			Criatura criaturaAtacante=null;
			MapaCriaturas mapa=new MapaCriaturas(equipoCriaturas);
			mapa.ordenarPorPoderOfensivo();
			LinkedHashMap <String,Criatura> mapaCriaturas=mapa.getMapaCriaturas();
			Iterator itCriaturas=mapaCriaturas.keySet().iterator();
			while(itCriaturas.hasNext()){
				Object criatura=itCriaturas.next();
				if(mapaCriaturas.get(criatura).getSalud()>0){
					criaturaAtacante=mapaCriaturas.get(criatura);
					return(criaturaAtacante);			
				}
			}
			return (criaturaAtacante);
		}catch(Exception e){
			return (null);
		}
	}
	
	public Criatura getDefensor(){
		try{
			Criatura criaturaDefensora=null;
			MapaCriaturas mapa=new MapaCriaturas(equipoCriaturas);
			mapa.ordenarPorCapacidadDefensiva();
			LinkedHashMap <String,Criatura> mapaCriaturas=mapa.getMapaCriaturas();
			Iterator itCriaturas=mapaCriaturas.keySet().iterator();
			while(itCriaturas.hasNext()){
				Object criatura=itCriaturas.next();
				if(mapaCriaturas.get(criatura).getSalud()>0){
					criaturaDefensora=mapaCriaturas.get(criatura);	
					return(criaturaDefensora);			
				}
			}
			return (criaturaDefensora);
		}catch(Exception e){
			return (null);
		}
	}


	public int getPuntuacion(){
		return(puntos);
	}
	
	/*public boolean aptoParaBatalla(){
		return(algunaCriaturaViva());
	}*/
	
	public String volcadoJugador(){
		return(ID+":{"+nombre+"}");
	}
	
	public String toStringBatalla(){
		String resultado="";
		resultado=resultado+this.ID+":{(";
		Iterator itCriaturas=this.equipoCriaturas.keySet().iterator();
		while(itCriaturas.hasNext()){
			Criatura criatura=this.equipoCriaturas.get(itCriaturas.next());
			resultado=resultado+criatura.toShortString()+"),(";
		}
		resultado=resultado.substring(0,resultado.length()-2);
		resultado=resultado+"}";
		return(resultado);
	}
}
