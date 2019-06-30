package graph_algs;
import java.util.*;

/*
 * Default instance of graphs; directed, unweighted.\
 * We use the graph as a representation of arbitrary binary relationship
 */
public class Graph<T> {
	
	/*
	 * Graph with a generic type T; 
	 * the indices can be either a char, String, Integer, or even double
	 * will be mostly used for integers or char, though
	 */
	private Set<T> nodes = new LinkedHashSet<T>();
	private Set<Edge<T>> edges = new LinkedHashSet<Edge<T>>();
	int size=0;
	private int[][] adj;
	private boolean[][] adjbin;
	private ArrayList<ArrayList<T>> lst;
	
	
	//Constructors
	public Graph(){}
	
	public Graph( List<T> vertices) {
		this.nodes.addAll(vertices) ;
		this.size = this.nodes.size();
		this.adj =  new int[this.size][this.size];
		this.adjbin = new boolean[this.size][this.size];
		this.lst = new ArrayList<ArrayList<T>>();
		
		for(int i=0; i<this.size; i++) {
			this.lst.add(new ArrayList<T>());
		}
		
	}
	
	public Graph(ArrayList<Edge<T>> web) {
		this.edges.addAll(web);
		for(int i=0; i<web.size(); i++) {
			this.nodes.add(web.get(i).get_i());
			this.nodes.add(web.get(i).get_j());
		}
		this.size = this.nodes.size();
		this.adj =  new int[this.size][this.size];
		this.adjbin = new boolean[this.size][this.size];
		ArrayList<T> names= new ArrayList<T>();
		names.addAll(this.nodes);
		for(int i=0; i<web.size(); i++) {
			int n = names.indexOf(web.get(i).get_i());
			int m = names.indexOf(web.get(i).get_j());
			this.adj[n][m]= 1;
			this.adjbin[n][m]=true;
			this.lst.get(i).add(i, web.get(i).get_j());
		}
		}
	
