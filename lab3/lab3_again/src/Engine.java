import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Engine {

	private Vector<Clause> clauseVec = new Vector<Clause>();

	Engine(String[] stringClauses) {
		for (int i = 0; i < stringClauses.length; i++) {
			Clause c = new Clause(stringClauses[i]);

			// Add c to the clusters vec if it is valid
			if (!c.isEmpty()) {
				clauseVec.addElement(c);
				c.display();
			}
		}
	}

	public void resolve() {
			
		
		Clause c;
		
 		
		do {
			Vector<Clause> newKB = new Vector<Clause>();
			c = null;
			
			boolean cont = false;

			for (int i = 0; i < clauseVec.size() - 1; i++) {
				for (int j = i + 1; j < clauseVec.size(); j++) {
					c = resolution(clauseVec.get(i), clauseVec.get(j));
					
					// If i and j can be resolved into a new clause
					if (c != null){
						newKB.addElement(c);
					} else {
						newKB.addElement(clauseVec.get(i));
						newKB.addElement(clauseVec.get(j));
					}
				}
			}
			
			clauseVec = newKB;
		} while (c != null);
		
		
		System.out.println("Solved!");
	}

	public Clause resolution(Clause c1, Clause c2) {
		
		String literal = null;
		Clause newClause = new Clause();
		
		for(int i=0; i<c1.posVec.size(); i++){
			if(c2.negVec.contains(c1.posVec.get(i) ) )
					literal = c1.posVec.get(i);
		}
		
		for(int i=0; literal!=null && i<c1.posVec.size(); i++){
			if(c2.negVec.contains(c1.posVec.get(i) ) )
					literal = c1.posVec.get(i);
		}
		
		if (literal!=null){
			
			
			for(int i=0; i<c1.posVec.size(); i++){
				if( !c1.posVec.get(i).equals(literal) && !newClause.posVec.contains(c1.posVec.get(i)) )
					newClause.posVec.add(c1.posVec.get(i));
			}
			
			for(int i=0; i<c1.negVec.size(); i++){
				if( !c1.negVec.get(i).equals(literal) && !newClause.negVec.contains(c1.negVec.get(i)) )
					newClause.negVec.add(c1.negVec.get(i));
			}
			
			for(int i=0; i<c2.posVec.size(); i++){
				if( !c2.posVec.get(i).equals(literal) && !newClause.posVec.contains(c2.posVec.get(i)) )
					newClause.posVec.add(c2.posVec.get(i));
			}
			
			for(int i=0; i<c2.negVec.size(); i++){
				if( !c2.negVec.get(i).equals(literal) && !newClause.negVec.contains(c2.negVec.get(i)) )
					newClause.negVec.add(c2.negVec.get(i));
			}
		}
		
		if(literal != null && newClause.isContradictory())
			return null;
		return newClause;
	}
}
