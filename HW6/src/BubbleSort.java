public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
        int len = arr.length;
        boolean is_swapped; // Flag to track if any swaps are made in a pass
        for (int i = 0; i < len - 1; i++) {
            is_swapped = false;
            // Last i elements are already in place, so we don't need to check them
            for (int j = 0; j < len - i - 1; j++) {
                comparison_counter++; // Increment comparison count
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1); // Swap if the elements are in the wrong order
                    is_swapped = true; // Set the flag to true if a swap is made
                }
            }
            // If no swaps are made in an iteration, this means that the array is already sorted. We can terminate the sorting process early
            if (!is_swapped) break;
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}