package package1;

import java.util.Arrays;

public class QuickSort {
	public QuickSort() {}
	
	public void sort(int arr[]) {
		quickSort(arr,0,arr.length-1);
	}
	public void instrumentedSort(int[]arr) {
		Instrumentation.getInstance().activate(true);
		Instrumentation.getInstance().startTiming("Quick Sort");
		quickSort(arr,0,arr.length-1);
		Instrumentation.getInstance().stopTiming("Quickuick Sort");
		Instrumentation.getInstance().comment("Quick Sort of size: "+ arr.length);
	}
	private void quickSort(int arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);
	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	private int partition(int arr[], int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);

	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;

	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }

	    int swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;

	    return i+1;
	}
}
