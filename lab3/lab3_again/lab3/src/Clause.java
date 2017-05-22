import java.util.Vector;

public class Clause {
	
	public Vector<String> negVec = new Vector<String>();
	public Vector<String> posVec = new Vector<String>();
	
	Clause(){
		;
	}
	
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

    /**
     * Simplifies the clause by removing elements in both negVec and posVec
     */
    public void simplify(){
    	
    	int i=0;
    	int j=0;
    	
    	// Compare negVec with posVec
    	while(i<negVec.size()){
    		while(j<posVec.size()){
    			
    			if(negVec.get(i).equals(posVec.get(j))){
					//Remove from both negVec and posVec
                    negVec.removeElementAt(i);
                    posVec.removeElementAt(j);
                    
				}
    			j++;
    		}
    		i++;
    	}
    	
    	i=0;
    	j=0;
    	
    	// Compare posVec with negVec
    	while(i<posVec.size()){
    		while(j<negVec.size()){
    			
    			if(posVec.get(i).equals(negVec.get(j))){
					//Remove from both negVec and posVec
                    posVec.removeElementAt(i);
                    negVec.removeElementAt(j);
                    
				}
    			j++;
    		}
    		i++;
    	}	
		
	}
	
    public boolean isSubset(Clause c)
    {
    	for (int i = 0; i < posVec.size(); i++)
    	{
    		if (!c.posVec.contains(posVec.get(i)))
    			return false;
    	}
    	
    	for (int i = 0; i < negVec.size(); i++)
    	{
    		if (!c.negVec.contains(negVec.get(i)))
    			return false;
    	}
    	
    	return true;
    }
    
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((negVec == null) ? 0 : negVec.hashCode());
		result = prime * result + ((posVec == null) ? 0 : posVec.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clause other = (Clause) obj;
		if (negVec == null) {
			if (other.negVec != null)
				return false;
		} else if (!negVec.equals(other.negVec))
			return false;
		if (posVec == null) {
			if (other.posVec != null)
				return false;
		} else if (!posVec.equals(other.posVec))
			return false;
		return true;
	}

	public void display(){

		System.out.print("[");

		if(negVec.size() > 0){
			for(int i=0; i<negVec.size(); i++){
				
				System.out.print("-"+negVec.elementAt(i));
				if(i != negVec.size()-1)
					System.out.print(" V ");
			}
		}
		
		if(posVec.size() > 0 && negVec.size() > 0)
			System.out.print(" V ");
		
		if(posVec.size() > 0){
			for(int i=0; i<posVec.size(); i++){
				System.out.print(posVec.elementAt(i));
				if(i != posVec.size()-1)
					System.out.print(" V ");
			}
		}
		System.out.print("] \n");
	}

	public boolean isContradictory(){
		
		for(int i=0; i<posVec.size(); i++){
			for(int j=0; j<negVec.size(); j++){
				
				if(posVec.get(i).equals(negVec.get(j)))
					return true;
				
			}
		}
		
		return false;
	}

    public boolean isEmpty() {
	    return (negVec.size() + posVec.size()) == 0;
    }
}
