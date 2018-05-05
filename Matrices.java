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
            int[][] P = new int[n / 2][n / 2];
            int[][] Q = new int[n / 2][n / 2];
            int[][] R = new int[n / 2][n / 2];
            int[][] S = new int[n / 2][n / 2];
            int[][] T = new int[n / 2][n / 2];
            int[][] U = new int[n / 2][n / 2];
            int[][] V = new int[n / 2][n / 2];

            partition(n, arrA, a11, a12, a21, a22);
            partition(n, arrB, b11, b12, b21, b22);
            
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a11, a22), matrixAdd(n / 2, b11, b22), P);
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a21, a22), b11, Q);
            strassenMatrixMult(n / 2, a11, matrixSub(n / 2, b12, b22), R);
            strassenMatrixMult(n / 2, a22, matrixSub(n / 2, b21, b11), S);
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a11, a12), b22, T);
            strassenMatrixMult(n / 2, matrixSub(n / 2, a21, a11), matrixAdd(n / 2, b11, b12), U);
            strassenMatrixMult(n / 2, matrixSub(n / 2, a12, a22), matrixAdd(n / 2, b21, b22), V);
            
            int[][] c11 = matrixSub(n / 2, matrixAdd(n / 2, matrixAdd(n / 2, P, S), V), T);
            int[][] c12 = matrixAdd(n / 2, R, T);
            int[][] c21 = matrixAdd(n / 2, Q, S);
            int[][] c22 = matrixSub(n / 2, matrixAdd(n / 2, matrixAdd(n / 2, P, R), U), Q);
            
            matrixConcat(n, arr, c11, c12, c21, c22);
        }
    }
    
    private static void matrixConcat(int n, int[][] C, int[][] c11, int[][] c12, int[][] c21, int[][] c22)
    {
        for(int i = 0; i < n / 2; i++)
        {
            for(int j = 0; j < n/2; j++)
            {
                C[i][j] = c11[i][j];
                C[i][j+(n/2)] = c12[i][j];
                C[i+(n/2)][j] = c21[i][j];
                C[i+(n/2)][j+(n/2)] = c22[i][j];
            }
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
    
    private static int[][] matrixSub(int n, int[][] a, int[][] b)
    {
        int[][] c = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
    
    private static void partition(int n, int[][] a, int[][] a11, int[][] a12, int[][] a21, int[][] a22)
    {
        for(int i = 0; i < n/2; i++)
        {
            for(int j = 0; j < n/2; j++)
            {
                a11[i][j] = a[i][j];
                a12[i][j] = a[i][j+(n/2)];
                a21[i][j] = a[i+(n/2)][j];
                a22[i][j] = a[i+(n/2)][j+(n/2)];
            }
        }
    }
}
