public class Orco extends UsuarioLugarSagrado implements TemploMaldito{
	protected int fuerza;
	protected int escudo;
	protected int garrote;
	
	public Orco(String ID,String nombre,int Fuerza,int Garrote,int Escudo){
		super(ID,nombre);
		this.fuerza=Fuerza;
		this.escudo=Escudo;
		this.garrote=Garrote;
		this.calcularPoderOfensivo();
		this.calcularCapacidadDefensiva();
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
		poderOfensivo=(fuerza+garrote)/5;
	}
	
	protected void calcularCapacidadDefensiva() {
		capacidadDefensiva=(fuerza+escudo)/20;
	}

	public void defenderDe(Criatura atacante){
		this.restarVida(getPerdidaSalud(atacante));
		this.restarVida(3);
		this.setEscudo(this.getEscudo()-3);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void atacarA(Criatura defensor){
		defensor.defenderDe(this);
		this.restarVida(1);
		this.setGarrote(this.getGarrote()-3);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public int getEscudo(){
		return (escudo);
	}
	
	public int getGarrote(){
		return (garrote);
	}
	
	public void setEscudo(int escudoNuevo){
		if (escudoNuevo<0) escudoNuevo=0;
		else if (escudoNuevo>90) escudoNuevo=90;
		this.escudo=escudoNuevo;
	}
	
	public void setGarrote(int garroteNuevo){
		if (garroteNuevo<0) garroteNuevo=0;
		else if (garroteNuevo>90) garroteNuevo=90;
		this.garrote=garroteNuevo;
	}
	
	public void aumentarValores(){
		int nuevoGarrote=this.garrote+2*visitasLugarSagrado;
		int nuevoEscudo=this.escudo+visitasLugarSagrado;
		this.setGarrote(nuevoGarrote);
		this.setEscudo(nuevoEscudo);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}

	public String toStringBatalla(){
		String datos="O:{"+ID+","+nombre+",F"+fuerza+",G"+garrote+",E"+escudo+"}";
		return (datos);
	}
	
	public String toString(){
		String datos="O:{"+ID+","+nombre+",F"+fuerza+",G"+garrote+",E"+escudo+","+getPoderOfensivo()+","+getCapacidadDefensiva()+","+getSalud()+"}";
		return (datos);
	}

}
