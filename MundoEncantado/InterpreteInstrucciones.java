import java.util.*;
import java.io.*;

public class InterpreteInstrucciones {
	private LinkedHashMap <String,Jugador> jugadores;
	private LinkedHashMap <String,Criatura> bosque;
	
	public InterpreteInstrucciones(LinkedHashMap<String,Jugador> players,LinkedHashMap<String,Criatura> criaturas){
		this.jugadores=players;
		this.bosque=criaturas;
	}
	
	public LinkedHashMap <String,Jugador> getMapaJugadores(){
		return(this.jugadores);
	}
	
	public LinkedHashMap <String,Criatura> getMapaCriaturas(){
		return(this.bosque);
	}
	
	public boolean cargarJugadores(String archivo_jugadores){
		try{
			ParseadoJugadores leerJugadores=new ParseadoJugadores(archivo_jugadores);
			leerJugadores.leerFicheroJugadores();
			
			LinkedHashMap <String,Jugador> jugadoresParaAnadir=leerJugadores.getMapaJugadores();
			Iterator itJugadores=jugadoresParaAnadir.keySet().iterator();
			
			while(itJugadores.hasNext()){
				Jugador player=jugadoresParaAnadir.get(itJugadores.next());
				this.jugadores.put(player.getID(),player);
			}
			return (true);
		
		}catch(Exception e){
			System.out.println(e);
			return (false);
		}
	}
	
	public boolean crearJugador(String ID,String nombre){
		try{
			Jugador nuevoJugador=new Jugador(ID,nombre);
			if(jugadores.get(ID)!=null) return false;
			else {
				this.jugadores.put(nuevoJugador.getID(),nuevoJugador);
				return (true);
			}
		}catch(Exception e){
			return (false);
		}
	}
	
	public boolean borrarJugador(String ID){
		Jugador jugador=jugadores.get(ID);
		if(jugador==null) return false;
		this.jugadores.remove(ID);
		return (true);
	}
	
	public boolean volcarJugadores(String ficheroVolcado){
		PrintWriter salida = null; // a�adirla

		try {
			salida = new PrintWriter(new FileOutputStream(ficheroVolcado));
		} catch (FileNotFoundException e) {
			return(false);
		}
		Iterator itJugadores=jugadores.keySet().iterator();
		
		while(itJugadores.hasNext()){
			Jugador jugador=jugadores.get(itJugadores.next());
			String linea=jugador.volcadoJugador();
			salida.println(linea);
		}
		salida.close();	
		return true;
	}

	public boolean cargarCriaturas(String archivo_criaturas){
		try{
			ParseadoCriaturas leerCriaturas=new ParseadoCriaturas(archivo_criaturas);
			leerCriaturas.leerFicheroCriaturas();
			
			LinkedHashMap <String,Criatura> criaturasParaAnadir=leerCriaturas.getMapaCriaturas();
			Iterator itCriaturas=criaturasParaAnadir.keySet().iterator();
			
			while(itCriaturas.hasNext()){
				Criatura criatura=criaturasParaAnadir.get(itCriaturas.next());
				this.bosque.put(criatura.getID(),criatura);
			}
			return (true);
		
		}catch(Exception e){
			return (false);
		}
	}

