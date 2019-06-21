package A2;
import java.util.*;

//260677676
//Author: Dan Yunheum Seol
public class Kruskal{

    public static WGraph kruskal(WGraph g){
        int u;
        int v;
        /* Fill this method (The statement return null is here only to compile) */
        int n = g.getNbNodes();
        DisjointSets partition = new DisjointSets(n); // you implement the partition structure
        WGraph G = new WGraph(); // here is your new graph to return
        ArrayList<Edge> E1 = g.listOfEdgesSorted(); // get the arrayList of edges sorted and change it to an array

        //The main for loop to construct MST
        for(int i=0; i< E1.size(); i++){

          //get the ith lightest edge
          Edge e = E1.get(i);
          u = e.nodes[0];
          v = e.nodes[1];

          if(IsSafe(partition, e)){ //Is the edge connecting the two nodes already connected?
            partition.union(u, v);
            G.addEdge(e);
          }
          ArrayList<Edge> E2 = G.listOfEdgesSorted();
          if (E2.size() == n-1){
            // did we construct a tree? for any tree T, |V(T)| - 1 = |E(T)|
              break;
          }

          }
        return G;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return true is here only to compile) */
        // We want to get the nodes that the edge e = (u, v) connect

        int u = e.nodes[0];
        int v = e.nodes[1];

        //is u, v already in the same equivalent class?
        if (p.find(u) == p.find(v)){
          return false;
        }



        return true;

    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);
        

   }
}
