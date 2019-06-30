package graph_algs;

import java.util.*;

import static java.lang.System.out;

public class dummy{
    	public static void main(String[] args){
    		
    		//Create an adjacency matrix
    		
    		ArrayList<Integer> nodes = new ArrayList<Integer>();
    		nodes.add(0);
    		nodes.add(1);
    		nodes.add(2);
    		nodes.add(3);
    		nodes.add(4);
    		
   		int[][] adj = {{0,0,1,1,0}, {0,1,0,1,1}, {1,0,0,0,0}, {1,0,0,1,1}, {0,0,1,0,0}};
//    		
    		Graph<Integer> g = new Graph<Integer>(nodes, adj);
    		g.print();
//    		
    	
//    		
   			out.println(adj.length);
   			out.println(adj[0].length);
   			
   			ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
   			edges.addAll(g.getEdges());
   			out.println(edges.get(0).toString(false));
   			out.println(new Edge<Integer>(3,4).equals(new Edge<Integer>(3,4)));
   			out.println(g.getEdges().contains(new Edge<Integer>(3,4)));
    		
    		
    		
    		}
}