import java.util.Vector;

public class Engine {

	private Vector<Clause> clauseVec = new Vector<Clause>();
	
	Engine(String[] stringClauses){
		for (int i = 0; i < stringClauses.length; i++) {
			Clause c = new Clause(stringClauses[i]);
			
			// Add c to the clusters vec if it is valid
			if(!c.isEmpty()){
				clauseVec.addElement(c);
				c.display();
			}
		}
	}

	public void resolve(){
        System.out.println("Resolving...");
    }
}
