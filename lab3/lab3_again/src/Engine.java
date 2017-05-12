import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Engine {

	public Vector<Clause> clauseVec = new Vector<Clause>();

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
		Vector<Clause> newKB = new Vector<Clause>();

		do {

			c = new Clause();
			// Vector<Clause> badClauses = new Vector<Clause>();

			for (int i = 0; i < clauseVec.size() - 1; i++) {
				for (int j = i + 1; j < clauseVec.size(); j++) {
					c = resolution(clauseVec.get(i), clauseVec.get(j));
					// If i and j can be resolved into a new clause
					if (c != null) {

						newKB.addElement(c);
					}
				}

			}
			
		
			// Simplify KB
			for (int i = 0; i < newKB.size(); i++) {
				for (int j = 0; j < clauseVec.size(); j++) {
					if (newKB.get(i).isSubset(clauseVec.get(j))) {
						clauseVec.get(j).posVec = newKB.get(i).posVec;
						clauseVec.get(j).negVec = newKB.get(i).negVec;
					} 
				}

			}
			
			
			for (int i = 0; i < newKB.size(); i++)
			{
				if (!clauseVec.contains(newKB.get(i)))
					clauseVec.add(newKB.get(i));
			}
			
			
			
			Set<Clause> uniClauses = new HashSet<Clause>();
			uniClauses.addAll(clauseVec);
			clauseVec.clear();
			clauseVec.addAll(uniClauses);
			

			

		} while (c != null && !c.isEmpty());

		System.out.println("Solved!");
		for (int i = 0; i < clauseVec.size(); i++) {
			if (clauseVec.get(i) != null && !clauseVec.get(i).isEmpty()) {
				clauseVec.get(i).display();
			}
		}
	}

	public Clause resolution(Clause c1, Clause c2) {

		String literal = null;
		Clause newClause = new Clause();

		for (int i = 0; i < c1.posVec.size(); i++) {
			if (c2.negVec.contains(c1.posVec.get(i)))
				literal = c1.posVec.get(i);
		}

		for (int i = 0; literal == null && i < c2.posVec.size(); i++) {
			if (c1.negVec.contains(c2.posVec.get(i)))
				literal = c2.posVec.get(i);
		}

		if (literal != null) {

			for (int i = 0; i < c1.posVec.size(); i++) {
				if (!c1.posVec.get(i).equals(literal) && !newClause.posVec.contains(c1.posVec.get(i)))
					newClause.posVec.add(c1.posVec.get(i));
			}

			for (int i = 0; i < c1.negVec.size(); i++) {
				if (!c1.negVec.get(i).equals(literal) && !newClause.negVec.contains(c1.negVec.get(i)))
					newClause.negVec.add(c1.negVec.get(i));
			}

			for (int i = 0; i < c2.posVec.size(); i++) {
				if (!c2.posVec.get(i).equals(literal) && !newClause.posVec.contains(c2.posVec.get(i)))
					newClause.posVec.add(c2.posVec.get(i));
			}

			for (int i = 0; i < c2.negVec.size(); i++) {
				if (!c2.negVec.get(i).equals(literal) && !newClause.negVec.contains(c2.negVec.get(i)))
					newClause.negVec.add(c2.negVec.get(i));
			}
		} else {
			return null;
		}

		if (literal != null && newClause.isContradictory()) {
			System.out.println("null");
			return null;
		}
		return newClause;
	}
}
