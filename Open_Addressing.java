package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initialized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    	int h = ((this.A*key) % power2(this.w) ) >> (this.w- this.r);
    	int g = (h+i) % power2(this.r);
    	return g;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    
    	int p;
    	
    		p = probe(key, 0);
    		if(this.Table[p] < 0){
    			this.Table[p] = key;
    			return 0;
    		} else {
    			for(int j=1; j<m; j++){
    				p = probe(key, j);
    				if(this.Table[p] < 0){
    					this.Table[p] = key;
    					return j;
    				}
    			}
    		
    		}
    		return m;
    		
    }
    /* Linear probing not necessarily optimal. 
     * public int insertKey(int key) {
		        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
		    	int i=0;
		    	int p;
		    	int k;
		    	int l = 0;
		    		p = probe(key, i);
		    		if(this.Table[p] < 0){
		    			this.Table[p] = key;
		    		} else {
		    			l++;
		    		for(int j=1; j<m; j++){
		    			k = (p+j) % m;
		    			if(this.Table[k] <0){
		    				this.Table[k] = key;
		    				return l;
		    			} else {
		    				l++;
		    			}
		    		}
		    		 
		    		}
		    		return l;
		    }
     * */

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    	//I am using a better way when it comes to linear probing.
    	int g = probe(key, 0);
    	if(this.Table[g] == key){
    		this.Table[g] = -5;
    		return 0;
    	} else if(this.Table[g] == -1){
    		return 0;
    	} else {

    		for(int j=1; j<m; j++){
    			g = probe(key, j);
    			if(this.Table[g] == key){
    	    		this.Table[g] = -5;
    	    		return j;
    	    	
    	    	}
    			if(this.Table[g] == -1){
    				return j;
    			}
    		}
    	}


        return m;
    }
    
    /* Linear probing not necessarily optimal. 
     *  public int removeKey(int key) {
		        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
		    	int i = 0;
		    	int g = probe(key, i);
		    	if(this.Table[g] == key){
		    		this.Table[g] = -5;
		    		return 0;
		    	} else {
		    		
		    		do {
		    			i++; 
		    			g++;
		    			if(this.Table[g%m]==key){
		    				this.Table[g%m] = -5;
		    				return i;
		    			}
		    				
		    		}while(this.Table[g%m]!=key && i < m);
		    	} 
		    	
		    	
		        return i;
		    }

     * */

}
