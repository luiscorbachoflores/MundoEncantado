public class Elfo extends UsuarioLugarSagrado implements LagoSagrado{
	private int inteligencia;
	private int arco;
	private int coraza;
	
	public Elfo(String ID,String nombre,int inteligencia,int arco,int coraza){
		super(ID,nombre);
		this.inteligencia=inteligencia;
		this.arco=arco;
		this.coraza=coraza;
		this.calcularCapacidadDefensiva();
		this.calcularPoderOfensivo();
	}
	
	public void recuperarse(){
		this.sumarVisitaLugarSagrado();
		this.setSalud(this.getSalud()+3*visitasLugarSagrado);
		this.aumentarValores();
		LagoSagrado.anadirVisita(this);
	}
	
	public void meterseEnLago(){
		this.recuperarse();
	}
	
	protected void calcularCapacidadDefensiva(){
		this.capacidadDefensiva=(inteligencia*coraza*coraza)/10;
	}
	
	protected void calcularPoderOfensivo(){
		this.poderOfensivo=(inteligencia*arco*arco)/5;
	}
	
	public int getArco(){
		return (arco);
	}
	
	public int getCoraza(){
		return (coraza);
	}
	
	public void setArco(int nuevoArco){
		if (nuevoArco>5) this.arco=5;
		else if (nuevoArco<2) this.arco=2;
		else this.arco=nuevoArco;
	}
	
	public void setCoraza(int nuevaCoraza){
		if (nuevaCoraza>5) nuevaCoraza=5;
		else if (nuevaCoraza<1) nuevaCoraza=1;
		this.coraza=nuevaCoraza;
	}
	
	public void defenderDe(Criatura atacante){
		this.restarVida(getPerdidaSalud(atacante));
		this.restarVida(3);
		this.setArco(this.getArco()-1);
		this.setCoraza(this.getCoraza()-1);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void atacarA(Criatura defensor){
		defensor.defenderDe(this);
		this.restarVida(3);
		this.setArco(this.getArco()-1);
		this.setCoraza(this.getCoraza()-1);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void aumentarValores(){
		int nuevoArco=this.getArco()+visitasLugarSagrado/2;
		int nuevaCoraza=this.getCoraza()+visitasLugarSagrado/2;
		this.setArco(nuevoArco);
		this.setCoraza(nuevaCoraza);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public String toStringBatalla(){
		String datos="E:{"+ID+","+nombre+",I"+inteligencia+",A"+arco+",C"+coraza+"}";
		return (datos);	
	}
	
	public String toString(){
		String datos="E:{"+ID+","+nombre+",I"+inteligencia+",A"+arco+",C"+coraza+","+getPoderOfensivo()+","+getCapacidadDefensiva()+","+getSalud()+"}";
		return (datos);
	}
}

