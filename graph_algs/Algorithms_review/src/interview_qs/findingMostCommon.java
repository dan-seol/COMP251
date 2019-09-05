package interview_qs;
import java.util.*;
public class findingMostCommon {
	
	public static Object mostCommon(Object[] arr) {
		ArrayList<Object> setList = new ArrayList<Object>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		for(int i=0; i<arr.length; i++){
			if(!setList.contains(arr[i])) {
				setList.add(arr[i]); 
				count.add(1);
			} else {
				int j = setList.indexOf(arr[i]);
				count.set(j, count.get(j)+1);
				
			}
		}
		//getting the index
		int m=0;

		for(int k =0; k<count.size(); k++ ) {
		  if(count.get(m) <count.get(k)) {
			  m = k;
		  }
		}
		return setList.get(m);
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String[] dummy1 = {"Hello", "Hi", "Hello", "Hello", "Hi", "Bye","Bye"};
			System.out.println(mostCommon(dummy1));
	}

}