	public Graph( List<T> vertices, int[][] adj) {
		if(adj.length==vertices.size()) {
		this.nodes.addAll(vertices) ;
		this.size = this.nodes.size();
		this.adj =  adj;
		this.adjbin = new boolean[this.size][this.size];
		this.lst = new ArrayList<ArrayList<T>>();
		
		for(int i=0; i<this.size; i++) {
			this.lst.add(new ArrayList<T>());
		}
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				this.adjbin[i][j] = (this.adj[i][j]>0);
				if(this.adjbin[i][j]) {
				this.lst.get(i).add(vertices.get(j));
				this.edges.add(new Edge<T>(vertices.get(i), vertices.get(j)));
				}
			}
			
		}
		}
		
	}
	
	public Graph( List<T> vertices, boolean[][] adjbin) {
		if(adj.length==vertices.size()) {
		this.nodes.addAll(vertices) ;
		this.size = this.nodes.size();
		this.adjbin =  adjbin;
		this.adj = new  int[this.size][this.size];
		this.lst = new ArrayList<ArrayList<T>>();
		
		for(int i=0; i<this.size; i++) {
			this.lst.add(new ArrayList<T>());
		}
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				
				if(this.adjbin[i][j]) {
				this.adj[i][j] = 1;
				this.lst.get(i).add(vertices.get(j));
				this.edges.add(new Edge<T>(vertices.get(i), vertices.get(j)));
				}
			}
			
		}
		}
		
	}

	public Graph( T[] vertices, int[][] adj) {
		if(adj.length==vertices.length) {
		for(int i=0; i<adj.length; i++) {this.nodes.add(vertices[i]);}
		this.size = this.nodes.size();
		this.adj =  adj;
		this.adjbin = new boolean[this.size][this.size];
		this.lst = new ArrayList<ArrayList<T>>();
		
		for(int i=0; i<this.size; i++) {
			this.lst.add(new ArrayList<T>());
		}
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				this.adjbin[i][j] = (this.adj[i][j]>0);
				if(this.adjbin[i][j]) {
				this.lst.get(i).add(vertices[j]);
				this.edges.add(new Edge<T>(vertices[i], vertices[j]));
				}
			}
			
		}
		}
		
	}
	
	public Graph( T[] vertices, boolean[][] adjbin) {
		if(adj.length==vertices.length) {
		for(int i=0; i<adj.length; i++) {this.nodes.add(vertices[i]);}
		this.size = this.nodes.size();
		this.adjbin =  adjbin;
		this.adj = new  int[this.size][this.size];
		this.lst = new ArrayList<ArrayList<T>>();
		
		for(int i=0; i<this.size; i++) {
			this.lst.add(new ArrayList<T>());
		}
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				
				if(this.adjbin[i][j]) {
				this.adj[i][j] = 1;
				this.lst.get(i).add(vertices[j]);
				this.edges.add(new Edge<T>(vertices[i], vertices[j]));
				}
			}
			
		}
		}
		
	}
	
	//Constructors end
	
	//Getters 
	public Set<T> getNodes() {
		return nodes;
	}
	
	public Set<Edge<T>> getEdges() {
		return edges;
	}
	
	public int getSize() {
		return size;
	}

	public int[][] getAdj() {
		return adj;
	}
	
	public boolean[][] getAdjbin() {
		return adjbin;
	}
	
	public ArrayList<ArrayList<T>> getLst() {
		return lst;
	}
	//Getters done

	public boolean addNode(T v) {
		int n = this.nodes.size();
		this.nodes.add(v);
		this.size = this.nodes.size();
		this.lst.add(new ArrayList<T>());
		if(n==0) {
			this.adj= new int[1][1]; 
			this.adjbin = new boolean[1][1];
			return true;
		} else {
			int[][] adj_ = new int[n+1][n+1];
			boolean[][] adjbin_ = new boolean[n+1][n+1];
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++) {
					adj_[i][j] = this.adj[i][j];
					adjbin_[i][j] = this.adjbin[i][j];
				}
				
			}
			this.adj = adj_;
			this.adjbin = adjbin_;
			return true;
		}
	}
	
	public boolean addEdge(Edge<T> e, boolean newNodes) {
		T i = e.get_i();
		T j = e.get_j();
		ArrayList<T> ordered = new ArrayList<T>();
		ordered.addAll(this.nodes);
		if(this.nodes.contains(i)&&this.nodes.contains(j)) {
			this.edges.add(e);
		 int i_ = ordered.indexOf(i);
		 int j_ = ordered.indexOf(j);
		 this.adj[i_][j_] = 1;
		 this.adjbin[i_][j_] = true;
		 this.lst.get(i_).add(j);
		 return true;
		} else {
			if(newNodes) {
				if(this.nodes.contains(i)){
					this.edges.add(e);

					this.addNode(j);
					this.addEdge(e, false);
				}else if (this.nodes.contains(j)) {
					this.edges.add(e);

					this.addNode(i);
					this.addEdge(e, false);
				} else {
					this.edges.add(e);

					this.addNode(i);
					this.addNode(j);
					this.addEdge(e, false);
				}
				return true;
			}else{return false;}
		}
		
	}
	
	public boolean addEdge(Edge<T> e, boolean newNodes, boolean undirected) {
		if(undirected) {
			this.addEdge(e, newNodes);
			this.addEdge(e.dual(), newNodes);
			return true;
		} else {
			this.addEdge(e, newNodes);
			return true;
		}
	}
	
	public boolean delNode(T v){
		ArrayList<T> ordered = new ArrayList<T>();
		ordered.addAll(this.nodes);
		if(!this.nodes.contains(v)) {return false;}
		else {
			int n = this.size;
			int[][] adj_ = new int[n-1][n-1];
			boolean[][] adj_bin = new boolean[n-1][n-1];
			int k_ = ordered.indexOf(v);
			for(int i=0; i<k_; i++) {
				for(int j=0; j<k_; j++) {
					adj_[i][j] = this.adj[i][j];
					adj_bin[i][j] = this.adjbin[i][j];

				}
				for(int j=k_+1; j<n; j++) {
					adj_[i][j-1] = this.adj[i][j];
					adj_bin[i][j-1] = this.adjbin[i][j];

				}
			
			}
			for(int i=k_+1; i<n; i++) {
				for(int j=0; j<k_; j++) {
					adj_[i-1][j] = this.adj[i][j];
					adj_bin[i-1][j] = this.adjbin[i][j];

				}
				for(int j=k_+1; j<n; j++) {
					adj_[i-1][j-1] = this.adj[i][j];
					adj_bin[i-1][j-1] = this.adjbin[i][j];

				}
				
			}
			this.nodes.remove(v);
			this.lst.remove(k_);
			this.size--;
			return true;
		}
		
	}
	
	public boolean delEdge(Edge<T> e) {
		if(this.edges.contains(e)) {
			ArrayList<T> ordered = new ArrayList<T>();
			ordered.addAll(this.nodes);
			int i_ = ordered.indexOf(e.get_i());
			int j_ = ordered.indexOf(e.get_j());
			this.adj[i_][j_] = 0;
			this.adjbin[i_][j_] = false;
			this.lst.get(i_).remove(e.get_j());
			return true;
			
		} else {
			return false;
		}
	}
	
	public boolean delEdge(Edge<T> e, boolean undirected) {
		if(undirected) {
			this.delEdge(e);
			this.delEdge(e.dual());
			return true;
		} else {
			this.delEdge(e);
			return true;
		}
	}
	
	
	

	public String toString() {
		String s = "";
		s = s+"****The nodes are :"+this.nodes.toString()+"**** \n";
		s = s+"****The edges are : \n";
		ArrayList<Edge<T>> ordered = new ArrayList<Edge<T>>();
		ordered.addAll(this.edges);
		for(int i=0; i< this.edges.size(); i++ ) {
			s = s+ ordered.get(i).toString(false)+"\n";
		}
		
		s= s+"**** \n";
		s = s+"****The adjacency matrix is represented as: \n";
		for(int i=0; i<this.adj.length; i++) {
			s = s+Arrays.toString(this.adj[i])+"\n";
		}
		s = s+"**** \n";
		return s;
	}
	
	public void print() {
		System.out.println(this.toString());
	}
}
