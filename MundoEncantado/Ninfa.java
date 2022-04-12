public class Ninfa extends UsuarioLugarSagrado implements LagoSagrado{
	private int divinidad;
	private int velocidad;
	private int engano;
	private int varita;
	private int armadura;
	
	public Ninfa (String ID, String nombre, int divinidad,int varita,int velocidad, int engano, int armadura){
		super(ID,nombre);	
		this.divinidad=divinidad;
		this.varita=varita;	
		this.velocidad=velocidad;
		this.engano=engano;
		this.armadura=armadura;		
		this.calcularPoderOfensivo();
		this.calcularCapacidadDefensiva();
	}
	
	public void recuperarse(){
		this.meterseEnLago();
	}
	
	public void meterseEnLago(){
		this.sumarVisitaLugarSagrado();
		this.setSalud(this.getSalud()+2+visitasLugarSagrado*2);
		this.aumentarValores();
		LagoSagrado.anadirVisita(this);
	}
	
	/*protected void comprobarValores(){
		if (divinidad>2 || divinidad<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (varita>1000 || varita<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (velocidad>2 || velocidad<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (engano>1 || engano<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
		
		if (armadura>1000 || armadura<0){
			System.out.println("No se puede crear una ninfa con esas caracteristicas");
			System.exit(-1);
		}
	}	
	*/
	protected void calcularPoderOfensivo() {
		poderOfensivo=divinidad*(velocidad+engano)+varita/100;
	}
	
	protected void calcularCapacidadDefensiva() {
		capacidadDefensiva=divinidad*(velocidad+engano)+armadura/200;
	}

	public String toStringBatalla(){
		String datos="N:{"+ID+","+nombre+",D"+divinidad+",V"+varita+",R"+velocidad+",E"+engano+",A"+armadura+"}";
		return (datos);	
	}
	
	public String toString(){
		String datos="N:{"+ID+","+nombre+",D"+divinidad+",V"+varita+",R"+velocidad+",E"+engano+",A"+armadura+","+getPoderOfensivo()+","+getCapacidadDefensiva()+","+getSalud()+"}";
		return (datos);
	}
	
	public void atacarA(Criatura defensor){
		defensor.defenderDe(this);
		this.restarVida(2);
		this.setVarita(this.getVarita()-5);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void defenderDe(Criatura atacante){
		this.restarVida(getPerdidaSalud(atacante));
		this.restarVida(2);
		this.setArmadura(this.getArmadura()-5);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
	public void setVarita(int varitaNueva){
		if (varitaNueva>1000) varita=1000;
		else if (varitaNueva<0) varita=0;
		else this.varita=varitaNueva;
	}
	
	public int getVarita(){
		return (varita);
	}
	
	public void setArmadura(int armaduraNueva){
		if (armaduraNueva>1000) armadura=1000;
		else if (armaduraNueva<0) armadura=0;
		else this.armadura=armaduraNueva;
	}
	
	public int getArmadura(){
		return (armadura);
	}
	
	public void aumentarValores(){
		int nuevaArmadura=this.armadura+visitasLugarSagrado*3;
		int nuevaVarita=this.varita+visitasLugarSagrado*3;
		this.setArmadura(nuevaArmadura);
		this.setVarita(nuevaVarita);
		calcularCapacidadDefensiva();
		calcularPoderOfensivo();
	}
	
}
