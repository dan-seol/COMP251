import java.util.*;
import java.io.*;

//260677676

public class islands {

    int [][] map;
    int length;
    int width;
    int islandsCounted;

    public islands(int [][] m) {
        map = m;
        length = m.length;
        width = m[0].length;
        islandsCounted = 0;
    }


    public void search() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {

                if(map[i][j] == 1) {
                    this.compare(i,j);
                    this.islandsCounted++;
                }
            }
        }
    }


    public void compare(int i, int j) {

        while(map[i][j] == 1) {
            map[i][j] = 0;
            if(j< width -1 && map[i][j+1] == 1) compare(i, (j+1));
            if(j>0 && map[i][j-1] == 1) compare(i, (j-1));
            if(i< length -1 && map[i+1][j] == 1) compare((i+1), j);
            if(i>0 && map[i-1][j] == 1) compare((i-1), j);

        }

    }


    public static void main(String [] args) throws IOException {
		 long start = System.currentTimeMillis();
     BufferedReader f;

        //get file
        try{
            f = new BufferedReader(new FileReader("testIslands.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Can't find file.");
            return;
        }


        int numOfProblem = Integer.parseInt(f.readLine());
        int [] islands = new int[numOfProblem];


        for(int i = 0; i<numOfProblem; i++) {
            int[] entriesLine = new int[2];

  	         String[] values = f.readLine().split(" ");
             for (int l = 0; l<2; l++) {
     	    	  entriesLine[l] = Integer.valueOf(values[l]);

     	      }
            int [][] m = new int[entriesLine[0]][entriesLine[1]];

            for(int j=0; j<entriesLine[0]; j++) {
                String s = f.readLine();
                s = s.replace('#', '0');
                s = s.replace('-', '1');
                String[] entries = s.split("");
              for(int k = 0; k<entriesLine[1]; k++){
                m[j][k] = Integer.valueOf(entries[k]);
              }
            }


            islands theIsland = new islands(m);

            theIsland.search();
            islands[i] = theIsland.islandsCounted;


        }
        f.close();

        try{
            PrintWriter writer = new PrintWriter("testIslands_solution.txt");
            for(int i=0; i<numOfProblem; i++) {
                writer.println(islands[i]);
            }
            writer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("shouldn't come here");
        }

        long end = System.currentTimeMillis();
        System.out.println( (end - start) + "ms");
    }
}
