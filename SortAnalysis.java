/**
 *   Name:      Arellano, Josue
 *   File:      SortAnalysis.java
 *   Project:   #1
 *   Due:       Apr 26, 2018
 *   Course:    cs331-s18
 *
 *   Description:
 *              This program implements and analyzes sorting algorithms and matrix
 *                  multiplication algorithms.
 */
package sortanalysis;
import java.util.Random;

public class SortAnalysis
{
    public static void main(String[] args)
    {
        //Random object to create random integers to populate arrays.
        Random rand = new Random();
        
        //This is initialized to try to stop the program from running for too long.
        long timer = System.currentTimeMillis();
        //Long type variables to find the time it takes to run certain methods.
        long startTimer;
        long endTimer;
        
        //Title: Sorting Algorithms
        System.out.println("SORTING ALGORITHM TEST");
        
        //for loop to create several different size of arrays to sort.
        for(int n = 10000; n < 100000000; n *= 2)
        {
            //New array of size n.
            int[] a = new int[n];

            //For loop to populate the array with random integers.
            for (int i = 0; i < n; i++)
            {
                a[i] = rand.nextInt(1000);
            }
            
            //Copies of array to be able to sort the same array and compare
            //  the time it takes for each algorithm to store the same array.
            int[] b = a;
            int[] c = a;

            //This comparison checks to see if the loop has been running for too
            //  long.
            if (System.currentTimeMillis() - timer > 5 * 60 * 1000)
            {
                System.out.println("Too much time has passed.");
                //break;
            }
            
            //Print the size of the array.
            System.out.printf("Array of size %,d\n", n);

            startTimer = System.currentTimeMillis();                        //Start timer.
            Sorting.exchangeSort(c);                                        //Run algorithm.
            endTimer = System.currentTimeMillis() - startTimer;             //Get runtime by getting the difference of the current time and the Start time.
            System.out.println("\tExchange Sort took " + endTimer + " ms.");//Display runtime.

            startTimer = System.currentTimeMillis();
            Sorting.quickSort(0, n, b);
            endTimer = System.currentTimeMillis() - startTimer;
            System.out.println("\tQuick Sort took " + endTimer + " ms.");

            startTimer = System.currentTimeMillis();
            Sorting.mergeSort(n, a);
            endTimer = System.currentTimeMillis() - startTimer;
            System.out.println("\tMerge Sort took " + endTimer + " ms.\n");
        }
        
        //Title: Matrix Multiplication Test
        System.out.println("MATRIX MULTIPLICATION TEST");
        
        //Reinitialize the timer to find the runtime of the following loop.
        timer = System.currentTimeMillis();
        
        //For loop to run matrix multiplication on multiple sizes of matrices.
        for(int n = 2; n < 100000000; n *= 2)
        {
            //These two-dimensional arrays are used to implement the matrix 
            //  multiplication.
            int[][] a = new int[n][n];
            int[][] b = new int[n][n];
            int[][] c = new int[n][n];
            
            //For loop to populate the arrays with random values.
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    
                    a[i][j] = rand.nextInt(100);
                    b[i][j] = rand.nextInt(100);
                }
            }

            //Check if the loop has run for more than 5 minutes.
            if (System.currentTimeMillis() - timer > 5 * 60 * 1000)
            {
                System.out.println("Too much time has passed.");
                break;
            }
            
            //Print size of array.
            System.out.printf("%,d X %,d Matrix\n", n, n);
            
            startTimer = System.currentTimeMillis();                                //Start timer.
            Matrices.matrixMult(n, a, b, c);                                        //Run algorithm.
            endTimer = System.currentTimeMillis() - startTimer;                     //Get runtime by getting the difference of the current time and the Start time.
            System.out.println("\tOriginal Matrix Mult took " + endTimer + " ms."); //Display runtime.
            
            startTimer = System.currentTimeMillis();
            Matrices.strassenMatrixMult(n, a, b, c);
            endTimer = System.currentTimeMillis() - startTimer;
            System.out.println("\tStrassen Matrix Mult took " + endTimer + " ms.\n");
        }
    }
    
}
