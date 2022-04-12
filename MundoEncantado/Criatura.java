import java.util.*;

public abstract class Criatura implements Comparable <Criatura>{
	protected String ID;
	protected String nombre;
	protected int capacidadDefensiva;
	protected int poderOfensivo;
	protected int salud;
	
	public Criatura(String numID,String name){
		ID=numID;
		nombre=name;
		salud=10;
	}
	
	public Criatura(){
		salud=10;
	}
		
	protected abstract void calcularCapacidadDefensiva();
	protected abstract void calcularPoderOfensivo();
	public abstract void atacarA(Criatura defensor);
	public abstract void defenderDe(Criatura atacante);
	public abstract String toStringBatalla();
	
	public void restarVida(int resta){
		salud=salud-resta;
		if(salud<0) salud=0;
	}
	
	public void setSalud(int saludNueva){
		if(saludNueva>10) salud=10;
		else if(saludNueva<0) salud=0;
		else salud=saludNueva;
	}

	public int getCapacidadDefensiva() {
		return(capacidadDefensiva);
	}
	
	public String getID() {
		return(ID);
	}

	public String getNombre() {
		return(nombre);
	}

	public int getSalud() {
		return(salud);
	}
	
	public int getPoderOfensivo() {
		return(poderOfensivo);
	}
	
	public int getFortaleza(){
		return(capacidadDefensiva+poderOfensivo);	
	}
	
	public int getPerdidaSalud(Criatura atacante){
		int diferencia=atacante.getPoderOfensivo()-this.getCapacidadDefensiva();
		if(diferencia<0) diferencia=0;
		return(diferencia);
	}

	public String toString() { //El toString por defecto en criatura, que se sobreescribira en las clases hijas
	        return (ID + ","+ salud);
	}

	public String toShortString(){ // e0,10
        return (ID + ","+ salud);
	}

	public int compareTo(Criatura comparando){ //Por id
		if(this.getID().equals(comparando.getID())){
			return(this.getNombre().compareTo(comparando.getNombre()));					
		}
		return(this.getID().compareTo(comparando.getID()));			
	}
	
	public static Comparator<Criatura> IDComparator=new Comparator<Criatura>(){
		public int compare(Criatura comparando1,Criatura comparando2){
			String IDComparando=comparando2.getID();
			String miID=comparando1.getID();			
			if(IDComparando.equals(miID)) return (comparando1.compareTo(comparando2));	
			else return(miID.compareTo(IDComparando));
		}
	};
		
	public static Comparator<Criatura> FortalezaComparator=new Comparator<Criatura>(){
		public int compare(Criatura comparando1,Criatura comparando2){
			int potenciaComparando=comparando2.getCapacidadDefensiva()+comparando2.getPoderOfensivo();
			int miPotencia=comparando1.getCapacidadDefensiva()+comparando1.getPoderOfensivo();			
			if(potenciaComparando==miPotencia) return (comparando1.compareTo(comparando2));	
			if(potenciaComparando<miPotencia) return (-1);		
			if(potenciaComparando>miPotencia) return (1);
			else return(0);
		}
	};
	
	public static Comparator<Criatura> PoderOfensivoComparator=new Comparator<Criatura>(){
		public int compare(Criatura comparando1,Criatura comparando2){
			int potenciaComparando=comparando2.getPoderOfensivo();
			int miPotencia=comparando1.getPoderOfensivo();			
			if(potenciaComparando==miPotencia) return (comparando1.compareTo(comparando2));	
			if(potenciaComparando<miPotencia) return (-1);		
			if(potenciaComparando>miPotencia) return (1);
			else return(0);
		}
	};
	
	public static Comparator<Criatura> CapacidadDefensivaComparator=new Comparator<Criatura>(){
		public int compare(Criatura comparando1,Criatura comparando2){
			int potenciaComparando=comparando2.getCapacidadDefensiva();
			int miPotencia=comparando1.getCapacidadDefensiva();			
			if(potenciaComparando==miPotencia) return (comparando1.compareTo(comparando2));	
			if(potenciaComparando<miPotencia) return (-1);		
			if(potenciaComparando>miPotencia) return (1);
			else return(0);
		}
	};
	
}

