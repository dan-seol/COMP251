import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class edge<T>{
    private T i;
    private T j;

    public edge(T node1, T node2){ 
        this.i = node1; 
        this.j = node2;}

    public edge(){}

    public T get_i(){return this.i;}
    public T get_j(){return this.j;} 

    public void set_i(T i_){this.i = i_;}
    public void set_j(T j_){this.j = j_;}
    
    public Set<T> nodes(){
         Set<T> nodes = new HashSet<T>(); 
         nodes.add(i); nodes.add(j); 
         return nodes;}

    public String toString(boolean directed){
        ArrayList<T> list = new ArrayList<T>();
        list.add(i);
        list.add(j);
        Set<T> nodes = new HashSet<T>(); 
         nodes.add(i); nodes.add(j); 
        if(directed){
            return "This edge contains node in order of "+list.toString();
        } else{
            return nodes.toString();
        }
    }
}

public class edgeW<T, W> extends edge<T>{
    private W w;
    public edgew(T node1, T node2, W weight){
        super(node1, node2);
        this.w = weight;
    } 

    public W get_w(){return this.w}

    public void set_w(W w_){this.w = w_;}
}