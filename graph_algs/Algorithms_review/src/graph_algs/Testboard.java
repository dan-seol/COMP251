package graph_algs;
import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Testboard {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 long start = System.currentTimeMillis();
		int[] info = new int[3];
		
		//List<int[]> Queries = new ArrayList<int[]>();
	    BufferedReader br;
	    String line = null;
	  //  BufferedWriter writer;

			try{
					br	= new BufferedReader(new FileReader("EdgeExistence.txt"));

				} catch(FileNotFoundException e){
					System.out.println("Can't find file.");
						return;
					}

	    line = br.readLine(); //Read the first line

	   String[] raw = line.split(" "); // Read the number of nodes and the number of edges
	    
	    for(int i=0; i<2;i++ ) {
	    	info[i] = Integer.parseInt(raw[i]);
	    }
	    List<Integer> Nodes = new ArrayList<Integer>();
	    for(int i=1; i<info[0]+1; i++) {
	    	Nodes.add(i);
	    }
	   Graph<Integer> g = new Graph<Integer>(Nodes);
	    for(int i=0; i<info[1]; i++) {
	    	line= br.readLine();
	    	String[] rawLine = line.split(" ");
	    	int[] arr = new int[2];
	    	arr[0]= Integer.parseInt(rawLine[0]);
	    	arr[1]= Integer.parseInt(rawLine[1]);
	    	g.addEdge(new Edge<Integer>(arr[0], arr[1]), false, true);
	    
	    }
	    info[2] = Integer.parseInt(br.readLine());
	    ArrayList<Edge<Integer>> Edges = new ArrayList<Edge<Integer>>();
	    Edges.addAll(g.getEdges());
	    
	    for(int k=0; k<info[2]; k++) {
	    	line= br.readLine();
	    	String[] rawLine = line.split(" ");
	    	int[] myarr = new int[2];
	    	myarr[0]= Integer.parseInt(rawLine[0]);
	    	myarr[1]= Integer.parseInt(rawLine[1]);
	    	boolean f = false;
	    	for(int l=0; l<Edges.size(); l++) {
	    		if(Edges.get(l).equals(new Edge<Integer>(myarr[0], myarr[1]))){
	    			f = true;
	    		}
	    	}
	    	
	    	if(f) {
	    		out.println("YES");
	    	} else {
	    		out.println("NO");
	    	}
	    }
	    br.close();
	    long end = System.currentTimeMillis();
	    out.println( (end - start) + "ms");
	    g.print();
	}

}
