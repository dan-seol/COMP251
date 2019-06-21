package A2;
import java.util.*;
//260677676
//Author: Dan Yunheum Seol
class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;


	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}



	/**
	 * This method is used to sort to compare assignment objects for sorting.
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return 1 if a1 should appear after a2
	 * Return -1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent
	 */

	@Override
	public int compare(Assignment a1, Assignment a2) {
		//YOUR CODE GOES HERE, DONT FORGET TO EDIT THE RETURN STATEMENT
		//Sort them by weights in an ascending order
		if(a1.weight < a2.weight){
			return -1;
		} else if (a1.weight > a2.weight){
			return 1;
		} else {
			return 0;
		}

	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m = size;
	}


	/**
	 *
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {

		//Use the following command to sort your Assignments:
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());

		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		//YOUR CODE GOES HERE
		//We loop it through
		//The main loop that determines the output
		if(lastDeadline > 100){
			return homeworkPlan; // it should not exceed 100;
		}
		for(int i = 0;  i < m; i++){
			if(Assignments.get(i).deadline < 1){
				return homeworkPlan;
			}
		}
		if(Assignments.get(m-1).weight > 100 || Assignments.get(0).weight < 1){
			return homeworkPlan;
		}
		for(int i = m-1; i >= 0; i--){
			boolean deadlineNotTaken = true; // a boolean variable that checks whether a deadline to do something is taken
			int weightTwins = 1;
			int j;
			if(i >0){
			j = i-1;
		} else {
			j = 0;
		}
		//Since the Assignments are sorted by weight in an increasing order,
		//We will make the first for loop iterate from the end of our list (right-most position)

			int minDate = Assignments.get(i).deadline; // Let's save the min(deadline|weight (given weight))  for the case
			//where we get two or more assignments with the same weight
			int position = i; // I use this variable to store its position : p \in Assignments :argmin(deadline|weight)


			while(Assignments.get(j).weight == Assignments.get(i).weight && (j >0)){
				weightTwins++; // we count the number of "weight twins", "weight triplets",..., "weight n-plets.."
					minDate =  min(Assignments.get(j).deadline, minDate);
					if(minDate == Assignments.get(j).deadline){
						position = j; // We choose the one that has the earliest deadline among the "weight n-plets"
					}
					j--; //we iterate it back while fixing the i, using another integer j
			}


			for(int k=0; k<m; k++){
				if(homeworkPlan[k] ==  Assignments.get(i).deadline){
					deadlineNotTaken = false; // we make sure whether the assignment we might put in already has a heavier competitor
				}
			}

			if(weightTwins == 1 && deadlineNotTaken ){// if the weight is unique (since sorted we already have max(weight|deadline))
				//and the deadline is not occupied by another, heavier assignment
				homeworkPlan[Assignments.get(i).number] = Assignments.get(i).deadline;
			} else if(weightTwins == 1 && !deadlineNotTaken ){ // if the deadline is taken;
					//we see whether we could finish that somewhat earlier, comparing them with the work on that deadline
				if(Assignments.get(i).deadline > 0){
				 Assignments.get(i).deadline--; // we see whether we could finish it one unit early

				 i++; // we add the iterator i and make it loop agfain

			 }

		 } else if(weightTwins != 1 && deadlineNotTaken) { //analogous
			 boolean ithNotTaken = true;
	 		for(int k=0; k<m; k++){
	 			if(homeworkPlan[k] >=  Assignments.get(i).deadline){
	 				ithNotTaken = false;
	 			}
	 		}
	 		if(ithNotTaken){
	 			homeworkPlan[Assignments.get(i).number] = Assignments.get(i).deadline;
	 		}
			} else {
					homeworkPlan[Assignments.get(position).number] = Assignments.get(position).deadline;
			}


		}
		boolean[] notTaken = new boolean[m]; // did we miss anything?

		for(int i=0; i<m; i++){
			notTaken[i] = true;

			for(int k=0; k<m; k++){
			 if(homeworkPlan[k] ==  Assignments.get(i).deadline){
				 notTaken[i] = false;
			 }


			 }
			 if(notTaken[i]){
				homeworkPlan[i] = Assignments.get(i).deadline;
		}
	}



		return homeworkPlan;
	}

//Helper method to return minimum between two integers
	public int min(int a, int b){
		if(a <= b){
			return a;
		} else {
			return b;
		}
	}

}
