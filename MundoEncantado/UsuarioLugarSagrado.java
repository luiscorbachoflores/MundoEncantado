public abstract class UsuarioLugarSagrado extends Criatura{
	protected int visitasLugarSagrado=0;
	
	public UsuarioLugarSagrado(String ID,String nombre){
		super(ID,nombre);
	}
	
	public abstract void aumentarValores();
	public abstract void recuperarse();
	
	public void sumarVisitaLugarSagrado(){
		visitasLugarSagrado++;
	}
	
	public int getVisitasLugarSagrado(){
		return visitasLugarSagrado;
	}
	
	public String getStringVisitasLugarSagrado(){ //e0=3
		return(ID+"="+visitasLugarSagrado);
	}
}

