public class Bruja extends UsuarioLugarSagrado implements TemploMaldito{
	protected int sabiduria;
	protected int magia;
	protected int vestido;
	protected int baston;
	
	public Bruja(String ID,String nombre,int sabiduria,int magia,int baston, int vestido){
		super(ID,nombre);
		this.sabiduria=sabiduria;
		this.magia=magia;
		this.baston=baston;
		this.vestido=vestido;
		this.calcularCapacidadDefensiva();
		this.calcularPoderOfensivo();
	}
	
	public void orar(){
		this.sumarVisitaLugarSagrado();
		this.aumentarValores();
		TemploMaldito.anadirVisita(this);
	}
	
	public void recuperarse(){
		this.orar();
	}
	
	protected void calcularPoderOfensivo() {
		poderOfensivo=(sabiduria+magia)*baston/5;
	}
	
	protected void calcularCapacidadDefensiva() {
		capacidadDefensiva=(sabiduria+magia)*vestido/10;
	}
	
	public void atacarA(Criatura defensor){
		defensor.defenderDe(this);
		this.restarVida(2);
		this.setBaston(this.getBaston()-1);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void defenderDe(Criatura atacante){
		this.restarVida(getPerdidaSalud(atacante));
		this.restarVida(2);
		this.setVestido(this.getVestido()-1);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	
	protected void setBaston(int nuevoValor){
		if (nuevoValor>10) baston=10;
		else if (nuevoValor<1) baston=1;
		else this.baston=nuevoValor;
	}
	
	public int getBaston(){
		return(baston);
	}
	
	protected void setVestido(int nuevoValor){
		if (nuevoValor>10) vestido=10;
		else if (nuevoValor<1) vestido=1;
		else this.vestido=nuevoValor;
	}
	
	public int getVestido(){
		return(vestido);
	}
	
	public void aumentarValores(){
		int nuevoBaston=this.baston+(2+visitasLugarSagrado)/2;
		int nuevoVestido=this.vestido+visitasLugarSagrado/2;
		this.setBaston(nuevoBaston);
		this.setVestido(nuevoVestido);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public String toStringBatalla(){
		String datos="B:{"+ID+","+nombre+",S"+sabiduria+",M"+magia+",B"+baston+",V"+vestido+"}";
		return (datos);	
	}
	
	public String toString(){
		String datos="B:{"+ID+","+nombre+",S"+sabiduria+",M"+magia+",B"+baston+",V"+vestido+","+getPoderOfensivo()+","+getCapacidadDefensiva()+","+getSalud()+"}";
		return (datos);	
	}
}

