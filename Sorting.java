/**
 *   Name:      Arellano, Josue
 *   File:      Sorting.java
 *   Project:   #1
 *   Due:       Apr 26, 2018
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
public class Sorting
{
    public static void mergeSort(int n, int[] a)
    {
        //Need this to figure out if the array is of size 1. If the array is
        //  size 1, then I no longer need to split the array.
        if(n > 1)
        {
            //These integers will be used to create the size of the temporary
            //  arrays. They need to be half the size of the original array.
            int h = n/2;
            int m = n - h;
            
            //Need temporary arrays to store the left and right of the array.
            int arrA[] = new int[h];
            int arrB[] = new int[m];
            
            //Copying left half of array to arrA.
            for(int i = 0; i < h; i++)
            {
                arrA[i] = a[i];
            }
            
            //Copying right half of array to arrB.
            for(int i = h; i < n; i++)
            {
                arrB[i-h] = a[i];
            }
            
            //Recursively mergeSort the left and right arrays.
            mergeSort(h, arrA);
            mergeSort(m, arrB);
            
            //Reunite the arrays into the original array.
            merge(h, m, arrA, arrB, a);
        }
    }
    
    private static void merge(int h, int m, int[] arrA, int[] arrB, int[] a)
    {
        //These indices are used to maintain the correct index while comparing
        //  the left and right arrays and while filling the original array.
        int i, j, k;
        i = j = k = 0;
        
        //This while loop will only run while there are still unvisited elements
        //  in both arrays.
        while(i < h && j < m)
        {
            if(arrA[i] < arrB[j])
            {
                a[k] = arrA[i];
                i++;
            } else 
            {
                a[k] = arrB[j];
                j++;
            }
            k++;
        }
        
        
        if(i == h)
        {
            while(j < m)
            {
                a[k] = arrB[j];         //Place the rest of arrB in a.
                j++; k++;               //Increment indices.
            }
        } else
        {
            while(i < h)
            {
                a[k] = arrA[i];         //Place the rest of arrA in a.
                i++; k++;               //Increment indices.
            }
        }
    }
    
    public static int partition(int low, int high, int arr[])
    {
        int i = low;
        int pivot = arr[low];
        for(int j = low + 1; j < high; j++)
        {
            if(arr[j] <= pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[low];
        arr[low] = temp;
        return i + 1;
    }
    
    public static void quickSort(int low, int high, int[] arr)
    {
        if(low < high)
        {
            int pivot = partition(low, high, arr);
            quickSort(low, pivot - 1, arr);
            quickSort(pivot + 1, high, arr);
        }
    }
    
    public static void exchangeSort(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[i] > arr[j])
                {
                    int temp = arr[i];
                    arr[i] = arr[j]; 
                    arr[j] = temp;
                }
            }
        }
    }
}
