public class Lucha {
	private Criatura criaturaAtacante;
	private Criatura criaturaDefensora;
	private Jugador jugadorAtacante;
	private Jugador jugadorDefensor;
	private Salida salida;
	
	public Lucha(Criatura atacante,Criatura defensor,Jugador agresivo,Jugador pasivo){
		this.criaturaAtacante=atacante;
		this.criaturaDefensora=defensor;
		this.jugadorAtacante=agresivo;
		this.jugadorDefensor=pasivo;
	}
	
	public Lucha(Jugador atacante,Jugador defensor,Salida modoSalida){
		this.jugadorAtacante=atacante;
		this.jugadorDefensor=defensor;
		this.criaturaAtacante=atacante.getAtacante();
		this.criaturaDefensora=defensor.getDefensor();
		this.salida=modoSalida;
	}
	
	public void ejecutarLucha(){
		criaturaAtacante.atacarA(criaturaDefensora);
	}
	
	public void imprimirLucha(int numeroLucha){
		salida.imprimirLinea("  LUCHA "+numeroLucha+": "+this.toString());
	}
	
	public String toString(){
		String datos=jugadorAtacante.getID()+"-"+criaturaAtacante.toStringBatalla()+" --> "+jugadorDefensor.getID()+"-"+criaturaDefensora.toStringBatalla();
		return (datos);
	}
}

