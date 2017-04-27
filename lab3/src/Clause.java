import java.util.Iterator;
import java.util.Vector;

public class Clause {
	
	private Vector<String> negVec = new Vector<String>();
	private Vector<String> posVec = new Vector<String>();
	
	Clause(String theString){
		// Clear whitespaces
		theString = theString.replaceAll(" ", "");

		theString = theString.replaceAll("V", " ");
		
		// Creates an array where variables are splitted 
		//... ["-a", "b", "c",...]
		String[] parts = theString.split(" "); 
		
		
		// Fill negVec and posVec
		for (int i = 0; i < parts.length; i++) {
			if(parts[i].contains("-")){
				parts[i] = parts[i].replace("-", "");
				negVec.addElement(parts[i]);
			} else {
				posVec.addElement(parts[i]);
			}
		}
	}
	
	public boolean isValid(){
		for(int i = 0; i < negVec.size(); i++){
			for(int j = 0; j < posVec.size(); j++){
				if(negVec.get(i).equals(posVec.get(j)))
					//ta bort dem
			}
		}
		return true;
	}
	
	
	public void display(){
		
		System.out.println("Clause: ");
		
		if(negVec.size() > 0){
			System.out.print("   negVec: ");
			for(int i=0; i<negVec.size(); i++){
				System.out.print(negVec.elementAt(i)+" ");
			}
			System.out.print("\n");
		}
		
		if(posVec.size() > 0){
			System.out.print("   posVec: ");
			for(int i=0; i<posVec.size(); i++){
				System.out.print(posVec.elementAt(i)+" ");
			}
			System.out.println();
		}
	}
	
	
	
}