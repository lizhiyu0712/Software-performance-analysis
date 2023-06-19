package package1;
import java.util.Random;
import java.util.Arrays;


public class Test {
	 public static int[] populateArray(int size) {
		 int[] arr = new int[size];
		 Random rand = new Random(); 
		 for (int i=0; i<size; i++) {
	            arr[i] = rand.nextInt(99998) + 1;
	     }
		 return arr;
	 }
	 public static void testPopulateArray(int[] sizes, int numLoop) {
		 Instrumentation.getInstance().startTiming("Test Populate Array");
		 Random rand = new Random(); 
		 for(int n:sizes) {
			 Instrumentation.getInstance().startTiming("Populate Array of size "+n+" x "+ numLoop+" times");
			 for(int t = 0; t < numLoop; t++ ) {
				 int[] arr = new int[n];
				 for (int i=0; i<n-1; i++) {
					 arr[i] = rand.nextInt(99998) + 1;
			     }
			 }
			 Instrumentation.getInstance().stopTiming("Populate Array of size "+n+" x "+ numLoop+" times");
		 }
		 
		 Instrumentation.getInstance().stopTiming("Test Populate Array");
	 }
	 
	 
	 public static void testOverHead(int numCalls) {
		 Instrumentation.getInstance().startTiming("Test Overhead");
		 for(int i = 0; i < numCalls-1; i++) {
			 Instrumentation.getInstance().startTiming("Overhead");
			 Instrumentation.getInstance().stopTiming("OverHead");
		 }
		 Instrumentation.getInstance().stopTiming("Test Overhead");
		 Instrumentation.getInstance().comment("Overhead call: "+(numCalls)+" times");
	 }
	 
	public static void testBubbleSortWithoutInstru(int[] sizes) {
		Instrumentation.getInstance().startTiming("Test Bubble Sort without instrumentation");
		BubbleSort bs = new BubbleSort();
		for(int n:sizes) {
			Instrumentation.getInstance().startTiming("Array Size = "+n);
			int[] arr = Test.populateArray(n);
			bs.sort(arr);
			Instrumentation.getInstance().stopTiming("Array Size = "+n);
		}
		Instrumentation.getInstance().stopTiming("Test Bubble Sort without instrumentation");
	}
	
	public static void testQuickSortWithoutInstru(int[] sizes) {
		Instrumentation.getInstance().startTiming("Test Quick Sort without instrumentation");
		QuickSort qs = new QuickSort();
		for(int n:sizes) {
			Instrumentation.getInstance().startTiming("Array Size = "+n);
			int[] arr = Test.populateArray(n);
			qs.sort(arr);
			Instrumentation.getInstance().stopTiming("Array Size = "+n);
		}
		Instrumentation.getInstance().stopTiming("Test Quick Sort without instrumentation");
	}
	
	public static void testBubbleSortWithArraySize(int size,int numLoop) {
		Instrumentation.getInstance().startTiming("Test Bubble Sort with array size"+size+" x"+numLoop+" times");
		BubbleSort bs = new BubbleSort();
		for(int i = 0; i < numLoop; i++) {
			int[] arr = Test.populateArray(size);
			bs.sort(arr);
		}
		Instrumentation.getInstance().stopTiming("Test Bubble Sort with array size"+size+" x"+numLoop+" times");
	}
	
	public static void testQuickSortWithArraySize(int size,int numLoop) {
		Instrumentation.getInstance().startTiming("Test Quick Sort with array size"+size+" x"+numLoop+" times");
		QuickSort qs = new QuickSort();
		for(int i = 0; i < numLoop; i++) {
			int[] arr = Test.populateArray(size);
			qs.sort(arr);
		}
		Instrumentation.getInstance().stopTiming("Test Quick Sort with array size"+size+" x"+numLoop+" times");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Program started ... will print 'Done!' when finished");
		Instrumentation ins = Instrumentation.getInstance();
		ins.activate(true);
		ins.startTiming("Main");
		int sizes[] = {10,50,100,500, 1000, 5000, 10000, 100000};
		
		//Test populate array of size n, time for 5000 iterations is recorded
		testPopulateArray(sizes, 5000);
		
		//Test overhead, time for 5000 calls is recorded
		testOverHead(5000);
		
		//Test Bubble Sort for different sizes without instrumentation
		testBubbleSortWithoutInstru(sizes);
		
		//Test Bubble Sort for different sizes without instrumentation
		testQuickSortWithoutInstru(sizes);
		
		//Test Bubble Sort array of size=20, numloop=10000
		testBubbleSortWithArraySize(20,10000);
		
		//Test Quick Sort array of size=20, numloop=10000
		testQuickSortWithArraySize(20,10000);
		
		
		BubbleSort bs = new BubbleSort();
		QuickSort qs = new QuickSort();
		
		// Test bubble sort and quicksort with instrumentation
		for(int n:sizes) {
			int[] arr1 = Test.populateArray(n);
			int[] arr2 = Arrays.copyOf(arr1,n);
			bs.instrumentedSort(arr1);
			qs.instrumentedSort(arr2);
		}
		
		
		ins.stopTiming("Main");
		ins.dump();
		ins.activate(false);
		System.out.println("Done!");
	}

}
