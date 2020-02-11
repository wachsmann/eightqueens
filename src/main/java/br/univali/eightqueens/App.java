package javaapplication1;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class JavaApplication1 
{

    static Boolean flag = false;
    static int[] solution;
    static int x = 0;
    
    public static void main(String[] args) {
        
        
        Scanner input = new Scanner(System.in);
    	
        System.out.print("Insira o número de raínhas: ");
        int n = input.nextInt();
        solution = new int[n];
                
        System.out.println("Insira a primeira rainha ("+n+"x"+n+")... ");
        System.out.println("Insira a linha:");
        int row = input.nextInt();
        System.out.print("Insira a coluna:");
        int column = input.nextInt();
        
        System.out.print("Força Bruta (0) - Back Tracking (1):");
        int solutionType = input.nextInt();
        if(outRangeChecker(n,row,column)) 
            solve(solutionType,n,row,column);
        
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
                
                long beggining = System.currentTimeMillis();
                
                bruteForce(queens,row);

                long ending = System.currentTimeMillis();
                
                System.out.println("Tempo total da força bruta: " +(ending - beggining));
                System.out.println("Número de iterações: " + x);

                break;
            case 1:// Backtrack
                
                long beggining2 = System.currentTimeMillis();

                enumerate(queens, 0, row);
                
                long ending2 = System.currentTimeMillis();
                System.out.println("Tempo total do back tracking: " +(ending2 - beggining2));
                System.out.println("Número de iterações: " + x);
                break;
            default:
                System.out.print("Tipo inválido!");
        }
    }
    private static void bruteForce(int[] queens,int row) {
       
        System.out.println("searching...");
       
        permute(queens, 0, queens.length-1,row);
    }

        /** 
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
     */
    private static boolean permute(int[] queens, int l, int r,int row) 
    {   

        if (l == r){ 

            if (isAllConsistent(queens)){
                x++;
                flag = true;
                solution = queens;
                printQueens(solution);
                return true;
            }
            
           
        }else
        { 
            if(flag)
                return true;
            else {
                for (int i = l; i <= r; i++) 
                { 
                    if(l != row && i != row)
                        queens = swap(queens,l,i); 

                    permute(queens, l+1, r,row); 

                    if(l != row && i != row)
                        queens = swap(queens,l,i); 
                    x++;
                } 
            }
        } 
        return false;
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

    
    public static Boolean enumerate(int[] q, int k, int row) {
        int n = q.length;
        if (k == n) {
            x++;
            flag = true;
            solution = q;
            printQueens(solution);
            return true;
            //printQueens(q);
        } else {
            if(flag) return true;
            for (int i = 0; i < n; i++) {
                if(flag) return true;
                if(k != row)
                    q[k] = i;  
                
                if (isConsistent(q, k)) {
                    enumerate(q, k + 1,row);
                }
                
                x++;
            }

        }
        return false;
    }

}
