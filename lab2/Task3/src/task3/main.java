package task3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

public class main {
	public static void main(String[] args){
		
		
		/*
		// TASK 3
		State schedule = new State();
		System.out.println("Starting board: ");
		schedule.print();
		schedule.solve();
		
		System.out.println("\nSolved board: ");
		schedule.print();
		System.out.println("Number of conflicts: "+schedule.numberOfConflicts);
		System.out.println("Schedule score: "+schedule.score);
		*/
		
		// TASK 4
		ArrayList<State> solvedSchedules = new ArrayList<State>();
		int maxScore = 0;
		int bestIndex = 0;
		
		while(solvedSchedules.size() < 500){
			State schedule = new State();
			schedule.solve();
			
			// Might get stuck in local minimum, if not solved dont add it
			if(schedule.numberOfConflicts != 0)
				continue;
			
			solvedSchedules.add(schedule);
		
			if(schedule.score > maxScore){
				maxScore = schedule.score;
				bestIndex = solvedSchedules.size()-1;
			}
			
			if(schedule.score == 4){
				System.out.println("Found an optimal schedule after " + solvedSchedules.size()+" iterations.");
				break;
			}
				
		}	
		
		System.out.println("Schedule: ");
		solvedSchedules.get(bestIndex).print();
		System.out.println("Fullfilled preferences: "+solvedSchedules.get(bestIndex).score);
	}
}
