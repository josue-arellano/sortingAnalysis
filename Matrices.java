/**
 *   Name:      Arellano, Josue
 *   File:      Matrices.java
 *   Project:   #1
 *   Due:       May 2, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package sortanalysis;

/**
 *
 * @author josue
 */
public class Matrices
{
    public static void matrixMult(int n, int arrA[][], int arrB[][], int arr[][])
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                arr[i][j] = 0;
                for(int k = 0; k < n; k++)
                {
                    arr[i][j] += arrA[i][k] * arrB[k][j];
                }
            }
        }
    }
    
    public static void strassenMatrixMult(int n, int arrA[][], int arrB[][], int arr[][])
    {
        if(n == 2)
        {
            matrixMult(2, arrA, arrB, arr);
        }
        else
        {
            int[][] a11 = new int[n / 2][n / 2];
            int[][] a12 = new int[n / 2][n / 2];
            int[][] a21 = new int[n / 2][n / 2];
            int[][] a22 = new int[n / 2][n / 2];
            int[][] b11 = new int[n / 2][n / 2];
            int[][] b12 = new int[n / 2][n / 2];
            int[][] b21 = new int[n / 2][n / 2];
            int[][] b22 = new int[n / 2][n / 2];

            partition(n / 2, arrA, a11, a12, a21, a22);
            partition(n / 2, arrB, b11, b12, b21, b22);
        }
    }
    
    private static int[][] matrixAdd(int n, int[][] a, int[][] b)
    {
        int[][] c = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    
    private static void partition(int n, int[][] a, int[][] a11, int[][] a12, int[][] a21, int[][] a22)
    {
        
    }
}
