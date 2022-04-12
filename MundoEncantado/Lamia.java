public class Lamia extends Criatura {
	private int astucia;
	private int seduccion;
	private int garras;
	private int miton;
	
	public Lamia(String ID, String nombre, int astucia, int seduccion, int garras, int miton){
		super(ID,nombre);
		this.astucia=astucia;
		this.seduccion=seduccion;
		this.garras=garras;
		this.miton=miton;
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
		//comprobarValores();
	}
	
	protected void calcularCapacidadDefensiva(){
		this.capacidadDefensiva=(seduccion+miton)/20;
	}
	
	protected void calcularPoderOfensivo(){
		this.poderOfensivo=(astucia+garras)/5;
	}
	
	/*protected void comprobarValores(){
		if (astucia>10 || astucia<1){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (seduccion>10 || seduccion<1){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (garras>100 || garras<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (miton>100 || miton<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
	}	
	*/
	public void atacarA(Criatura defensor){
		defensor.defenderDe(this);
		this.restarVida(1);
		this.setGarras(this.getGarras()-5);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void defenderDe(Criatura atacante){
		this.restarVida(getPerdidaSalud(atacante));
		this.restarVida(3);
		this.setMiton(this.getMiton()-3);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}	
	
	public int getMiton(){
		return (miton);
	}
	
	public int getGarras(){
		return (garras);
	}
	
	public void setMiton(int nuevoMiton){
		if(nuevoMiton>100) nuevoMiton=100;
		else if(nuevoMiton<0) nuevoMiton=0;
		this.miton=nuevoMiton;
	}
	
	public void setGarras(int nuevasGarras){
		if(nuevasGarras>100) nuevasGarras=100;
		else if(nuevasGarras<0) nuevasGarras=0;
		this.garras=nuevasGarras;
	}
	
	public String toString(){
		String datos="L:{"+ID+","+nombre+",A"+astucia+",S"+seduccion+",G"+garras+",M"+miton+","+getPoderOfensivo()+","+getCapacidadDefensiva()+","+getSalud()+"}";
		return(datos);
	}
	
	public String toStringBatalla(){
		String datos="L:{"+ID+","+nombre+",A"+astucia+",S"+seduccion+",G"+garras+",M"+miton+"}";
		return (datos);
	}
}

