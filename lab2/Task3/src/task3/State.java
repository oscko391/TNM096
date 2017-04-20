package task3;

import java.util.Arrays;
import java.util.Collections;

public class State {
	

	public String[] schedule = {"MT101", "MT102", "MT103", 
			"MT104", "MT105", "MT106", 
			"MT107", "MT201", "MT202", 
			"MT203", "MT204", "MT205", 
			"MT206", "MT301", "MT302", 
			"MT303", "MT304", "MT401", 
			"MT402", "MT403", "MT501", 
			"MT502", "     ", "     "};
	
	public int numberOfConflicts;
	public int score = 0;
	
	State(){
		Collections.shuffle(Arrays.asList(schedule));
		this.numberOfConflicts = totConflicts();
	}
	
	State(String[] startSchedule){
		this.schedule = startSchedule;
		this.numberOfConflicts = totConflicts();
	}
	
	public void print(){
		System.out.println("TP51    SP34    K3");
		System.out.println("----    ----    ----");
	    for(int row=0; row<8; row++){
	    	for(int col=0; col<3; col++){
	    		System.out.print(schedule[row*3+col]+"   ");
	    	}
	    	System.out.println("   "+(row+9) );
	    }
	}
	
	// Min conflicts solver
	public void solve(){
		int totConflicts= totConflicts(), noIt =0, maxIt = 100000;
		
		
		while( noIt < maxIt && totConflicts > 0){
			noIt++;
			
			// Get a random course index from the schedule
			int index = (int) Math.ceil((Math.random() * schedule.length))-1;
			int minIndex = findMinConflicts(index);
			swap(index, minIndex);
			
			totConflicts = totConflicts();
			//System.out.println("Conflicts: "+totConflicts);
			
		}
		
		this.numberOfConflicts = totConflicts;
		calcScore();
	}
	
	// Swaps index a with b
	void swap(int a, int b){
		//System.out.println("Swapping "+schedule[a]+" with "+schedule[b]+"\n");
		String temp = schedule[a];
		schedule[a] = schedule[b];
		schedule[b] = temp;
		  
	}
	
	
	// Calculates the total number of conflicts in the current schedule
	int totConflicts(){
		int totConflicts = 0;
		for(int i=0; i<schedule.length; i+=3){
			// Get course code index from all columns 
			String col1 = Character.toString(this.schedule[i].charAt(2));
			String col2 = Character.toString(this.schedule[i+1].charAt(2));
			String col3 = Character.toString(this.schedule[i+2].charAt(2));
			
			// Compare with eachother
			if( col1.equals(col2) )
				totConflicts++;
			if( col1.equals(col3) )
				totConflicts++;
			if( col2.equals(col3) )
				totConflicts++;
		}
		
		return totConflicts;
	}
	
	
	// Returns the number of conflicts ind will have have if it were to swap with i
	int findConflicts(String compare, int i){
		int col = i%3;
		int conf = 0;
		String neigh1 = "";
		String neigh2 = "";
		
		// Special case for MT5**
		if(compare.equals("5")) return 0;
		
		// Get neigbours from the column
		switch (col){
			case 0:
				neigh1 = schedule[i+1];
				neigh2 = schedule[i+2];
				break;
			case 1:
				neigh1 = schedule[i-1];
				neigh2 = schedule[i+1];
				break;
			case 2:
				neigh1 = schedule[i-1];
				neigh2 = schedule[i-2];
				break;
		}
		
		if(compare.equals(neigh1))
			conf++;
		if(compare.equals(neigh2))
			conf++;
		
		return conf;
	}
	
	// Returns index to the position where ind has the least min-conflicts 
	int findMinConflicts(int ind){
		String currEv = this.schedule[ind];
		String courseDigit = Character.toString(currEv.charAt(2));
		
		int minIndex = ind;
		int minIndConflicts = 9999;
		
		
		// Special case for empty slots in schedule
		if( courseDigit.equals(" ") ) {return ind; }
		
		// Compare currEv with all other slots in schedule
		for(int i=0; i<this.schedule.length; i++){
			String compare = this.schedule[i];
			// If found a new slot with less conflicts, update minIndex and minIndConflicts
			
			int noConflicts = findConflicts(courseDigit, i);
			if(noConflicts < minIndConflicts){
				minIndConflicts = noConflicts;
				minIndex = i;
			}
		}
		
				
		return minIndex;
	}
	
	// Calculates a score based on Task 4
	void calcScore(){
		
		int row0 = 0*3;
		int row3 = 3*3;
		int row4 = 4*3;
		int row5 = 5*3;
		int row7 = 7*3;
		
		// Loop through row 0, (09:00)
		for(int i=row0; i<row0+3; i++){
			// If curr pos only contains whitespaces i.e, not scheduled
			if(schedule[i].trim().length() == 0 )
				this.score++;
				
		}	
		
		//...3 (12:00)
		for(int i=row3; i<row3+3; i++){
			if(schedule[i].trim().length() == 0 )
				this.score++;
		}
		// and 7 (16:00)
		for(int i=row7; i<row7+3; i++){
			if(schedule[i].trim().length() == 0 )
				this.score++;
		}
		
		// Check if MT501/502 are scheduled at 1pm or 2pm
		for(int i=row4; i<row4+3; i++){
			if(schedule[i].equals("MT501") || schedule[i].equals("MT502"))
				this.score++;
		}
		
		for(int i=row5; i<row5+3; i++){
			if(schedule[i].equals("MT501") || schedule[i].equals("MT502"))
				this.score++;
		}
		
		
	}
}





