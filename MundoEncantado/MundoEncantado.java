import java.util.*;

public class MundoEncantado {
	
	public static void main (String[] args) {
		try{
			if(args[0].equals("-i")){		//Juego por instrucciones
				if(args.length==4){			//Clase instrucciones (parametros={archivo instrucciones, archivo salida})
					PartidaInstrucciones partida=new PartidaInstrucciones(args[1],args[3]);
					partida.leerFicheroInstrucciones();
				}
				else{						//Clase instrucciones (parametros={archivo instrucciones})
					PartidaInstrucciones partida=new PartidaInstrucciones(args[1]);
					partida.leerFicheroInstrucciones();
				}
			}
			
			else if(args[0].equals("-j")){  //Juego normal
				ParseadoJuegoNormal parsing=null;
				
				if(args.length==4){
					parsing=new ParseadoJuegoNormal(args[1],args[3]);
				}
				
				else if(args.length==6){
					if(args[4].equals("-r")){
						//Clase parseadoJuegoNormal (parametros={archivo jugadores, archivo criaturas, archivo reparto})
						parsing=new ParseadoJuegoNormal(args[1],args[3],args[5]);
					}
					else if(args[4].equals("-o")){
						//Clase parseadoJuegoNormal (parametros={archivo jugadores, archivo criaturas, archivo salida})
						parsing=new ParseadoJuegoNormal(args[1],args[3]);
						parsing.setSalida(args[5]);
					}
					else{
						System.out.println("ERROR");
					}
				}
				
				else if(args.length==8){
					//Clase parseadoJuegoNormal (parametros={archivo jugadores, archivo criaturas,archivo reparto, archivo salida})
					parsing=new ParseadoJuegoNormal(args[1],args[3],args[5],args[7]);
				}
							
				else{
					System.out.println("Hay un error con los parametros");
					System.exit(-1);
				}
				
				//Clase parseadoJuegoNormal (parametros={archivo jugadores, archivo criaturas,archivo reparto (ALEATORIO SI NO SE INTRODUJO), archivo salida (POR PANTALLA SI NO SE INTRODUJO)})
				Partida partida=new Partida(parsing.getMapaJugadores(),parsing.getMapaCriaturas(),parsing.getReparto(),parsing.getSalida());
				partida.realizarPartida();
			}
		
			else{
				System.out.println("Los parametros no son correctos");
				System.exit(-1);
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("No hay suficientes parametros");
			System.exit(-1);
		}
	}
}

