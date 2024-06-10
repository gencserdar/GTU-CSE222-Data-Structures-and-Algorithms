public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    private int partition(int start, int end){
        // Select the pivot element
        int pivot = arr[end];
        // Initialize the index i to keep track of elements less than the pivot
        int i = start - 1;
        // Iterate through the subarray from start to end
        for (int j = start; j < end; j++) {
            comparison_counter++; // Increment comparison counter
            // If the current element is less than pivot,then swap it with the element at index i+1 and increment i
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        // Swap the pivot element with the element at index i+1 to place it at its correct position
        swap(i + 1, end);
        // Return the index where the pivot element ends up after partitioning
        return i + 1;
    }

    // This method recursively sorts the subarray arr[start, ... , end].
    private void sort(int start, int end){
        if (start < end) {
            // Partition the subarray to get the pivot index
            int pivot_index = partition(start, end);
            // Sort elements before the pivot index
            sort(start, pivot_index - 1);
            // Sort elements after the pivot index
            sort(pivot_index + 1, end);
        }
    }


    @Override
    public void sort() {
        // Send full array into recursive sort function
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
