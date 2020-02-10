package br.univali.eightqueens;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main(String[] args) {
        int n = 8;
    
        //enumerate(n);
        
        Scanner input = new Scanner(System.in);
    	
        System.out.println("Insira a primeira rainha ("+n+"x"+n+")... ");
        System.out.println("Insira a linha:");
        int row = input.nextInt();
        System.out.print("Insira a coluna:");
        int column = input.nextInt();
        System.out.print("Força Bruta (0) - Other Solution...(1):");
        int solutionType = input.nextInt();
        if(outRangeChecker(n,row,column)) solve(solutionType,n,row,column);
        
    }
    private static Boolean outRangeChecker(int n, int row, int column){
        return row < n && row >= 0 && column < n && column >= 0 ? true : false;        
    }
    private static void solve(int solutionType,int n, int row,int column) {
        //create vector 
        int [] queens = new int[n];

        
        switch(solutionType){
            case 0:// brute force
                queens[0] = 0;
                queens[1] = 1;
                queens[2] = 2;
                queens[3] = 3;
                queens[4] = 4;
                queens[5] = 5;
                queens[6] = 6;
                queens[7] = 7;
                
                queens[row] = column;
                queens[column] = row;
                
                bruteForce(queens, row);
                break;
            case 1:// Backtrack
                enumerate(queens, 0);
                
                break;
            default:
                System.out.print("Tipo inválido!");
        }
    }
    private static void bruteForce(int[] queens,int row) {
       
        System.out.println("searching...");
       
        permute(queens,row);
    }


  

    private static void permute(int[] queens,int row){
        Boolean found = false;
        while(!found){
            for (int i = 0; i < queens.length; i++) {
                if(found) break;   
                for (int j = 1; j < queens.length; j++) {
                    if(found) break;
                    if(i != row && j != row)
                        queens = swap(queens, i, j);
                        //for (int m = 0; m < queens.length; m++) {System.out.print(queens[m]);System.out.println("");}
                        if(isAllConsistent(queens)){found=true; break;} 
                    
                    for (int k = queens.length-j; k >= 0; k--) {
                        if(j != row && k != row)
                            queens = swap(queens, j, k);
                            //for (int m = 0; m < queens.length; m++) {System.out.print(queens[m]);}System.out.println("");
                            if(isAllConsistent(queens)){found=true; break;}  
                    }
                    
                } 
                        
            }
        }
        printQueens(queens);

    }
    /** 
     * Swap int at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped array 
     */
    public static int[] swap(int[] intArr, int i, int j) 
    { 
        int temp; 
        
        temp = intArr[i] ; 
        intArr[i] = intArr[j]; 
        intArr[j] = temp; 
        return intArr; 
    } 
    public static boolean isAllConsistent(int[] queens) {
        for (int n = 0; n < queens.length; n++) {
            for (int i = 0; i < n; i++) {
                if (queens[i] == queens[n])             return false;   // same column
                if ((queens[i] - queens[n]) == (n - i)) return false;   // same major diagonal
                if ((queens[n] - queens[i]) == (n - i)) return false;   // same minor diagonal
            }
        }
        return true;
    }
    /***************************************************************************
     * Return true if queen placement q[n] does not conflict with other queens q[0]
     * through q[n-1]
     ***************************************************************************/
    public static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n])             return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }

   /***************************************************************************
    * Prints n-by-n placement of queens from permutation q in ASCII.
    ***************************************************************************/
    public static void printQueens(int[] q) {
        
        //System.out.println(isAllConsistent(q));
        //printQueensNumbers(q);
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) System.out.print("Q ");
                else           System.out.print("* ");//System.out.print("* ");
            }
            System.out.println();
        
        }  
        System.out.println();
    }
    public static void printQueensNumbers(int[] q) {

        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) System.out.print(q[i]+"* ");
                else           System.out.print(q[i]+" ");//System.out.print("* ");
            }
            System.out.println();
        
        }  
        System.out.println();
    }

   /***************************************************************************
    *  Try all permutations using backtracking
    ***************************************************************************/
    public static void enumerate(int n) {
        int[] a = new int[n];
        enumerate(a, 0);
    }

    public static void enumerate(int[] q, int k) {
        int n = q.length;
        if (k == n) printQueens(q);
        else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (isConsistent(q, k)) enumerate(q, k+1);
            
            }

        }
    }  



}
