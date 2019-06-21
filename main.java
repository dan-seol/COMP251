package A1;

import A1.Chaining.*;
import A1.Open_Addressing.*;
import java.io.*;
import java.util.*;

public class main {

    /**
     * Calculate 2^w
     */
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    /**
     * Uniformly generate a random integer between min and max, excluding both
     */
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        //if the seed is equal or above 0, we use the input seed, otherwise not.
        if (seed >= 0) {
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max - min - 1);
        return i + min + 1;
    }

    /**
     * export CSV file
     */
    public static void generateCSVOutputFile(String filePathName, ArrayList<Double> alphaList, ArrayList<Double> avColListChain, ArrayList<Double> avColListProbe) {
        File file = new File(filePathName);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            int len = alphaList.size();
            fw.append("Alpha");
            for (int i = 0; i < len; i++) {
                fw.append("," + alphaList.get(i));
            }
            fw.append('\n');
            fw.append("Chain");
            for (int i = 0; i < len; i++) {
                fw.append("," + avColListChain.get(i));
            }
            fw.append('\n');
            fw.append("Open Addressing");
            for (int i = 0; i < len; i++) {
                fw.append(", " + avColListProbe.get(i));
            }
            fw.append('\n');
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*===========PART 1 : Experimenting with n===================*/
        //Initializing the three arraylists that will go into the output 
        ArrayList<Double> alphaList = new ArrayList<Double>();
        ArrayList<Double> avColListChain = new ArrayList<Double>();
        ArrayList<Double> avColListProbe = new ArrayList<Double>();

        //Keys to insert into both hash tables
        int[] keysToInsert = {164, 127, 481, 132, 467, 160, 205, 186, 107, 179,
            955, 533, 858, 906, 207, 810, 110, 159, 484, 62, 387, 436, 761, 507,
            832, 881, 181, 784, 84, 133, 458, 36};

        //values of n to test for in the experiment
        int[] nList = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        //value of w to use for the experiment on n
        int w = 10;

        for (int n : nList) {

            //initializing two hash tables with a seed
            Chaining MyChainTable = new Chaining(w, 137);
            Open_Addressing MyProbeTable = new Open_Addressing(w, 137);

            /*Use the hash tables to compute the average number of 
                        collisions over keys keysToInsert, for each value of n. 
                        The format of the three arraylists to fill is as follows:
                        
                        alphaList = arraylist of all tested alphas 
                                   (corresponding to each tested n)
                        avColListChain = average number of collisions for each
                                         Chain experiment 
                                         (make sure the order matches alphaList)
                        avColListProbe =  average number of collisions for each
                                         Linear Probe experiment
                                           (make sure the order matches)
                        The CSV file will output the result which you can visualize
             */
            //ADD YOUR CODE HERE
            double[] tempListChain = new double[n];
        	double[] tempListProbe = new double[n];
            for(int i=0; i<n; i++){
            	
               tempListChain[i] = MyChainTable.insertKey(keysToInsert[i]);
               tempListProbe[i] = MyProbeTable.insertKey(keysToInsert[i]);
                
            }
            double s = 0; 
            double t = 0;
            for(int i=0; i<n; i++){
            	s = s + tempListChain[i];
            	t = t + tempListProbe[i];
            }
            
            alphaList.add((double)n/(double)MyChainTable.m);
            avColListChain.add(s/n);
            avColListProbe.add(t/n);
            
        }

        generateCSVOutputFile("n_comparison.csv", alphaList, avColListChain, avColListProbe);

        /*===========    PART 2 : Test removeKey  ===================*/
 /* In this exercise, you apply your removeKey method on an example.
        Make sure you use the same seed, 137, as you did in part 1. You will
        be penalized if you don't use the same seed.
         */
        //Please not the output CSV will be slightly wrong; ignore the labels.
        ArrayList<Double> removeCollisions = new ArrayList<Double>();
        ArrayList<Double> removeIndex = new ArrayList<Double>();
        int[] keysToRemove = {6, 8, 164, 180, 127, 3, 481, 132, 4, 467, 5, 160,
            205, 186, 107, 179};

        //ADD YOUR CODE HERE
        
        Open_Addressing MyProbeTableRemv = new Open_Addressing(w, 137);
        
        for(int i=0; i<16; i++){
        	MyProbeTableRemv.insertKey(keysToInsert[i]);
        }
        
        for(int j=0; j<16; j++){
        	removeCollisions.add((double)MyProbeTableRemv.removeKey(keysToRemove[j]));
        	removeIndex.add((double)j);
        }
        generateCSVOutputFile("remove_collisions.csv", removeIndex, removeCollisions, removeCollisions);

        /*===========PART 3 : Experimenting with w===================*/

 /*In this exercise, the hash tables are random with no seed. You choose 
                values for the constant, then vary w and observe your results.
         */
        //generating random hash tables with no seed can be done by sending -1
        //as the seed. You can read the generateRandom method for detail.
        int[] w_vec = new int[18];
        for(int i=0; i<18; i++ ){
        	w_vec[i] = 6+i;
        }
       
        int randomNumber;
       
       
        //Lists to fill for the output CSV, exactly the same as in Task 1.
        ArrayList<Double> alphaList2 = new ArrayList<Double>();
        ArrayList<Double> avColListChain2 = new ArrayList<Double>();
        ArrayList<Double> avColListProbe2 = new ArrayList<Double>();
        
        //ADD YOUR CODE HERE
      
      for(int u : w_vec){
        	double[] Alpha_mu = new double[10];
        	double[] col_mu_ch = new double[10];
        	double[] col_mu_oa = new double[10];
        	
        	for(int i=0; i<10; i++){
        		double ch = 0;
            	double oa = 0;
        	
	        	 Chaining MyChainTable3 = new Chaining(u, -1);
	             Open_Addressing MyProbeTable3 = new Open_Addressing(u, -1);
	             int n = 10;
	             int[] key_vec = new int[n]; // to prevent saturation
	             for(int j=0; j<key_vec.length; j++){
		             randomNumber = generateRandom(0,55,-1);
		             key_vec[j] = randomNumber;
		         	}
	           
	             for (int j=0; j< key_vec.length;j++) {
	            	    for (int k=j+1;k <key_vec.length;k++) {
	            	        if (key_vec[j]==key_vec[k]){ // or use .equals()
	            	            while(key_vec[j]==key_vec[k]){
	            	            	key_vec[k] = generateRandom(0,55,-1);
	            	            }
	            	        }
	            	    }
	   	             ch = ch + MyChainTable3.insertKey(key_vec[j]);
		             oa = oa + MyProbeTable3.insertKey(key_vec[j]);
		           
	            	}
        	
	             
	            
	             Alpha_mu[i] = (double)(n)/(double)(power2((int)Math.ceil((double)(u)/2)));
	             col_mu_ch[i] = (double)(ch)/(double)(n);
	             col_mu_oa[i] = (double)(oa)/(double)(n);
	             
        	}
        	double Alpha = 0;
        	double MU_ch = 0;
        	double MU_oa = 0;
        	for(int i=0; i<10; i++){
        		Alpha = Alpha + Alpha_mu[i];
        		MU_ch = MU_ch + col_mu_ch[i];
        		MU_oa = MU_oa + col_mu_oa[i];
        	}
        	alphaList2.add(Alpha/10);
        	avColListChain2.add(MU_ch/10);
        	avColListProbe2.add(MU_oa/10);        	
        	
        }
        
        generateCSVOutputFile("w_comparison.csv", alphaList2, avColListChain2, avColListProbe2);

    }

}
