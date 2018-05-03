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
            
        }
    }
}
