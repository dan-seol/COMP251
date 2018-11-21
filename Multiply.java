//*******************************************************************
// 260677676
// Dan Yunheum Seol
// Assignment 4
//*******************************************************************
import java.util.*;
import java.io.*;

public class Multiply{

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {
        int[] product = new int[2];
        // YOUR CODE GOES HERE  (Note: Change return statement)
        if(size == 1){
            product[0] = x*y;
            product[1] = 1;
        
        } else {
        int m = (size+1)/2;
        int doublem = (int) Math.pow(2, m);
       
        int a = x/doublem;
        int b = x % doublem;
      
        int c = y/doublem;
        int d = y % doublem;
    
        int e = naive(m, a, c)[0];
        product[1] =  product[1] + naive(m, a, c)[1];
        int f = naive(m, b, d)[0];
        product[1] = product[1]+naive(m, b, d)[1];
        int g = naive(m, b, c)[0];
        product[1] = product[1]+naive(m, b, c)[1];
        int h = naive(m, a, d)[0];
        product[1] = product[1] + naive(m, a, d)[1];
           // +
        // +
        // +
        //bitwise operator returning errors - fixing it to Math.pow() method with int type casting
        int dbldblm = (int) Math.pow(2,2*m); 
        int z  = e*dbldblm + (doublem*(g+h)) + f;
        product[1] = product[1] + 3*m;
        product[0] = z;
        }
        
       
        return product;
        
    }

    public static int[] karatsuba(int size, int x, int y) {
        
        // YOUR CODE GOES HERE  (Note: Change return statement)
        int[] product = new int[2];
        if (size == 1){
            product[0] = x*y;
            product[1] = 1;
        } else {
        int m = (size+1)/2;        //bitwise operator returning errors - fixing it to Math.pow() method with int type casting 
        int doublem = (int) Math.pow(2, m);
        
        int a = x/doublem; // does not count
        int b = x %(doublem); //counts..?
        
       	
        int c = y/doublem; // does not count
        int d = y % doublem;
              
        int e = karatsuba(m, a, c)[0];
        product[1] =  product[1] + karatsuba(m, a, c)[1];
        int f = karatsuba(m, b, d)[0];
        product[1] = product[1]+karatsuba(m, b, d)[1];
        int g = karatsuba(m, a-b, c-d)[0];
        product[1] = product[1]+karatsuba(m, a-b, c-d)[1];
		//This contributes to another 2m
        int dbldblm = (int) Math.pow(2,2*m);
        int z = e*(dbldblm) + ((e+f-g)*doublem)+(f);
        product[1] = product[1] + 6*m;
        product[0] = z;
        }

        return product;
        
    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}