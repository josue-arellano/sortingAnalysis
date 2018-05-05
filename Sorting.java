/**
 *   Name:      Arellano, Josue
 *   File:      Sorting.java
 *   Project:   #1
 *   Due:       Apr 26, 2018
 *   Course:    cs331-s18
 *
 *   Description:
 *              This program implements and analyzes sorting algorithms and matrix
 *                  multiplication algorithms.
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
    
    //This method is used to split the array into two different arrays by selecting
    //  a pivot and placing the elements that are smaller than the pivot into 
    //  the left side of the array and the larger balues at the right.
    public static int partition(int low, int high, int arr[])
    {
        //Index that begins at the low which is passed in as an argument for each
        //  partition.
        int i = low;
        
        //Value that will be compared and that will split the array.
        int pivot = arr[low];
        
        //For loop to visit all the values to figure out which side of the pivot
        //  they belong.
        for(int j = low + 1; j < high; j++)
        {
            //Compare the value to the pivot.
            if(arr[j] <= pivot)
            {
                //Increment i to access the next larger than pivot value.
                i++;
                
                //Swap the value at j with the value at i.
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //Place the pivot in between the two partitions of the array by swapping
        //  it with the last value that is smaller than the pivot.
        int temp = arr[i];
        arr[i] = arr[low];
        arr[low] = temp;
        
        //Return the value of the pivot.
        return i + 1;
    }
    
    //This method implents the quicksort algorithm.
    public static void quickSort(int low, int high, int[] arr)
    {
        //Only perfom actions if the array is larger than 1.
        if(low < high)
        {
            //find the pivot and rearrange the array by calling the partition method.
            int pivot = partition(low, high, arr);
            
            //Recursively quicksort the left and right portions of the array.
            quickSort(low, pivot - 1, arr);
            quickSort(pivot + 1, high, arr);
        }
    }
    
    //This method implements the exchange sort algorithm.
    public static void exchangeSort(int[] arr)
    {
        //Loop through all the values in the array.
        for(int i = 0; i < arr.length; i++)
        {
            //Loop through all the values that follow the current index to find the
            //  following smallest value.
            for(int j = i + 1; j < arr.length; j++)
            {
                //If a value is smaller than the current value at i then swap
                //  with that value.
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
