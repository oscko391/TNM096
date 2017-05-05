import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        Vector<Clause> newClauseVec = clauseVec;
        
        while(newClauseVec.size() > 1){
        	 Clause n = resolution(newClauseVec.get(newClauseVec.size()-1), newClauseVec.get(newClauseVec.size()-2));
        	 
        	 // Remove 2 last elements
        	 newClauseVec.remove(newClauseVec.size()-1);
        	 newClauseVec.remove(newClauseVec.size()-1);
        	 
        	 if(!n.isEmpty()){
        		 // Add n to front of vector
        		 newClauseVec.add(n);
        	 }
        	 
        }
        

        
        System.out.println("\nDone! Solution: ");
        newClauseVec.get(0).display();
        
    }
	
	public Clause resolution(Clause c1, Clause c2){
		Clause newClause = new Clause();
		
		Set<String> nPosVec = new HashSet<String>();
		Set<String> nNegVec = new HashSet<String>();
		
		nPosVec.addAll(c1.posVec);
		nPosVec.addAll(c2.posVec);
		
		nNegVec.addAll(c1.negVec);
		nNegVec.addAll(c2.negVec);
		
		
		for (String i : nPosVec) {
			newClause.posVec.add(i);
		}
		
		for (String i : nNegVec) {
			newClause.negVec.add(i);
		}
		
		newClause.simplify();
		return newClause;
	}
}
