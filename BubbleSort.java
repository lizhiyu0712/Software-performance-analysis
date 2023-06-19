package package1;

import java.util.Arrays;

public class BubbleSort {
	public BubbleSort() {}
	
	public void sort(int[]arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = 0; j < arr.length-i-1; j++) {
				int temp = arr[j];
				if(arr[j] > arr[j+1]) {
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	public void instrumentedSort(int[]arr) {
		Instrumentation.getInstance().activate(true);
		Instrumentation.getInstance().startTiming("Bubble Sort");
		sort(arr);
		Instrumentation.getInstance().stopTiming("Bubble Sort");
		Instrumentation.getInstance().comment("Bubble Sort of size: "+ arr.length);
	}

}
