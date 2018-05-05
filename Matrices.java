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

public class Matrices
{
    //This method implements matrix multiplication.
    public static void matrixMult(int n, int arrA[][], int arrB[][], int arr[][])
    {
        //Loop through all values in the array.
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                //Initialize arr[i][j] to zero to allow for easier 
                //  multiplication computation.
                arr[i][j] = 0;
                
                //Loop through all the values of arrA and arrB.
                for(int k = 0; k < n; k++)
                {
                    //Populate arr with the product of arrA and arrB at each index.
                    arr[i][j] += arrA[i][k] * arrB[k][j];
                }
            }
        }
    }
    
    //This method implements the strassen method of multiplying matrices.
    public static void strassenMatrixMult(int n, int arrA[][], int arrB[][], int arr[][])
    {
        //If the matrix is only a 2x2 matrix, then perform regular multiplication.
        if(n == 2)
        {
            matrixMult(2, arrA, arrB, arr);
        }
        else
        {
            //Create several arrays that will store the division of the original array.
            int[][] a11 = new int[n / 2][n / 2];    //Top left of array.
            int[][] a12 = new int[n / 2][n / 2];    //Top right of the array.
            int[][] a21 = new int[n / 2][n / 2];    //Bottom left of the array.
            int[][] a22 = new int[n / 2][n / 2];    //Bottom right of the array.
            
            //Create several arrays that will store the division of the original array.
            int[][] b11 = new int[n / 2][n / 2];    //Top left of the array.
            int[][] b12 = new int[n / 2][n / 2];    //Top right of the array.
            int[][] b21 = new int[n / 2][n / 2];    //Bottom left of the array.
            int[][] b22 = new int[n / 2][n / 2];    //Bottom right of the array.
            
            int[][] P = new int[n / 2][n / 2];      //P = (a11 + a22) * (b11 + b22)
            int[][] Q = new int[n / 2][n / 2];      //Q = ((A21 + a22) * b11
            int[][] R = new int[n / 2][n / 2];      //R = a11 * (b12 - b22)
            int[][] S = new int[n / 2][n / 2];      //S = a22 * (b21 - b11)
            int[][] T = new int[n / 2][n / 2];      //T = (a11 + a12) * b22
            int[][] U = new int[n / 2][n / 2];      //U = (a21 - a11) * (b11 + b12)
            int[][] V = new int[n / 2][n / 2];      //V = (a12 - a22) * (b21 + b22)

            //Split the matrices into four smaller matrices.
            partition(n, arrA, a11, a12, a21, a22);
            partition(n, arrB, b11, b12, b21, b22);
            
            //Recusively call stressanMatrixMult on the result of the 
            //  calcultions of P, Q, R, S, T, U, V.
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a11, a22), 
                               matrixAdd(n / 2, b11, b22), P);   //P = (a11 + a22) * (b11 + b22)
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a21, a22), 
                               b11, Q);                          //Q = ((A21 + a22) * b11
            strassenMatrixMult(n / 2, a11, matrixSub(n / 2, b12, b22), 
                               R);                               //R = a11 * (b12 - b22)
            strassenMatrixMult(n / 2, a22, matrixSub(n / 2, b21, b11), 
                               S);                               //S = a22 * (b21 - b11)
            strassenMatrixMult(n / 2, matrixAdd(n / 2, a11, a12), 
                               b22, T);                          //T = (a11 + a12) * b22
            strassenMatrixMult(n / 2, matrixSub(n / 2, a21, a11), 
                               matrixAdd(n / 2, b11, b12), U);   //U = (a21 - a11) * (b11 + b12)
            strassenMatrixMult(n / 2, matrixSub(n / 2, a12, a22), 
                               matrixAdd(n / 2, b21, b22), V);   //V = (a12 - a22) * (b21 + b22)
            
            //Populate the four submatrices of the array with the stressan method calculations.
            int[][] c11 = matrixSub(n / 2, matrixAdd(n / 2, 
                                    matrixAdd(n / 2, P, S), V), T); //c11 = P + S - T + V
            int[][] c12 = matrixAdd(n / 2, R, T);                   //c12 = R + T
            int[][] c21 = matrixAdd(n / 2, Q, S);                   //c21 = Q + S
            int[][] c22 = matrixSub(n / 2, matrixAdd(n / 2, 
                                    matrixAdd(n / 2, P, R), U), Q); //c22 = P + R - Q + U
            
            //Repopulate the original array after performing all the calculations.
            matrixConcat(n, arr, c11, c12, c21, c22);
        }
    }
    
    //Method used to populate an array with 4 different matrices that are half 
    //  of the matrix being populated.
    private static void matrixConcat(int n, int[][] C, int[][] c11, int[][] c12, 
                                     int[][] c21, int[][] c22)
    {
        //Loop through all the values of each matrix.
        for(int i = 0; i < n / 2; i++)
        {
            for(int j = 0; j < n/2; j++)
            {
                //Copy the matrices into the matrix.
                C[i][j] = c11[i][j];
                C[i][j+(n/2)] = c12[i][j];
                C[i+(n/2)][j] = c21[i][j];
                C[i+(n/2)][j+(n/2)] = c22[i][j];
            }
        }
    }
    
    //Method used to add two matrices together.
    private static int[][] matrixAdd(int n, int[][] a, int[][] b)
    {
        //Create new two-dimensional array to return added values.
        int[][] c = new int[n][n];
        
        //Loop through all the values in a and b to compute addition.
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                //Place sumation of a and b at each index in c.
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    
    //Method used to subtract two matrices.
    private static int[][] matrixSub(int n, int[][] a, int[][] b)
    {
        //Create new two-dimensional array to return subtracted values.
        int[][] c = new int[n][n];
        
        //Loop through all the values in a and b to compute subtraction.
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                //Place difference of a and b at each index in c.
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
    
    //Method used to split a two-dimensional array into four smaller 
    //  dimensional arrays.
    private static void partition(int n, int[][] a, int[][] a11, int[][] a12, 
                                  int[][] a21, int[][] a22)
    {
        //Loop through all the values of the original array.
        for(int i = 0; i < n/2; i++)
        {
            for(int j = 0; j < n/2; j++)
            {
                //populate the smaller arrays.
                a11[i][j] = a[i][j];
                a12[i][j] = a[i][j+(n/2)];
                a21[i][j] = a[i+(n/2)][j];
                a22[i][j] = a[i+(n/2)][j+(n/2)];
            }
        }
    }
}
