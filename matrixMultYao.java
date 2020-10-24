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

Sample Test Run:

Jordan Bui
011821368
10/23/2020
CS 3310 Midterm Project 3
Chained Matrix Multiplication: Yao(1982)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
-> Test
Array of Matrix Dimensions: 4 matrices
Dimension Values:
 10  20  30  40  30 

Matrix Dimensions:
 10 x 20   20 x 30   30 x 40   40 x 30  

# of array elements: 5 elements
Number of Matrices: 4 Matrices
Minimum Number of Multiplications is 30000
Time Elapsed in nanoseconds: 79359 ns
Time Elapsed in milliseconds: 0 ms
--------------------------------------------------
-> Matrix of Random Dimensions
Array of Matrix Dimensions: 10 matrices
Dimension Values:
 38  20  88  77  10  86  87  70   8  60  13 

Matrix Dimensions:
 38 x 20   20 x 88   88 x 77   77 x 10   10 x 86  
 86 x 87   87 x 70   70 x 8     8 x 60   60 x 13  

# of array elements: 11 elements
Number of Matrices: 10 Matrices
Minimum Number of Multiplications is 214224
Time Elapsed in nanoseconds: 23247 ns
Time Elapsed in milliseconds: 0 ms
--------------------------------------------------
-> Matrix of Random Dimensions
Array of Matrix Dimensions: 100 matrices
Dimension Values:
 55  72  16  41  83  55  13  29  24   5 
 19  15  46  43  79  80  95  39  84  39 
 72  95  45   3  82  72  51  25  39  16 
 80  37  15  23  86  76  58  29  82  44 
 24  93  68  32  64  23  27  62   4  92 
 70  80   9  37  42  79  90  48  57  51 
 19  98  41   3  72  14  89  84  85  31 
  4  78  37  86  66  95  19  67  69  25 
 62  95  31  43  92  67  11  84  86  51 
 90  23  38  74   6  55  80  61  17  47  45 

Matrix Dimensions:
 55 x 72   72 x 16   16 x 41   41 x 83   83 x 55  
 55 x 13   13 x 29   29 x 24   24 x 5     5 x 19  
 19 x 15   15 x 46   46 x 43   43 x 79   79 x 80  
 80 x 95   95 x 39   39 x 84   84 x 39   39 x 72  
 72 x 95   95 x 45   45 x 3     3 x 82   82 x 72  
 72 x 51   51 x 25   25 x 39   39 x 16   16 x 80  
 80 x 37   37 x 15   15 x 23   23 x 86   86 x 76  
 76 x 58   58 x 29   29 x 82   82 x 44   44 x 24  
 24 x 93   93 x 68   68 x 32   32 x 64   64 x 23  
 23 x 27   27 x 62   62 x 4     4 x 92   92 x 70  
 70 x 80   80 x 9     9 x 37   37 x 42   42 x 79  
 79 x 90   90 x 48   48 x 57   57 x 51   51 x 19  
 19 x 98   98 x 41   41 x 3     3 x 72   72 x 14  
 14 x 89   89 x 84   84 x 85   85 x 31   31 x 4   
  4 x 78   78 x 37   37 x 86   86 x 66   66 x 95  
 95 x 19   19 x 67   67 x 69   69 x 25   25 x 62  
 62 x 95   95 x 31   31 x 43   43 x 92   92 x 67  
 67 x 11   11 x 84   84 x 86   86 x 51   51 x 90  
 90 x 23   23 x 38   38 x 74   74 x 6     6 x 55  
 55 x 80   80 x 61   61 x 17   17 x 47   47 x 45  

# of array elements: 101 elements
Number of Matrices: 100 Matrices
Minimum Number of Multiplications is 1206923
Time Elapsed in nanoseconds: 630207 ns
Time Elapsed in milliseconds: 0 ms
--------------------------------------------------
-> Matrix of Random Dimensions
# of array elements: 1001 elements
Number of Matrices: 1000 Matrices
Minimum Number of Multiplications is 491411
Time Elapsed in nanoseconds: 14732259 ns
Time Elapsed in milliseconds: 14 ms
--------------------------------------------------

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
