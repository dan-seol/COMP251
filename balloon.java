import java.io.*;
import java.util.*;
public class balloon {
	//260677676

	//length of an arrayList = list.size()
	/*
	 * myArrayList<Integer> myNewSortedList = myArrayList.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
	 */
	//Take a greedy approach


	public static int sum(int[] array){
		int sum = 0;
		for(int i=0; i<array.length; i++){
			sum += array[i];
		}
		return sum;

	}

	public static int max(int[] array){
		int max = -1;

		for(int i = 0 ; i<array.length; i++){
			if(max < array[i]){
				max = array[i];
			}
		}
		return max;

	}

	public static int indexOf(int[] array, int value){
		for(int i=0; i< array.length; i++){
			if(array[i] == value){
				return i;
			}
		}
		return -1;
	}

	public static int arrow(ArrayList<Integer> list){



		int arrowCounts = 0;

		//you turn it to an array


		int[] arr = new int[list.size()];
		for(int i=0; i<arr.length; i++){
			arr[i] = list.get(i);
		}
		int sum = sum(arr);
		int maxLeft = max(arr);
		int maxi = indexOf(arr, maxLeft);




		while(sum!=0){

				sum -= maxLeft;
				arr[maxi] = 0;

				maxLeft--;

			for(int k = maxi+1; k< arr.length; k++){

					if(arr[k]==maxLeft)
					{

					sum -= maxLeft;
					arr[k] = 0;

					maxLeft--;


					}

				}
			arrowCounts++;

			maxLeft = max(arr);
			maxi = indexOf(arr, maxLeft);


		}


		return arrowCounts;
	}
	public static void main(String[] args) throws IOException {
			 long start = System.currentTimeMillis();



		List<ArrayList<Integer>> group = new ArrayList<ArrayList<Integer>>();
	    BufferedReader br;
	    String line = null;
	    BufferedWriter writer;

			try{
					br	= new BufferedReader(new FileReader("testBalloons.txt"));

				} catch(FileNotFoundException e){
					System.out.println("Can't find file.");
						return;
					}

	    while ((line = br.readLine()) != null) {
	    	ArrayList<Integer> entriesLine = new ArrayList<Integer>();

	      String[] values = line.split(" ");
	      for (String str : values) {
	    	  entriesLine.add(Integer.valueOf(str));

	      }

	     group.add(entriesLine);
	    }
	    br.close();


	    /*
	    System.out.println("The list "+group.get(2)+" took " + arrow(group.get(2)) +" arrows");
	    System.out.println("The list "+group.get(3)+" took " + arrow(group.get(3)) +" arrows");
	    System.out.println("The list "+group.get(4)+" took " + arrow(group.get(4)) +" arrows");
	    System.out.println("The list "+group.get(5)+" took " + arrow(group.get(5)) +" arrows");
	    System.out.println("The list "+group.get(6)+" took " + arrow(group.get(6)) +" arrows");
	    System.out.println("The list "+group.get(7)+" took " + arrow(group.get(7)) +" arrows");
	    */
			try{
				writer	= new BufferedWriter(new FileWriter("testBalloons_solution.txt"));
	    for(int i=2; i<group.size()-1; i++){
			Integer k = new Integer(arrow(group.get(i)));
	    writer.write(k.toString());
	    writer.write('\n');

	    }
			Integer klast = new Integer(arrow(group.get(group.size()-1)));
	    writer.write(klast.toString());
	    writer.close();

			long end = System.currentTimeMillis();
			System.out.println( (end - start) + "ms");
		} catch(FileNotFoundException e){
			System.out.println("Can't find file.");
				return;
		}
	  }
}
