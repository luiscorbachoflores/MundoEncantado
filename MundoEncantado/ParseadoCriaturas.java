import java.util.*;
import java.io.*;

public class ParseadoCriaturas {
	private String archivo_criaturas=null;
	private LinkedHashMap <String,Criatura> criaturas=new LinkedHashMap <String,Criatura>();
	
	
	public ParseadoCriaturas(String f_criaturas){
		this.archivo_criaturas=f_criaturas;
	}
	
	public void leerFicheroCriaturas(){
		Scanner entrada = null;
		try {
			entrada = new Scanner(new FileInputStream(archivo_criaturas));
		} catch (FileNotFoundException e) {
			return;
		}
		String linea = null;
		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();
			Criatura nuevaCriatura=parseadoCriatura(linea);
			criaturas.put(nuevaCriatura.getID(),nuevaCriatura);
		}
		entrada.close();
		return;
	}
	
	public Criatura parseadoCriatura(String info){
		String tipo=info.split(":")[0];
		String datos=info.split(":")[1];
		datos=datos.replace("{","");
		datos=datos.replace("}","");
		String ID=datos.split(",")[0];
		String nombre=datos.split(",")[1];
		ArrayList<Integer> variables=new ArrayList<Integer>();
		String variableString=null;
		try{
			variableString=datos.split(",")[2];
			variables.add(Integer.parseInt(variableString.substring(1)));
			
			variableString=datos.split(",")[3];
			variables.add(Integer.parseInt(variableString.substring(1)));
			
			variableString=datos.split(",")[4];
			variables.add(Integer.parseInt(variableString.substring(1)));
			
			variableString=datos.split(",")[5];
			variables.add(Integer.parseInt(variableString.substring(1)));
			
			variableString=datos.split(",")[6];
			variables.add(Integer.parseInt(variableString.substring(1)));
		}
		
		catch(IndexOutOfBoundsException e){
			
		}
		
		finally{
			Criatura nuevaCriatura=null;
			if (tipo.equals("O")){
				nuevaCriatura=new Orco(ID,nombre,variables.get(0),variables.get(1),variables.get(2));
			}
			else if (tipo.equals("B")){
				nuevaCriatura=new Bruja(ID,nombre,variables.get(0),variables.get(1),variables.get(2),variables.get(3));
			}
			else if (tipo.equals("E")){
				nuevaCriatura=new Elfo(ID,nombre,variables.get(0),variables.get(1),variables.get(2));
			}
			else if (tipo.equals("N")){
				nuevaCriatura=new Ninfa(ID,nombre,variables.get(0),variables.get(1),variables.get(2),variables.get(3),variables.get(4));
			}
			else if (tipo.equals("L")){
				nuevaCriatura=new Lamia(ID,nombre,variables.get(0),variables.get(1),variables.get(2),variables.get(3));
			}
			else {
				System.out.println("Ha habido un error con el parseado de la criatura");
				return (null);
			}
			return (nuevaCriatura);
		}
	}

	public LinkedHashMap<String,Criatura> getMapaCriaturas(){
		return(criaturas);
	}

}