	public boolean crearNinfa(String ID,String nombre,String strDivinidad,String strVelocidad,String strEngano,String strVarita,String strArmadura){
		try{
			if(bosque.get(ID)!=null) return false;
			
			int divinidad=Integer.parseInt(strDivinidad);
			int velocidad=Integer.parseInt(strVelocidad);
			int engano=Integer.parseInt(strEngano);
			int varita=Integer.parseInt(strVarita);
			int armadura=Integer.parseInt(strArmadura);
			Ninfa nuevaNinfa=new Ninfa(ID,nombre,divinidad,varita,velocidad,engano,armadura);
			bosque.put(ID,nuevaNinfa);
			return true;
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean crearOrco(String ID,String nombre,String strFuerza,String strGarrote,String strEscudo){
		try{
			if(bosque.get(ID)!=null) return false;
			
			int fuerza=Integer.parseInt(strFuerza);
			int garrote=Integer.parseInt(strGarrote);
			int escudo=Integer.parseInt(strEscudo);
			Orco nuevoOrco=new Orco(ID,nombre,fuerza,garrote,escudo);
			bosque.put(ID,nuevoOrco);
			return (true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean crearBruja(String ID,String nombre,String strSabiduria,String strMagia,String strBaston,String strVestido){
		try{
			if(bosque.get(ID)!=null) return false;
			
			int sabiduria=Integer.parseInt(strSabiduria);
			int magia=Integer.parseInt(strMagia);
			int baston=Integer.parseInt(strBaston);
			int vestido=Integer.parseInt(strVestido);
			Bruja nuevaBruja=new Bruja(ID,nombre,sabiduria,magia,baston,vestido);
			bosque.put(ID,nuevaBruja);
			return (true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean crearElfo(String ID,String nombre,String strInteligencia,String strArco,String strCoraza){
		try{
			if(bosque.get(ID)!=null) return false;
			
			int inteligencia=Integer.parseInt(strInteligencia);
			int arco=Integer.parseInt(strArco);
			int coraza=Integer.parseInt(strCoraza);
			Elfo nuevoElfo=new Elfo(ID,nombre,inteligencia,arco,coraza);
			bosque.put(ID,nuevoElfo);
			return(true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean crearLamia(String ID,String nombre,String strAstucia,String strSeduccion,String strGarras,String strMiton){
		try{
			if(bosque.get(ID)!=null) return false;
			
			int astucia=Integer.parseInt(strAstucia);
			int seduccion=Integer.parseInt(strSeduccion);
			int garras=Integer.parseInt(strGarras);
			int miton=Integer.parseInt(strMiton);
			Lamia nuevaLamia=new Lamia(ID,nombre,astucia,seduccion,garras,miton);
			bosque.put(ID,nuevaLamia);
			return(true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean borrarCriatura(String ID){
		try{
			if(bosque.get(ID)==null) return false;
			
			bosque.remove(ID);
			return (true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean volcarCriaturas(String ficheroVolcadoCriaturas){
		PrintWriter salida = null; // a�adirla

		try {
			salida = new PrintWriter(new FileOutputStream(ficheroVolcadoCriaturas));
		} catch (FileNotFoundException e) {
			return (false);
		}
		MapaCriaturas mapaOrdenado=new MapaCriaturas(bosque);
		mapaOrdenado.ordenarPorID();
		bosque=mapaOrdenado.getMapaCriaturas();
		Iterator itCriaturas=bosque.keySet().iterator();
		
		while(itCriaturas.hasNext()){
			Criatura criatura=bosque.get(itCriaturas.next());
			String linea=criatura.toStringBatalla();
			salida.println(linea);
		}
		salida.close();	
		return true;	
	}
	
	public Criatura mostrarCriatura(String ID){
		try{
			return(bosque.get(ID));
		}catch(Exception e){
			return(null);
		}
	}
	
	public Criatura mostrarCriaturaMasOfensiva(){
		try{
			MapaCriaturas reordenarBosque=new MapaCriaturas(bosque);
			reordenarBosque.ordenarPorPoderOfensivo();
			return (reordenarBosque.getPrimeraCriatura());
		}catch(Exception e){
			return(null);
		}
	}
	
	public Criatura mostrarCriaturaMasDefensiva(){
		try{
			MapaCriaturas reordenarBosque=new MapaCriaturas(bosque);
			reordenarBosque.ordenarPorCapacidadDefensiva();
			return (reordenarBosque.getPrimeraCriatura());
		}catch(Exception e){
			return(null);
		}
	}
	
	public boolean ataque(String strAtacante,String strDefensora){
		try{
			Criatura atacante=bosque.get(strAtacante);
			Criatura defensora=bosque.get(strDefensora);
			if(defensora==null || atacante==null) return false;
			atacante.atacarA(defensora);
			return(true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean visitarLugarSagrado(String ID){
		try{
			Criatura criatura=bosque.get(ID);
			if(criatura==null) return false;
			if(criatura instanceof UsuarioLugarSagrado){
				UsuarioLugarSagrado usuarioSagrado=(UsuarioLugarSagrado)criatura;	
				usuarioSagrado.recuperarse();
			}
			return (true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public boolean generarAsignacionCriaturas(String strCantidad,String salida){
		try{
			if (jugadores.size()<=1) return false;
			if (jugadores.size()>bosque.size()) return false;
			int cantidad=Integer.parseInt(strCantidad);
			String resultado="";
			for (int i=0;i<cantidad;i++){
				RepartoCriaturas reparto=new RepartoCriaturas(jugadores,bosque);
				reparto.asignarCriaturasAleatoriamente();
				resultado=resultado+reparto.exportarReparto()+"\n";
				reparto.desligarCriaturas();
			}
			return(true);
		}catch(Exception e){
			return(false);
		}
	}

	public void jugarPartida(String fichero_reparto,String fichero_salida){
		Partida partida=new Partida(jugadores,bosque,fichero_reparto,fichero_salida);
		new Salida(fichero_salida).crearFichero();
		partida.realizarPartida();
		return;
	}
}

