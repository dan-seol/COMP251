package graph_algs;

public class WEdge<T, W> extends Edge<T>{
    private W w;
    public WEdge(T node1, T node2, W weight){
        super(node1, node2);
        this.w = weight;
    } 

    public W get_w(){return this.w;}

    public void set_w(W w_){this.w = w_;}
}
