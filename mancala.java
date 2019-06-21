import java.io.*;
import java.util.*;
import static java.lang.System.out;

//260677676
//Got tremendous amount of help from Nathalie Raffray
public class mancala {

	public static int sum(int[] array){
		int sum = 0;
		for(int i=0; i<12; i++){
			sum += array[i];
		}
		return sum;

	}

	public static int min3(int a, int b, int c){
		return (int) Math.min(Math.min(a,b),c);
	}

public static int moves(int[] array, int b){
int best=  b;
int temp1 = b;
int temp2 = b;
boolean A;
boolean B;
boolean C;

if(sum(array)==1){
	best = 1;
	return best;
}
	for(int i = 0; i<array.length; i++){



		if(i<array.length-2){
			A = (array[i] == 0);
			B = (array[i+1] == 1);
			C = (array[i+2] ==1);
			if(A&&B&&C){
				array[i] = 1;
				array[i+1] = 0;
				array[i+2] = 0;
				temp1 = moves(array, b-1);


				array[i] =  0;
				array[i+1] = 1;
				array[i+2] = 1;

			}
		}

		if(i>2){
			A = (array[i] == 0);
			B = (array[i-1] == 1);
			C = (array[i-2] ==1);
			if(A&&B&&C){
				array[i] = 1;
				array[i-1] = 0;
				array[i-2] = 0;
				temp2 = moves(array, b-1);
				array[i] =  0;
				array[i-1] = 1;
				array[i-2] = 1;
			}
		}

		best = min3(temp1, temp2, best);
		}

/*
		if(best==0){
			return sum(array);
		} else{*/
		return   best;
	}




	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 long start = System.currentTimeMillis();



			List<int[]> group = new ArrayList<int[]>();
		    BufferedReader br;
		    String line = null;
		    BufferedWriter writer;

				try{
						br	= new BufferedReader(new FileReader("testMancala.txt"));

					} catch(FileNotFoundException e){
						System.out.println("Can't find file.");
							return;
						}

		    line = br.readLine(); //Read the first line

		    int nbOfProblems =  Integer.parseInt(line); // Read the number of problems


		    while ((line = br.readLine()) != null) {
		    	int[] entriesLine = new int[12];

		      String[] values = line.split(" ");
		      for (int i=0; i<12; i++) {
		    	  entriesLine[i] = (Integer.valueOf(values[i]));

		      }

		     group.add(entriesLine);
		    }
		    br.close();
				/*
		 out.println("There are "+ nbOfProblems + " problems.");
		 out.println("List "+0+Arrays.toString(group.get(0)));
		 out.println("List "+1+Arrays.toString(group.get(1)));
		 out.println("List "+2+Arrays.toString(group.get(2)));
		 out.println("List "+3+Arrays.toString(group.get(3)));
		 out.println("List "+4+Arrays.toString(group.get(4)));
		 out.println("List "+5+Arrays.toString(group.get(5)));
		 */
try{		writer	 = new BufferedWriter(new FileWriter("testMancala_solution.txt"));
for(int i=0; i<nbOfProblems-1; i++){
//out.println("List " +i+ "results" + moves(group.get(i), sum(group.get(i))));
Integer k = new Integer(moves(group.get(i), sum(group.get(i))));
writer.write(k.toString());
writer.write('\n');
}
//out.println("List " +(nbOfProblems-1)+ "results" + moves(group.get(nbOfProblems-1), sum(group.get(nbOfProblems-1))));
Integer klast = new Integer(moves(group.get(nbOfProblems-1), sum(group.get(nbOfProblems-1))));
writer.write(klast.toString());
writer.close();
long end = System.currentTimeMillis();
out.println( (end - start) + "ms");
} catch(FileNotFoundException e){

}

	}

}
