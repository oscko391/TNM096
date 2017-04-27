import java.util.Vector;

public class Engine {

	private String input;
	private Vector<Clause> clauseVec = new Vector<Clause>();
	
	Engine(String[] stringClauses){
		for (int i = 0; i < stringClauses.length; i++) {
			Clause c = new Clause(stringClauses[i]);
			
			// Add c to the clusters vec if it is valid
			if(c.isValid()){
				clauseVec.addElement(c);
				c.display();
			}
		}
	}
}
