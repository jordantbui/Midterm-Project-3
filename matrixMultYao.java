/*
Jordan Bui
011821368
Fall 2020
10/23/2020
CS 3310
Midterm Project 3
Chained Matrix Multiplication: Yao(1982)
- Chained Matrix Multiplication using Yao Solution
- Array of matrix dimensions and the array size is passed into the function

Time Complexity:
	T(n) = O(n^2) 

 */
import java.util.*;
// Yao Matrix Multiplication Class
public class matrixMultYao
{
	/** Prints time elapsed in nanoseconds and milliseconds, converts nanoseconds to milliseconds
	   @param start Start time in nanoseconds
	   @param end End time in nanoseconds */
	private static void timeElapsed(long start, long end)
	{
		long tNano = end - start;
		long tMilli = tNano/1000000;
		System.out.println("Time Elapsed in nanoseconds: " + tNano + " ns");
		System.out.println("Time Elapsed in milliseconds: " + tMilli + " ms");
		System.out.println("--------------------------------------------------");
	}
	/** Prints integer array passed into the function and outputs each matrix dimension
	    @param arr[] Integer array to be printed */
	private static void printMArray(int arr[])
	{
		int len = arr.length - 1;
		System.out.println("Array of Matrix Dimensions: " + len + " matrices");
		// Print array of dimension values
		System.out.println("Dimension Values:");
		for (int i = 0; i < arr.length; i++)
		{
			if (i % 10 == 0 && i > 0 && i != len)
				System.out.println();
			System.out.printf("%3d" + " ", arr[i]);
		}
		System.out.println("\n");
		// Print list of dimensions
		System.out.println("Matrix Dimensions:");
		int j = 0;
		for (int i = 1; i < arr.length; i ++)
		{
			if (j % 5 == 0 && j > 0)
				System.out.println();
			System.out.printf("%3d" + " x " + "%-3d" + " ", arr[i - 1], arr[i]);
			j++;
		}
		System.out.println("\n");
		
	}
	/** Matrix Multiplication Yao Solution Function
	    @param  dim[] Array of the dimensions of each matrix
	    @param  n Size of the array (# of different matrix dimension values)
	    @return Returns an integer value of the number of multiplications */
	public static int mMultYao (int dim[], int n)
	{
	    int dp[][] = new int[n][n]; 
	    // Fill all same matrix elements with 0
	    for (int i = 1; i < n; i++) 
	        dp[i][i] = 0; 
	    // Simply following above recursive formula
	    for (int L = 1; L < n - 1; L++)  
	    	for (int i = 1; i < n - L; i++)      
	    		dp[i][i + L] = Math.min(dp[i + 1][i + L] + dim[i - 1] * dim[i] * dim[i + L], 
	                    				dp[i][i + L - 1] + dim[i - 1] * dim[i + L - 1] * dim[i + L]);      
	    return dp[1][n - 1];
	}
	
	/** Tests the program and demonstrates matrix multiplication using the Yao Solution
	    - Displays number of multiplications
	    - Displays run times in nanoseconds and milliseconds for various sizes
	    - Displays arrays of size 100 or less */
	public static void main(String[] args)
	{
		Random rand = new Random();
		// Prints name and title
		System.out.println("Jordan Bui\n011821368\n10/23/2020");
		System.out.println("CS 3310 Midterm Project 3\n"
				+ "Chained Matrix Multiplication: Yao(1982)\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// Initialize test array
		int arr[] = {10, 20, 30, 40, 30} ; 
	    int size = arr.length;
	    int numMatrix = arr.length - 1;
		// Runs Yao Solution for test array
	    System.out.println("-> Test");
	    printMArray(arr);
	    System.out.println("# of array elements: " + arr.length + " elements");
	    System.out.println("Number of Matrices: " + numMatrix + " Matrices");
		long tStart = System.nanoTime();
		System.out.print("Minimum Number of Multiplications is " + mMultYao(arr, size) + "\n");
		long tEnd = System.nanoTime();
		// Calculate time elapsed
		timeElapsed(tStart, tEnd);
		
		// Test Number of Matrices = 10, 100, 1000
		int loSize = 10;
		int hiSize = 1000;
		while (loSize <= hiSize)
		{
			size = loSize + 1;
			int randArr[] = new int[size];
			// Generate random array of matrix dimensions
			for (int i = 0; i < size; i++)
				randArr[i] = rand.nextInt(100);
			System.out.println("-> Matrix of Random Dimensions");
			if (loSize <= 100)
				printMArray(randArr);
		    System.out.println("# of array elements: " + randArr.length + " elements");
		    System.out.println("Number of Matrices: " + loSize + " Matrices");
			tStart = System.nanoTime();
			System.out.print("Minimum Number of Multiplications is " + mMultYao(randArr, loSize) + "\n");
			tEnd = System.nanoTime();
			timeElapsed(tStart, tEnd);
			// Change size of next array
			loSize *= 10;
		}
	}
}
