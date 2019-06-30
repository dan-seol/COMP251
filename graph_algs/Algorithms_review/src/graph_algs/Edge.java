package graph_algs;

import java.util.*;


public class Edge<T>{
    private T i;
    private T j;

    public Edge(T node1, T node2){ 
        this.i = node1; 
        this.j = node2;}

    public Edge(){}

    public T get_i(){return this.i;}
    public T get_j(){return this.j;} 

    public void set_i(T i_){this.i = i_;}
    public void set_j(T j_){this.j = j_;}
    
    public Set<T> nodes(){
         Set<T> nodes = new LinkedHashSet<T>(); 
         nodes.add(i); nodes.add(j); 
         return nodes;}
    
    public Edge<T> dual(){
    	return(new Edge<T>(this.j, this.i));
    }
    
    public boolean equals(Edge<T> e) {
    	if(this.i.equals(e.get_i()) &&this.j.equals(e.get_j())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    public String toString(boolean directed){
        ArrayList<T> list = new ArrayList<T>();
        list.add(i);
        list.add(j);
        Set<T> nodes = new LinkedHashSet<T>(); 
         nodes.add(i); nodes.add(j); 
        if(directed){
            return "This edge contains nodes:  "+list.toString();
        } else{
            return nodes.toString();
        }
    }
    
    
}
