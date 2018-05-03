/**
 *   Name:      Arellano, Josue
 *   File:      SortAnalysis.java
 *   Project:   #1
 *   Due:       Apr 26, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package sortanalysis;
import java.util.Random;

public class SortAnalysis
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        System.out.println("SORTING ALGORITHM TEST");
        for(int n = 10000; n < 10000; n *= 2)
        {
            int[] a = new int[n];

            for (int i = 0; i < n; i++)
            {
                a[i] = rand.nextInt(1000);
            }
            int[] b = a;
            int[] c = a;
            
            System.out.printf("Array of size %,d\n", n);

            long exTime = System.currentTimeMillis();
            Sorting.exchangeSort(c);
            long exTimeEnd = System.currentTimeMillis() - exTime;
            System.out.println("\tExchange Sort took " + exTimeEnd + " ms.");

            long qTime = System.currentTimeMillis();
            Sorting.quickSort(0, n, b);
            long qTimeEnd = System.currentTimeMillis() - qTime;
            System.out.println("\tQuick Sort took " + qTimeEnd + " ms.");

            long mTime = System.currentTimeMillis();
            Sorting.mergeSort(n, a);
            long mTimeEnd = System.currentTimeMillis() - mTime;
            System.out.println("\tMerge Sort took " + mTimeEnd + " ms.");
            
            System.out.println();
        }
        
        System.out.println("MATRIX MULTIPLICATION TEST");
        for(int n = 2; n < 1000000; n *= 2)
        {
            int[][] a = new int[n][n];
            int[][] b = new int[n][n];
            int[][] c = new int[n][n];
            
            System.out.printf("%,d X %,d Matrix\n", n, n);
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    a[i][j] = rand.nextInt(10);
                    b[i][j] = rand.nextInt(10);
                }
            }
            
            long mTime = System.currentTimeMillis();
            Matrices.matrixMult(n, a, b, c);
            long mTimeEnd = System.currentTimeMillis() - mTime;
            System.out.println("\tOriginal Matrix Mult took " + mTimeEnd + " ms.");
        }
    }
    
}
